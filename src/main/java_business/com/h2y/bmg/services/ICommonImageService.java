package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.CommonImage;
import com.h2y.bmg.entity.SysDictMain;

/**
 * 项目名称：h2ybmg2  
 * 类名称：ICommonImageService  
 * 类描述：  图片服务业务逻辑接口
 * 创建人：侯飞龙  
 * 创建时间：2015年5月8日 下午2:46:19  
 * 修改人：侯飞龙
 * 修改时间：2015年5月8日 下午2:46:19  
 * 修改备注：  
 * @version
 */
public interface ICommonImageService{
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,long unitId);
	
	/**
	 * 保存操作
	 * @param op add：添加 modify：修改
	 * @param commonImage 图片信息
	 * @param sysDictMain 图片信息存储路径
	 */
	public void save(HttpServletRequest request,String op,CommonImage commonImage,SysDictMain sysDictMain);
}
