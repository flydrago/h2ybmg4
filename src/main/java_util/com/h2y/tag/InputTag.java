package com.h2y.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.h2y.bmg.entity.SysDictDetail;
import com.h2y.bmg.services.ISysCacheService;
import com.h2y.dict.DictUtil;
import com.h2y.spring.IocUtil;

/**
 * 自定义标签（select、checkbox、radio）
 */
public class InputTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//元素id
	private String name;//元素名称
	private String type;//编辑类型
	private String dictcode;//字典编码
	private String title;//标题信息
	private String value;//元素值
	private String css;//元素class属性
	private String style;//元素style样式
	private Object initoption;//select 初始化选项value,text:value,text
	private String readonly;//只读属性
	private String onclick;//单击事件
	private String onchange;//事件
	private String disabled;//禁用

	private static String inputRadioF="<input type=\"radio\" id=\"%s\"  name=\"%s\"    value=\"%s\"   class=\"%s\"  title=\"%s\"  style=\"%s\"  %s    /><label for=\"%s\">%s</label>&nbsp;&nbsp;";
	private static String inputCheckboxF="<input type=\"checkbox\"  id=\"%s\"  name=\"%s\"    value=\"%s\"   class=\"%s\"  title=\"%s\"  style=\"%s\"   %s   /><label for=\"%s\">%s</label>&nbsp;&nbsp;";
	private static String inputSelectF="<select  id=\"%s\"  name=\"%s\" class=\"%s\"  title=\"%s\"  style=\"%s\"   %s    >";
	private static String selectOptionF="<option value=\"%s\" title=\"%s\"  %s>%s</option>";

	protected ISysCacheService sysCacheService = null;
	
	private void clear(){
		this.id=null;
		this.css=null;
		this.dictcode=null;
		this.name=null;
		this.style=null;
		this.title=null;
		this.type=null;
		this.onclick=null;
		this.initoption=null;
		this.readonly=null;
		this.onchange=null;
		this.disabled=null;
	}

	public int doEndTag() throws JspException {
		clear();
		return Tag.EVAL_PAGE;
	}

	public int doStartTag() throws JspException {
		
		sysCacheService = (ISysCacheService) IocUtil.getBean("sysCacheService");
		
		String htmlText=null;
		if ("checkbox".equals(this.getType())){
			htmlText = getCheckboxHtml();
		}else if ("radio".equals(this.getType())){
			htmlText = getRadioHtml();
		}else if ("select".equals(this.getType())){
			htmlText = getSelectHtml();
		}

		try {
			pageContext.getOut().write(htmlText);
		} catch (Exception e) {
			e.printStackTrace();
			throw new JspException("write   error!");
		}
		return SKIP_BODY;
	}


	/**
	 * 得到checkBox控件html
	 * @return
	 */
	public String getRadioHtml(){

		StringBuffer html=new StringBuffer();

		List<SysDictDetail> sysDictDetails = getListDictDetail();

		if (sysDictDetails!=null && !sysDictDetails.isEmpty()) {

			for (SysDictDetail sysDictDetail : sysDictDetails) {

				html.append(String.format(inputRadioF,
						this.getId()+"_"+sysDictDetail.getCode(),
						this.getName(),
						sysDictDetail.getCode(),
						this.getCss(),
						this.getTitle(),
						this.getStyle(),
						this.getValue().equals(sysDictDetail.getCode()) ? " checked=\"checked\" ":""+
						this.getOnclick()+this.getOnchange()+this.getReadonly()+this.getDisabled(),
						this.getId()+"_"+sysDictDetail.getCode(),
						sysDictDetail.getValue()
						));
			}
		}
		return html.toString();
	}


	/**
	 * 得到checkBox控件html
	 * @return
	 */
	public String getCheckboxHtml(){

		StringBuffer html=new StringBuffer();

		String[] vals=this.getValue().split(",");

		List<SysDictDetail> sysDictDetails = getListDictDetail();

		if (sysDictDetails!=null && !sysDictDetails.isEmpty()) {

			for (SysDictDetail sysDictDetail : sysDictDetails) {

				html.append(String.format(inputCheckboxF,
						this.getId()+"_"+sysDictDetail.getCode(),
						this.getName(),
						sysDictDetail.getCode(),
						this.getCss(),
						this.getTitle(),
						this.getStyle(),
						this.getCheckboxChecked(vals, sysDictDetail.getCode())+
						this.getOnclick()+this.getOnchange()+this.getReadonly()+this.getDisabled(),
						this.getId()+"_"+sysDictDetail.getCode(),
						sysDictDetail.getValue()
						));
			}
		}
		return html.toString();
	}

	/**
	 * 得到select控件html
	 * @return
	 */
	public String getSelectHtml(){

		StringBuffer html=new StringBuffer();

		html.append(String.format(inputSelectF, 
				this.getId(),
				this.getName(),
				this.getCss(),
				this.getTitle(),
				this.getStyle(),
				this.getOnclick()+this.getOnchange()+this.getReadonly()+this.getDisabled()
				));

		List<SysDictDetail> sysDictDetails = getListDictDetail();

		if (sysDictDetails!=null && !sysDictDetails.isEmpty()) {

			for (SysDictDetail sysDictDetail : sysDictDetails) {
				html.append(String.format(selectOptionF,
						sysDictDetail.getCode(),
						sysDictDetail.getValue(),
						this.getValue().equals(sysDictDetail.getCode()) ? "selected=\"selected\"":"",
						sysDictDetail.getValue()
						));
			}
		}
		return html.toString()+"</select>";
	}

	/**
	 * 得到复选框是否选中
	 * @param vals
	 * @param value
	 * @return
	 */
	private String getCheckboxChecked(String [] vals,String value){

		if (vals!=null && vals.length>0) {
			for (String string : vals) {
				if (string.equals(value)) {
					return " checked=\"checked\" ";
				}
			}
		}
		return "";
	}


	/**
	 * 获取字典列表项，一级初始化的选项
	 * @return
	 */
	private  List<SysDictDetail> getListDictDetail(){

		List<SysDictDetail> list = new ArrayList<SysDictDetail>(); 

		//添加自定义选项列表到集合中
		addOptionsToList(list);

		if (this.getDictcode()!=null && !this.getDictcode().equals("")) {
			list.addAll(DictUtil.getDetailListByMainCode(sysCacheService.getLoginUnitId(pageContext.getSession().getId()), this.getDictcode()));
		}
		return list;
	}

	/**
	 * 添加自定义选项列表到集合中
	 * @return
	 */
	private void addOptionsToList(List<SysDictDetail> list){

		//字符串
		if (this.getInitoption() instanceof String) {

			String temp=(String)this.initoption;
			String [] atemp=temp.split(":");		
			if (atemp!=null) {
				for(String astr:atemp){
					String[] items=astr.split(",");
					if (items.length==2){
						SysDictDetail sysDictDetail = new SysDictDetail();
						sysDictDetail.setCode(items[0]);
						sysDictDetail.setValue(items[1]);
						list.add(sysDictDetail);
					}
				}
			}
		}else if (this.getInitoption() instanceof ArrayList<?>) {//列表

			List<Map<String,Object>> initList = (ArrayList<Map<String,Object>>)this.initoption;
			for (Map<String,Object> obj : initList) {
				SysDictDetail sysDictDetail = new SysDictDetail();
				sysDictDetail.setCode(obj.get("value").toString());
				sysDictDetail.setValue(obj.get("text").toString());
				list.add(sysDictDetail);
			}
		}
	}

	public String processOnClick(){
		
		return " onclick=\""+onclick+"\" ";
	}

	public String processOnChange(){
		return css;
	}


	public String processReadonly(){
		return css;

	}

	public String processDisabled(){
		
		
		return css;
	}


	public String getId() {

		if (id==null) {
			return "";
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {

		if (name==null) {
			return "";
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {

		if (type==null) {
			return "";
		}
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getDictcode() {
		if (dictcode==null) {
			return "";
		}
		return dictcode;
	}
	public void setDictcode(String dictcode) {
		this.dictcode = dictcode;
	}

	public String getTitle() {
		if (title==null) {
			return "";
		}
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getValue() {

		if (value==null) {
			return "";
		}
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCss() {
		if (css==null) {
			return "";
		}
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}


	public String getStyle() {

		if (style==null) {
			return "";
		}
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}


	public String getOnclick() {

		if (StringUtils.isNotBlank(onclick)) {
			return " onclick=\""+onclick+"\" ";
		}
		return "";
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
	public Object getInitoption() {
		return initoption;
	}

	public void setInitoption(Object initoption) {
		this.initoption = initoption;
	}

	public String getReadonly() {

		if (StringUtils.isNotBlank(readonly) && readonly.equals("readonly")) {
			return  " readonly=\"readonly\" ";
		}
		return "";
	}
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}


	public String getOnchange() {

		if (StringUtils.isNotBlank(onchange)) {
			return  " onchange=\""+onchange+"\" ";
		}
		return "";
	}
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}


	public String getDisabled() {

		if (StringUtils.isNotBlank(disabled) && disabled.equals("disabled")) {
			return  " disabled=\"disabled\" ";
		}
		return "";
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

}
