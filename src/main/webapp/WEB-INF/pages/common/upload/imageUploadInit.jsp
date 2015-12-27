<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../include/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head> 
       <%@ include file="../../include/top_list.jsp"%>
        <script type="text/javascript" src="<%=uiPath%>lib/fullAvatarEditor/scripts/swfobject.js"></script>
        <script type="text/javascript" src="<%=uiPath%>lib/fullAvatarEditor/scripts/fullAvatarEditor.js"></script>
        <script type="text/javascript">
         var basePath=null;
         var imageListData = null;
	     $(function (){
	    	   basePath="<%=uiPath%>";
	     });
	     
	     function h2y_returnData(){
        	return imageListData;
         }
		</script>
    </head> 
    <body> 
         <div style="width:630px;margin: 0 auto;">
			 <input type="hidden" name="imageListData" id="imageListData"/>
			<div>
				<p id="swfContainer">
                                                          本组件需要安装Flash Player后才可使用，请从<a href="http://www.adobe.com/go/getflashplayer">这里</a>下载安装。
				</p>
			</div>
        </div>
		<script type="text/javascript">
            swfobject.addDomLoadEvent(function () {
                var swf = new fullAvatarEditor("swfContainer", {
					    id: 'swf',
						upload_url: '<%=basePath%>common/upload/doUploadImage.htm',
						src_upload:0,
						avatar_box_border_width:1,
						button_visible:true,
						avatar_intro:'最终会生成以下尺寸的图片，请注意是否清晰',
						avatar_scale:4
					}, function (msg) {
						
						switch(msg.code)
						{
							case 3 :
								if(msg.type == 0)
								{
									alert("摄像头已准备就绪且用户已允许使用。");
								}
								else if(msg.type == 1)
								{
									alert("摄像头已准备就绪但用户未允许使用！");
								}
								else
								{
									alert("摄像头被占用！");
								}
							break;
							case 5 : 
								if(msg.type == 0)
								{
									if(msg.content.sourceUrl)
									{
										alert("原图已成功保存至服务器，url为：\n" +msg.content.sourceUrl);
									}
									imageListData = msg.content.dataList;
									//$("#imageListData").val(JSON.stringify(msg.content.dataList));
									//alert(JSON.stringify(msg.content));
									//alert("商品LOGO已成功上传，url为：\n" + msg.content.avatarUrls.join("\n"));
								}
							break;
						}
					}
				);
            });
			var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
			document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F5f036dd99455cb8adc9de73e2f052f72' type='text/javascript'%3E%3C/script%3E"));
        </script>
    </body> 
</html> 
