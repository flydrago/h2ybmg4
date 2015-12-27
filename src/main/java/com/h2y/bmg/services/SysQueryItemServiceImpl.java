package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ISysCommUtilDao;
import com.h2y.bmg.dao.ISysQueryItemDao;
import com.h2y.bmg.dao.ISysTypeDao;
import com.h2y.bmg.entity.ListItem;
import com.h2y.bmg.entity.SysMenu;
import com.h2y.bmg.entity.SysQueryItem;
import com.h2y.bmg.entity.SysType;
import com.h2y.bmg.util.SysBaseUtil.SysJoinType;
import com.h2y.util.JSONUtil;

/**
  * ServiceImpl
 * time:2014-10-27
 * email:info@hwttnet.com
 */
@Service("sysQueryItemService")
public class SysQueryItemServiceImpl implements ISysQueryItemService{


	@Autowired
	protected ISysQueryItemDao sysQueryItemDao;
	
	@Autowired
	protected ISysCommUtilDao sysCommUtilDao;
	
	@Autowired
	protected ISysTypeDao sysTypeDao;
	
	@Autowired
	protected ISysMenuService sysMenuService;

	/**
	 * Add data, pay attention to the path of the gain of algorithm
	 * 
	 * @param sysQueryItem
	 *
	 */
	public void add(SysQueryItem sysQueryItem) {
		// TODO Auto-generated method stub

		sysQueryItemDao.add(sysQueryItem);
	}


	public void delete(long id) {
		// TODO Auto-generated method stub
		sysQueryItemDao.deleteById(id);
	}

	//public void deleteByIds(List<long> ids){
	//	sysQueryItemDao.deleteByIds(ids);
	//}

	public void update(SysQueryItem sysQueryItem) {
		// TODO Auto-generated method stub
		sysQueryItemDao.update(sysQueryItem);
	}

	public SysQueryItem get(long id) {
		// TODO Auto-generated method stub
		return sysQueryItemDao.get(id);
	}
	
	
	public Map<String,Object> getGirdData(Map<String,Object> map){
		
		Map<String,Object> gridData = new HashMap<String, Object>();

		List<Map<String,Object>> dataList = sysQueryItemDao.getListMap(map);
		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", dataList.size());
		return gridData;
	}
	
	  /**
     * 保存查询
     * @param joinId
     * @param joinType
     * @param dataList
     */
    public void saveQueryItem(long joinId,String joinType,List<Map<String,Object>> dataList){
    	
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("joinId",joinId);
		params.put("joinType",joinType);
		sysQueryItemDao.deleteByJoinType(params);
		
		List<SysQueryItem> sysQueryItemList = new ArrayList<SysQueryItem>();
		
		if (dataList!=null && !dataList.isEmpty()) {
			
			for (Map<String,Object> dataMap: dataList){
				
				SysQueryItem sysQueryItem = new SysQueryItem();
				
				sysQueryItem.setJoinId(joinId);
				sysQueryItem.setJoinType(joinType);
				sysQueryItem.setDatasourceType(dataMap.get("DATASOURCE_TYPE")+"");
				sysQueryItem.setDatasourceValue(dataMap.get("DATASOURCE_VALUE")+"");
				sysQueryItem.setDataType(dataMap.get("DATA_TYPE")+"");
				sysQueryItem.setFieldName(dataMap.get("FIELD_NAME")+"");
				sysQueryItem.setFormName(dataMap.get("FORM_NAME")+"");
				sysQueryItem.setInputName(dataMap.get("INPUT_NAME")+"");
				sysQueryItem.setName(dataMap.get("NAME")+"");
				sysQueryItem.setOperator(dataMap.get("OPERATOR")+"");
				if(dataMap.get("ORD")!=null){
					sysQueryItem.setOrd(Long.valueOf(dataMap.get("ORD")+""));
				}
				sysQueryItem.setQueryType(dataMap.get("QUERY_TYPE")+"");
				sysQueryItem.setRow(Long.valueOf(dataMap.get("ROW")+""));
				sysQueryItem.setRowspan(Long.valueOf(dataMap.get("ROWSPAN")+""));
				sysQueryItem.setValue(dataMap.get("VALUE")+"");
				if (dataMap.get("WIDTH")!=null && !dataMap.get("WIDTH").equals("")) {
					sysQueryItem.setWidth(Long.valueOf(dataMap.get("WIDTH")+""));
				}
				if (dataMap.get("X")!=null && !dataMap.get("X").equals("")) {
					sysQueryItem.setX(Long.valueOf(dataMap.get("X")+""));
				}
				sysQueryItemList.add(sysQueryItem);
			}
			sysQueryItemDao.addBatch(sysQueryItemList);
		}
    }
    
