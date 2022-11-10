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
	<form id = "frm" method="post">
		<input type="hidden" value="${view.seq}" name="seq" id="seq">
		<div>제목 : ${view.subject}</div>
		<div>작성자 : ${view.name}</div>
		<div>내용 : ${view.content}</div>
	<button type="button" onclick='location.href="/board/list"'>목록</button>
	<button type="button" onclick='fn_goUpdateView(${view.seq})'>수정</button>
	<button type="button" onclick='fn_boardDelete(${view.seq})'>삭제</button>
	</form>
</body>
</html>