<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>学生管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/scoresys/scoresysStudent/">学生列表</a></li>
		<shiro:hasPermission name="scoresys:scoresysStudent:edit"><li><a href="${ctx}/scoresys/scoresysStudent/form">学生添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scoresysStudent" action="${ctx}/scoresys/scoresysStudent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>学号：</label>
				<form:input path="stuId" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="stuName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>班级</th>
				<th>科目</th>
				<th>分数</th>
				<th>备注信息</th>
				<shiro:hasPermission name="scoresys:scoresysStudent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="scoresysStudent">
			<tr>
				<td><a href="${ctx}/scoresys/scoresysStudent/form?id=${scoresysStudent.id}">
					${scoresysStudent.stuId}
				</a></td>
				<td>
					${scoresysStudent.stuName}
				</td>
				<td>
					${scoresysStudent.stuClass}
				</td>
				<td>
					${scoresysStudent.stuMajor}
				</td>
				<td>
					${scoresysStudent.stuScore}
				</td>
				<td>
					${scoresysStudent.remarks}
				</td>
				<shiro:hasPermission name="scoresys:scoresysStudent:edit"><td>
    				<a href="${ctx}/scoresys/scoresysStudent/form?id=${scoresysStudent.id}">修改</a>
					<a href="${ctx}/scoresys/scoresysStudent/delete?id=${scoresysStudent.id}" onclick="return confirmx('确认要删除该学生吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>