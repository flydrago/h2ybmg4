package com.h2y.bmg.controllers;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.services.IFileDownService;
import com.h2y.security.Base64Util;
import com.h2y.spring.IocUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @project:h2ymbs
 * @what:   图片显示
 * @author: duanxg
 * @update: 2014/10/19 9:22
 * @Email: 
 */
@Controller
@RequestMapping(value = "common/image")
@Scope("prototype")
public class ImageDownController{

    private Logger logger = Logger.getLogger(ImageDownController.class);

    private static final String GIF = "image/gif;charset=GB2312";
    //设定输出的类型
    private static final String JPG = "image/jpeg;charset=GB2312";

    private static final String PNG = "image/png;charset=GB2312";

    private static final String BMP = "image/bmp;charset=GB2312";

    
    /**
     * 图片显示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "view")
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getParameter("URLPATH");
        logger.debug("\nurlPath" + path);
        long fileSize = -1l;

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        String imageName = "";
        

        if (StringUtils.isNotBlank(path) && path.startsWith("http:")) {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //此连接的 URL 引用的资源的内容长度，或者如果内容长度未知，则返回 -1。
            fileSize = connection.getContentLength();
            try {
                bis = new BufferedInputStream(connection.getInputStream());

                URL url2 = connection.getURL();
                String path2 = url2.getPath();
                imageName = connection.getHeaderField(path2.substring(path2.lastIndexOf("/") + 1));
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } else {
        	
    		
    		//组合文件信息的服务类名称
    		String fileBean = request.getParameter("fileBean");
    		String id = request.getParameter("id");
    		
    		FileDownMode fileDownMode = new FileDownMode();
			if (fileBean!=null) {
				IFileDownService fileDownService = (IFileDownService)IocUtil.getBean(fileBean);
				fileDownMode = fileDownService.getFileInfo(request, id);
			}else {
				// 服务器相对路径
				fileDownMode.setFilePath(Base64Util.decodeBytesInAndroid(path));
			}
        	
			//解密
            File file = new File(fileDownMode.getFilePath());
            if (!file.exists())
                return;
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            fileSize = file.length();
            imageName = file.getName();
        }

        //在浏览器界面展示图片
        printImage(response, bis, fileSize, imageName);
        fis.close();
    }


    /**
     * 展示图片
     *
     * @param imageName
     * @param fileSize
     * @throws IOException
     */
    private void printImage(HttpServletResponse response, BufferedInputStream bis, long fileSize, String imageName) throws IOException {

        if (bis != null) {
            logger.debug("image print begin");
            //输出图片的类型的标志　　　　
            //得到输出流
            OutputStream output = response.getOutputStream();
            //使用编码处理文件流的情况：
            String imp = imageName.toLowerCase();

            //设定输出的类型
            if (imp.endsWith(".jpg") || imp.endsWith(".jpeg")) {
                //设定输出的类型
                response.setContentType(JPG);
            } else if (imp.endsWith(".gif")) {
                response.setContentType(GIF);
            } else if (imp.endsWith(".png")) {
                response.setContentType(PNG);
            } else if (imp.endsWith(".bmp")) {
                response.setContentType(BMP);
            } else {
                response.setContentType(JPG);
            }

            response.setHeader("fileName", imageName);
            response.addHeader("fileLength", fileSize + "");//设置大小

            //输入缓冲流
            BufferedOutputStream bos = new BufferedOutputStream(output);
            //输出缓冲流
            byte data[] = new byte[4096];
            //缓冲字节数
            int size = 0;
            size = bis.read(data);
            while (size != -1) {
                bos.write(data, 0, size);
                size = bis.read(data);
            }

            if (bis != null)
                bis.close();
            //清空输出缓冲流
            bos.flush();

            if (bos != null)
                bos.close();

            //关闭文件流
            if (bis != null)
                bis.close();
            if (output != null)
                output.close();
            logger.debug("print file end");
        }
    }
}
