<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* ,com.cust.model.*,com.broadcast.model.*"%>
<%
    @SuppressWarnings("unchecked")
    ArrayList<BroadcastVO> broadcastConList = (ArrayList<BroadcastVO>) session.getAttribute("broadcastConList");
	CustVO custVO = (CustVO) session.getAttribute("cust");
	int unReadCount = (int) session.getAttribute("unReadCount");
	String sex="MR.";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/cust/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

<title>Main Page</title>
</head>
<body>



<nav class="navbar navbar-dark bg-primary">
<% if ((custVO.getCust_sex()).equals("f")) sex="MISS.";%>
<font size="5" color="#CCCCCC"><%=sex%><%=custVO.getCust_name()%>歡迎!</font><br>



	<form method="post" action="<%=request.getContextPath()%>/broadcast/broadcast.do">
		<div class="dropdown" >
			<button type=button class="btn btn-dark dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown" name="EnterBroadMsg" >
				推播通知訊息 <span class="badge badge-light">${unReadCount}</span>
			  </button>
			    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" >
	                 <c:forEach var="broadcastVO" items="${broadcastConList}" varStatus="readiIndex">
				      <c:if test="${not empty broadcastVO.broadcast_con}">
					     <button type="submit" name="readAction" class="dropdown-item" value="${readiIndex.index}">${broadcastVO.broadcast_con}>
					     <input type="hidden" name="action" value="read"></button>
				      </c:if>	
			         </c:forEach>
			       <c:if test="${empty broadcastConList}">
					  <li>---沒有未讀的推播訊息---</li>
				   </c:if>
			   </div>
		</div>
	</form>
</nav>



	<script
		src="<%=request.getContextPath()%>/back-end/cust/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/back-end/cust/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/cust/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous">
		
	</script>


</body>
</html>