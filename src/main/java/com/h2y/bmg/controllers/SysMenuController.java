package com.h2y.bmg.controllers;

import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.entity.SysMenu;
import com.h2y.bmg.services.ISysMenuService;
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
import java.util.Map;


/**
 * 菜单管理url跳转控制类

 * 作者：侯飞龙

 * 时间：2014-10-13 上午11:31:51

 * 电子邮件：1162040314@qq.com
 */
@Controller
@RequestMapping(value="/sys/menu")
@Scope("prototype")
public class SysMenuController extends BaseController{

	private static Logger logger = Logger.getLogger(SysMenuController.class);

    @Autowired
	protected ISysMenuService sysMenuService;
	
	
	/**
	 * 页面初始化;
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/init")
	public ModelAndView init(){

		ModelAndView view = new ModelAndView();
		view.setViewName("sys/menu/init");
		view.addObject("treedata", JSONUtil.getJson(sysMenuService.getTreeData()));
		return view;
	}
	
	/**
	 * 编辑初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@ModelAttribute SysMenu sysMenu,@RequestParam String op){

		ModelAndView view = new ModelAndView();
		if (op.equals("modify")) {
			sysMenu = sysMenuService.get(sysMenu.getId());
            view.addObject("prentMenuList",sysMenuService.getParentMenus(0));
		}
		view.addObject("sysMenu",sysMenu);
		view.addObject("op",op);
		view.setViewName("sys/menu/add");

		return view;
	}


    /**
     * 获取列表
     * @param op
     * @return
     */
	@RequestMapping(value="/getList")
	public void getList(@RequestParam String op){
		
		if (op.equals("tree")) {
			
			outJson(sysMenuService.getTreeData());
		}else if(op.equals("grid")) {
			
			String pid = request.getParameter("pid");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("pid", Long.valueOf(pid));
            outJson(sysMenuService.getGridData(map));
		}
	}


    /**
     * 保存
     * @param sysMenu
     * @return
     */
	@RequestMapping(value="/save")
	public void save(@ModelAttribute SysMenu sysMenu,String op){

		Map<String,Object> map = new HashMap<String, Object>();
		try {
			map.put("code", "1");
			map.put("msg", "保存成功！");

            if(op.equals("add")){

                sysMenuService.add(sysMenu);
            }else{

                sysMenuService.update(sysMenu);
            }
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			map.put("code", "0");
			map.put("msg", "保存失败！");
		}
        outJson(map);
	}


    /**
     * 删除
     * @param sysMenu
     * @return
     */
    @RequestMapping(value="/delete")
    public void delete(@ModelAttribute SysMenu sysMenu){

        Map<String,Object> map = new HashMap<String, Object>();

        String code = "1";
        String msg = "删除成功！";

        try {

            if (sysMenuService.isHasChildMenu(sysMenu.getId())){
                code = "0";
                msg = "当前菜单下面有子级菜单，不可删除！";
            }else{
                sysMenuService.delete(sysMenu.getId());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            code = "0";
            msg = "删除失败！";
        }
        map.put("code",code);
        map.put("msg",msg);
        outJson(map);
    }

}
