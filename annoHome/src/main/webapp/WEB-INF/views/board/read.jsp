<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function replyFun(root, boardNumber,groupNumber,sequenceNumber,sequenceLevel,pageNumber){
		
		var url=root+"/board/write.do?boardNumber="+boardNumber;
		url += "&groupNumber="+ groupNumber +"&sequenceNumber="+sequenceNumber;
		url += "&sequenceLevel="+sequenceLevel+"&pageNumber="+pageNumber;
		
		//alert(url);
		location.href=url;
	}
	
</script>
</head>
<body>
	<c:set var="root" value="${pageContext.request.contextPath}"/>
	
	<div align="center" style="margin-top: 200px">
		<table border="1">
			<tr>
				<td align="center" height="20" width="125">글번호</td>
				<td align="center" height="20" width="125">${boardDto.boardNumber}</td>
				
				<td align="center" height="20" width="125">조회수</td>
				<td align="center" height="20" width="125">${boardDto.readCount}</td>
			</tr>
			
			<tr>
				<td align="center" height="20" width="125">작성자</td>
				<td align="center" height="20" width="125">${boardDto.writer}</td>
						
				<td align="center" height="20" width="125">작성일</td>
				<td align="center" height="20" width="125">
						<fmt:formatDate value="${boardDto.writeDate}"
										pattern="yyyy-MM-dd hh:mm:ss"/>
				</td>
			</tr>
			
			<tr>
				<td align="center" height="350" width="125">글내용</td>
				<td valign="top" height="350" colspan="3">${boardDto.content}</td>
			</tr>
			
			<tr>
				<td height="30" colspan="4" align="center">
					<input type="button" value="글수정" 
						onclick="location.href='${root}/board/update.do?boardNumber=${boardDto.boardNumber}&pageNumber=${pageNumber}'"/>
								
					<input type="button" value="글삭제" 
						onclick="location.href='${root}/board/delete.do?boardNumber=${boardDto.boardNumber}&pageNumber=${pageNumber}'"/>
					
					<input type="button" value="답글" 
							onclick="replyFun('${root}','${boardDto.boardNumber}','${boardDto.groupNumber}',
								'${boardDto.sequenceNumber}','${boardDto.sequenceLevel}','${pageNumber}')"/>
								
					<input type="button" value="글목록" 
							onclick="location.href='${root}/board/list.do?pageNumber=${pageNumber}'"/>
				</td>
			</tr>
		</table>
	
	</div>
	
</body>
</html>