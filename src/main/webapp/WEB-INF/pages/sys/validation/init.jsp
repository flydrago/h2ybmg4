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
            //url: "sys/validation/getList.htm?op=tree",
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

        var url_1 = "sys/validation/getList.htm?op=grid";

        $("#listgrid").ligerGrid({
            columns: [
                {display: '字段ID', name: 'FIELD_ID', width: 200, align: 'left', isSort:false, type: 'string',editor: { type: 'text' }},
                {display: '验证类型', name: 'RULE_TYPE', width: 200, align: 'left', isSort:false,type: 'string',
                    editor: { type: 'select',data: ruleTypeList , valueColumnName:'RULE_TYPE'},
                    render:function(rowdata, index, value){
                        return getDataText(ruleTypeList,"RULE_TYPE",value);
                    }
                },
                {display: '验证参数', name: 'RULE_TYPE_VALUE', width: 150, align: 'left', isSort:false, type: 'string',editor: { type: 'text' }},
                {display: '提示信息', name: 'MSG', width: 200, align: 'left', isSort:false, type: 'string',editor: { type: 'text' }},
                {display: '是否启用', name: 'IF_WORK', width: 100, align: 'left', isSort:false, type: 'string',
                    editor: { type: 'select',data: ifWorkList, valueColumnName:'IF_WORK'},
                    render:function(rowdata, index, value){
                        return getDataText(ifWorkList,"IF_WORK",value);
                    }
                }
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
        manager.addRow({"FIELD_ID":"","RULE_TYPE":"required","RULE_TYPE_VALUE":"","MSG":"","IF_WORK":1});
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
        $.post("sys/validation/save.htm",{validationData:JSON.stringify(data),joinId:joinId,joinType:joinType}, function (data) {

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