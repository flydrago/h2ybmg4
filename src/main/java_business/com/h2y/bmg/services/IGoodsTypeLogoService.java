package com.h2y.bmg.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.h2y.bmg.entity.GoodsTypeLogo;
import com.h2y.bmg.entity.SysDictMain;
import com.h2y.bmg.entity.SysUser;

/**
 * 类描述：商品类型Logo的业务操作接口 作者：侯飞龙 时间：2015年1月7日上午9:59:01 邮件：1162040314@qq.com
 */
public interface IGoodsTypeLogoService {

	/**
	 * 得到表格数据
	 * 
	 * @param request
	 *            访问对象
	 * @return
	 */
	public Map<String, Object> getGridData(HttpServletRequest request);

	/**
	 * 保存操作
	 * 
	 * @param request
	 *            访问对象
	 * @param op
	 *            操作类型 （add：添加 modify：修改）
	 * @param goodsTypeLogo
	 *            类型Logo
	 * @param sysDictMain
	 *            存储路径信息
	 */
	public void save(HttpServletRequest request, String op,
			GoodsTypeLogo goodsTypeLogo, SysDictMain sysDictMain,
			SysUser sysUser);

}
