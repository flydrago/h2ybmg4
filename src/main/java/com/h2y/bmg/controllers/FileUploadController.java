package com.h2y.bmg.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.portlet.ModelAndView;

import com.h2y.bmg.entity.SysDictMain;
import com.h2y.dict.DictUtil;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.FileTool;
import com.h2y.util.JSONUtil;


/**
 * 文件操作控制

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/common/upload")
@Scope("prototype")
public class FileUploadController{

	private final static Logger logger = Logger.getLogger(FileUploadController.class);

	/**
	 * 文件上传初始化
	 * @return
	 */
	@RequestMapping(value="/uploadInit")
	public ModelAndView uploadInit(HttpServletRequest request){
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("common/upload/uploadInit");
		
		Enumeration<String> names = (Enumeration<String>) request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			request.setAttribute(name, request.getParameter(name));
		}
		return view;
	}
	
	/**
	 * Logo文件上传初始化
	 * @return
	 */
	@RequestMapping(value="/imageUploadInit")
	public ModelAndView imageUploadInit(HttpServletRequest request){
		
		ModelAndView view = new ModelAndView();
		
		view.setViewName("common/upload/imageUploadInit");
		Enumeration<String> names = (Enumeration<String>) request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			request.setAttribute(name, request.getParameter(name));
		}
		return view;
	}
	
	/**
	 * 文件上传
	 * @param myfiles 上传文件
	 * @param request 访问对象
	 */
	@RequestMapping(value="/doUpload", method=RequestMethod.POST)  
	public void upload(HttpServletRequest request, HttpServletResponse response){  

		List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		int i = 0;
		
		while (fileNames.hasNext()) {

			String name = (String) fileNames.next();//文件参数名称
			MultipartFile myfile = multipartRequest.getFile(name);
			if(myfile.isEmpty()){  
				logger.info("文件未上传");  
			}else{  

				String OriginalFileName = myfile.getOriginalFilename();
				String saveName = DateUtil.getSystemTime().getTime() +""+i+OriginalFileName.substring(OriginalFileName.lastIndexOf("."), OriginalFileName.length());

				
				SysDictMain sysDictMain = DictUtil.getMainByCode("fileuploadtemp_path");
				//如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中  
//				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
				
				if (sysDictMain==null || sysDictMain.getDictValue()==null) {
					out(response, "请维护文件上传暂存路径，编码为：fileuploadtemp_path");
					logger.warn("请维护文件上传暂存路径，编码为：fileuploadtemp_path");
					return;
				}
				
				//日期作为相对路径
				SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");		
				String relative_path = formatdate.format(new Date());
				String realPath = sysDictMain.getDictValue()+relative_path;

				File filePath = new File(realPath);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}

				try {

					FileTool.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}  

				Map<String,Object> fileData = new HashMap<String, Object>();
				fileData.put("fileSize", myfile.getSize());
				fileData.put("fileName", myfile.getOriginalFilename());
				fileData.put("saveName", saveName);
				try {
					fileData.put("savePath", Base64Util.encodeBytesInAndroid(realPath+"/"+saveName));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
				fileList.add(fileData);
			}  
			
			i++;
		}
		//响应客户端
		out(response, JSONUtil.getJson(fileList));
	}  
	
	
	/**
	 * 文件上传Logo
	 * @param myfiles 上传文件
	 * @param request 访问对象
	 */
	@RequestMapping(value="/doUploadImage", method=RequestMethod.POST)  
	public void uploadImage(HttpServletRequest request, HttpServletResponse response){  

		List<Map<String,Object>> fileList = new ArrayList<Map<String,Object>>();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> fileNames = multipartRequest.getFileNames();

		int i = 1;
		while (fileNames.hasNext()) {

			String name = (String) fileNames.next();//文件参数名称
			MultipartFile myfile = multipartRequest.getFile(name);
			if(myfile.isEmpty()){  
				logger.info("文件未上传");  
			}else{  

				String saveName = DateUtil.getSystemTime().getTime()+""+i+ ".jpg";
				SysDictMain sysDictMain = DictUtil.getMainByCode("fileuploadtemp_path");
				
				if (sysDictMain==null || sysDictMain.getDictValue()==null) {
					out(response, "请维护文件上传暂存路径，编码为：fileuploadtemp_path");
					logger.warn("请维护文件上传暂存路径，编码为：fileuploadtemp_path");
					return;
				}
				
				//日期作为相对路径
				SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");		
				String relative_path = formatdate.format(new Date());
				String realPath = sysDictMain.getDictValue()+relative_path;

				File filePath = new File(realPath);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}

				try {

					FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, saveName));
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}  
				
				Map<String,Object> fileData = new HashMap<String, Object>();
				fileData.put("fileSize", myfile.getSize());
				fileData.put("fileName", "logo"+i+".jpg");
				fileData.put("saveName", saveName);
				fileData.put("fileOrd", i);
				System.out.println("存储路径为："+realPath+"/"+saveName);
				try {
					fileData.put("savePath", Base64Util.encodeBytesInAndroid(realPath+"/"+saveName));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					logger.error(e.getMessage(), e);
				}
				fileList.add(fileData);
			}  
			i++;
		}
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("success", true);
		dataMap.put("dataList", fileList);
		
		//响应客户端
		out(response, JSONUtil.getJson(dataMap));
	}  
	
	/**
	 * 输出数据
	 * @param response
	 * @param value
	 */
	private void out(HttpServletResponse response,String value) {
		PrintWriter out = null;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.println(value);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
}
