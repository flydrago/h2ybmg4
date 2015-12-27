package com.h2y.bmg.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.entity.SysButton;
import com.h2y.bmg.services.ISysButtonService;
import com.h2y.bmg.services.ISysMenuService;
import com.h2y.util.JSONUtil;

/**
 * 按钮管理url跳转控制类
 * <p/>
 * 作者：侯飞龙
 * <p/>
 * 时间：2014-10-13 上午11:31:51
 * <p/>
 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/sys/button")
public class SysButtonController extends BaseController {

    private static Logger logger = Logger.getLogger(SysButtonController.class);

    @Autowired
    protected ISysButtonService sysButtonService;

    @Autowired
    protected ISysMenuService sysMenuService;
    
    /**
     * 页面初始化;
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/init")
    public ModelAndView init() {
    	
//    	logger.info("学历列表："+DictUtil.getDetailListByMainCode(getLoginUnitId(), "xueli"));
//    	logger.info("学历对象："+DictUtil.getDetailByCode(getLoginUnitId(), "xueli", "dazhuan"));
//    	logger.info("学历列表："+DictUtil.getMainByCode("notice_type"));
//    	logger.info("学历列表："+DictUtil.getDetailListByMainCode(1, "notice_type"));
//    	logger.info("学历列表："+DictUtil.getDetailListByMainCode(1, "news_type"));
    	//调试短信
//    	sysSmsTempService.debugSms();
    	
    	
//    	SysSmsTemp sysSmsTemp = new SysSmsTemp();
//    	sysSmsTemp.setCreateDate(DateUtil.getSystemTime());
//    	sysSmsTemp.setMemo("测试");
//    	sysSmsTemp.setMobile("13838257740");
//    	sysSmsTemp.setModuleName("按钮测试");
//    	sysSmsTemp.setMsg("验证码：0");
//    	sysSmsTemp.setUnitId(0);
//    	sysSmsTemp.setUserId(0);
//    	System.out.println("发送短信返回："+sysSmsTempService.sendSms(sysSmsTemp));
    	
    	
//    	SysSmsTemp sysSmsTemp1 = new SysSmsTemp();
//    	sysSmsTemp1.setCreateDate(DateUtil.getSystemTime());
//    	sysSmsTemp1.setMemo("测试1");
//    	sysSmsTemp1.setMobile("13838257740");
//    	sysSmsTemp1.setModuleName("按钮测试1");
//    	sysSmsTemp1.setMsg("验证码：1");
//    	sysSmsTemp1.setUnitId(0);
//    	sysSmsTemp1.setUserId(0);
//    	
//    	
//       	SysSmsTemp sysSmsTemp2 = new SysSmsTemp();
//       	sysSmsTemp2.setCreateDate(DateUtil.getSystemTime());
//       	sysSmsTemp2.setMemo("测试2");
//       	sysSmsTemp2.setMobile("13838257740");
//       	sysSmsTemp2.setModuleName("按钮测试2");
//       	sysSmsTemp2.setMsg("验证码：2");
//       	sysSmsTemp2.setUnitId(0);
//       	sysSmsTemp2.setUserId(0);
//       	
//       	List<SysSmsTemp> list = new ArrayList<SysSmsTemp>();
//       	list.add(sysSmsTemp1);
//       	list.add(sysSmsTemp2);
//       	sysSmsTempService.sendBatchSms(list);
    	
    	
    	
        ModelAndView view = new ModelAndView();
        view.setViewName("sys/button/init");
        view.addObject("treedata", JSONUtil.getJson(sysMenuService.getButtonMenuTreeData()));
        return view;
    }


    /**
     * 获取列表
     *
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/getList")
    public void getList(@RequestParam String menuId) {

        outJson(sysButtonService.getGridData(Long.valueOf(menuId)));
    }


    /**
     * 保存
     *
     * @return
     */
    @RequestMapping(value = "/save")
    public void save(String menuId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("code", "1");
            map.put("msg", "保存成功！");
            String buttonData = request.getParameter("buttonData");
            List<Map<String, Object>> dataList = JSONUtil.jsonToListMap(buttonData);
            sysButtonService.saveButton(Long.valueOf(menuId), dataList);
            logger.debug("按钮数据：" + buttonData);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("code", "0");
            map.put("msg", "保存失败！");
        }
        outJson(map);
    }


    /**
     * 删除
     * @param sysButton
     * @return
     */
    @RequestMapping(value = "/delete")
    public void delete(@ModelAttribute SysButton sysButton) {

        Map<String, Object> map = new HashMap<String, Object>();

        String code = "1";
        String msg = "删除成功！";

        try {

            sysButtonService.delete(sysButton.getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            code = "0";
            msg = "删除失败！";
        }
        map.put("code", code);
        map.put("msg", msg);
        outJson(map);
    }

}
