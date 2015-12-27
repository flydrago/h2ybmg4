package com.h2y.bmg.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.h2y.bmg.entity.SysUnits;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.services.ISysButtonService;
import com.h2y.bmg.services.ISysCacheService;
import com.h2y.bmg.services.ISysGridColumnsService;
import com.h2y.bmg.services.ISysQueryItemService;
import com.h2y.bmg.services.ISysValidationService;
import com.h2y.bmg.basic.WbsKeys.SInvokeKeys;
import com.h2y.util.JSONUtil;
import com.h2y.util.PropertiesUtil;

/**
 * 基础Controller
 * 作者：段晓刚
 * 时间：2014-3-8 下午3:11:48
 * 电子邮件：@qq.com
 * Gmail :
 */
@Controller
public class BaseController {
	
	//按钮
    @Autowired
	protected ISysButtonService sysButtonService;

    //表格列
	@Autowired
	protected ISysGridColumnsService sysGridColumnsService;
	
	//验证
	@Autowired
	protected ISysValidationService sysValidationService;
	
	//查询
	@Autowired
	protected ISysQueryItemService sysQueryItemService;
	
	//缓存
	@Autowired
    protected ISysCacheService sysCacheService;

    protected DecimalFormat format = new DecimalFormat("0.00");
    protected HttpServletResponse response;
    protected HttpServletRequest request;
    protected HttpSession session;

    private Map<String, Object> mUserEntity = new HashMap<String, Object>();

    //存储支付宝配置信息
    private Map<String, String> alipayMap = new HashMap<String, String>();

    //获取招行支付配置信息
    private Map<String, String> cmbMap = new HashMap<String, String>();
    //url通知
    private Map<String, String> urlMap = new HashMap<String, String>();

    protected Charset utf_8 = Charset.forName("UTF-8");
    

    /**
     * 获取用户信息
     *
     * @return
     */
    protected Map<String, Object> getCurrentUser() {

        return null;
    }

    /**
     * 获取支付宝配置信息
     *
     * @return
     * @throws java.io.IOException
     * @update：2014年8月4日 下午4:37:38
     */
    protected Map<String, String> getAlipayConfing() throws IOException {

        if (alipayMap == null || alipayMap.isEmpty())
            alipayMap = PropertiesUtil.getInstance("/webconfig.properties").getMap();
        return alipayMap;
    }

    /**
     * 获取招行支付的配置信息
     *
     * @return
     * @throws java.io.IOException
     * @update：2014年8月4日 下午4:39:53
     */
    protected Map<String, String> getMCMBConfing() throws IOException {

        if (cmbMap == null || cmbMap.isEmpty())
            cmbMap = PropertiesUtil.getInstance("/webconfig.properties").getMap();
        return alipayMap;
    }

    /**
     * 获取URL配置信息
     *
     * @return
     * @throws java.io.IOException
     * @update：2014年8月4日 下午8:42:09
     */
    protected Map<String, String> getURLConfing() throws IOException {

        if (urlMap == null || urlMap.isEmpty())
            urlMap = PropertiesUtil.getInstance("/webconfig.properties").getMap();
        return urlMap;
    }

    /**
     * @ModelAttribute放置在方法上面：表示请求该类的每个Action前都会首先执行它， 你可以将一些准备数据的操作放置在该方法里面
     */
    @ModelAttribute
    public void setReqAndResp(HttpServletResponse response, HttpServletRequest request) {
        this.response = response;
        this.request = request;
        this.session = request.getSession();
    }

    protected void outJson(Object obj) {
        out(JSONUtil.getJson(obj));
    }

    /**
     * 输出数据
     *
     * @param value
     */
    protected void out(String value) {
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

    //	protected void outPrintln(String value){
    //		PrintWriter out = null;
    //		try {
    //			response.setContentType("text/html;charset=UTF-8");
    //			out = response.getWriter();
    //			out.println(JSON2Util.getJson(obj));
    //		} catch (IOException e) {
    //			e.printStackTrace();
    //		} finally{
    //			if(out != null){
    //				out.flush();
    //				out.close();
    //			}
    //		}
    //	}


    /**
     * 得到当前用户的单位Id
     * @return
     */
    protected long getLoginUnitId(){

        return sysCacheService.getLoginUnitId(session.getId());
    }

    /**
     * 得到登录用户的Id
     * @return
     */
    protected long getLoginUserId(){

        return sysCacheService.getLoginUserId(session.getId());
    }
    
    
    /**
     * 得到登录用户对象
     * @return
     */
    protected SysUser getLoginUser(){

    	return sysCacheService.getLoginUser(session.getId());
    }
    
    
    /**
     * 得到登录单位对象
     * @return
     */
    protected SysUnits getLoginUnit(){

    	return sysCacheService.getLoginUnit(session.getId());
    }
    
    /**
     * 得到当前系统角色
     * @return
     */
    protected long getLoginSysRoleId(){

    	 return sysCacheService.getLoginSysRoleId(session.getId());
    }
    
    /**
	 * 获取参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @update：2015年4月7日 下午1:45:43
	 */
	protected Map<String, Object> getReqMap() {

		Map<String, Object> reqMap = new HashMap<String, Object>();

		// 获取参数
		reqMap.put(SInvokeKeys.slock.value(),request.getParameter(SInvokeKeys.slock.value()));
		reqMap.put(SInvokeKeys.skey.value(),request.getParameter(SInvokeKeys.skey.value()));
		reqMap.put(SInvokeKeys.sid.value(),request.getParameter(SInvokeKeys.sid.value()));
		reqMap.put(SInvokeKeys.os.value(),request.getParameter(SInvokeKeys.os.value()));
		reqMap.put(SInvokeKeys.osv.value(),request.getParameter(SInvokeKeys.osv.value()));
		reqMap.put(SInvokeKeys.appv.value(),request.getParameter(SInvokeKeys.appv.value()));
		reqMap.put(SInvokeKeys.postData.value(),request.getParameter(SInvokeKeys.postData.value()));

		return reqMap;
	}
	
	/**
	 * 获取业务参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @update：2015年4月7日 下午1:51:24
	 */
	protected Map<String, Object> getPostMap() {
		Map<String, Object> postMap = new HashMap<String, Object>();

		Map<String, Object> reqMap = getReqMap();

		if (reqMap != null) {
			Object _post = reqMap.get(SInvokeKeys.postData.value());
			System.out.println(_post);
			postMap = JSONUtil.getMap(_post+"");
		}

		return postMap;
	}
    
	/**
	 * 获取返回主体参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	protected Map<String, Object> getResultMap() {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> reqMap = getReqMap();

		if (reqMap != null) {
			resultMap.put(SInvokeKeys.slock.value(),reqMap.get(SInvokeKeys.slock.value()));
			resultMap.put(SInvokeKeys.skey.value(),reqMap.get(SInvokeKeys.skey.value()));
			resultMap.put(SInvokeKeys.sid.value(),reqMap.get(SInvokeKeys.sid.value()));
			resultMap.put(SInvokeKeys.os.value(),reqMap.get(SInvokeKeys.os.value()));
			resultMap.put(SInvokeKeys.osv.value(),reqMap.get(SInvokeKeys.osv.value()));
			resultMap.put(SInvokeKeys.appv.value(),reqMap.get(SInvokeKeys.appv.value()));
		}

		return resultMap;
	}
}