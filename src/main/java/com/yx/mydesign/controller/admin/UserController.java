package com.yx.mydesign.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yx.mydesign.service.UserService;
import com.yx.mydesign.bean.User;

/*处理与用户相关的逻辑*/
@Controller
public class UserController {
	@Autowired 
	UserService userService;
	@RequestMapping(value="/getAllUser")
	public String getAllUser(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,Model model){
		System.out.println("pageNumber========"+pageNumber);
		//指定默认的参数值  起始的查询页数为1
		//引入分页查询插件PageHelper   需要传入页码和每页的结果数
		PageHelper.startPage(pageNumber, 6);
		//startPage后面紧跟的这个查询就是一个分页查询
		List<User> userList = userService.getAllUser();
		//使用PageInfo对结果进行包装
		//封装了详细的分页信息,将其传递给页面,在页面进行显示即可
		PageInfo<User> pageInfo = (PageInfo<User>)new PageInfo<User>(userList,5);//显示的指定连续显示的页数为5页
		model.addAttribute("page",pageInfo);
		return "/user/userList";
	}
	/**
	 * 查询
	 */
	@RequestMapping("/userSearch")
	public String search(@RequestParam(value="pageNumber",defaultValue="1")Integer pageNumber,Model model,@ModelAttribute User user) {
		System.out.println(user.getUsername());
		PageHelper.startPage(pageNumber, 6);
		List<User> userByNameList = userService.searchLikeByUserName(user);
		PageInfo<User> page = new PageInfo<User>(userByNameList);
		model.addAttribute("page", page);
		System.out.println("查询到的条数："+ userByNameList.size());
//		model.addAttribute("searchParam", adDto);
//		PageHelper.startPage(pageNumber, 6);
		
//		List<Ad> adList = adService.searchByPage(adDto)
		return "/user/userList";
	}
	/**
	 * 根据用户ID值获取到用户的详细信息 
	 * @param userid*/
	@RequestMapping(value="/userDetail",method=RequestMethod.GET)
	public String userDetail(@RequestParam("userid") Integer userid,Model model){
		User user = userService.getUserById(userid);
		model.addAttribute("user",user);
		return "/user/userdetail";
	}
	/**
	 * 根据用户ID值删除用户 
	 * @param userid*/
	@RequestMapping(value="/userDelete",method=RequestMethod.GET)
	public String userDelete(@RequestParam("userid") Integer userid){
		userService.deleteUserById(userid);
		return "redirect:/getAllUser";
	}
}
