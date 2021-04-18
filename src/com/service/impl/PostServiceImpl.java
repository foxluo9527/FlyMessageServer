package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dao.CommunityPostDao;
import com.dao.UserDao;
import com.po.CommunityPost;
import com.po.CommunityPostItem;
import com.po.UserPageQuery;
import com.service.PostService;
import com.util.Constant;
import com.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;

@Service("PostService")
@Transactional
public class PostServiceImpl implements PostService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommunityPostDao communityPostDao;

    @Override
    public String addPost(CommunityPost post) {
        JSONObject result = new JSONObject();
        try {
            int id = communityPostDao.addPost(post);
            if (id > 0) {
                result.put("code", 200);
                result.put("postId", post.getCommunityPostId());
                result.put("msg", "发表帖子成功");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "发表帖子失败");
        }
        return result.toJSONString();
    }

    @Override
    public String addPostItem(int postId, MultipartFile file, int u_id) {
        JSONObject result = new JSONObject();
        if (file != null) {
            try {
                String fileUrl = "";
                String fileName = URLDecoder.decode(file.getOriginalFilename(), "utf-8");
                String realpath = "C:\\upload/file/postFile";
                //实现文件上传
                String fileType = fileName.substring(fileName.lastIndexOf('.'));
                //防止文件名重名
                fileName = MyUtil.getStringID() + fileType;
                File targetFile = new File(realpath, fileName);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
                }
                fileUrl = "http://" + Constant.addr + "/FlyMessage/file/postFile/" + fileName;
                //上传
                try {
                    file.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CommunityPostItem item = new CommunityPostItem();
                item.setCommunityPostItemUrl(fileUrl);
                item.setCommunityPostId(postId);
                item.setCommunityPostItemType(0);//暂时只能上传图片
                if (communityPostDao.addPostItem(item) > 0) {
                    result.put("code", 200);
                    result.put("msg", "上传帖子附图成功");
                } else {
                    result.put("code", 500);
                    result.put("msg", "上传帖子附图失败");
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                result.put("code", 500);
                result.put("msg", "上传帖子附图失败");
            }
        }
        return result.toJSONString();
    }

    @Override
    public String delPostItem(int postId, int postItemId, int u_id) {
        JSONObject result = new JSONObject();
        CommunityPostItem item = communityPostDao.getCommunityPostItem(postItemId);
        if (item != null && communityPostDao.delCommunityPostItem(postItemId, u_id) > 0) {
            String realpath = "C:\\upload/file/postFile" +
                    item.getCommunityPostItemUrl().replace("http://www.foxluo.cn/FlyMessage/file/postFile", "");
            File file=new File(realpath);
            file.delete();
            result.put("code", 200);
            result.put("msg", "删除帖子附图成功");
        } else {
            result.put("code", 500);
            result.put("msg", "删除帖子附图失败");
        }
        return result.toJSONString();
    }

    @Override
    public String delPost(int postId, int u_id) {
        JSONObject result = new JSONObject();
        if (communityPostDao.delPost(postId,u_id)>0){
            ArrayList<CommunityPostItem> items= (ArrayList<CommunityPostItem>) communityPostDao.getAllCommunityPostItem(postId);
            for (CommunityPostItem item : items) {
                delPostItem(postId,item.getCommunityPostItemId(),u_id);
            }
            result.put("code", 200);
            result.put("msg", "删除帖子成功");
        }else {
            result.put("code", 500);
            result.put("msg", "删除帖子失败");
        }
        return result.toJSONString();
    }

    @Override
    public String getPosts(UserPageQuery pageQuery,int u_id) {
        JSONObject result = new JSONObject();
        //直接获取表中已有数据，不进行个性化推荐与屏蔽过滤
        ArrayList<CommunityPost> posts= (ArrayList<CommunityPost>) communityPostDao.getPosts(pageQuery);
        if (posts!=null){
            for (CommunityPost post : posts) {
                post.setPostItems((ArrayList<CommunityPostItem>) communityPostDao.getAllCommunityPostItem(post.getCommunityPostId()));
            }
            result.put("code", 200);
            result.put("msg", "获取社区帖子成功");
            result.put("posts", JSONObject.toJSON(posts));
        }else {
            result.put("code", 500);
            result.put("msg", "获取社区帖子失败");
        }
        return result.toJSONString();
    }

    @Override
    public String getUserPost(int userId, int u_id) {
        return null;
    }

    @Override
    public String editPostContent(int postId, String content, int u_id) {
        return null;
    }

    @Override
    public String zanPost(int postId, int u_id) {
        return null;
    }

    @Override
    public String cancelZanPost(int postId, int u_id) {
        return null;
    }

    @Override
    public String addPostComment(int postId, String content, int u_id) {
        return null;
    }

    @Override
    public String zanPostComment(int postCommentId, int u_id) {
        return null;
    }

    @Override
    public String cancelZanPostComment(int postCommentId, int u_id) {
        return null;
    }

    @Override
    public String delPostComment(int postCommentId, int u_id) {
        return null;
    }

    @Override
    public String replyPostComment(int postCommentId, String content, int u_id) {
        return null;
    }
}
