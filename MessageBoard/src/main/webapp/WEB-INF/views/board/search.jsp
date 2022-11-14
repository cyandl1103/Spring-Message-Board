<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${path}/resources/js/board.js" charset="utf-8"></script>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
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
	<div class="board-header">
		<nav class="navbar navbar-dark bg-dark">
			<div class="body-title">
				<img src="${path}/resources/images/logo.png" class="board-logo" alt="Logo Image">
				<a class="navbar-brand" href="/board/list">게시판 웹 사이트</a>
			</div>
			<form class="form-inline" id="searchForm" method="get" action="/board/search">
					<input class="form-control mr-sm-2" type="text" id="subject" name="subject" placeholder="검색어를 입력하세요" required>
					<button class="btn btn-primary" type="submit">검색</button>
			</form>
		</nav>
	</div>
	
	<div class="body-container">
	<div class="frm-title">
		" ${param.subject} "  에 대한 검색 결과
	</div>
		<form id="frm">
			<input type="hidden" value="" name="seq" id="seq">
			<table class="table">
				<thead class="table table-hover">
					<tr align="center">
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">내용</th>
						<th scope="col">작성자</th>
						<th scope="col">날짜</th>
						<th scope="col">조회수</th>
					</tr>	
				</thead>
				<tbody>
					<c:forEach items="${search}" var="search">
						<tr align="center">
							<th scope="row"> ${search.seq} </th>
							<td class="body-content"> <a href="#" onclick="fn_goView(${search.seq})"> ${search.subject} </a> </td>
							<td class="body-content"> ${search.content} </td>
							<td class="body-content"> ${search.name} </td>
							<fmt:parseDate value="${search.reg_date}" var="dateValue" pattern="yyyyMMddHHmmss"/>
							<td> <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd"/> </td>
							<td> ${search.readCount} </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="frm-button">
				<button class="btn btn-primary" type="button" onclick="location.href='/board/list'">목록</button>
			</div>
		</form>
	</div>
</body>
</html>