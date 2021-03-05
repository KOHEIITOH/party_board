<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String strSHIMEI = (String)request.getParameter("shimei");
	String strPARTY = (String)request.getParameter("party");
	String strDETAIL = (String)request.getParameter("detail");

%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">

		<link rel="shortcut icon" href="./images/faviconP.ico" />
		<link rel="stylesheet" type="text/css" href="./css/check.css">

		<!-- <link href="https://fonts.googleapis.com/css?family=Playfair+Display|Great+Vibes" rel="stylesheet"> -->

		<title>確認|PARTY BOARD</title>
	</head>

	<body>
		<div class="header">
			<h1 id="PartyBoard" >PAR<span class="TYColor">TY</span> BOARD</h1>
    		<h4>- Welcome to PARTY -</h4>
    	</div>

		<form action="PartyServlet" method="post" name="info" onSubmit="return check()">

			<table>
				<tr>
					<td>投稿者：<input type="hidden" name="shimei" value="<%= strSHIMEI %>"><%= strSHIMEI %></td>
				</tr>
				<tr>
					<td>パーティー名:<input type="hidden" name="party" value="<%= strPARTY %>"><%= strPARTY %></td>
				</tr>
				<tr>
					<td rowspan="2" >内容・詳細<br><pre><input type="hidden" name="detail" value="<%= strDETAIL %>"><%= strDETAIL %></pre></td>
				</tr>
			</table>

			<p id="con"><input type="submit"  name="con" value="投稿">　　　　　　　　　　　　　<input type="button" value="戻る" onClick="history.go(-1)"></p>

		</form>

	</body>
</html>