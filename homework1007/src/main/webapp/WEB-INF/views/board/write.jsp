<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		section{
			width: 400px;
			margin: 0 auto;
		}
	</style>
</head>
<body>
	<section>
		<h1>글쓰기</h1>
		<div>
			<a href="${root }">HOME</a>
			<form action="proc" method="post">
				<p>
					<input type="text" name="creater" placeholder="작성자">
				</p>
				<p>
					<input type="text" name="subject" placeholder="제목">
				</p>
				<p>
					<textarea rows="10" cols="50"  name="content" placeholder="내용을입력하세요"></textarea>
				</p>
				<p>
					<button type="submit">등록</button>
				</p>
			</form>
		</div>
	</section>
</body>
</html>