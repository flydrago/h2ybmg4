<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<title>${mname}</title>
<%@ include file="../../include/top_list.jsp" %>
<script type="text/javascript">
    var id = 0;
    var joinId = 0;
    var parentJoinId = 0;

    var ruleTypeList = [{RULE_TYPE:'required',text:'必填字段'},
                        {RULE_TYPE:'remote',text:'Ajax验证'},
                        {RULE_TYPE:'email',text:'电子邮件'},
                        {RULE_TYPE:'url',text:'网址'},
                        {RULE_TYPE:'date',text:'合法日期'},
                        {RULE_TYPE:'dateISO',text:'合法日期ISO'},
                        {RULE_TYPE:'number',text:'数字'},
                        {RULE_TYPE:'digits',text:'整数'},
                        {RULE_TYPE:'creditcard',text:'信用卡号'},
                        {RULE_TYPE:'equalTo',text:'相同'},
                        {RULE_TYPE:'accept',text:'指定后缀'},
                        {RULE_TYPE:'maxlength',text:'最大长度'},
                        {RULE_TYPE:'minlength',text:'最小长度'},
                        {RULE_TYPE:'rangelength',text:'长度范围'},
                        {RULE_TYPE:'range',text:'值范围'},
                        {RULE_TYPE:'max',text:'最大值'},
                        {RULE_TYPE:'min',text:'最小值'},
                        {RULE_TYPE:'alnum',text:'字母数字'},
                        {RULE_TYPE:'alnumex',text:'字母数字下划线'},
                        {RULE_TYPE:'cellphone',text:'手机号'},
                        {RULE_TYPE:'telephone',text:'电话号码'},
                        {RULE_TYPE:'zipcode',text:'邮政编码'},
                        {RULE_TYPE:'chcharacter',text:'汉字'},
                        {RULE_TYPE:'qq',text:'QQ'},
                        {RULE_TYPE:'idcard',text:'身份证'}
                        ];

    var ifWorkList = [{IF_WORK:'1',text:'启用'},{IF_WORK:'0',text:'不启用'}];
    
    
    var dataTypeList = [{DATA_TYPE:'string',text:'字符串'},
                        {DATA_TYPE:'int',text:'数字'},
                        {DATA_TYPE:'date',text:'日期'}];
    
    var inputNameList = [{INPUT_NAME:'string',text:'字符串'},
                        {INPUT_NAME:'int',text:'数字'},
                        {INPUT_NAME:'date',text:'日期'}];
    
    var datasourceTypeList = [{DATASOURCE_TYPE:'input',text:'输入'},
                              {DATASOURCE_TYPE:'json',text:'json数组'},
                              {DATASOURCE_TYPE:'sql',text:'sql语句'},
                              {DATASOURCE_TYPE:'dict',text:'字典'}];
    
    var queryTypeList = [{QUERY_TYPE:'input',text:'输入'},
                         {QUERY_TYPE:'select',text:'选择'},
                         {QUERY_TYPE:'radio',text:'单选'},
                         {QUERY_TYPE:'checkbox',text:'复选'}];
    
    
    var operatorList = [{OPERATOR:'=',text:'等于'},
                        {OPERATOR:'>',text:'大于'},
                        {OPERATOR:'>=',text:'大于等于'},
                        {OPERATOR:'<',text:'小于'},
                        {OPERATOR:'<=',text:'小于等于'},
                        {OPERATOR:'like',text:'like'},
                        {OPERATOR:'in',text:'in'}];
    
    var rowsList = [{ROW:'1',text:'1'},
                    {ROW:'2',text:'2'},
                    {ROW:'3',text:'3'},
                    {ROW:'4',text:'4'},
                    {ROW:'5',text:'5'},
                    {ROW:'6',text:'6'},
                    {ROW:'7',text:'7'},
                    {ROW:'8',text:'8'},
                    {ROW:'9',text:'9'},
                    {ROW:'10',text:'10'}];
    
    var rowspanList = [{ROWSPAN:'1',text:'1'},
	                    {ROWSPAN:'2',text:'2'},
	                    {ROWSPAN:'3',text:'3'},
	                    {ROWSPAN:'4',text:'4'},
	                    {ROWSPAN:'5',text:'5'},
	                    {ROWSPAN:'6',text:'6'},
	                    {ROWSPAN:'7',text:'7'},
	                    {ROWSPAN:'8',text:'8'},
	                    {ROWSPAN:'9',text:'9'},
	                    {ROWSPAN:'10',text:'10'}];
    
    var joinType = "${joinType}";
    $(function () {

        $("#toptoolbar").ligerToolBar({items: [
            { line: true },
            {text: '添加', click: h2y_add, icon: 'add'},
            { line: true },
            {text: '删除', click: h2y_delete, icon: 'delete'},
            { line: true },
            {text: '保存', click: h2y_save, icon: 'save'},
            { line: true },
            {text: '刷新', click: h2y_refresh, icon: 'refresh'}
        ]});


        $("#layout1").ligerLayout({
            leftWidth: 190,
            height: "100%",
            topHeight: 23,
            allowTopResize: false
        });


        $("#tree1").ligerTree({
            //远程加载，有时会加载不出来
            //url: "sys/queryitem/getList.htm?op=tree",
            data:${treedata},
            checkbox: false,
            nodeWidth: 120,
            onSelect: f_onSelect,
            idFieldName: "id",
            parentIDFieldName: "pid",
            textFieldName: "text"
        });

        f_getList();
    });

    function f_getList() {

        var url_1 = "sys/queryitem/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [
                {display: '序号', name: 'ORD', width: 80, align: 'left', isSort:false, type: 'string',editor: { type: 'spinner' }},
                {display: '字段名称', name: 'FIELD_NAME', width: 200, align: 'left', isSort:false,type: 'string',editor: { type: 'text' }},
                {display: 'Form名称', name: 'FORM_NAME', width: 150, align: 'left', isSort:false, type: 'string',editor: { type: 'text' }},
                {display: '显示名称', name: 'NAME', width: 200, align: 'left', isSort:false, type: 'string',editor: { type: 'text' }},
                {display: '宽度', name: 'WIDTH', width: 100, align: 'left', isSort:false, type: 'string',editor: { type: 'int' }},
                {display: '数据类型', name: 'DATA_TYPE', width: 100, align: 'left', isSort:false, type: 'string',
                	editor: { type: 'select',data: dataTypeList , valueColumnName:'DATA_TYPE' },
                	render:function(rowdata, index, value){
                        return getDataText(dataTypeList,"DATA_TYPE",value);
                    }},
                {display: '编辑类型', name: 'INPUT_NAME', width: 100, align: 'left', isSort:false, type: 'string',
                	editor: { type: 'select',data: inputNameList , valueColumnName:'INPUT_NAME' },
                	render:function(rowdata, index, value){
                        return getDataText(inputNameList,"INPUT_NAME",value);
                    }},
                {display: '默认值', name: 'VALUE', width: 100, align: 'left', isSort:false, type: 'string',editor: { type: 'text' }},
                {display: '数据源类型', name: 'DATASOURCE_TYPE', width: 100, align: 'left', isSort:false, type: 'string',
                	editor: { type: 'select',data: datasourceTypeList , valueColumnName:'DATASOURCE_TYPE' },
                	render:function(rowdata, index, value){
                        return getDataText(datasourceTypeList,"DATASOURCE_TYPE",value);
                    }},
                {display: '数据源值', name: 'DATASOURCE_VALUE', width: 100, align: 'left', isSort:false, type: 'string',editor: { type: 'text' }},
                {display: '表单类型', name: 'QUERY_TYPE', width: 100, align: 'left', isSort:false, type: 'string',
                	editor: { type: 'select',data: queryTypeList , valueColumnName:'QUERY_TYPE' },
                	render:function(rowdata, index, value){
                        return getDataText(queryTypeList,"QUERY_TYPE",value);
                    }},
                {display: '操作符', name: 'OPERATOR', width: 100, align: 'left', isSort:false, type: 'string',
                    	editor: { type: 'select',data: operatorList , valueColumnName:'OPERATOR' },
                    	render:function(rowdata, index, value){
                            return getDataText(operatorList,"OPERATOR",value);
                        }},
                {display: '行号', name: 'ROW', width: 50, align: 'left', isSort:false,editor: { type: 'spinner' }},
                {display: '行占列数', name: 'ROWSPAN', width: 50, align: 'left', isSort:false,editor: { type: 'spinner' }}
            ],
            url: url_1,
            parms: [
                { name: "joinId", value: joinId},{ name: "joinType", value: joinType}
            ],
            showTitle: false,
            dataAction: "server",
            sortName: "ID",
            pageSize: 20,
            height: "100%",
            width: "100%",
            usePager: false,
            enabledEdit: true,
            clickToEdit: true,
            isScroll: false,
            pageSizeOptions: [20, 30, 50, 100],
            onSelectRow: function (row, index, data) {
                id = row.ID;
            },
            onDblClickRow: function (row, index, data) {
                //alert("行双击事件");
            }
        });
    }

    function getDataText(dataRows,key,value){

        var text = "";
         $(dataRows).each(function(){

             if(this[key] == value){
                 text = this.text;
                 return;
             }
         })
        return text;
    }

    function f_html(row) {

        return "";
    }

    function f_onSelect(node) {
        joinId = node.data.id;
        parentJoinId = node.data.pid;
        f_query();
    }

    function h2y_add() {

        if(joinId==null || joinId == 0){
            alert("请选择左侧二级树节点！");
            return;
        }

        if(parentJoinId==0){
            alert("顶级不可添加列维护！");
            return;
        }

        var manager = $("#listgrid").ligerGetGridManager();
        manager.addRow({"ORD":"1","FIELD_NAME":"","FORM_NAME":"","NAME":"","WIDTH":"","VALUE":"",
        	"DATA_TYPE":"string","INPUT_NAME":"string","DATASOURCE_TYPE":"input","DATASOURCE_VALUE":"",
        	"QUERY_TYPE":"input","OPERATOR":"=","ROW":"1","ROWSPAN":"1"});
    }

    function h2y_modify() {

        var manager = $("#listgrid").ligerGetGridManager();
        var row = manager.getSelectedRow();
        if (!row) { alert('请选择行'); return; }
        manager.beginEdit(row);
    }

    function h2y_save(){

        var manager = $("#listgrid").ligerGetGridManager();
        manager.endEdit();
        var data = manager.getData();

        //alert(JSON.stringify(data));
        //return;
        $.post("sys/queryitem/save.htm",{queryItemData:JSON.stringify(data),joinId:joinId,joinType:joinType}, function (data) {

            var jsonReturn = eval("(" + data + ")");
            if (jsonReturn.code == "1") {
                alert(jsonReturn.msg);
                f_query()
            } else {
                alert(jsonReturn.msg);
            }
        });
    }

    function h2y_delete() {

        var manager = $("#listgrid").ligerGetGridManager();
        manager.deleteSelectedRow();
    }

    function h2y_refresh() {
        document.location.reload();
    }

    function f_query() {
        var manager = $("#listgrid").ligerGetGridManager();
        manager.setOptions({
            parms: [
                { name: "joinId", value: joinId},{ name: "joinType", value: joinType}
            ]
        });
        manager.loadData(true);
    }

</script>


</head>
<body>
<div id="layout1" style="width: 100%">

    <div position="top">
        <table width="100%" class="my_toptoolbar_td">
            <tr>
                <td id="my_toptoolbar_td">
                    <div class="l-toolbar">&nbsp;${mname}</div>
                </td>
                <td align="right" width="50%">
                    <div id="toptoolbar"></div>
                </td>
            </tr>
        </table>
    </div>

    <div position="left" style="height: 94%; overflow: auto;">
        <ul id="tree1"></ul>
    </div>

    <div position="center" title="">
        <%--
     <div id="conditiondiv" style="align:center;padding-top:5px;padding-bottom:5px;">${exparams.conditionHtml} </div>
     --%>
        <div id="listgrid"></div>
    </div>
</div>
</body>
</html>