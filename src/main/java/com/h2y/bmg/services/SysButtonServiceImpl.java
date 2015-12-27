package com.h2y.bmg.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.h2y.bmg.dao.ISysButtonDao;
import com.h2y.bmg.dao.ISysPrivilegeListDao;
import com.h2y.bmg.entity.SysButton;
import com.h2y.bmg.entity.SysMenu;
import com.h2y.bmg.entity.SysUser;

/**
 * ServiceImpl
 *
 * @author hwttnet
 *         version:1.2
 *         time:2014-10-16
 *         email:info@hwttnet.com
 */
@Service("sysButtonService")
public class SysButtonServiceImpl implements ISysButtonService {


    @Autowired
    protected ISysButtonDao sysButtonDao;

    @Autowired
    protected ISysMenuService sysMenuService;
    
    @Autowired
    protected ISysPrivilegeListDao sysPrivilegeListDao;

    /**
     * Add data, pay attention to the path of the gain of algorithm
     *
     * @param sysButton
     */
    public void add(SysButton sysButton) {
        // TODO Auto-generated method stub

        sysButtonDao.add(sysButton);
    }


    public void delete(Long id) {
        // TODO Auto-generated method stub
        sysButtonDao.deleteById(id);
    }

    public void deleteByIds(List<Long> ids) {
        sysButtonDao.deleteByIds(ids);
    }

    public void update(SysButton sysButton) {
        // TODO Auto-generated method stub
        sysButtonDao.update(sysButton);
    }

    public SysButton get(Long id) {
        // TODO Auto-generated method stub
        return sysButtonDao.get(id);
    }


    public List<SysButton> getList(SysButton sysButton) {
        List<SysButton> list = sysButtonDao.getList(sysButton);
        //
        //sysButton = null;

        return list;
    }


    /**
     * getListPage
     *
     * @return
     */
    public List<SysButton> getListPage(Map<String, Object> map) {
        //map.put("aaa", new SysButton());
        return sysButtonDao.getListPage(map);
    }

    /**
     * getRows
     *
     * @param map
     * @return
     */
    public Long getRows(Map<String, Object> map) {

        return sysButtonDao.getRows(map);
    }


    /**
     * 得到菜单的按钮列表
     *
     * @param menuId
     * @return
     */
    public Map<String, Object> getGridData(long menuId) {

        Map<String, Object> gridData = new HashMap<String, Object>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuId", menuId);
        List<Map<String, Object>> dataList = sysButtonDao.getListMap(map);
        if (dataList == null) {
            dataList = new ArrayList<Map<String, Object>>();
        }
        gridData.put("Rows", dataList);
        gridData.put("Total", dataList.size());
        return gridData;

    }


    /**
     * 保存按钮数据
     *
     * @param menuId
     * @param dataList
     */
    public void saveButton(long menuId, List<Map<String, Object>> dataList) {

        //sysButtonDao.deleteByMenuId(menuId);

        List<SysButton> list = new ArrayList<SysButton>();
        
        List<Long> deletefilerIds = new ArrayList<Long>();
        
        if (dataList!=null && !dataList.isEmpty()) {

            for (Map<String, Object> data : dataList) {

                SysButton sysButton = new SysButton();
                sysButton.setMenuId(menuId);
                sysButton.setButtonName(data.get("BUTTON_NAME") + "");
                if (data.get("BUTTON_URL") != null && !data.get("BUTTON_URL").equals("")) {
                    sysButton.setButtonUrl(data.get("BUTTON_URL") + "");
                }

                sysButton.setButtonIcon(data.get("BUTTON_ICON") + "");

                if (data.get("BUTTON_CLICK") != null && !data.get("BUTTON_CLICK").equals("")) {
                    sysButton.setButtonClick(data.get("BUTTON_CLICK") + "");
                }
                if (data.get("BUTTON_ORD") != null && !data.get("BUTTON_ORD").equals("")) {
                    sysButton.setButtonOrd(Long.valueOf(data.get("BUTTON_ORD") + ""));
                }
                if (data.get("IF_VISIBLE") != null && !data.get("IF_VISIBLE").equals("")) {
                    sysButton.setIfVisible(Long.valueOf(data.get("IF_VISIBLE") + ""));
                }
                if (data.get("IF_PUBLIC") != null && !data.get("IF_PUBLIC").equals("")) {
                    sysButton.setIfPublic(Long.valueOf(data.get("IF_PUBLIC") + ""));
                }
                if (data.get("IF_SYS") != null && !data.get("IF_SYS").equals("")) {
                    sysButton.setIfSys(Long.valueOf(data.get("IF_SYS") + ""));
                }
                
                if (data.get("ID")==null || data.get("ID").equals("")) {
                	list.add(sysButton);
				}else {
					deletefilerIds.add(Long.valueOf(data.get("ID")+""));
					sysButton.setId(Long.valueOf(data.get("ID")+""));
					sysButtonDao.update(sysButton);
				}
            }
            
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("menuId", menuId);
            if (!deletefilerIds.isEmpty()) {
            	params.put("filterIdList", deletefilerIds);
			}
            //删除
            sysButtonDao.deleteByMenuId(params);
            if (!list.isEmpty()) {
            	sysButtonDao.addBatch(list);
			}
        }
    }

    /**
     * 得到菜单的按钮
     * @param request
     * @param unitId
     * @param userId
     * @return
     */
    public String getMenuButtons(HttpServletRequest request,Long sysRoleId,SysUser sysUser){
    	
    	
    	 StringBuffer buttonStrBuffer = new StringBuffer("");
         
         SysMenu sysMenu = sysMenuService.getSysMenuByRequest(request);
         if (sysMenu!=null){
        	 
        	 List<SysButton> buttonList = new ArrayList<SysButton>();
        	 //系统菜单，并且为系统用户
        	 if (sysMenu.getIfSys()==1 && sysUser.getUnitId()==1 && sysUser.getIfSys()==1) {
        		 
            	 buttonList = sysButtonDao.getButtonByMenuId(sysMenu.getId());
             }else{
            	 
            	 List<Long> roleIds = sysPrivilegeListDao.getRoleIdsByUserId(sysUser.getId());
            	 if (roleIds!=null && !roleIds.isEmpty()) {
            		 Map<String,Object> buttonMap = new HashMap<String, Object>();
                     buttonMap.put("menuId",sysMenu.getId());
                     buttonMap.put("unitId", sysUser.getUnitId());
                     buttonMap.put("sysRoleId", sysRoleId);
                     buttonMap.put("roleList", roleIds);   
                     buttonList = sysButtonDao.getUserButtons(buttonMap);
            	 }
             }

             if (buttonList!=null && !buttonList.isEmpty()){

                 for (SysButton sysButton:buttonList){

                     if (!buttonStrBuffer.toString().equals("")){
                         buttonStrBuffer.append(",");
                     }
                     buttonStrBuffer.append(String.format("{ line:true },{ text: '%s' , click: %s , icon:'%s' }",
                                                 sysButton.getButtonName(),
                                                 sysButton.getButtonClick(),
                                                 sysButton.getButtonIcon()));
                 }
             }
         }
         return buttonStrBuffer.toString();
    }
}