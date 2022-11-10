<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${path}/resources/js/board.js" charset="utf-8"></script>
<title>게시판 웹 사이트</title>
</head>
<body>
	<form id="regForm" method="post">
		<div>
			제목 : <input type="text" placeholder="제목" id="subject" name="subject">
		</div>
		<div>
			작성자 : <input type="text" placeholder="작성자" id="name" name="name">
		</div>
		<div>
			내용 : <input type="text" placeholder="내용" id="content" name="content">
		</div>
		<div>
			<button type="button" onclick="fn_boardRegister();"> 등록 </button>
		</div>
	</form>
</body>
</html>