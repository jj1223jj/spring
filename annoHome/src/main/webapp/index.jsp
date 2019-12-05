<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${root}/fileBoard/write.do">파일 글쓰기</a>
	<a href="${root}/fileBoard/list.do">파일 글목록</a>
	<br/><br/>
	<a href="${root}/guest/write.do">방명록 작성</a>
	<br/><br/>
	<a href="${root}/board/write.do">글쓰기</a>
	<a href="${root}/board/list.do">글목록</a>
	<a href="${root}/board/list.do">말자</a>
</body>
</html>