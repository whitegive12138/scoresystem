<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分组管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/scoresys/scoresysGroup/">分组列表</a></li>
		<li class="active"><a href="${ctx}/scoresys/scoresysGroup/form?id=${scoresysGroup.id}">分组<shiro:hasPermission name="scoresys:scoresysGroup:edit">${not empty scoresysGroup.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="scoresys:scoresysGroup:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="scoresysGroup" action="${ctx}/scoresys/scoresysGroup/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">组名：</label>
			<div class="controls">
				<form:input path="groupName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">学生表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>学号</th>
								<th>姓名</th>
								<th>班级</th>
								<th>科目</th>
								<th>分数</th>
								<th>备注信息</th>
								<shiro:hasPermission name="scoresys:scoresysGroup:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="scoresysStudentList">
						</tbody>
						<shiro:hasPermission name="scoresys:scoresysGroup:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#scoresysStudentList', scoresysStudentRowIdx, scoresysStudentTpl);scoresysStudentRowIdx = scoresysStudentRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="scoresysStudentTpl">//<!--
						<tr id="scoresysStudentList{{idx}}">
							<td class="hide">
								<input id="scoresysStudentList{{idx}}_id" name="scoresysStudentList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="scoresysStudentList{{idx}}_delFlag" name="scoresysStudentList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="scoresysStudentList{{idx}}_stuId" name="scoresysStudentList[{{idx}}].stuId" type="text" value="{{row.stuId}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="scoresysStudentList{{idx}}_stuName" name="scoresysStudentList[{{idx}}].stuName" type="text" value="{{row.stuName}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="scoresysStudentList{{idx}}_stuClass" name="scoresysStudentList[{{idx}}].stuClass" type="text" value="{{row.stuClass}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="scoresysStudentList{{idx}}_stuMajor" name="scoresysStudentList[{{idx}}].stuMajor" type="text" value="{{row.stuMajor}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<input id="scoresysStudentList{{idx}}_stuScore" name="scoresysStudentList[{{idx}}].stuScore" type="text" value="{{row.stuScore}}" maxlength="3" class="input-small "/>
							</td>
							<td>
								<textarea id="scoresysStudentList{{idx}}_remarks" name="scoresysStudentList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="scoresys:scoresysGroup:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#scoresysStudentList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var scoresysStudentRowIdx = 0, scoresysStudentTpl = $("#scoresysStudentTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(scoresysGroup.scoresysStudentList)};
							for (var i=0; i<data.length; i++){
								addRow('#scoresysStudentList', scoresysStudentRowIdx, scoresysStudentTpl, data[i]);
								scoresysStudentRowIdx = scoresysStudentRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="scoresys:scoresysGroup:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>