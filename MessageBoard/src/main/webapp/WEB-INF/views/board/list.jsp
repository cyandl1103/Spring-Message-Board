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
				<c:forEach items="${list}" var="list">
					<tr align="center">
						<td> ${list.seq} </td>
						<td> <a href="#" onclick="fn_goView(${list.seq})"> ${list.subject} </a> </td>
						<td> ${list.content} </td>
						<td> ${list.name} </td>
						<fmt:parseDate value="${list.reg_date}" var="dateValue" pattern="yyyyMMddHHmmss"/>
						<td> <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd"/> </td>
						<td> ${list.readCount} </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button type="button" onclick="location.href='registerView'">글 쓰기</button>
	</form>
</body>
</html>