package com.h2y.bmg.controllers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.h2y.util.JSONUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.h2y.bmg.basic.BaseController;
import com.h2y.bmg.dao.IVoteCandidateDao;
import com.h2y.bmg.dao.IVoteCandidateDetailDao;
import com.h2y.bmg.dao.IVoteCandidatePicDao;
import com.h2y.bmg.dao.IVoteItemDao;
import com.h2y.bmg.dao.IVoteSubjectDao;
import com.h2y.bmg.entity.SysUser;
import com.h2y.bmg.entity.VoteCandidate;
import com.h2y.bmg.services.ISysLogService;
import com.h2y.bmg.services.IVoteCandidateService;
import com.h2y.bmg.util.SysBaseUtil;
import com.h2y.bmg.util.SysBaseUtil.BusinessTableName;
import com.h2y.bmg.util.SysBaseUtil.OpRresult;
import com.h2y.bmg.util.SysBaseUtil.OpType;
/**
 * 
 *   
 * 项目名称：h2ybmg2  
 * 类名称：VoteCandidateController  
 * 类描述：  投票候选人跳转类
 * 创建人：李剑 
 * 创建时间：2015年9月2日 上午9:19:02  
 * 修改人：李剑
 * 修改时间：2015年9月2日 上午9:19:02  
 * 修改备注：  
 * @version
 */
@Controller
@RequestMapping(value = "/business/votecandidate")
@Scope("prototype")
public class VoteCandidateController extends BaseController{

	private final static Logger logger = Logger.getLogger(VoteCandidateController.class);
	//
	@Autowired
	protected ISysLogService sysLogService;

	@Autowired
	protected IVoteSubjectDao voteSubjectDao;

	@Autowired
	protected IVoteCandidateDetailDao voteCandidateDetailDao;

	@Autowired
	protected IVoteCandidatePicDao  voteCandidatePicDao;

	@Autowired
	protected IVoteCandidateService VoteCandidateService;

	@Autowired
	protected IVoteCandidateDao voteCandidateDao;

	@Autowired
	protected IVoteItemDao voteItemDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
	}

	@InitBinder("voteCandidate")    
	public void initBinder1(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("voteCandidate.");    
	}

	@InitBinder("voteCandidateDetail")    
	public void initBinder2(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("voteCandidateDetail.");    
	}

	@InitBinder("voteItem")    
	public void initBinder3(WebDataBinder binder) {    
		binder.setFieldDefaultPrefix("voteItem.");    
	}


	/**
	 * 编辑页面初始化
	 * @param votecandidate
	 * @param op 操作类型，添加、修改
	 * @return
	 */
	@RequestMapping(value = "/init")
	public ModelAndView init() {
		ModelAndView view = new ModelAndView();
		view.setViewName("business/votecandidate/init");
		SysUser sysUser = getLoginUser();
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("voteCandidate_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("voteCandidate_query", sysUser.getUnitId()));
		view.addObject("subId",request.getParameter("id"));
		return view;
	}



	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/getList")
	public void getList() {

		outJson(VoteCandidateService.getGridData(request, getLoginUnitId()));
	}

	/**
	 * 候选人删除
	 * @param voteCandidate
	 */
	@RequestMapping(value = "/delete")
	public 	void delete(@ModelAttribute VoteCandidate voteCandidate){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			VoteCandidate voteCandidate2 = voteCandidateDao.get(voteCandidate.getId());
			voteCandidate2.setStatus(-1);
			voteCandidateDao.update(voteCandidate2);
			map.put("code", "1");
			map.put("msg", "删除成功！");
			sysLogService.addLog(request, getLoginUser(), "投票候选人", OpType.delete+"", 
					OpRresult.success+"", "投票候选人删除成功！", voteCandidate.getId()+"", BusinessTableName.candidate.name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "0");
			map.put("msg", "删除失败！");
			sysLogService.addLog(request, getLoginUser(), "投票候选人", OpType.delete+"", 
					OpRresult.fail+"", "投票候选人删除失败！", voteCandidate.getId()+"", BusinessTableName.candidate.name);
		}
		outJson(map);	
	}


	/**
	 * 候选人详细
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public ModelAndView detail() {
		ModelAndView view = new ModelAndView();
		String candidateId = request.getParameter("id");
		view.setViewName("business/votecandidate/detail");
		try {
			view.addObject("voteCandidate", voteCandidateDao.get(Long.parseLong(candidateId)));
			view.addObject("voteCandidateDetail", voteCandidateDetailDao.getByUserId(Long.parseLong(candidateId)));
			List<Map<String, Object>> dataList = voteCandidatePicDao.getPicId(Long.parseLong(candidateId));
			view.addObject("picId", JSONUtil.getJson(dataList));
			view.addObject("activityURL", SysBaseUtil.ACTIVITY_URL);
		}
		catch(Exception e){
			logger.error(e.getMessage(), e);
			sysLogService.addLog(request, getLoginUser(), "投票候选人", "详细显示", 
					OpRresult.fail+"", "投票候选人详细显示失败！", candidateId+"", BusinessTableName.candidate.name);
		}
		return view;
	}

	/**
	 * 候选人得票记录
	 * @return
	 */
	@RequestMapping(value = "/voterecord")
	public ModelAndView voteRecord() {
		ModelAndView view = new ModelAndView();
		String candidateId = request.getParameter("id");
		view.addObject("candidateId",candidateId);
		SysUser sysUser = getLoginUser();
		view.setViewName("business/votecandidate/voterecord");
		view.addObject("gridComluns", sysGridColumnsService.getGridColumsByCode("voteRecord_grid"));
		view.addObject("conditionHtml", sysQueryItemService.getConditionHtmlByCode("voteRecord_query", sysUser.getUnitId()));
		return view;
	}

	/**
	 * 获取候选人投票记录列表
	 */
	@RequestMapping(value = "/getvoterecordList")
	public void getvoteRecordList() {

		outJson(VoteCandidateService.getVoteItemsGridData(request, getLoginUnitId()));
	}

}
