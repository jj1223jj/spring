<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="${root}/resources/css/board/style.css">
		<script type="text/javascript" src="${root}/resources/javascript/board/write.js"></script>
	</head>
	<body>
		<h3>
			boardNumber : ${boardNumber },
			groupNumber : ${groupNumber },
			sequenceNumber : ${sequenceNumber },
			sequenceLevel : ${sequenceLevel }
		</h3>
	
		<div class="contents">
		
			<div class="title">
				<p class="title_main">글쓰기</p>
				<a class="list" href="${root}/board/list.do">글목록</a>
			</div>
			
			<form  action="${root}/board/writeOk.do"
					method="post" onsubmit="return boardForm(this)">
				<input type="hidden" name="boardNumber" value="${boardNumber}"/>
				<input type="hidden" name="groupNumber" value="${groupNumber}"/>
				<input type="hidden" name="sequenceNumber" value="${sequenceNumber}"/>
				<input type="hidden" name="sequenceLevel" value="${sequenceLevel}"/>
				
				<ul class="write_box">
					<li>
						<p>작성자</p>
						<p>
							<input type="text" name="writer">
						</p>
					</li>
					<li>
						<p>제목</p>
						<p>
							<input type="text" name="subject">
						</p>
					</li>
					<li>
						<p>이메일</p>
						<p>
							<input type="text" name="email">
						</p>
					</li>
					<li class="long">
						<p>내용</p>
						<p>
							<textarea rows="10" cols="10" name="content"></textarea>
						</p>
					</li>
					<li>
						<p>비밀번호</p>
						<p>
							<input type="password" name="password">
						</p>
					</li>
					<li>
						<p class="col1">
							<input class="btn" type="submit" value="글쓰기">
							<input class="btn" type="reset" value="다시작성">
							<input class="btn" type="button" value="목록보기" 
								onclick="location.href='${root}/board/list.do'">
						</p>
					</li>
				</ul>
			</form>
		</div>
	</body>
</html>