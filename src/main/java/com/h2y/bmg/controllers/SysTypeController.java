package com.h2y.bmg.controllers;


import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.ISysTypeDao;
import com.h2y.bmg.entity.SysType;
import com.h2y.bmg.services.ISysTypeService;
import com.h2y.util.JSONUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统类型（列维护、查询、验证）管理url跳转控制类
 * <p/>
 * 作者：侯飞龙
 * <p/>
 * 时间：2014-10-13 上午11:31:51
 * <p/>
 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value = "/sys/type")
@Scope("prototype")
public class SysTypeController extends BaseController {


    private static Logger logger = Logger.getLogger(SysTypeController.class);

    @Autowired
    protected ISysTypeService sysTypeService;

    @Autowired
    protected ISysTypeDao sysTypeDao;


    /**
     * 页面初始化;
     *
     * @param type 系统类型 menu:菜单、grid:列维护住白哦
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/init")
    public ModelAndView init(@RequestParam String type) {

        ModelAndView view = new ModelAndView();
        view.setViewName("sys/type/init");
        view.addObject("type",type);

        List<Map<String, Object>> treeList = sysTypeService.getChildTreeData(type, 0l);

        Map<String, Object> rootMap = new HashMap<String, Object>();
        rootMap.put("id", 0);
        rootMap.put("text", "类型");
        treeList.add(rootMap);

        view.addObject("treedata", JSONUtil.getJson(treeList));
        return view;
    }


    /**
     * 页面初始化;
     *
     * @param sysType 系统类型 menu:菜单、grid:列维护住白哦
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    public ModelAndView init(@ModelAttribute SysType sysType,@RequestParam String op) {

        ModelAndView view = new ModelAndView();

        if (op.equals("modify")) {
            sysType = sysTypeService.get(sysType.getId());
        }
        view.addObject("sysType",sysType);
        view.addObject("op",op);
        view.setViewName("sys/type/add");
        return view;
    }


    /**
     * 获取列表
     *
     * @param pid     关联Id
     * @param type 关联类型
     */
    @RequestMapping(value = "/getList")
    public void getList(@RequestParam String type, @RequestParam long pid) {

        outJson(sysTypeService.getGridData(type, pid));
    }


    /**
     * 保存
     *
     * @return
     */
    @RequestMapping(value = "/save")
    public void save(@ModelAttribute SysType sysType,@RequestParam String op) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("code", "1");
            map.put("msg", "保存成功！");

            if (sysTypeService.isHasSameCode(op, sysType)){
                map.put("code", "0");
                map.put("msg", "编码重复，请用其他编码！");
            }else{
                if (op.equals("add")){
                    sysTypeService.add(sysType);
                }else{
                    sysTypeService.update(sysType);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("code", "0");
            map.put("msg", "保存失败！");
        }
        outJson(map);
    }


    /**
     * 保存
     *
     * @return
     */
    @RequestMapping(value = "/delete")
    public void save(@ModelAttribute SysType sysType) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("code", "1");
            map.put("msg", "删除成功！");

            if (sysTypeService.isHasChild(sysType)){
                map.put("code", "0");
                map.put("msg", "当前类型有子级，不可删除！");
            }else{
               sysTypeService.delete(sysType.getId());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("code", "0");
            map.put("msg", "删除失败！");
        }
        outJson(map);
    }

}
