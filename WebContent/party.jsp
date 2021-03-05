<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList;"%>
<%
	//dataを取得
	ArrayList<Object> data = new ArrayList<Object>();
	data = (ArrayList<Object>)request.getAttribute("DATA");
	//arrayConの要素を取り出して出力を作る

		String strMsg = "";

		for(int i=data.size()-1;i>=0;i=i-4)
		{

			//Timestamp date = data.get(i-2);
			strMsg = strMsg + "<table>";
			strMsg = strMsg+"<tr>";
			strMsg = strMsg+"<td class='toko'><p class='table'>　投稿者：" +data.get(i-3) + "</td>";
			strMsg = strMsg+"<td class='date'><p class='table'>投稿日：" + data.get(i-2) + "　</td>";
			strMsg = strMsg + "</tr>";
			strMsg = strMsg + "<tr>";
			strMsg = strMsg +"<td colspan='2'><p class='table'>パーティー名：<span class='textColor'>" + data.get(i-1) + "</span></p></td>";
			strMsg = strMsg + "</tr>";
			strMsg = strMsg + "<tr>";
			strMsg = strMsg +"<td colspan='2'><p class='table'>内容・詳細</p><br><pre class='detail'>"+ data.get(i) + "</pre></td>";
			strMsg = strMsg + "</tr>";
			strMsg = strMsg + "</table>";
			strMsg = strMsg + "<br>";

		}
%>

<!DOCTYPE html>

<html lang="ja">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Script-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Content-Script-Type" content="text/css" />
		<meta http-equiv="Content-Script-Type" content="text/javascript" />

        <link rel="shortcut icon" href="./images/faviconP.ico" />
        <link rel="stylesheet" type="text/css" href="./css/party.css">

        <link href="https://fonts.googleapis.com/css?family=Playfair+Display|Great+Vibes" rel="stylesheet">

        <title>PARTY BOARD</title>
    </head>

    <body>

    	<div>

				<a class="home" href="./Home.html">HOME</a>

				<!-- <p class="PartyTextRight">PARTY</p> -->

           		<h1 id="PartyBoard" >PAR<span class="TYColor">TY</span> BOARD</h1>
				<h4>- Welcome to PARTY -</h4>

				<a class="con" href="./input.html">投稿</a>


        	<div class="main">

				<%= strMsg %>

			<div class="footer">
        		<p>お問い合わせ</p>

				<p> 管理者メールアドレス：chaos.39@outlook.jp</p>

				<h5>© copyright 2018 PARTY BOARD</h5>
        	</div>

        	</div>

        </div>


		<a href="#PartyBoard" class="right">↑</a> <!-- ページトップに戻る-->


        <script type="text/javascript">
		//<![CDATA[
			function refresh(){
				window.location.reload(false);
			}
		//]]>
		</script>
    </body>

</html>