<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>应用画板</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div id="d-main-content">
		<div id="app-panel">
			<div id="graph-panel" class="col-sm-10" style="overflow: auto;">
				<div id="tmp-panel"></div>
			</div>
			<div id="group-list" class="col-sm-2">
				<div>
					<div id="btn-save" class="save-btn">
						<i class="fa fa-save"></i> 保存
					</div>
					<div id="btn-save-as" class="save-btn" style="display: none;">
						<i class="fa fa-save"></i><i class="fa fa-pencil"></i> 另存为
					</div>
				</div>
				<!-- panel group for operations component list -->
				<div id="panel-group-operations" class="panel-group" role="tablist"
					aria-multiselectable="true"></div>
			</div>
		</div>
		<!-- node details start -->
		<div class="modal fade" id="detailModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true"
			data-backdrop="static">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close editCancel"
							data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">配置容器参数</h4>
					</div>
					<div class="modal-body">
						<label for="hostName" class="control-label">选择机器节点</label>
						<div id="hostNames">
							<ul></ul>
						</div>
						<form class="form-horizontal" role="form"
							style="margin-top: 15px;">
							<div class="form-group col-sm-6">
								<label for="containerName" class="control-label pull-left"
									style="padding-right: 1em;">容器名称:</label>
								<div class="col-sm-8">
									<input id="containerName" name="containerName"
										class="form-control">
								</div>
							</div>
							<div class="form-group col-sm-6">
								<label for="containerPort" class="control-label pull-left"
									style="padding-right: 2em;">端口号:</label>
								<div class="col-sm-8">
									<input type="number" id="containerPort" name="containerPort"
										class="form-control">
								</div>
							</div>
							<div class="form-group col-sm-6">
								<label for="initCount" class="control-label pull-left">初始实例数:</label>
								<div class="col-sm-8">
									<input type="number" id="initCount" name="initCount"
										class="form-control">
								</div>
							</div>
							<div class="form-group col-sm-6">
								<label for="maxCount" class="control-label pull-left">最大实例数:</label>
								<div class="col-sm-8">
									<input type="number" id="maxCount" name="maxCount"
										class="form-control">
								</div>
							</div>
						</form>
						<table id="actionParams"
							class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th style="width: 20%;">参数</th>
									<th style="width: 60%;">值</th>
									<th>描述</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default editCancel"
							data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary editSave">保存</button>
					</div>
				</div>
			</div>
		</div>
		<!-- task commit start -->
		<div style="margin-top: 200px;" class="modal fade" id="saveModal"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">保存应用</h4>
					</div>
					<div class="modal-body">
						<div style="width: 50%;">
							<div class="form-group">
								<label for="appName">应用名称</label> <input type="text"
									class="form-control" id="appName" placeholder="应用名称">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" id="save-app-btn" class="btn btn-primary">保存</button>
					</div>
				</div>
			</div>
		</div>
		<!-- task commit end -->
		<!-- operation start -->
		<div style="margin-top: 200px;" class="modal fade" id="operationModal"
			tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
			aria-hidden="true" data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">选择操作</h4>
					</div>
					<div class="modal-body">
						<ul class="operation-list">
							<li id="start" data-oper-type="START" data-view-id="1">
								<div class="oper-icon">
									<i class="fa fa-play text-success"></i>
								</div>
								<div class="oper-text">启动</div>
							</li>
							<li id="stop" data-oper-type="STOP" data-view-id="1">
								<div class="oper-icon">
									<i class="fa fa-stop text-danger"></i>
								</div>
								<div class="oper-text">停止</div>
							</li>
							<li id="remove" data-oper-type="REMOVE" data-view-id="2">
								<div class="oper-icon">
									<i class="fa fa-minus text-danger"></i>
								</div>
								<div class="oper-text">移除</div>
							</li>
							<li id="copy" data-oper-type="COPY" data-view-id="2">
								<div class="oper-icon">
									<i class="fa fa-plus text-primary"></i>
								</div>
								<div class="oper-text">复制</div>
							</li>
							<li id="fail" data-oper-type="FAIL" data-view-id="3">
								<div class="oper-icon">
									<i class="fa fa-times text-danger"></i>
								</div>
								<div class="oper-text">模拟故障</div>
							</li>
							<li id="fail" data-oper-type="START" data-view-id="3">
								<div class="oper-icon">
									<i class="fa fa-play text-success"></i>
								</div>
								<div class="oper-text">故障恢复</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- operation end -->
		<!-- node details end -->
		<script src="${basePath}assets/js/jsPlumb/dom.jsPlumb-1.7.2.js"></script>
		<script src="${basePath}assets/js/cloudeploy/app/graph/j-class.js"></script>
		<script src="${basePath}assets/js/cloudeploy/app/graph/node.js"></script>
		<script src="${basePath}assets/js/cloudeploy/app/app-plumb.js"></script>
		<script src="${basePath}assets/js/cloudeploy/app/app-orchestration.js"></script>
	</div>
</body>
</html>