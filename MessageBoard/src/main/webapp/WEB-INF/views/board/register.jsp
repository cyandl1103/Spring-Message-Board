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
	<form id="regForm" method="post">
		<div>
			���� : <input type="text" placeholder="����" id="subject" name="subject">
		</div>
		<div>
			�ۼ��� : <input type="text" placeholder="�ۼ���" id="name" name="name">
		</div>
		<div>
			���� : <input type="text" placeholder="����" id="content" name="content">
		</div>
		<div>
			<button type="button" onclick="fn_boardRegister();"> ��� </button>
		</div>
	</form>
</body>
</html>