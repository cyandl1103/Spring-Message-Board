<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${path}/resources/js/board.js" charset="utf-8"></script>
<script src="${path}/resources/js/practice.js" charset="utf-8"></script>
<title>게시판 웹 사이트</title>

<!--  bootstrap css -->
<link rel="stylesheet" 
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
	
<link rel="stylesheet" 
	href="${pageContext.request.contextPath}/resources/css/custom.css">
</head>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Black+And+White+Picture&family=Black+Han+Sans&family=Cute+Font&family=Do+Hyeon&family=Dokdo&family=East+Sea+Dokdo&family=Gaegu&family=Gamja+Flower&family=Gothic+A1&family=Gugi&family=Hi+Melody&family=Jua&family=Kirang+Haerang&family=Nanum+Brush+Script&family=Nanum+Gothic&family=Nanum+Gothic+Coding&family=Nanum+Myeongjo&family=Nanum+Pen+Script&family=Noto+Sans+KR&family=Noto+Serif+KR&family=Poor+Story&family=Single+Day&family=Song+Myung&family=Stylish&family=Sunflower:wght@300&family=Yeon+Sung&display=swap" rel="stylesheet">

<body>
	<div id="wrap">
		<div class="home-header">
			<nav class="navbar navbar-dark bg-dark">
				<div class="home-title">
					<img src="resources/images/logo.png" class="home-logo" alt="Logo Image">
					<a>게시판 웹 사이트</a>
				</div>
			</nav>
		</div>
	
		<div class="body-container">
			<div class="home-content">
<%-- 				<P id="timeNow">  현재 시각 : ${serverTime}. </P> --%>
				<div class="home-login">

					<form action="/user/loginCheck" id="loginFrm" method="post">
						<table class="table">
							<tbody class="table-no-border">
								<tr>
									<th scope="row">아이디</th>
									<td><input id="id" name="id" type="text" placeholder="아이디" class="form-control mr-sm-2" required></td>
								</tr>
								<tr>
									<th scope="row">비밀번호</th>
									<td><input id="pw" name="pw" type="password" placeholder="비밀번호" class="form-control mr-sm-2" required></td>
								</tr>
								<tr>
									<td colspan="2"><button type="submit" class="btn btn-primary" style="width : 100%"> 로그인 </button></td>
								</tr>
								
								<tr>
									<td colspan="2" style="text-align: center"><a href="user/registerView"> 회원가입 </a></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				
			</div>
			<div class="home-button">
				<button class="btn btn-primary" type="button" onclick='location.href="/board/list"'>비회원으로 계속하기</button>
				<button class="btn btn-primary" type="button" onclick='practice();'>JS 연습</button>
			</div>
		</div>
	
		<div class="board-footer bg-dark">
			<div class="footer-text">
				Made with Spring Framework
			</div>
		</div>
	</div>
</body>
</html>
