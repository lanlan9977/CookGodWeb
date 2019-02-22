<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="Big5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/cust/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<title>Menu Order</title>
</head>
<body>


	<form class="form-inline" method="post" action="<%=request.getContextPath()%>/menuOrder/menuOrder.do">
		<label class="my-1 mr-2" for="inlineFormCustomSelectPref"></label> 
		  <input type="TEXT" name="MenuOrderID" size="45" placeholder="輸入欲審核嚴選套餐訂單編號" value="M00001">
		   <select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref" name="MenuOrderStatus">
			  <option selected>請審核...</option>
			  <option value="g1">審核通過</option>
			  <option value="g2">審核不通過</option>
		    </select> 
		  <input type="hidden" name="action" value="SendStatus">
		<button type="submit" class="btn btn-primary my-1">發送</button>
	</form>





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
		crossorigin="anonymous"></script>

</body>
</html>