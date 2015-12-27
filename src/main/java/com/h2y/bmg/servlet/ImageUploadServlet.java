package com.h2y.bmg.servlet;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.h2y.bmg.entity.Result;
import com.h2y.dict.DictUtil;
import com.h2y.util.JSONUtil;

public class ImageUploadServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setHeader("P3P","CP=CAO PSA OUR"); 
		String contentType = request.getContentType();
		PrintWriter out = response.getWriter();
		String showUrl=DictUtil.getMainByCode("imageshowroot_url").getDictValue();
		String lOrP="0";
		String type=request.getParameter("type");
		if("fwb".equals(type)){
			uploadFwb(request,response,out);
			return;
		}
		String uploadBasePath=DictUtil.getMainByCode("fileuploadroot_path").getDictValue();
		if ( contentType.indexOf("multipart/form-data") >= 0 ){
			try {
				Result result = new Result();
				result.avatarUrls = new ArrayList();
				result.success = false;
				result.msg = "Failure!"; 
                String tempFod="temp";
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				FileItemIterator fileItems = upload.getItemIterator(request);
				//定义一个变量用以储存当前头像的序号
				int avatarNumber = 1;
				//取服务器时间确保文件名无重复。
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS"); 
				String fileName = simpleDateFormat.format(new Date());
				uploadBasePath+=tempFod+File.separator+fileName+File.separator;
				//基于原图的初始化参数
				String initParams = "";
				BufferedInputStream	inputStream;
				BufferedOutputStream outputStream;
				
				File file = new File(uploadBasePath);
				if (!file.exists()) {
					 file.mkdirs();
			    }
				//遍历表单域
				while( fileItems.hasNext() )
				{
					FileItemStream fileItem = fileItems.next();
					String fieldName = fileItem.getFieldName();
					//是否是原始图片 file 域的名称（默认的 file 域的名称是__source，可在插件配置参数中自定义。参数名：src_field_name）
					Boolean isSourcePic = fieldName.equals("__source");
					 
					//修改头像时设置默认加载的原图url为当前原图url+该参数即可，可直接附加到原图url中储存，不影响图片呈现。
					if ( fieldName.equals("__initParams") )
					{
						inputStream = new BufferedInputStream(fileItem.openStream());
						byte[] bytes = new byte [inputStream.available()];
						inputStream.read(bytes); 
						initParams = new String(bytes, "UTF-8");
						inputStream.close();
					}
					//如果是原始图片 file 域的名称或者以默认的头像域名称的部分“__avatar”打头
					else if (isSourcePic || fieldName.startsWith("__avatar") || fieldName.startsWith("imgFile")){
						
						//文件扩展名
						String fileExt = fileItem.getName().substring(fileItem.getName().lastIndexOf(".") + 1).toLowerCase();
						SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssS");
						String lastName=df.format(new Date());
						String virtualPath="";
						//原始图片（默认的 file 域的名称是__source，可在插件配置参数中自定义。参数名：src_field_name）。
						if( isSourcePic )
						{
							result.sourceUrl = virtualPath = File.separator+"source_" + fileItem.getName();
						}
						//头像图片（默认的 file 域的名称：__avatar1,2,3...，可在插件配置参数中自定义，参数名：avatar_field_names）。
						else
						{
							if(fieldName.startsWith("__avatar")){
								virtualPath = File.separator+"logo" + avatarNumber + "_" + lastName+".jpg";
							}
							//针对上传商品图片
							if(fieldName.startsWith("imgFile")){
								virtualPath = File.separator+"pic" + avatarNumber + "_" + lastName+"."+fileExt;
								lOrP="1";
							}
							avatarNumber++;
						}
						inputStream = new BufferedInputStream(fileItem.openStream());
						
						outputStream = new BufferedOutputStream(new FileOutputStream(file.getPath() + virtualPath));
						Streams.copy(inputStream, outputStream, true);
						result.avatarUrls.add(file.getPath() + virtualPath);
						inputStream.close();
			            outputStream.flush();
			            outputStream.close();
			            if("1".equals(lOrP)){
			            	String returnUrl=showUrl+"?path="+file.getPath() + virtualPath;
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("error", 0);
							map.put("url", file.getPath() + virtualPath);
							map.put("surl", returnUrl);
							out.println(JSONUtil.getJson(map));
						}
					}	
				}
				if ( result.sourceUrl != null )
				{
					result.sourceUrl += initParams;
				}
				
				if("0".equals(lOrP)){
					result.success = true;
					result.msg = "Success!";
					result.error="0";
					out.print(JSONUtil.getJson(result));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	//保存富文本中上传的文件
	public void uploadFwb(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
		String savePath=DictUtil.getMainByCode("fwbuploadroot_path").getDictValue()+ "attached"+"/";
		String showUrl=DictUtil.getMainByCode("imageshowroot_url").getDictValue();
		System.out.print(savePath);
		//文件保存目录URL
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		//最大文件大小
		long maxSize = 1000000;
		response.setContentType("text/html; charset=UTF-8");
		if(!ServletFileUpload.isMultipartContent(request)){
			out.println(getError("请选择文件。"));
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
			uploadDir.mkdirs();
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			out.println(getError("上传目录没有写权限。"));
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
			out.println(getError("目录名不正确。"));
			return;
		}
		//创建文件夹
		savePath += dirName + "/";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		int num=0;
		try {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();
				long fileSize = item.getSize();
				if (!item.isFormField()) {
					num++;
					//检查文件大小
					if(item.getSize() > maxSize){
						out.println(getError("上传文件大小超过限制。"));
						return;
					}
					//检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
						out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
						return;
					}

					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssS");
					String newFileName = "pic"+num+"_"+ df.format(new Date())+"." + fileExt;
					File uploadedFile=null;
					try{
						 uploadedFile = new File(savePath+"/"+newFileName);
						item.write(uploadedFile);
  					}catch(Exception e){
						out.println(getError("上传文件失败。"));
						return;
					}
					String returnUrl=showUrl+"?path="+uploadedFile;
					Map<String,Object> map = new HashMap<String, Object>();
					//JSONObject obj = new JSONObject();
					map.put("error", 0);
					map.put("url", returnUrl);
					out.println(JSONUtil.getJson(map));
				}

				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private String getError(String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		//JSONObject obj = new JSONObject();
		map.put("error", 1);
		map.put("message", message);
		return JSONUtil.getJson(map);
	}
	

}
