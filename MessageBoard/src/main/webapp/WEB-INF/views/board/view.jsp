<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${path}/resources/js/board.js" charset="utf-8"></script>
<title>�Խ��� �� ����Ʈ</title>
</head>
<body>
	<form id = "frm" method="post">
		<input type="hidden" value="${view.seq}" name="seq" id="seq">
		<div>���� : ${view.subject}</div>
		<div>�ۼ��� : ${view.name}</div>
		<div>���� : ${view.content}</div>
	<button type="button" onclick='location.href="/board/list"'>���</button>
	<button type="button" onclick='fn_goUpdateView(${view.seq})'>����</button>
	<button type="button" onclick='fn_boardDelete(${view.seq})'>����</button>
	</form>
</body>
</html>