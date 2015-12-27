package com.h2y.bmg.services;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.h2y.bmg.dao.IGoodsMarkInfoDao;
import com.h2y.bmg.entity.FileDownMode;
import com.h2y.bmg.entity.GoodsMarkInfo;
import com.h2y.security.Base64Util;
import com.h2y.util.DateUtil;
import com.h2y.util.JSONUtil;
import com.h2y.util.PinyinUtil;

/**
 * 类描述：商品标签详细的业务操作接口
 * 作者：侯飞龙
 * 时间：2015年1月7日上午9:59:28
 * 邮件：1162040314@qq.com
 */
@Service("goodsMarkInfoService")
public class GoodsMarkInfoServiceImpl implements IGoodsMarkInfoService,IFileDownService{

	@Autowired
	protected IGoodsMarkInfoDao goodsMarkInfoDao;

	/**
	 * 获取列表
	 */
	public Map<String, Object> getGridData(HttpServletRequest request) {

		Map<String,Object> gridData = new HashMap<String, Object>();

		String page = request.getParameter("page");
		String pagesize = request.getParameter("pagesize");
		String sortname = request.getParameter("sortname");
		String sortorder = request.getParameter("sortorder");
		String markId = request.getParameter("markId");
		//查询条件
		String ifQuery = request.getParameter("ifQuery");
		if (sortname!=null && !sortname.equals("")) {

			sortname = sortname.toLowerCase();
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("page",Integer.parseInt(page));
		map.put("pagesize",Integer.parseInt(pagesize));
		map.put("sortorder", sortorder);
		map.put("sortname", sortname);
		map.put("markId", markId);
		map.put("ifQuery", ifQuery);
		List<Map<String,Object>> dataList = goodsMarkInfoDao.getListMap(map);

		if (dataList==null) {
			dataList = new ArrayList<Map<String,Object>>();
		}
		gridData.put("Rows", dataList);
		gridData.put("Total", goodsMarkInfoDao.getListRows(map));
		return gridData;
	}

	/**
	 * 保存
	 */
	@Transactional(rollbackFor = Exception.class)
	public void save(HttpServletRequest request,String op,GoodsMarkInfo goodsMarkInfo,String dictPath) {

		//获取上传文件
		String [] file_array = request.getParameterValues("goodsMarkInfo_file");

		if (op.equals("add")) {
			//处理图片
			setFileData(file_array,goodsMarkInfo,dictPath);

			goodsMarkInfo.setCreateDate(DateUtil.getSystemTime());
			goodsMarkInfo.setFirSpellName(PinyinUtil.getPinYinHeadChar(goodsMarkInfo.getName()));
			goodsMarkInfo.setSpeName(PinyinUtil.getPinYin(goodsMarkInfo.getName()));
			goodsMarkInfoDao.add(goodsMarkInfo);
		}else {

			GoodsMarkInfo goodsMarkInfo2 = goodsMarkInfoDao.get(goodsMarkInfo.getId());

			//若图片未修改
			goodsMarkInfo.setFileName(goodsMarkInfo2.getFileName());
			goodsMarkInfo.setFileSize(goodsMarkInfo2.getFileSize());
			goodsMarkInfo.setFileSuffix(goodsMarkInfo2.getFileSuffix());
			goodsMarkInfo.setDiskFileName(goodsMarkInfo2.getDiskFileName());
			goodsMarkInfo.setRelativePath(goodsMarkInfo2.getRelativePath());
			goodsMarkInfo.setRootPath(goodsMarkInfo2.getRootPath());

			//处理图片
			setFileData(file_array,goodsMarkInfo,dictPath);

			goodsMarkInfo.setUpdateDate(DateUtil.getSystemTime());
			goodsMarkInfo.setCreateDate(goodsMarkInfo2.getCreateDate());
			goodsMarkInfo.setFirSpellName(PinyinUtil.getPinYinHeadChar(goodsMarkInfo.getName()));
			goodsMarkInfo.setSpeName(PinyinUtil.getPinYin(goodsMarkInfo.getName()));
			goodsMarkInfoDao.update(goodsMarkInfo);
		}
	}




	/**
	 * 设置活动的图片信息
	 * @param file_array 上传文件信息
	 * @param findActivity 活动对象
	 * @param dictPath 存储路径
	 */
	private void setFileData(String [] file_array,GoodsMarkInfo goodsMarkInfo,String dictPath){

		//设置活动图片
		if (null!=file_array && file_array.length >0) {

			String fileJsonData = file_array[0];
			Map<String,Object> map = JSONUtil.getMap(fileJsonData);
			if (map.get("id")==null) {//新增图片

				String saveName = map.get("saveName")+"";
				String savePath = "";
				try {
					savePath = Base64Util.decodeBytesInAndroid(map.get("savePath")+"");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				goodsMarkInfo.setDiskFileName(saveName);
				goodsMarkInfo.setFileName(map.get("fileName")+"");
				goodsMarkInfo.setFileSize(Long.valueOf(map.get("fileSize")+""));
				goodsMarkInfo.setFileSuffix(saveName.substring(saveName.lastIndexOf(".")+1, saveName.length()));
				goodsMarkInfo.setRootPath(dictPath);

				//日期作为相对路径
				SimpleDateFormat formatdate=new SimpleDateFormat("yyyy/MM/dd/");		
				String relative_path = formatdate.format(new Date());
				goodsMarkInfo.setRelativePath(relative_path);
				File filePath = new File(dictPath+relative_path);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				try {

					if (!new File(dictPath+relative_path+saveName).exists()) {
						FileUtils.copyFile(new File(savePath), new File(dictPath+relative_path+saveName));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}



	/**
	 * 显示图片
	 * @param request
	 * @param id
	 * @return
	 */
	public FileDownMode getFileInfo(HttpServletRequest request, String id) {

		FileDownMode fileDownMode = new FileDownMode();
		if (id!=null && !id.equals("")) {

			GoodsMarkInfo goodsMarkInfo = goodsMarkInfoDao.get(Long.valueOf(id));
			String fileName = goodsMarkInfo.getDiskFileName();
			fileDownMode.setFilePath(goodsMarkInfo.getRootPath()+goodsMarkInfo.getRelativePath()+fileName);
		}
		return fileDownMode;
	}



	/**
	 * 由标签名称查询  判断该标签是否重复
	 * @param goodsMark
	 * @return
	 */
	public int getSameMarkInfoName(GoodsMarkInfo goodsMarkInfo,String op) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("op", op);
		params.put("name", goodsMarkInfo.getName());
		params.put("id", goodsMarkInfo.getId());
		params.put("markId", goodsMarkInfo.getMarkId()); 
		return goodsMarkInfoDao.getSameMarkInfoName(params);
	}




}