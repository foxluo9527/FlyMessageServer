package com.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.FriendDao;
import com.dao.UserDao;
import com.po.BlackListBean;
import com.po.FriendBean;
import com.po.UserBean;
import com.po.UserHeadBean;
import com.po.UserPageQuery;
import com.po.UserPrivacyBean;
import com.po.UserSignBean;
import com.util.MyUtil;
import com.util.TokenUtil;
import com.websocket.MsgScoketHandle;
import com.util.Base64Util;
import com.util.Constant;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao UserDao;
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private MsgScoketHandle msgScoketHandle;

    private UserBean user = new UserBean();

    BlackListBean checkBlackList = new BlackListBean();

    FriendBean friend = new FriendBean();

    /**
     * 已弃用的注册API接口，统一为手机短信验证注册
     */
    @Override
    public String register(UserBean user) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
//		user.setU_create_time(new Date());
//		user.setU_name(UUID.randomUUID().toString().substring(9));
//		if (UserDao.register(user)>0) {
//			result.put("code", 200);
//			result.put("account",user.getU_name());
//			result.put("msg", "注册成功");
//		}else {
//			result.put("code", 500);
//			result.put("msg", "注册失败");
//		}
        result.put("code", 500);
        result.put("msg", "注册失败:请使用短信注册");
        return result.toJSONString();
    }

    @Override
    public String login(String username, String pass) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        user.setU_name(username);
        user.setU_phone(username);
        user.setU_pass(Base64Util.encode(pass.getBytes()));
        UserBean loginUser = UserDao.login(user);
        if (loginUser != null) {
            String token = UUID.randomUUID().toString();
            TokenUtil.addToken(token, loginUser, 6 * 60);
            result.put("code", 200);
            result.put("loginUser", JSON.toJSON(loginUser));
            result.put("token", token);
            result.put("msg", "登录成功");
        } else {
            result.put("code", "500");
            result.put("msg", "登录失败");
        }
        return result.toJSONString();
    }

    @Override
    public String exit(String token) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        if (TokenUtil.removeToken(token)) {
            result.put("code", 200);
            result.put("msg", "退出登录成功");
        } else {
            result.put("code", 500);
            result.put("msg", "未找到登录用户");
        }
        return result.toJSONString();
    }

    @Override
    public String changeBgImg(UserBean user, HttpServletRequest request) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        try {
            boolean flag = false;
            String bgUrl = "";
            String fileName = user.getBgImage().getOriginalFilename();
            if (user.getBgImage() != null) {
                String realpath = request.getSession().getServletContext().getRealPath("/") + "/images/userBgImg";
                //实现文件上传
                String fileType = fileName.substring(fileName.lastIndexOf('.'));
                //防止文件名重名
                fileName = MyUtil.getStringID() + fileType;
                File targetFile = new File(realpath, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                bgUrl = "http://" + Constant.addr + "/FlyMessage/images/userBgImg/" + fileName;
                //上传
                try {
                    user.getBgImage().transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                user.setU_bg_img(bgUrl);
                if (UserDao.updateMsg(user) > 0) {
                    flag = true;
                }
            }
            if (flag) {
                result.put("code", 200);
                result.put("bgUrl", bgUrl);
                result.put("msg", "修改背景成功");
            } else {
                result.put("code", 500);
                result.put("msg", "修改背景失败");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result.toJSONString();
    }


    @Override
    public String changeHead(UserBean user, HttpServletRequest request) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        try {
            boolean flag = false;
            String headUrl = "";
            String fileName = user.getHeadImage().getOriginalFilename();
            if (user.getHeadImage() != null) {
                String realpath = request.getSession().getServletContext().getRealPath("/") + "/images/userHeads";
                //实现文件上传
                String fileType = fileName.substring(fileName.lastIndexOf('.'));
                //防止文件名重名
                fileName = MyUtil.getStringID() + fileType;
                File targetFile = new File(realpath, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                headUrl = "http://" + Constant.addr + "/FlyMessage/images/userHeads/" + fileName;
                //上传
                try {
                    user.getHeadImage().transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                user.setU_head_img(headUrl);
                if (UserDao.changeHead(user) > 0) {
                    flag = true;
                }
            }
            if (flag) {
                result.put("code", 200);
                result.put("headUrl", headUrl);
                result.put("msg", "修改头像成功");
            } else {
                result.put("code", 500);
                result.put("msg", "修改头像失败");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result.toJSONString();
    }

    @Override
    public String getUserHeads(UserPageQuery query) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        try {
            List<UserHeadBean> heads = UserDao.getUserHeads(query);
            result.put("code", 200);
            result.put("heads", JSONObject.toJSON(heads));
            result.put("msg", "获取头像成功");
        } catch (Exception e) {
            // TODO: handle exception
            result.put("code", 500);
            result.put("msg", "获取头像失败");
        }

        return result.toJSONString();
    }

    @Override
    public String addUserSign(UserSignBean sign) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        if (UserDao.addUserSign(sign) > 0) {
            result.put("code", 200);
            result.put("msg", "添加签名成功");
        } else {
            result.put("code", 500);
            result.put("msg", "添加签名失败");
        }
        return result.toJSONString();
    }

    @Override
    public String delUserSign(UserSignBean sign) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        if (UserDao.delUserSign(sign) > 0) {
            result.put("code", 200);
            result.put("msg", "删除签名成功");
        } else {
            result.put("code", 500);
            result.put("msg", "删除签名失败");
        }
        return result.toJSONString();
    }

    @Override
    public String getUserSigns(UserPageQuery query) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        List<UserSignBean> signs = UserDao.getUserSigns(query);
        if (signs != null) {
            result.put("code", 200);
            result.put("sign", JSONObject.toJSON(signs));
            result.put("msg", "获取签名成功");
        } else {
            result.put("code", 500);
            result.put("msg", "获取签名失败");
        }
        return result.toJSONString();
    }

    @Override
    public String searchUser(UserPageQuery query) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        try {
            List<UserBean> queryUsers = UserDao.queryUser(query);
            JSONArray resultArray = new JSONArray();
            for (int i = 0; i < queryUsers.size(); i++) {
                checkBlackList.setSource_u_id(queryUsers.get(i).getU_id());
                checkBlackList.setObject_u_id(query.getU_id());
                //检测用户是否在对方黑名单，并且检测对方隐私设置：是否能查看资料
                if (UserDao.checkShowMsgPrivacy(queryUsers.get(i).getU_id()) != 1 && UserDao.checkBlackList(checkBlackList) == 0) {
                    JSONObject resultUser = (JSONObject) JSONObject.toJSON(queryUsers.get(i));
                    friend.setF_object_u_id(queryUsers.get(i).getU_id());
                    friend.setF_source_u_id(query.getU_id());
                    if (friendDao.checkFriendRelationship(friend) > 0) {
                        resultUser.put("isFriend", true);
                    } else {
                        resultUser.put("isFriend", false);
                    }
                    resultUser.remove("u_pass");
                    resultUser.remove("u_phone");
                    resultUser.remove("u_forbidden");
                    resultUser.put("isOnline", msgScoketHandle.getUserOnlineState(friend.getF_object_u_id()));
                    resultArray.add(resultUser);
                }
            }
            result.put("code", 200);
            result.put("result", JSONObject.toJSON(resultArray));
            result.put("msg", "查询用户成功");
        } catch (Exception e) {
            // TODO: handle exception
            result.put("code", 500);
            result.put("msg", "查询用户失败");
            e.printStackTrace();
        }
        return result.toJSONString();
    }

    @Override
    public String updateUserMsg(UserBean user) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        if (UserDao.updateMsg(user) > 0) {
            result.put("code", 200);
            result.put("msg", "修改用户信息成功");
        } else {
            result.put("code", 500);
            result.put("msg", "修改用户信息失败");
        }
        return result.toJSONString();
    }

    @Override
    public String changeUserPrivacy(UserPrivacyBean privacy) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        if (UserDao.changeUserPrivacy(privacy) > 0) {
            result.put("code", 200);
            result.put("msg", "修改用户隐私设置成功");
        } else {
            result.put("code", 500);
            result.put("msg", "修改用户隐私设置失败");
        }
        return result.toJSONString();
    }

    @Override
    public String getUserPrivacy(UserBean user) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        UserPrivacyBean privacy = UserDao.getUserPrivacy(user);
        if (privacy != null) {
            result.put("code", 200);
            result.put("privacy", JSONObject.toJSON(privacy));
            result.put("msg", "获取用户隐私设置信息成功");
        } else {
            result.put("code", 500);
            result.put("msg", "获取用户隐私设置信息失败");
        }
        return result.toJSONString();
    }

    @Override
    public String delUserHead(int h_id) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        if (UserDao.delUserHead(h_id) > 0) {
            result.put("code", 200);
            result.put("msg", "删除头像成功");
        } else {
            result.put("code", 500);
            result.put("msg", "删除头像失败");
        }
        return result.toJSONString();
    }

    @Override
    public String changeOldHead(UserBean user, int h_id) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        UserHeadBean headBean = UserDao.getHeadById(h_id);
        if (headBean != null && user != null) {
            user.setNowDate(new Date());
            user.setU_head_img(headBean.getHead_img_link());
            if (UserDao.changeHead(user) > 0 && UserDao.delUserHead(h_id) > 0) {
                result.put("code", 200);
                result.put("msg", "更换头像成功");
            } else {
                result.put("code", 500);
                result.put("msg", "更换头像失败");
            }
        } else {
            result.put("code", 500);
            result.put("msg", "更换头像失败");
        }
        return result.toJSONString();
    }

    @Override
    public String getUserByName(int u_id, String u_name) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        UserBean user = UserDao.getUserByName(u_name);
        if (user != null && UserDao.checkShowMsgPrivacy(user.getU_id()) != 1) {
            JSONObject resultUser = (JSONObject) JSONObject.toJSON(user);
            friend.setF_object_u_id(user.getU_id());
            friend.setF_source_u_id(u_id);
            int isFriend = friendDao.checkFriendRelationship(friend);
            if (isFriend > 0) {
                resultUser.put("isFriend", true);
            } else {
                resultUser.put("isFriend", false);
            }
            resultUser.remove("u_pass");
            resultUser.remove("u_phone");
            resultUser.remove("u_forbidden");
            resultUser.put("isOnline", msgScoketHandle.getUserOnlineState(friend.getF_object_u_id()));
            result.put("code", 200);
            result.put("user", resultUser);
            result.put("msg", "获取用户信息成功");
        } else {
            result.put("code", 500);
            result.put("msg", "获取用户信息失败");
        }
        return result.toJSONString();
    }

    @Override
    public String getUserByID(int u_id, int object_u_id) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        UserBean user = UserDao.getUserById(object_u_id);
        if (user != null) {
            JSONObject resultUser = (JSONObject) JSONObject.toJSON(user);
            friend.setF_object_u_id(user.getU_id());
            friend.setF_source_u_id(u_id);
            int isFriend = friendDao.checkFriendRelationship(friend);
            if (isFriend > 0) {
                resultUser.put("isFriend", true);
            } else {
                resultUser.put("isFriend", false);
            }
            resultUser.remove("u_pass");
            resultUser.remove("u_phone");
            resultUser.remove("u_forbidden");
            resultUser.put("isOnline", msgScoketHandle.getUserOnlineState(friend.getF_object_u_id()));
            result.put("code", 200);
            result.put("user", resultUser);
            result.put("msg", "获取用户信息成功");
        } else {
            result.put("code", 500);
            result.put("msg", "获取用户信息失败");
        }
        return result.toJSONString();
    }

    @Override
    public String checkUserName(String u_name) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        if (UserDao.checkUserName(u_name) == 0) {
            result.put("code", 200);
            result.put("msg", "用户名未使用");
        } else {
            result.put("code", 500);
            result.put("msg", "用户名已使用");
        }
        return result.toJSONString();
    }

    @Override
    public String checkUserPhone(String phone) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        if (UserDao.checkPhone(phone) == null) {
            result.put("code", 200);
            result.put("msg", "手机号未使用");
        } else {
            result.put("code", 500);
            result.put("msg", "手机号已使用");
        }
        return result.toJSONString();
    }

    @Override
    public String changePass(UserBean user) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        if (UserDao.changePass(user) > 0) {
            result.put("code", 200);
            result.put("msg", "修改密码成功");
        } else {
            result.put("code", 500);
            result.put("msg", "修改密码失败");
        }
        return result.toJSONString();
    }

    @Override
    public String changePhone(UserBean user) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        if (UserDao.changePhone(user) > 0) {
            result.put("code", 200);
            result.put("msg", "修改手机号成功");
        } else {
            result.put("code", 500);
            result.put("msg", "修改手机号失败");
        }
        return result.toJSONString();
    }


}
