<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<meta charset="UTF-8"> 
		<title>방명록</title>
		<link rel="stylesheet" href="${root}/resources/css/guest/write.css">
		<script type="text/javascript" src="${root}/resources/javascript/guest/guest.js"></script>
	</head>
	<body>
		<jsp:include page="../../../index.jsp"/>
		<h3>
			currentPage: ${currentPage}, 
			boardSize: ${boardSize},
			count: ${count }</h3>
		<div class="wrap">
			<c:if test="${count==0 || currentPage==1 }">
				<form action="${root}/guest/writeOk.do" method="post" style="height:200px;">
					<div>
						<span class="input_wrap">
							<label>이름</label>
							<input type="text" name="name" size="12">
						</span>
						<span class="input_wrap">
							<label>비밀번호</label>
							<input type="password" name="password" size="12">
						</span>
					</div>
					<div class ="content">
						<textarea rows="5" cols="50" name="message"></textarea>
					</div>
					<div class="input_button">
						<input type="submit" value="확인">
						<input type="reset" value="취소">
					</div>
				</form>
			</c:if>
			
			<c:if test="${count>0 }">
				<c:forEach var="guestDto" items="${guestList }">
				
					<div align ="center" style="border: 1px solid #666; margin-bottom: 10px;
										padding: 10px; height: 150px;">
					<div class="input_wrap" >
						${guestDto.num} &nbsp;&nbsp;
						<fmt:formatDate value="${guestDto.writeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
						<br/><br/>
						<a href="javascript:updateCheck('${root}','${guestDto.num}','${currentPage}')">수정</a> &nbsp;
						<a href="javascript:deleteCheck('${root}','${guestDto.num}','${currentPage}')">삭제</a>
					</div >
					<div class ="content">
						<p>내용: ${guestDto.message}</p>
					</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	
	<%--
		페이지 번호 
		1. 총 페이지 수 : 전체 레코드수(count)와 페이지당 게시물 수(boardSize)
		2. 페이지 블럭 :	시작번호, 끝번호  10 [1]..[10]
		3.
	 --%>
		<div align="center">
			<c:if test="${count>0 }">
				<%-- 1. 총 페이지 수 : count/boardSize --%>
				<fmt:parseNumber var="pageCount" integerOnly="true"
				value="${count/boardSize+(count%boardSize==0 ? 0:1) }" />
				
				<%-- 2. 페이지 블럭 --%>
				<c:set var="pageBlock" value="${3}" />
				
				<%-- 2. 페이지 블럭 / 시작, 끝번호 
						int startPage = (int)((currentPage-1)/pageBlock)*pageBlock+1;
						int endPage = startPage+pageBlock -1; --%>
						
				<fmt:parseNumber var="rs" value="${ (currentPage-1)/pageBlock }" integerOnly="true"/>
				<c:set var="startPage" value="${rs*pageBlock+1 }"/>
				<c:set var="endPage" value="${startPage+pageBlock-1 }"/>
				
				<%-- 3. 총 레코드 수:270/10=27  요청페이지:25 (21,30)  --%>
				<c:if test="${ endPage>pageCount }">
					<c:set var="endPage" value="${pageCount}" />
				</c:if>
				
				<%-- 이전 --%>
				<c:if test="${ startPage > pageBlock }">
					<a href="${root}/guest/write.do?pageNumber=${startPage-pageBlock}">[이전]</a>
				</c:if>
				
				<c:forEach var="i" begin="${startPage}" end="${endPage }">
					<a href="${root}/guest/write.do?pageNumber=${i}">[${i}]</a>
				</c:forEach>
				
				<%-- 다음 --%>
				<c:if test="${ endPage < pageCount }">
					<a href="${root}/guest/write.do?pageNumber=${startPage+pageBlock}">[다음]</a>
				</c:if>	
				
				
				
				
			</c:if>
		
		</div>
	
	
	
	</body>
</html>






















