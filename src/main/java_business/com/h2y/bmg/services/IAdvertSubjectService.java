package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.entity.AdvertSubject;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUnits;

/**
 * 项目名称：h2ybmg2  
 * 类名称：IAdvertSubjectService  
 * 类描述：广告主题业务操作接口  
 * 创建人：侯飞龙  
 * 创建时间：2015年4月8日 上午9:38:20  
 * 修改人：侯飞龙
 * 修改时间：2015年4月8日 上午9:38:20  
 * 修改备注：  
 * @version
 */
public interface IAdvertSubjectService{
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param unitId 单位Id
	 * @return
	 */
	public Map<String,Object> getGridData(HttpServletRequest request,SysUnits sysUnits);
	
	/**
	 * 得到表格数据
	 * @param request 访问对象
	 * @param subjectId 广告主题Id
	 * @return
	 */
	public Map<String,Object> getGoodsGridData(HttpServletRequest request,long subjectId);
	
	
	/**
	 * 广告主题保存
	 * @param request 访问对象
	 * @param op 操作类型
	 * @param advertSubject 广告主题对象
	 * @param sysDictMain 广告主题图片存储路径
	 */
	@Transactional(rollbackFor=Exception.class)
	public void save(HttpServletRequest request,String op,AdvertSubject advertSubject,SysDictMain sysDictMain);
	
	
	/**
	 * 广告主题保存
	 * @param request 访问对象
	 * @param op 操作类型
	 */
	@Transactional(rollbackFor=Exception.class)
	public void saveGoods(HttpServletRequest request,String op);
}
