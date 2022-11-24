<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR" name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${path}/resources/js/board.js" charset="utf-8"></script>
<title>게시판 웹 사이트</title>

<!--  bootstrap css -->
<link rel="stylesheet" 
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
	
<link rel="stylesheet" 
	href="${pageContext.request.contextPath}/resources/css/custom.css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Black+And+White+Picture&family=Black+Han+Sans&family=Cute+Font&family=Do+Hyeon&family=Dokdo&family=East+Sea+Dokdo&family=Gaegu&family=Gamja+Flower&family=Gothic+A1&family=Gugi&family=Hi+Melody&family=Jua&family=Kirang+Haerang&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Gothic+Coding&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Noto+Serif+KR&family=Poor+Story&family=Single+Day&family=Song+Myung&family=Stylish&family=Sunflower:wght@300&family=Yeon+Sung&display=swap" rel="stylesheet">	
</head>
<body>
	<div id="wrap">
		<div class="board-header">
			<nav class="navbar navbar-dark bg-dark">
				<div class="body-title">
					<img src="${path}/resources/images/logo.png" class="board-logo" alt="Logo Image">
					<a class="navbar-brand" href="/">게시판 웹 사이트</a>
				</div>

			</nav>
		</div>
		
		<div class="body-container">
			<div class="frm-title">회원가입</div>
			
			<form id="regForm" method="post" action="userRegister">
				<table class="table">
					<tbody class="table-border">
						<tr>
							<th scope="row">아이디</th>
							<td><input class="form-control mr-sm-2" type="text" placeholder="아이디" id="userId" name="userId" maxlength="20"></td>
						</tr>
						<tr>
							<th scope="row">비밀번호</th>
							<td><input class="form-control mr-sm-2" type="password" placeholder="비밀번호" id="userPw" name="userPw" maxlength="20"></td>
						</tr>
						<tr>
							<th scope="row">이름</th>
							<td><input class="form-control mr-sm-2" type="text" placeholder="이름" id="userName" name="userName" maxlength="20"></td>
						</tr>
	
						<tr>
							<td colspan="2" style="padding-top: 20px;">
								<button class="btn btn-primary" type="button" onclick="fn_userRegister();"> 가입하기 </button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		
		<div class="board-footer bg-dark">
			<div class="footer-text">
				Made with Spring Framework
			</div>
		</div>
	</div>
</body>
</html>