<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${path}/resources/js/board.js" charset="utf-8"></script>
<head>
<meta charset="UTF-8">
<title>게시판 웹 사이트</title>
</head>
<body>
	<h3>" ${param.subject} "  에 대한 검색 결과 </h3>
	<form id="frm">
		<input type="hidden" value="" name="seq" id="seq">
		<table border="0">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>	
			</thead>
			<tbody>
				<c:forEach items="${search}" var="search">
					<tr align="center">
						<td> ${search.seq} </td>
						<td> <a href="#" onclick="fn_goView(${search.seq})"> ${search.subject} </a> </td>
						<td> ${search.content} </td>
						<td> ${search.name} </td>
						<fmt:parseDate value="${search.reg_date}" var="dateValue" pattern="yyyyMMddHHmmss"/>
						<td> <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd"/> </td>
						<td> ${search.readCount} </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button type="button" onclick="location.href='/board/list'">목록</button>
	</form>
</body>
</html>