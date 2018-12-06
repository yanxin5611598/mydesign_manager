package com.yx.mydesign.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yx.mydesign.bean.UserCheckResult;
import com.yx.mydesign.service.client.UserCheckResultService;
/*设备历史使用检测的数据以及 使用者的信息**/
@Controller
public class UserCheckResultController {
	@Autowired
	private UserCheckResultService checkResultService;
	
	@RequestMapping(value="/getAllCheckResult")
	//获取数据库中的所有用户上传的检测数据
	public String getAllCheckResult(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,Model model){
		//引入分页查询插件PageHelper   需要传入页码和每页的结果数
		PageHelper.startPage(pageNumber, 4);
		//startPage后面紧跟的这个查询就是一个分页查询
		List<UserCheckResult> list = checkResultService.getAllData();
		System.out.println("checkresult:"+list.get(0).getTime());
		//使用PageInfo对结果进行包装
		//封装了详细的分页信息,将其传递给页面,在页面进行显示即可
		PageInfo<UserCheckResult> pageInfo = new PageInfo<UserCheckResult>(list);
		model.addAttribute("page", pageInfo);
		return "checkResult/checkResultList";
	}
	@RequestMapping("/checkResultSearch")
	public ModelAndView checkResultSearch(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("checkResult/checkResultList");
		String deviceID = request.getParameter("deviceID");
		String username = request.getParameter("username");
		String place = request.getParameter("place");
		PageHelper.startPage(pageNumber, 4);
		List<UserCheckResult> list = checkResultService.getDataByItemsLike(deviceID,username,place);
		PageInfo<UserCheckResult> page = new PageInfo<UserCheckResult>(list);
		mv.addObject("page", page);
		return mv;
	}
	@RequestMapping("/checkResultDetail")
	public String checkResultDetail(@RequestParam("id") Integer id,Model model){
		UserCheckResult result = checkResultService.getResultByID(id);
		model.addAttribute("result", result);
		return "checkResult/checkResultDetail";
	}
	@RequestMapping("/checkResultDelete")
	public String checkResultDelete(@RequestParam("id") Integer id){
		checkResultService.deleteDataById(id);
		//重定向
		return "redirect:/getAllCheckResult";
	}
}
