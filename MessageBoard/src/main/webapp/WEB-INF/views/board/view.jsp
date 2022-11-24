<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
					<a class="navbar-brand" href="/board/list">게시판 웹 사이트</a>
				</div>
				
				<div class="navbar-brand user">
					<a class="navbar-brand" href="#" onclick="fn_userMenu();">${userName}</a>
				</div>
			</nav>
		</div>
		
		<div class="body-container">
			<div class="body-board">
				<div class="frm-title">${view.subject}</div>
				<form id = "frm" method="post">
					<input type="hidden" value="${view.seq}" name="seq" id="seq">
					<table class="table">
						<tr>
							<th scope="row">작성자</th>
							<td> ${view.name}</td>
						</tr>
						<tr>
							<th scope="row" width="90px" >내용</th>
							<td style="word-break:break-all">${view.content}</td>
						</tr>
						<tr>
							<th scope="row">파일</th>
							<c:choose>
								<c:when test="${file_name ne null}">
									<td> <a href="#" onclick="fn_downloadFile('${view.file}')"> ${file_name} </a> </td>
								</c:when>
								<c:when test="${file_name eq null}"><td></td></c:when>
							</c:choose>
						</tr>
						<tr>
							<td colspan="3" style="padding-top: 20px;">
								<button class="btn btn-primary" type="button" onclick='location.href="/board/list"'>목록</button>
								<button class="btn btn-primary" type="button" onclick='fn_goUpdateView(${view.seq})'>수정</button>
								<button class="btn btn-primary" type="button" onclick='fn_boardDelete(${view.seq})'>삭제</button>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<div class="body-regReply">
				<form id="replyFrm" method="post">
					<table class="table">
						<tr><th scope="row" class="reply-title">댓글 작성</th></tr>
						<tr>
							<c:choose>
								<c:when test="${userName eq null}">
									<td><input class="form-control mr-sm-2" type="text" placeholder="이름" id="name" name="name" maxlength="20"></td>
								</c:when>
								<c:when test="${userName ne null}">
									<td><input class="form-control mr-sm-2" type="text" placeholder="${userName}" id="name" name="name" maxlength="20" value="${userName}" readonly></td>
								</c:when>
							</c:choose>
						</tr>
						<tr>
							<td style="border: none; padding-top:0.1rem"><textarea class="form-control mr-sm-2" type="text" placeholder="내용을 입력해주세요." id="content" name="content" maxlength="100" rows="3"></textarea></td>
						</tr>
						<tr>
							<td colspan="3" style="border: none;">
								<button class="btn btn-primary" type="button" onclick="fn_replyRegister();" style="float: right;">등록</button>
							</td>
						</tr>
					</table>
					<input type="hidden" name="bseq" id="bseq" value="${view.seq}"/>
				</form>
			</div>
			
			<div class="body-reply">

				<table class="table" id="listTable">
					<thead class="table table-hover">
						<tr><th scope="row" class="reply-title" colspan="4">댓글</th></tr>
					</thead>
					<c:forEach items="${list}" var="list">
						<input type="hidden" value="${list.rseq}" name="rseq" id="rseq">
						<tbody>
							<tr align="left">
								<td style="font-weight: bold;">
									<c:forEach begin="1" end="${list.re_level}" > &nbsp&nbsp&nbsp&nbsp </c:forEach>
									${list.name}
								
								</td>
								<fmt:parseDate value="${list.reg_date}" var="dateValue" pattern="yyyyMMddHHmmss"/>
								<td style="color:#999999;" align="right"> <fmt:formatDate value="${dateValue}" pattern="yyyy-MM-dd"/></td>
								<td style="width: 130px;">
								<c:choose>
									<c:when test="${userName eq null}">
										<button class="btn btn-primary little" type="button" onclick='fn_replyReplyView(${list.rseq}, "");' id="replyReplyView">답글</button>
									</c:when>
									<c:when test="${userName ne null}">
										<button class="btn btn-primary little" type="button" onclick='fn_replyReplyView(${list.rseq}, "${userName}");' id="replyReplyView">답글</button>
									</c:when>
								</c:choose>
									
									<button class="btn btn-primary little" type="button" onclick='fn_replyDelete(${list.rseq});' >삭제</button>
								</td>
							</tr>
							<tr align="left">
								<td style="word-break:break-all; height:100px; border: none; vertical-align: top;" colspan="4" >
								 	<c:forEach begin="1" end="${list.re_level}" > &nbsp&nbsp&nbsp&nbsp </c:forEach>
								 	${list.content} 
								</td>
							</tr>
						</tbody>

						<tbody id="addReply_${list.rseq}"></tbody>

					</c:forEach>
			
				</table>
			
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