<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.cust.model.*"%>
<%
	CustVO custVO = (CustVO) session.getAttribute("cust");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>�s��:<font color=red><b>*</b></font></td>
			<td><%=custVO.getCust_ID()%></td>
		</tr>
		<tr>
			<td>�m�W:</td>
			<td><input type="TEXT" name="name" size="45"
				value="<%=custVO.getCust_name()%>" /></td>
		</tr>
		<tr>
			<td>�a�}:</td>
			<td><input type="TEXT" name="addr" size="45"
				value="<%=custVO.getCust_addr()%>" /></td>
		</tr>
		<tr>
			<td>�q�l�l��:</td>
			<td><input type="TEXT" name="mail" size="45"
				value="<%=custVO.getCust_mail()%>" /></td>
		</tr>
		<tr>
			<td>�ʧO:</td>
			<td><input type="TEXT" name="sex" size="45"
				value="<%=custVO.getCust_sex()%>" /></td>
		</tr>
	</table>
</body>
</html>