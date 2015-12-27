package com.h2y.bmg.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.services.IFileDownService;
import com.h2y.security.Base64Util;
import com.h2y.spring.IocUtil;


/**
 * 文件操作控制

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/common/file")
@Scope("prototype")
public class FileDownController{

	private final static Logger logger = Logger.getLogger(FileDownController.class);

	/**
	 * 下载部分
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/down")
	public void down(HttpServletRequest request, HttpServletResponse response){
		
		//组合文件信息的服务类名称
		String fileBean = request.getParameter("fileBean");
		String id = request.getParameter("id");
		
		FileInputStream fis = null;
		BufferedInputStream bis = null ;
		ServletOutputStream servletOutputStream = null;
		BufferedOutputStream bos = null ;
		
		try {
			
			
			FileDownMode fileDownMode = new FileDownMode();
			if (fileBean!=null) {
				IFileDownService fileDownService = (IFileDownService)IocUtil.getBean(fileBean);
				fileDownMode = fileDownService.getFileInfo(request, id);
			}else {
				
				// 服务器相对路径
				String path = request.getParameter("path");
				String saveName = request.getParameter("saveName");
				//解密
				path = Base64Util.decodeBytesInAndroid(path);
				fileDownMode.setFilePath(path);
				fileDownMode.setSaveName(saveName);
			}
			// 服务器绝对路径
			//path = getServletContext().getRealPath("/") + path;

			response.setContentType("text/html;charset=UTF-8" );
			
			File file = new File(fileDownMode.getFilePath());
			// 检查文件是否存在
			if(!file.exists()){
				out(response, "指定文件不存在！");
				return;
			}
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			long fileSize  = file.length();
			
			//如果存储名称不指定，默认使用路径中的文件名
			String saveName = fileDownMode.getSaveName();
			if(!StringUtils.isNotBlank(saveName)){
				
				String path = fileDownMode.getFilePath();
				if(path!=null){
					int index = path.lastIndexOf("\\"); // 前提：传入的path字符串以“\”表示目录分隔符
					saveName = path.substring(index + 1 );
					if(saveName.contains("/"))
						saveName = saveName.substring(saveName.lastIndexOf("/")+1);
				}
			}
			
			
			//文件存储名称gbk编码
			String fileName = new String(saveName.getBytes("GBK"), "ISO-8859-1");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition","attachment;filename="+fileName);
			response.addHeader("Content-Length", fileSize+"" );//设置大小
			response.setContentLength((int) fileSize);

			servletOutputStream = response.getOutputStream();
			//bis = new BufferedInputStream( new FileInputStream(path));
			bos = new BufferedOutputStream(servletOutputStream);
			byte[] buff = new byte[1024*8];
			int bytesRead;
			while (-1!=(bytesRead=bis.read(buff,0,buff.length))) {
				bos.write(buff, 0 , bytesRead);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			out(response, "出现异常！");
		} finally {
			try {
				if(bis!=null)
					bis.close();
				if(fis!=null)
					fis.close();
				if(bos!=null)
					bos.close();
				//此处的流不能关闭在bos之前否则在tomcat下下载会出现问题
				if(servletOutputStream!=null)
					servletOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
    /**
     * 输出数据
     *
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
