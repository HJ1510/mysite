package com.bitacademy.mysite.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.mysite.security.Auth;
import com.bitacademy.mysite.security.AuthUser;
import com.bitacademy.mysite.service.UserService;
import com.bitacademy.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo, BindingResult result, Model model) {
		if(result.hasErrors()) {
//			List<ObjectError> errors = result.getAllErrors();
//			for(ObjectError error:errors) {
//				System.out.println(error);
//		}
			model.addAllAttributes(result.getModel()); //result.getModel()=>map 반환 map에있는 key값을 model에 setting
//			model.addAttribute("userVo", userVo); // @ModelAttribute로 대체 가능
			return "user/join";
			
		}
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(Model model, @AuthUser UserVo authUser) {
		// UserVo authUser = (UserVo)session.getAttribute("authUser");
		UserVo userVo = userService.findUser(authUser.getNo());
		model.addAttribute("userVo", userVo);
		
		return "user/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session, UserVo userVo, @AuthUser UserVo authUser) {
		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);		
		authUser.setName(userVo.getName());
		
		return "redirect:/user/update";
	}

	
	//web MVC Config
	@RequestMapping("/auth")
	public void auth() {
	}
	
	@RequestMapping("/logout")
	public void logout() {
	}
}
