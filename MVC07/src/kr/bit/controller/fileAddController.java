package kr.bit.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class fileAddController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String UPLOAD_DIR = "file_repo"; 
		String uploadPath = request.getServletContext().getRealPath("")+File.separator+UPLOAD_DIR;
		File currentDirPath = new File(uploadPath); // 업로드할 경로를 File 객체로 만들기
		// dir 없으면 생성
		if (!currentDirPath.exists())
			currentDirPath.mkdir();
		
		// 파일을 업로드 할 때 임시 저장될 임시 저장경로를 설정
		// file upload시 필요한 API
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 파일을 실제로 저장할 장소
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		
		String fileName = null;
		
		// request 이용해서 업로드 도와주는 객체
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// request 안의 파일 정보를 쉽게 읽어올 수 있도록 변환
			// items -> [FileItem], [], []..
			List<FileItem> items = upload.parseRequest(request); // request 안에 여러개의 파일이 업로드 된 경우
			
			// FileItem들의 정보 가져옴
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = items.get(i);
				// 파일인지 파라미터(파일 외 form 정보들)인지 구분
				if (fileItem.isFormField()) // 폼필드이면
					System.out.println(fileItem.getFieldName()+"="+fileItem.getString("utf-8"));
				else { // 파일이면
					if(fileItem.getSize()>0) { // 임시저장소에 파일이 있음
						// 파일 경로 중 파일이름 추출
						int idx = fileItem.getName().lastIndexOf("\\"); // window : \\, linux : /
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						fileName = fileItem.getName().substring(idx+1); // 파일이름
						
						// 파일 생성
						File uploadFile = new File(currentDirPath+"\\"+fileName);
						// 파일 중복 체크
						if (uploadFile.exists()) { // 시분초 + 파일이름으로 다시 저장
							fileName = System.currentTimeMillis()+"_"+fileName;
							uploadFile = new File(currentDirPath+"\\"+fileName);
						}
						
						// 임시 파일을 새로운 (실제)파일 경로에 쓰기
						fileItem.write(uploadFile); // 임시경로 ~> 새로운 경로에 파일 쓰기
					}
				}
					
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// $.ajax() 쪽으로 업로드 된 최종 파일 이름을 전송 -> DB에 저장하기 위해
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(fileName);
		
		return null;
	}

}
