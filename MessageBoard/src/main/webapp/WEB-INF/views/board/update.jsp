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
	<form id="frm" method="post">
	<input type="hidden" id="seq" name="seq" value="${view.seq}">
	<input type="hidden" id="name" name="name" value="${view.name}">
	<div>
		���� : <input type="text" placeholder="����" id="subject" name="subject" value="${view.subject}">
	</div>
	<div>
		�ۼ��� : ${view.name}
	</div>
	<div>
		���� : <input type="text" placeholder="����" id="content" name="content" value="${view.content}">
	</div>
	<div>
		<button type="button" onclick="fn_boardUpdate();"> ���� </button>
	</div>
	</form>
</body>
</html>