    public List<SysQueryItem> getListByJoinType(long joinId,String joinType){
    	
    	Map<String,Object> map = new HashMap<String, Object>();
    	map.put("joinId", joinId);
    	map.put("joinType", joinType);
    	return sysQueryItemDao.getListByJoinType(map);
    }
    
    
	/**
	 * 根据菜单的访问链接，得到对应验证
	 * @param request
	 */
	public String getConditionHtmlByRequest(HttpServletRequest request,long unitId){
		
    	SysMenu sysMenu = sysMenuService.getSysMenuByRequest(request);
    	if (sysMenu!=null && sysMenu.getIfQuery()==1) {
    		List<SysQueryItem> sysQueryItems = getListByJoinType(sysMenu.getId(), SysJoinType.MENU);
    		return getConditionHtml(sysQueryItems, unitId);
    	}
    	return "";
	}
	
	
	/**
	 * 根据编码的访问链接，得到对应验证
	 * @param request
	 */
	public String getConditionHtmlByCode(String typeCode,long unitId){
		
		String html = "";
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("code", typeCode);
		params.put("type", SysJoinType.QUERY);
		SysType sysType = sysTypeDao.getSysTypeByCode(params);
		if (null!=sysType) {
			
			List<SysQueryItem> sysQueryItems = getListByJoinType(sysType.getId(), SysJoinType.QUERY);
			html = getConditionHtml(sysQueryItems,unitId);
		}
		
    	return html;
	}
    
    
    
