-- member(회원) table 생성
create table member(
	num int primary key auto_increment,
	id varchar(20) not null,
	pass varchar(20) not null,
	name varchar(30) not null,
	age int not null,
	email varchar(30) not null,
	phone varchar(30) not null,
	unique key(id)
);

-- 검색
select * from member;

-- 저장
insert into member(id, pass, name, age, email, phone)
values('admin', 'admin', '관리자', '40',
'admin@naver.com', '010-1111-1111');

-- 수정
update member
set age=45, phone='010-1111-0000'
where id='admin';

-- 삭제
delete from member
where id='admin';

drop table member;