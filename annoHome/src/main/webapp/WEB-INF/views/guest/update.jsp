<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center" style="margin: 100px auto">
		<form class="form" action="${root}/guest/updateOk.do" method="post">
			<input type="hidden" name="num" value="${guestDto.num}" /> 
			<input type="hidden" name="pageNumber" value="${pageNumber}" />

			<div class="title">
				<span class="input_wrap"> 
					<label>이름</label> 
					<input type="text" name="name" size="12" value="${guestDto.name}" disabled="disabled" />
				</span> 
				<span class="input_wrap"> 
					<label>비밀번호</label> 
					<input type="password" name="password" size="12" value="${guestDto.password} ">
				</span>
			</div>
			<div class="content">
				<textarea rows="5" cols="50" name="message">${guestDto.message}</textarea>
			</div>
			<div class="input_button" style="text-align: right;">
				<input type="submit" value="확인"> 
				<input type="reset" value="취소">
			</div>
		</form>
	</div>
</body>
</html>