    /**
	 * 生成查询表单
	 * 方法说明：
	 * 传入的参数是否需要检查：需要
	 * 返回值是否需要检查：需要
	 * Aug 21, 2012
	 */
	private String getConditionHtml(List<SysQueryItem> sysQueryItems,long unitId){
		
		int labelWidth=80;
		StringBuffer sb=new StringBuffer();
		StringBuffer divsb=new StringBuffer();
		//form_name,field_name,input_name,data_type,query_type,operator
		String formatdiv="%s,%s,%s,%s,%s,%s";
		
		int beginrow=-1;
		int rowCount=sysQueryItems.size();
		for(int i=0;i<rowCount;i++)
		{
			SysQueryItem sysQueryItem = sysQueryItems.get(i);
			
			sysQueryItem.setunitId(unitId);
			
			String name = getStringFromObj( sysQueryItem.getName());
			String field_name = getStringFromObj( sysQueryItem.getFieldName());
			String form_name =getStringFromObj( sysQueryItem.getFormName());
			String input_name = getStringFromObj( sysQueryItem.getInputName());
			String 	data_type =getStringFromObj( sysQueryItem.getDataType());
			String query_type =getStringFromObj( sysQueryItem.getQueryType());
			String operator =getStringFromObj(sysQueryItem.getOperator());

			int row = Integer.parseInt(sysQueryItem.getRow()+"");
			int rowspan = Integer.parseInt(sysQueryItem.getRowspan()+"");

			if (divsb.length()==0)
				divsb.append(String.format(formatdiv, form_name,field_name,input_name,data_type,query_type,operator));
			else
				divsb.append(":"+String.format(formatdiv, form_name,field_name,input_name,data_type,query_type,operator));

			if (beginrow!=row){  //新的一行
				beginrow=row;
				if (sb.length()==0)
					sb.append("<table id='id-querytable' class='css-querytable' ><tr height='25px'>");
				else
					sb.append("</tr><tr  height='25px'>");
				String  htmlInput=getHtmlInput(sysQueryItem);
				sb.append(String.format("<td align='right' width='%dpx'  >%s:&nbsp;</td><td colspan='%d'  align='left'   >%s</td>",labelWidth,name,rowspan,htmlInput));
			}else{
				String  htmlInput=getHtmlInput(sysQueryItem);
				sb.append(String.format("<td align='right'   width='%dpx'  >%s:&nbsp;</td><td colspan='%d'  align='left' >%s</td>", labelWidth,name,rowspan,htmlInput));

			}

		}

		if (beginrow==-1) return "";
		sb.append("</tr></table>");
		return sb.toString()+String.format("<div style='display:none;' id='fields_queryinfo'>%s</div>", divsb.toString());

		//	return "";
	}
	
	
	/**
	 * 生成标记文本
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getHtmlInput(SysQueryItem sysQueryItem){

		StringBuffer html= new StringBuffer();
		String format=null;

		
		String name = getStringFromObj( sysQueryItem.getName());
		String field_name = getStringFromObj( sysQueryItem.getFieldName());
		String form_name =getStringFromObj( sysQueryItem.getFormName());
		String input_name = getStringFromObj( sysQueryItem.getInputName());
		String 	data_type =getStringFromObj( sysQueryItem.getDataType());
		String 	value =getStringFromObj( sysQueryItem.getValue());
		String datasource_type =getStringFromObj( sysQueryItem.getDatasourceType());
		String datasource_value = getStringFromObj( sysQueryItem.getDatasourceValue());
		String query_type =getStringFromObj( sysQueryItem.getQueryType());
		String operator =getStringFromObj(sysQueryItem.getOperator());

		int row = Integer.parseInt(sysQueryItem.getRow()+"");
		int rowspan = Integer.parseInt(sysQueryItem.getRowspan()+"");
		long width =sysQueryItem.getWidth();
		long x = sysQueryItem.getX();

		if("input".equals(query_type)){
			format="<input style='width:%dpx;' id='%s'  name='%s'   type='text'  value='%s' />";
			html.append(String.format(format, width,form_name,form_name,value));
		}else if("checkbox".equals(query_type)){

			html.append(getCheckBox(sysQueryItem));
		}else if ("radio".equals(query_type)){
			html.append(getRadio(sysQueryItem));
		}else if ("select".equals(query_type)){
			html.append(getSelect(sysQueryItem));
		}

		return html.length()==0?"":html.toString();
	}
	
	
	
	
	
	
	
	
	/**
	 * 生成checkbox
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getCheckBox(SysQueryItem sysQueryItem){

		StringBuffer html=new StringBuffer();
		String format="<input type='checkbox' name='%s' value='%s' />&nbsp;%s &nbsp;&nbsp;";

		
		String name = getStringFromObj( sysQueryItem.getName());
		String field_name = getStringFromObj( sysQueryItem.getFieldName());
		String form_name =getStringFromObj( sysQueryItem.getFormName());
		String input_name = getStringFromObj( sysQueryItem.getInputName());
		String 	data_type =getStringFromObj( sysQueryItem.getDataType());
		String 	value =getStringFromObj( sysQueryItem.getValue());
		String datasource_type =getStringFromObj( sysQueryItem.getDatasourceType());
		String datasource_value = getStringFromObj( sysQueryItem.getDatasourceValue());
		String query_type =getStringFromObj( sysQueryItem.getQueryType());
		String operator =getStringFromObj(sysQueryItem.getOperator());

		int row = Integer.parseInt(sysQueryItem.getRow()+"");
		int rowspan = Integer.parseInt(sysQueryItem.getRowspan()+"");
		long width =sysQueryItem.getWidth();
		long x = sysQueryItem.getX();
		
		
		List<ListItem>  list=null;
		if ("json".equals(datasource_type)){
			list=getDataFromJson(datasource_value);

		}else if ("dict".equals(datasource_type)){
			
			list=getDataFromDict(datasource_value,sysQueryItem.getunitId());
		}else if ("sql".equals(datasource_type)){
			list=getDataFromSql(datasource_value);
		}
		if (list==null) return "";
		for(int i=0;i<list.size();i++){
			ListItem item=list.get(i);	
			html.append(String.format(format, form_name,item.getValue(),item.getText()));
		}

		return html.toString();
	}

	/**
	 * 生成radio
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getRadio(SysQueryItem sysQueryItem){

		StringBuffer html=new StringBuffer();
		String format="<input type='radio' name='%s' value='%s' />&nbsp;%s &nbsp;&nbsp;";

		String name = getStringFromObj( sysQueryItem.getName());
		String field_name = getStringFromObj( sysQueryItem.getFieldName());
		String form_name =getStringFromObj( sysQueryItem.getFormName());
		String input_name = getStringFromObj( sysQueryItem.getInputName());
		String 	data_type =getStringFromObj( sysQueryItem.getDataType());
		String 	value =getStringFromObj( sysQueryItem.getValue());
		String datasource_type =getStringFromObj( sysQueryItem.getDatasourceType());
		String datasource_value = getStringFromObj( sysQueryItem.getDatasourceValue());
		String query_type =getStringFromObj( sysQueryItem.getQueryType());
		String operator =getStringFromObj(sysQueryItem.getOperator());

		int row = Integer.parseInt(sysQueryItem.getRow()+"");
		int rowspan = Integer.parseInt(sysQueryItem.getRowspan()+"");
		long width =sysQueryItem.getWidth();
		long x = sysQueryItem.getX();
		
		
		List<ListItem>  list=null;
		if ("json".equals(datasource_type)){
			list=getDataFromJson(datasource_value);

		}else if ("dict".equals(datasource_type)){
			list=getDataFromDict(datasource_value,sysQueryItem.getunitId());
		}else if ("sql".equals(datasource_type)){
			list=getDataFromSql(datasource_value);
		}
		if (list==null) return "";
		for(int i=0;i<list.size();i++){
			ListItem item=list.get(i);	
			html.append(String.format(format, form_name,item.getValue(),item.getText()));
		}
		return html.toString();
	}

	@SuppressWarnings("unused")
	private String getSelect(SysQueryItem sysQueryItem){
		StringBuffer html=new StringBuffer();


		String format="<option  value='%s' >%s</option>";

		String name = getStringFromObj( sysQueryItem.getName());
		String field_name = getStringFromObj( sysQueryItem.getFieldName());
		String form_name =getStringFromObj( sysQueryItem.getFormName());
		String input_name = getStringFromObj( sysQueryItem.getInputName());
		String 	data_type =getStringFromObj( sysQueryItem.getDataType());
		String 	value =getStringFromObj( sysQueryItem.getValue());
		String datasource_type =getStringFromObj( sysQueryItem.getDatasourceType());
		String datasource_value = getStringFromObj( sysQueryItem.getDatasourceValue());
		String query_type =getStringFromObj( sysQueryItem.getQueryType());
		String operator =getStringFromObj(sysQueryItem.getOperator());

		int row = Integer.parseInt(sysQueryItem.getRow()+"");
		int rowspan = Integer.parseInt(sysQueryItem.getRowspan()+"");
		long width =sysQueryItem.getWidth();
		long x = sysQueryItem.getX();
		
		
		List<ListItem>  list=null;
		if ("json".equals(datasource_type)){
			list=getDataFromJson(datasource_value);

		}else if ("dict".equals(datasource_type)){
			list=getDataFromDict(datasource_value,sysQueryItem.getunitId());
		}else if ("sql".equals(datasource_type)){
			list=getDataFromSql(datasource_value);
		}
		if (list==null) return "";
		for(int i=0;i<list.size();i++){
			if (i==0)
				html.append(String.format("<select id='%s' name='%s' >" , form_name,form_name));
			ListItem item=list.get(i);	
			html.append(String.format(format,item.getValue(),item.getText()));
		}

		if (html.length()>0)
			html.append("</select>");
		return html.toString();
	}

	/**
	 * Json 取值
	 * @param obj
	 * @return
	 */
	private List<ListItem> getDataFromJson(String datasource_value){
		List<ListItem> list=new ArrayList<ListItem>();

		List<Map<String,Object>> jsonList = JSONUtil.jsonToListMap(datasource_value);
		
		for (Map<String, Object> map : jsonList) {
			
			ListItem item=new ListItem();

			item.setValue(map.get("value")+"");        		         
			item.setText(map.get("text")+"");
			list.add(item);
		}
		return list;
	}

