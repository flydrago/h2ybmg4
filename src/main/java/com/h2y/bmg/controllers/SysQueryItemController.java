package com.h2y.bmg.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.services.ISysMenuService;
import com.h2y.bmg.services.ISysQueryItemService;
import com.h2y.bmg.services.ISysTypeService;
import com.h2y.bmg.util.SysBaseUtil.SysJoinType;
import com.h2y.util.JSONUtil;

/**
 * 查询维护url跳转控制类
 * 作者：侯飞龙
 * 时间：2014-10-13 上午11:31:51
 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value="/sys/queryitem")
@Scope("prototype")
public class SysQueryItemController extends BaseController{

    private static Logger logger = Logger.getLogger(SysQueryItemController.class);

    @Autowired
    protected ISysMenuService sysMenuService;

    @Autowired
    protected ISysTypeService sysTypeService;
    
    @Autowired
    protected ISysQueryItemService sysQueryItemService;
    
    /**
     * 页面初始化;
     * @param joinType 关联表类型 menu:菜单、grid:列维护住白哦
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/init")
    public ModelAndView init(@RequestParam String joinType) {

        ModelAndView view = new ModelAndView();
        view.setViewName("sys/queryitem/init");
        view.addObject("joinType",joinType);
        if(joinType.equals(SysJoinType.MENU)){
            view.addObject("treedata", JSONUtil.getJson(sysMenuService.getQueryMenuTreeData()));
        }else if (joinType.equals(SysJoinType.QUERY)){
            view.addObject("treedata", JSONUtil.getJson(sysTypeService.getTreeData(joinType)));
        }
        return view;
    }


    /**
     * 获取列表
     * @param joinId 关联Id
     * @param joinType 关联类型
     */
    @RequestMapping(value = "/getList")
    public void getList(@RequestParam long joinId,@RequestParam String joinType) {

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("joinId",joinId);
        map.put("joinType",joinType);

        outJson(sysQueryItemService.getGirdData(map));
    }


    /**
     * 保存
     * @return
     */
    @RequestMapping(value = "/save")
    public void save(@RequestParam long joinId,@RequestParam String joinType) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("code", "1");
            map.put("msg", "保存成功！");
            String queryItemData = request.getParameter("queryItemData");
            List<Map<String, Object>> dataList = JSONUtil.jsonToListMap(queryItemData);
            sysQueryItemService.saveQueryItem(joinId, joinType, dataList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            map.put("code", "0");
            map.put("msg", "保存失败！");
        }
        outJson(map);
    }
}
