<%@page import="java.util.List"%>
<%@page import="dto.board2.Board2DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		*{padding: 0;margin: 0}
		h1{
			width: 800px;
			margin: 40px auto;
		}
		ul{
			width: 800px;
			margin: 0 auto;
			display: grid;
			grid-template-columns: 100px 300px 100px auto auto;
			border-bottom: 1px solid #222; 
		}
		li{
			list-style: none;
			padding: 0.5em 1em;
		}
	</style>
</head>
<body>
	<h1>게시글</h1>
	<a href="write">글쓰기</a>
	<ul>
		<li>글번호</li>
		<li>제목</li>
		<li>조회수</li>
		<li>작성자</li>
		<li>작성일</li>
	</ul>
	
	<c:forEach var="dto" items="${list }">
	<ul>
		<li>${dto.bno }</li>
		<li>
			<a href="detail?bno=${dto.bno }">${dto.subject }</a>
		</li>
		<li>${dto.readCount }</li>
		<li>${dto.creater }</li>
		<li>${dto.updatedDate }</li>
	</ul>
	</c:forEach>
	
</body>
</html>