	/**
	 * 字典取值
	 * @param obj
	 * @return
	 */
	private List<ListItem> getDataFromDict(String datasource_value,long corId){
		List<ListItem> list=new ArrayList<ListItem>();
		String fsql="SELECT code as value,value text from  tb_sys_dict_detail where dict_main_id in   (select id from tb_sys_dict_main where dict_code='%s') ORDER BY ord";
		String sql=String.format(fsql, datasource_value);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("commSql", sql);

		List< Map<String,Object>> rows=sysCommUtilDao.getListMap(params);

		ListItem defaultItem=new ListItem();
		defaultItem.setText("");
		defaultItem.setValue("");
		list.add(defaultItem);
		
		for(int i=0;i<rows.size();i++){
			Map<String,Object> obj=rows.get(i);
			ListItem item=new ListItem();
			item.setText(obj.get("text").toString());
			item.setValue(obj.get("value").toString());
			list.add(item);
		}
		return list;
	}
	
	
	/**
	 * sql 取值
	 * @param obj
	 * @return
	 */
	private List<ListItem> getDataFromSql(String datasource_value){
		List<ListItem> list=new ArrayList<ListItem>();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("commSql", datasource_value);
		List< Map<String,Object>> rows=sysCommUtilDao.getListMap(params);

		for(int i=0;i<rows.size();i++){
			Map<String,Object> obj=rows.get(i);
			ListItem item=new ListItem();
			item.setText(obj.get("text").toString());
			item.setValue(obj.get("value").toString());
			list.add(item);
		}
		return list;
	}
	
	
	/**
	 * 转换null 到 ""
	 * @param value
	 * @return
	 */
	private String getStringFromObj(Object value){
		if (value==null)
			return "";
		return (String)value;
	
	}
}