package com.h2y.bmg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.h2y.bmg.entity.FileData;

/**
 * 类描述：商品图片数据库操作   
 * 作者：侯飞龙
 * 时间：2015年1月26日下午5:01:51
 * 邮件：1162040314@qq.com
 */
@Component
public interface IFileDataDao{

	/**
	 * add
	 */
	public void add(FileData fileData);

	/**
	 * update
	 */
	public void update(FileData fileData);

	/**
	 * delete
	 */
	public void deleteById(long id);

	/**
	 * get
	 * @return
	 */
	public FileData get(long id);

	/**
	 * 批量添加图片信息
	 * @param list
	 */
	public void addBatch(List<FileData> list);

	/**
	 * 更具商品版本号，得到对应的列表
	 * @param map
	 * {dataId:数据Id（dataType为0 商品Id、为1：定价Id）,
	 * dataVersion:数据版本号（dataType为0 商品版本号、为1：定价版本号）,
	 * dataType:数据类型：0：商品图片、1：定价商品图片}
	 * @return
	 */
	public List<Map<String,Object>> getListByGoodsVersion(Map<String,Object> map);

	/**
	 * 获取图片
	 * dataType:数据类型：0：商品图片、1：定价商品图片
	 * @param map
	 * @return
	 */
	public List<FileData> getFileListByGoodsVersion(Map<String,Object> map);


	/**
	 * 根据商品id，删除对应的图片
	 * @param map
	 * {goodsId：商品id,
	 * ids:删除要过滤的id null时，不做判断}
	 */
	public void deleteByGoodsId(Map<String,Object> map);
}