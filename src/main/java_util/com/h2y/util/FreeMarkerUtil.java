package com.h2y.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtil {

	private static Logger logger = Logger.getLogger(FreeMarkerUtil.class);

	/**
	 * @param dataMap 数据Map
	 * @param path 模板文件路径
	 * @param sourceDocument freemarker模板文件名称
	 * @return 经freemarker解析后的内容
	 */
	public static String DocumentHandler(Map<String,Object> dataMap,String path,String sourceDocument){
		Configuration configuration = null;
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		configuration.setClassForTemplateLoading(FreeMarkerUtil.class,path);
		Template t = null;
		try {
			t = configuration.getTemplate(sourceDocument);
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringWriter out = null;
		try {
			out = new StringWriter();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			t.process(dataMap, out);
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(out!=null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return out.getBuffer().toString();
	}


	/**
	 * @param dataMap 数据Map
	 * @param path 模板文件加载目录
	 * @param sourceDocument freemarker模板文件名称
	 * @return 经freemarker解析后的内容
	 */
	public static String getContentFromFileTemplate(Map<String,Object> dataMap,String path,String sourceDocument){
		Configuration configuration = null;
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		StringWriter out = null;
		try {

			configuration.setDirectoryForTemplateLoading(new File(path));
			Template t = configuration.getTemplate(sourceDocument);
			out = new StringWriter();
			t.process(dataMap, out);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		} finally {
			try {
				if(out!=null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return out.getBuffer().toString();
	}



	/**
	 * @param dataMap 数据Map
	 * @param path 模板文件加载目录
	 * @param sourceDocument freemarker模板文件名称
	 * @return 经freemarker解析后的内容
	 */
	public static String getContentFromStringTemplate(Map<String,Object> dataMap,String stringTemplate){

		Configuration configuration = null;
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		StringWriter out = null;
		try {
			
			configuration.setTemplateLoader(new StringTemplateLoader(stringTemplate));
			Template template = configuration.getTemplate(""); 
			out = new StringWriter();
			template.process(dataMap, out);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		} finally {
			try {
				if(out!=null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return out.getBuffer().toString();
	}


	public static void main(String[] args) {
//		Map<String,Object>  map = new HashMap<String, Object>();
//		map.put("TITLE", "sss");
//		List<Map<String,Object>> atts = new ArrayList<Map<String,Object>>();
//		Map<String,Object>  att = new HashMap<String, Object>();
//		att.put("FILENAME", "aaa");
//		att.put("DISKFILENAME", "D:/1111/222.xml");
//		map.put("noticeAttachs", atts);
//		String s = DocumentHandler(map,"", "notice.html");
//		System.out.println(s);
		
		Map<String,Object> root = new HashMap<String, Object>();
		root.put("user", "侯飞龙");
		
		List<Map<String,Object>> aihaolist = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> aihao1 = new HashMap<String, Object>();
		aihao1.put("name", "足球");
		
		Map<String,Object> aihao2 = new HashMap<String, Object>();
		aihao2.put("name", "篮球");
		aihaolist.add(aihao1);
		aihaolist.add(aihao2);
		root.put("aihaolist", aihaolist);
		System.out.println(getContentFromStringTemplate(root, "你好：${user}{$}<#list aihaolist as aihao>爱好：${aihao.name}\r\n</#list>"));
	}
}
