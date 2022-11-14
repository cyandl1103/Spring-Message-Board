<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR" name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${path}/resources/js/board.js" charset="utf-8"></script>
<title>�Խ��� �� ����Ʈ</title>

<!--  bootstrap css -->
<link rel="stylesheet" 
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
	
<link rel="stylesheet" 
	href="${pageContext.request.contextPath}/resources/css/custom.css">
	<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Black+And+White+Picture&family=Black+Han+Sans&family=Cute+Font&family=Do+Hyeon&family=Dokdo&family=East+Sea+Dokdo&family=Gaegu&family=Gamja+Flower&family=Gothic+A1&family=Gugi&family=Hi+Melody&family=Jua&family=Kirang+Haerang&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Gothic+Coding&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Noto+Serif+KR&family=Poor+Story&family=Single+Day&family=Song+Myung&family=Stylish&family=Sunflower:wght@300&family=Yeon+Sung&display=swap" rel="stylesheet">
</head>
<body>
	<div class="board-header">
		<nav class="navbar navbar-dark bg-dark">
			<div class="body-title">
				<img src="${path}/resources/images/logo.png" class="board-logo" alt="Logo Image">
				<a class="navbar-brand" href="/board/list">�Խ��� �� ����Ʈ</a>
			</div>
		</nav>
	</div>
	<div class="body-container">
		<div class="frm-title">�����ϱ�</div>
		
		<form id="frm" method="post">
		<input type="hidden" id="seq" name="seq" value="${view.seq}">
		<input type="hidden" id="name" name="name" value="${view.name}">
		<table class="table">
			<tr>
				<th scope="row">����</th>
				<td><input class="form-control mr-sm-2" type="text" placeholder="����" id="subject" name="subject" value="${view.subject}"></td>
			</tr>
			<tr>
				<th scope="row">�ۼ���</th> 
				<td>${view.name}</td>
			</tr>
			<tr>
				<th scope="row">����</th>
				<td><textarea class="form-control mr-sm-2" placeholder="����" id="content" name="content" maxlength="1000" rows="8">${view.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" style="padding-top: 20px;">
					<button  class="btn btn-primary" type="button" onclick="fn_boardUpdate();" style="float: right;"> ���� </button>
					<button class="btn btn-primary" type="button" onclick='location.href="javascript:history.back();"'>���</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>