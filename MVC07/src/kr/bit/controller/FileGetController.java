package kr.bit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileGetController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String filename=request.getParameter("filename");
		System.out.println(filename);
		
		String UPLOAD_DIR = "file_repo";
		String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		File f = new File(uploadPath+"\\"+filename);
		
		// 클라이언트로부터 넘어오는 파일이름에 한글이 있는 경우 깨지지 않게 하기위함
		filename=URLEncoder.encode(filename, "UTF-8");
		// 파일 이름 사이에 +를 공백으로 바꿔주기
		filename = filename.replace("+", " ");
		// 다운로드 받을 준비 (서버에서 클라이언트에게 다운로드 준비를 시킴 -> 다운로드 창을 띄운다)
		response.setContentLength((int)f.length()); // 파일 길이
		response.setContentType("application/x-msdownload;charset=utf-8"); // 컨텐츠 타입
		response.setHeader("Content-Disposition", "attachment;filename="+filename+";"); // 다운받을 때 화면
		response.setHeader("Content-Transfer-Encoding", "binary"); // 파일은 binary 로 변환되어 전송됨 
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		
		// 실제 다운로드를 하는 부분
		FileInputStream in = new FileInputStream(f); // 파일 읽기 준비
		// 파일 출력
		OutputStream out = response.getOutputStream();
		byte[] buffer = new byte[1024];
		while(true) {
			int count = in.read(buffer); // buffer만큼 파일 읽어오기
			if (count == -1) { // 파일을 다 읽었을 때
				break;
			}
			out.write(buffer, 0, count); // buffer에 있는 데이터를 읽어들임 (다운로드 퍼센트)
		}
		in.close();
		out.close();
		
		return null;
	}

}
