//form_name,field_name,input_name,data_type,query_type,operator

//设置输入格式
function hwtt_setSqlCondition(){
    var fieldsList=$("#fields_queryinfo").html();
    
    if(fieldsList==null || fieldsList==undefined || fieldsList==""){
    	return;
    }
    var fieldList=fieldsList.split(":");
    for(var i=0;i<fieldList.length;i++){
       //alert(fieldList[i]);
       var fields=fieldList[i].split(",");
       
       if(fields[2]=="date"){
    	   $("#"+fields[0]).datepicker({ dateFormat: 'yy-mm-dd',changeYear: true,changeMonth:true });
       }else if(fields[2]=="int"){
    	   
    	   $("#"+fields[0]).blur(function(){
    		   
    		   this.value=this.value.replace(/[^1-9]/g,"");
    	   });
       }
    }
}

//生成where条件
function hwtt_getSqlCondition(){
	var fieldsList=$("#fields_queryinfo").html();
    var fieldList=fieldsList.split(":");
    var sqlWhere="";
    for(var i=0;i<fieldList.length;i++){
       //alert(fieldList[i]);
       var fields=fieldList[i].split(",");
       var sqlCondition=f_getCondition(fields);
       if (sqlCondition=="") continue;
       if (sqlWhere=="")
  		   sqlWhere= sqlCondition;
  	   else
  		   sqlWhere=sqlWhere + " and " +sqlCondition ;    
    }
    return sqlWhere;
}

//取得输入值
function f_getValue(fields){
	var value="";
	if (fields[4]=="input" || fields[4]=="select")
		value=$("#"+fields[0]).val();
	else if (fields[4]=="checkbox" ){
		 $("input:[name="+fields[0]+"]:checkbox:checked").each(function(){
			 if (fields[3]=="string"){
				  if (value=="")
					  value="'"+$(this).val()+"'";
				  else
					  value=value+",'"+$(this).val()+"'"; 
			 }else{
				 if (value=="")
					  value=$(this).val();
				  else
					  value=value+","+$(this).val();
			 }
            
         });
		
	}else if (fields[4]=="radio" ){
		value=$("input:[name="+fields[0]+"]:radio:checked").val();
	}
	
	if (value==undefined)  value="";
	
	return f_injectFilter(value);
}

//注入过滤
function f_injectFilter(value){
	//var re= /select|update|delete|exec|count|'|"|=|;|>|<|%/i;
	var re= /where|and|select|update|truncate|drop|delete|exec|count|'|"|=|;|>|<|%/i;
	if ( re.test(value) )
	{
		value="";
	}
	return value;
}

//处理各种数据库条件
function f_getCondition(fields){
	return f_getCondition_mysql(fields);
}

//生成单个输入域where条件  
function f_getCondition_mysql(fields){
	 var sqlWhere="";
	 var value=f_getValue(fields);
	 if (value=="") return "";
	 
	 fields[5] = fields[5].replace("&lt;","<").replace("&gt;",">");
	 
	 if(fields[3]=="date"){
		 if (fields[5]=="like")
		    sqlWhere="convert("+fields[1]+",date) "+ fields[5] + " %"+value+"%";			 
		 else	 
		    sqlWhere="convert("+fields[1]+",date) "+ fields[5] + " str_to_date('"+value+"','%Y-%m-%d')" ; 
     }else if (fields[3]=="string"){
    	 if (fields[5]=="like")
 		    sqlWhere=fields[1]+" "+ fields[5] + "'%"+value+"%'";
 		 else if(fields[5]=="in")
 			 sqlWhere=fields[1]+" "+ fields[5] + " ("+ value+ ")"; 
 		 else
 		    sqlWhere=fields[1]+" "+ fields[5] + " '"+ value+ "'"; 
     }else{
    	 if (fields[5]=="like")
  		    sqlWhere=fields[1]+" "+ fields[5] + " %"+value+"%";
    	 else if(fields[5]=="in")
 			 sqlWhere=fields[1]+" "+ fields[5] + " ("+ value+ ")"; 
  		 else
  		    sqlWhere=fields[1]+" "+ fields[5] + value; 
     }
	 
	 return sqlWhere;
}
