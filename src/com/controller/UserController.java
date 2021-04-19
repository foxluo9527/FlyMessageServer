package com.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.po.Token;
import com.po.UserBean;
import com.po.UserPageQuery;
import com.po.UserPrivacyBean;
import com.po.UserSignBean;
import com.service.UserService;
import com.util.Base64Util;
import com.util.TokenUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",
			method=RequestMethod.GET,
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String login(String username,String pass) {
		return userService.login(username, pass);
	}
	
	
	@RequestMapping(value="/register",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String register(@ModelAttribute UserBean user) {
		return userService.register(user);
	}
	
	
	@RequestMapping(value="/exit",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String exit(String loginToken) {
		return userService.exit(loginToken);
	}
	
	@RequestMapping(value="/getUserByName",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	 String getUserByName(String u_name,String loginToken) {
		int u_id=0;
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			u_id=token.getUser().getU_id();
		}
		return userService.getUserByName(u_id,u_name);
	}
	
	@RequestMapping(value="/getUserByID",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	 String getUserByID(int object_u_id,String loginToken) {
		int u_id=0;
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			u_id=token.getUser().getU_id();
		}
		return userService.getUserByID(u_id, object_u_id);
	}
	
	
	@RequestMapping(value="/changeHead",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changeHead(@Autowired UserBean user,String loginToken,HttpServletRequest request) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			user.setU_id(token.getUser().getU_id());
		}
		return userService.changeHead(user, request);
	}
	
	@RequestMapping(value="/changeBgImg",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changeBgImg(@Autowired UserBean user,String loginToken,HttpServletRequest request) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			user.setU_id(token.getUser().getU_id());
		}
		return userService.changeBgImg(user, request);
	}
	
	
	@RequestMapping(value="/getUserHeads",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	 String getUserHeads(@ModelAttribute UserPageQuery query,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			query.setU_id(token.getUser().getU_id());
		}
		return userService.getUserHeads(query);
	}
	
	
	@RequestMapping(value="/delUserHead",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delUserHead(String loginToken,int h_id) {
		return userService.delUserHead(h_id);
	}
	
	@RequestMapping(value="/changeOldHead",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changeOldHead(String loginToken,int h_id) {
		UserBean userBean=null;
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			userBean=token.getUser();
		}
		return userService.changeOldHead(userBean,h_id);
	}
	@RequestMapping(value="/updateUserMsg",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateUserMsg(@ModelAttribute UserBean user,String brithday,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			user.setU_id(token.getUser().getU_id());
		}
		if (brithday!=null) {
			 long brithdayLong=Long.parseLong(brithday);
			 user.setU_brithday(new Date(brithdayLong));
		}
		return userService.updateUserMsg(user);
	}
	
	
	@RequestMapping(value="/searchUser",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String searchUser(@ModelAttribute UserPageQuery query,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			query.setU_id(token.getUser().getU_id());
		}
		return userService.searchUser(query);
	}
	
	
	@RequestMapping(value="/addUserSign",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addUserSign(@ModelAttribute UserSignBean sign,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			sign.setU_id(token.getUser().getU_id());
		}
		sign.setTime(new Date());
		return userService.addUserSign(sign);
	}
	
	
	@RequestMapping(value="/delUserSign",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delUserSign(@ModelAttribute UserSignBean sign,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			sign.setU_id(token.getUser().getU_id());
		}
		return userService.delUserSign(sign);
	}
	
	
	@RequestMapping(value="/getUserSigns",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getUserSigns(@ModelAttribute UserPageQuery query,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			query.setU_id(token.getUser().getU_id());
		}
		return userService.getUserSigns(query);
	}
	
	@RequestMapping(value="/changeUserPrivacy",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changeUserPrivacy(@ModelAttribute UserPrivacyBean privacy,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			privacy.setP_u_id(token.getUser().getU_id());
		}
		return userService.changeUserPrivacy(privacy);
	}
	
	@RequestMapping(value="/getUserPrivacy",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getUserPrivacy(@ModelAttribute UserBean user,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null&&user.getU_id()==0) {
			user.setU_id(token.getUser().getU_id());
		}
		return userService.getUserPrivacy(user);
	}
	/**
	 * GET Stirng u_name
	 * @param u_name
	 * @return
	 */
	@RequestMapping(value="/checkUserName",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String checkUserName(String u_name) {
		return userService.checkUserName(u_name);
	}
	/**
	 * GET String phone
	 * @param phone
	 * @return
	 */
	@RequestMapping(value="/checkUserPhone",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String checkUserPhone(String phone) {
		return userService.checkUserPhone(phone);
	}
	/**
	 * GET String u_pass,String loginToken
	 * @param user
	 * @param loginToken
	 * @return
	 */
	@RequestMapping(value="/changePass",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changePass(@ModelAttribute UserBean user,String loginToken) {
		user.setU_pass(Base64Util.encode(user.getU_pass().getBytes()));
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			user.setU_id(token.getUser().getU_id());
			user.setU_phone(token.getUser().getU_phone());
		}
		return userService.changePass(user);
	}
	/**
	 * GET String u_phone,String loginToken
	 * @param user
	 * @param loginToken
	 * @return
	 */
	@RequestMapping(value="/changePhone",
			produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changePhone(@ModelAttribute UserBean user,String loginToken) {
		Token token=TokenUtil.getTokenUser(loginToken);
		if (token!=null) {
			user.setU_id(token.getUser().getU_id());
		}
		return userService.changePhone(user);
	}
}
