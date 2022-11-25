<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
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
					<a class="navbar-brand" href="/board/list">게시판 웹 사이트</a>
				</div>
				<div class="navbar-brand user">
					<a class="navbar-brand" href="#" onclick="fn_userMenu();">${userName}</a>
				</div>
			</nav>
		</div>
		
		<div class="body-container">
			<div class="frm-title">글쓰기</div>
			
			<form:form id="regForm" method="post" enctype="multipart/form-data" modelAttribute="uploadFile" action="upload" accept-charset="UTF-8">
				<table class="table">
					<tr>
						<th scope="row">제목</th>
						<td><input class="form-control mr-sm-2" type="text" placeholder="" id="subject" name="subject" maxlength="200"></td>
					</tr>
					<tr>
						<th scope="row">작성자</th>
						<c:choose>
							<c:when test="${userName eq null}">
								<td><input class="form-control mr-sm-2" type="text" placeholder="" id="name" name="name" maxlength="20"></td>
							</c:when>
							<c:when test="${userName ne null}">
								<td>${userName}</td>
							</c:when>
						</c:choose>
					</tr>
					<tr>
						<th scope="row">내용</th>
						<td><textarea class="form-control mr-sm-2" type="text" placeholder="" id="content" name="content" maxlength="1000" rows="8"></textarea></td>
					</tr>
					<tr>
						<th scope="row">파일</th>
						<td><input class="form-control mr-sm-2" type="file" id="file" name="file"></td>
					</tr>
					<tr>
						<td colspan="2" style="padding-top: 20px;">
							<button class="btn btn-primary" type="button" onclick='location.href="/board/list"'>취소</button>
							<button class="btn btn-primary" type="button" onclick="fn_boardRegister();"  style="float: right;"> 등록 </button>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
		
		<div class="board-footer bg-dark">
			<div class="footer-text">
				Made with Spring Framework
			</div>
		</div>
	</div>
</body>
</html>