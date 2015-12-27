package com.h2y.bmg.services;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;


/**
 * 
 *   
 * 项目名称：h2ybmg2  
 * 类名称：IMSmsHisService  
 * 类描述：  短信发送记录查看服务接口类
 * 创建人：李剑 
 * 创建时间：2015年9月21日 上午11:41:25  
 * 修改人：李剑
 * 修改时间：2015年9月21日 上午11:41:25  
 * 修改备注：  如果你看到这个，那么说明你现在已经在负责我以前的项目了。我感到非常抱歉。愿上帝保佑你。
 * @version
 */
public interface IMSmsHisService{
	
	public Map<String, Object> getGridData(HttpServletRequest request);
	
}
