package com.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dao.CommunityPostDao;
import com.dao.UserDao;
import com.po.*;
import com.service.PostService;
import com.util.Constant;
import com.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

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
                String fileUrl;
                String fileName = URLDecoder.decode(Objects.requireNonNull(file.getOriginalFilename()), "utf-8");
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
        CommunityPost post = communityPostDao.getPost(postId);
        CommunityPostItem item = communityPostDao.getPostItem(postItemId);
        try {
            if (item != null && post != null && post.getuId() == u_id && communityPostDao.delCommunityPostItem(postItemId) > 0) {
                String realpath = "C:\\upload/file/postFile" +
                        item.getCommunityPostItemUrl().replace("http://www.foxluo.cn/FlyMessage/file/postFile", "");
                File file = new File(realpath);
                file.delete();
                result.put("code", 200);
                result.put("msg", "删除帖子附图成功");
            } else {
                result.put("code", 500);
                result.put("msg", "删除帖子附图失败");
            }
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "删除帖子附图失败");
        }
        return result.toJSONString();
    }

    @Override
    public String delPost(int postId, int u_id) {
        JSONObject result = new JSONObject();
        CommunityPost post = new CommunityPost();
        post.setuId(u_id);
        post.setCommunityPostId(postId);
        if (communityPostDao.delPost(post) > 0) {
            //删除帖子所有附图
            ArrayList<CommunityPostItem> items = (ArrayList<CommunityPostItem>) communityPostDao.getAllCommunityPostItem(postId);
            for (CommunityPostItem item : items) {
                delPostItem(postId, item.getCommunityPostItemId(), u_id);
            }
            //删除帖子所有评论
            ArrayList<CommunityPostComment> comments = (ArrayList<CommunityPostComment>) communityPostDao.getPostComments(postId);
            for (CommunityPostComment comment : comments) {
                //删除评论下所有点赞记录
                ArrayList<CommunityPostCommentZan> commentZans = (ArrayList<CommunityPostCommentZan>) communityPostDao.getPostCommentZans(comment.getCommunityPostCommentId());
                for (CommunityPostCommentZan commentZan : commentZans) {
                    communityPostDao.delPostCommentZan(commentZan.getCommunityPostCommentZanId());
                }
                //删除评论下所有回复
                ArrayList<CommunityPostCommentReply> replies = (ArrayList<CommunityPostCommentReply>) communityPostDao.getPostCommentReplies(comment.getCommunityPostCommentId());
                for (CommunityPostCommentReply reply : replies) {
                    communityPostDao.delPostCommentReply(reply.getCommunityPostCommentReplyId());
                }
                delPostComment(comment.getCommunityPostCommentId(), u_id);
            }
            //删除所有帖子点赞记录
            ArrayList<CommunityPostZan> postZans = (ArrayList<CommunityPostZan>) communityPostDao.getPostZans(postId);
            for (CommunityPostZan postZan : postZans) {
                communityPostDao.delPostZan(postZan.getCommunityPostZanId());
            }
            result.put("code", 200);
            result.put("msg", "删除帖子成功");
        } else {
            result.put("code", 500);
            result.put("msg", "删除帖子失败");
        }
        return result.toJSONString();
    }

    @Override
    public String getPosts(UserPageQuery pageQuery, int u_id) {
        JSONObject result = new JSONObject();
        //直接获取表中已有数据，不进行个性化推荐与屏蔽过滤
        ArrayList<CommunityPost> posts = (ArrayList<CommunityPost>) communityPostDao.getPosts(pageQuery);
        if (posts != null) {
            for (CommunityPost post : posts) {
                CommunityPost post1 = new CommunityPost();
                post1.setuId(u_id);
                post1.setCommunityPostId(post.getCommunityPostId());
                post.setPostItems((ArrayList<CommunityPostItem>) communityPostDao.getAllCommunityPostItem(post.getCommunityPostId()));
                post.setZanState(communityPostDao.getPostZanState(post1));
            }
            result.put("code", 200);
            result.put("msg", "获取社区帖子成功");
            result.put("posts", JSONObject.toJSON(posts));
        } else {
            result.put("code", 500);
            result.put("msg", "获取社区帖子失败");
        }
        return result.toJSONString();
    }

    @Override
    public String getUserPost(UserPageQuery query, int u_id) {
        JSONObject result = new JSONObject();
        ArrayList<CommunityPost> posts = (ArrayList<CommunityPost>) communityPostDao.getUserPosts(query);
        if (posts != null) {
            for (CommunityPost post : posts) {
                post.setPostItems((ArrayList<CommunityPostItem>) communityPostDao.getAllCommunityPostItem(post.getCommunityPostId()));
            }
            result.put("code", 200);
            result.put("msg", "获取用户社区主页帖子成功");
            result.put("posts", JSONObject.toJSON(posts));
        } else {
            result.put("code", 500);
            result.put("msg", "获取用户社区主页帖子失败");
        }
        return result.toJSONString();
    }

    @Override
    public String editPostContent(int postId, String content, int u_id) {
        JSONObject result = new JSONObject();
        CommunityPost post = communityPostDao.getPost(postId);
        if (post != null) {
            post.setCommunityPostContent(content);
            if (communityPostDao.updatePost(post) > 0) {
                result.put("code", 200);
                result.put("msg", "修改帖子内容成功");
            }
        } else {
            result.put("code", 500);
            result.put("msg", "修改帖子内容失败");
        }
        return result.toJSONString();
    }

    @Override
    public String zanPost(int postId, int u_id) {
        JSONObject result = new JSONObject();
        CommunityPost post = communityPostDao.getPost(postId);
        if (post != null) {
            post.setZanCount(post.getZanCount() + 1);
            post.setZanState(1);
            CommunityPostZan zan = new CommunityPostZan();
            zan.setCreateTime(new Date());
            zan.setCommunityPostId(postId);
            if (communityPostDao.zanPost(zan) > 0 && communityPostDao.updatePost(post) > 0) {
                result.put("code", 200);
                result.put("msg", "点赞帖子成功");
            } else {
                result.put("code", 500);
                result.put("msg", "点赞帖子失败");
            }
        } else {
            result.put("code", 500);
            result.put("msg", "点赞帖子失败");
        }
        return result.toJSONString();
    }

    @Override
    public String cancelZanPost(int postId, int u_id) {
        JSONObject result = new JSONObject();
        CommunityPost post = communityPostDao.getPost(postId);
        if (post != null) {
            post.setZanState(0);
            post.setuId(u_id);
            post.setZanCount(Math.max(post.getZanCount() - 1, 0));
            if (communityPostDao.cancelZanPost(post) > 0 && communityPostDao.updatePost(post) > 0) {
                result.put("code", 200);
                result.put("msg", "取消点赞帖子成功");
            } else {
                result.put("code", 500);
                result.put("msg", "取消点赞帖子失败");
            }
        } else {
            result.put("code", 500);
            result.put("msg", "取消点赞帖子失败");
        }
        return result.toJSONString();
    }

    @Override
    public String addPostComment(int postId, String content, int u_id) {
        JSONObject result = new JSONObject();
        CommunityPost post = communityPostDao.getPost(postId);
        if (post != null) {
            UserBean sendUser = userDao.getUserById(u_id);
            post.setCommentCount(post.getCommentCount() + 1);
            CommunityPostComment comment = new CommunityPostComment();
            comment.setState(1);//暂时不审核
            comment.setCommunityPostCommentContent(content);
            comment.setCommunityPostId(postId);
            comment.setCreateTime(new Date());
            comment.setSendUHead(sendUser.getU_head_img());
            comment.setSendUName(sendUser.getU_nick_name());
            if (communityPostDao.addPostComment(comment) > 0 && communityPostDao.updatePost(post) > 0) {
                result.put("code", 200);
                result.put("msg", "评论帖子成功");
            } else {
                result.put("code", 500);
                result.put("msg", "评论帖子失败");
            }
        } else {
            result.put("code", 500);
            result.put("msg", "评论帖子失败");
        }
        return result.toJSONString();
    }

    @Override
    public String zanPostComment(int postCommentId, int u_id) {
        JSONObject result = new JSONObject();
        CommunityPostComment comment = communityPostDao.getPostComment(postCommentId);
        if (comment != null) {
            comment.setZanNum(comment.getZanNum()+1);
            comment.setHotValue(comment.getHotValue() + 1);
            CommunityPostCommentZan zan = new CommunityPostCommentZan();
            zan.setZanUId(u_id);
            zan.setCommunityPostCommentId(postCommentId);
            UserBean zanUser = userDao.getUserById(u_id);
            zan.setZanUHead(zanUser.getU_head_img());
            zan.setZanUName(zanUser.getU_nick_name());
            if (communityPostDao.zanPostComment(zan) > 0 && communityPostDao.updatePostComment(comment) > 0) {
                result.put("code", 200);
                result.put("msg", "点赞评论成功");
            } else {
                result.put("code", 500);
                result.put("msg", "点赞评论失败");
            }
        } else {
            result.put("code", 500);
            result.put("msg", "点赞评论失败");
        }
        return result.toJSONString();
    }

    @Override
    public String cancelZanPostComment(int postCommentId, int u_id) {
        JSONObject result = new JSONObject();
        CommunityPostComment comment = communityPostDao.getPostComment(postCommentId);
        if (comment != null) {
            comment.setZanNum(comment.getZanNum()-1);
            comment.setHotValue(comment.getHotValue() - 1);
            CommunityPostCommentZan zan=new CommunityPostCommentZan();
            zan.setZanUId(u_id);
            zan.setCommunityPostCommentId(postCommentId);
            if (communityPostDao.cancelZanPostComment(zan) > 0 && communityPostDao.updatePostComment(comment) > 0) {
                result.put("code", 200);
                result.put("msg", "取消点赞评论成功");
            } else {
                result.put("code", 500);
                result.put("msg", "取消点赞评论失败");
            }
        } else {
            result.put("code", 500);
            result.put("msg", "取消点赞评论失败");
        }
        return result.toJSONString();
    }

    @Override
    public String delPostComment(int postCommentId, int u_id) {
        JSONObject result = new JSONObject();
        try {
            CommunityPostComment comment = communityPostDao.getPostComment(postCommentId);
            if (comment != null && comment.getSendUId() == u_id) {
                ArrayList<CommunityPostCommentReply> replies = (ArrayList<CommunityPostCommentReply>) communityPostDao.getPostCommentReplies(comment.getCommunityPostCommentId());
                for (CommunityPostCommentReply reply : replies) {
                    communityPostDao.delPostCommentReply(reply.getCommunityPostCommentReplyId());
                }
                ArrayList<CommunityPostCommentZan> zans = (ArrayList<CommunityPostCommentZan>) communityPostDao.getPostCommentZans(comment.getCommunityPostCommentId());
                for (CommunityPostCommentZan zan : zans) {
                    communityPostDao.delPostZan(zan.getCommunityPostCommentZanId());
                }
                if (communityPostDao.delPostComment(postCommentId) > 0) {
                    result.put("code", 200);
                    result.put("msg", "删除评论成功");
                } else {
                    result.put("code", 500);
                    result.put("msg", "删除评论失败");
                }
            } else {
                result.put("code", 500);
                result.put("msg", "删除评论失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "删除评论失败");
        }
        return result.toJSONString();
    }

    @Override
    public String replyPostComment(int postCommentId, String content, int u_id) {
        JSONObject result = new JSONObject();
        CommunityPostComment comment = communityPostDao.getPostComment(postCommentId);
        if (comment != null) {
            UserBean sendUser = userDao.getUserById(u_id);
            comment.setHotValue(comment.getHotValue() + 1);
            CommunityPostCommentReply reply = new CommunityPostCommentReply();
            reply.setCommunityPostCommentId(postCommentId);
            reply.setCreateTime(new Date());
            reply.setReplyContent(content);
            reply.setReplyUId(comment.getSendUId());
            reply.setReplyUName(comment.getSendUName());
            reply.setSendUHead(comment.getSendUHead());
            reply.setSendUId(u_id);
            reply.setSendUHead(sendUser.getU_head_img());
            reply.setSendUName(sendUser.getU_nick_name());
            if (communityPostDao.addPostCommentReply(reply) > 0 && communityPostDao.updatePostComment(comment) > 0) {
                result.put("code", 200);
                result.put("msg", "回复评论成功");
            } else {
                result.put("code", 500);
                result.put("msg", "回复评论失败");
            }
        } else {
            result.put("code", 500);
            result.put("msg", "回复评论失败");
        }
        return result.toJSONString();
    }

    @Override
    public String getPost(int postId, int u_id) {
        JSONObject result = new JSONObject();
        try {
            CommunityPost post = communityPostDao.getPost(postId);
            if (post != null) {
                post.setShowCount(post.getShowCount() + 1);
                communityPostDao.updatePost(post);
                ArrayList<CommunityPostItem> items = (ArrayList<CommunityPostItem>) communityPostDao.getAllCommunityPostItem(postId);
                post.setPostItems(items);
                ArrayList<CommunityPostComment> comments = (ArrayList<CommunityPostComment>) communityPostDao.getPostComments(postId);
                for (CommunityPostComment comment : comments) {
                    ArrayList<CommunityPostCommentReply> replies = (ArrayList<CommunityPostCommentReply>) communityPostDao.getPostCommentReplies(comment.getCommunityPostCommentId());
                    comment.setReplies(replies);
                }
                post.setComments(comments);
                ArrayList<CommunityPostZan> postZans = (ArrayList<CommunityPostZan>) communityPostDao.getPostZans(postId);
                post.setZans(postZans);
                result.put("code", 200);
                result.put("post", JSONObject.toJSON(post));
                result.put("msg", "获取帖子详情成功");
            } else {
                result.put("code", 500);
                result.put("msg", "获取帖子详情失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "获取帖子详情失败");
        }
        return result.toJSONString();
    }
}
