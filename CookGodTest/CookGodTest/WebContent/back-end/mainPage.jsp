<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* ,com.cust.model.*,com.broadcast.model.*"%>
<%
	CustVO custVO = (CustVO) session.getAttribute("cust");
	List<BroadcastVO> broadcastList = (ArrayList<BroadcastVO>) session.getAttribute("broadcast");
%>


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="BroadcastVO" items="${broadcastList}">
		<c:if test="${not empty BroadcastVO }">
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</c:if>
	</c:forEach>
	<table>
		<tr>
			<td>½s¸¹:<font color=red><b>*</b></font></td>
			<td><%=custVO.getCust_ID()%></td>
		</tr>
		<tr>
			<td>©m¦W:</td>
			<td><input type="TEXT" name="name" size="45"
				value="<%=custVO.getCust_name()%>" /></td>
		</tr>

	</table>
</body>
</html>