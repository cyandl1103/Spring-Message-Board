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
	<form id="frm" method="post">
	<input type="hidden" id="seq" name="seq" value="${view.seq}">
	<input type="hidden" id="name" name="name" value="${view.name}">
	<div>
		제목 : <input type="text" placeholder="제목" id="subject" name="subject" value="${view.subject}">
	</div>
	<div>
		작성자 : ${view.name}
	</div>
	<div>
		내용 : <input type="text" placeholder="내용" id="content" name="content" value="${view.content}">
	</div>
	<div>
		<button type="button" onclick="fn_boardUpdate();"> 수정 </button>
	</div>
	</form>
</body>
</html>