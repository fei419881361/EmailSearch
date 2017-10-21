<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JBatchQuery</title>
        <link rel="stylesheet" type="text/css" href="css/Query.css"/>
		<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="js/ManualQuery.js"></script>
    </head>
    <body>

    	<div class="page">
    		<div class="inside1">
    	<h2>请输入邮箱信息</h2>
			<form action="###" method="post">
				<input type="text" id="searchBox" placeholder="请输入邮箱号" />
				<input type="submit" id="queryButton" value="查询" /><a href="BatchQuery.jsp">批量查询</a>
			</form>
    	<div class="table">

    	<table id="result" cellspacing="20px" align="center">
    		<tr>
    			<td>邮箱信息</td>
    			<td>记录数</td>
    			<td>安全评估</td>
    		</tr>
    		
    	</table>
    	</div>
    	</div>
    	</div>
 	</body>
</html>