<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BatchQuery</title>
        <link rel="stylesheet" type="text/css" href="css/Query.css"/>
		<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="js/BatchQuery.js"></script>
		<script type="text/javascript" src="js/JquerySession.js"></script>
		<script type="text/javascript" src="js/jquery.blockUI.js"></script>
    </head>
    <body>
    	<div class="page">
    	<div class="inside1">
    	<h2>请输入邮箱信息</h2>
		<form id="uploadForm" enctype="multipart/form-data" method="post">
    		<input type="file" id="searchBox1" name="file"/>
    		<input type="button" id="queryButton" value="批量查询" /><a href="ManualQuery.jsp">手动查询</a>
    	</form>
    	<div class="table">
    	<table id="result" cellspacing="20px" align="center">
    		<tr>
    			<td>邮箱信息</td>
    			<td>记录数</td>
    			<td>安全评估</td>
    		</tr>
    	</table>
				<div id="ppp">
					<span id="span">共0条记录 </span><span id="nowPageNumber"> 当前第1页</span>&nbsp;
					<span id="one" style="cursor: pointer;color: hotpink">首页</span>&nbsp;
					<span id="a_up" style="cursor: pointer;color: hotpink">上一页</span>&nbsp;
					<span id="a_down" style="cursor: pointer;color: hotpink">下一页</span>&nbsp;
					<span id="over" style="cursor: pointer; color: hotpink">尾页</span>&nbsp;
					<a href="/emailSearch/downloadServlet" id="a">导出</a>
				</div>
    	</div>

    	</div>
    	</div>
 	</body>
</html>