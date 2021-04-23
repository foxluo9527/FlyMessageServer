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
                result.put("postId", post.getCommunity_post_id());
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
                item.setCommunity_post_item_url(fileUrl);
                item.setCommunity_post_id(postId);
                item.setCommunity_post_item_type(0);//暂时只能上传图片
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
            if (item != null && post != null && post.getU_id() == u_id && communityPostDao.delCommunityPostItem(postItemId) > 0) {
                String realpath = "C:\\upload/file/postFile" +
                        item.getCommunity_post_item_url().substring("http://www.foxluo.cn/FlyMessage/file/postFile".length());
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
        post.setU_id(u_id);
        post.setCommunity_post_id(postId);
        if (communityPostDao.delPost(post) > 0) {
            //删除帖子所有附图
            ArrayList<CommunityPostItem> items = (ArrayList<CommunityPostItem>) communityPostDao.getAllCommunityPostItem(postId);
            for (CommunityPostItem item : items) {
                delPostItem(postId, item.getCommunity_post_item_id(), u_id);
            }
            //删除帖子所有评论
            ArrayList<CommunityPostComment> comments = (ArrayList<CommunityPostComment>) communityPostDao.getPostComments(postId);
            for (CommunityPostComment comment : comments) {
                //删除评论下所有点赞记录
                ArrayList<CommunityPostCommentZan> commentZans = (ArrayList<CommunityPostCommentZan>) communityPostDao.getPostCommentZans(comment.getCommunity_post_comment_id());
                for (CommunityPostCommentZan commentZan : commentZans) {
                    communityPostDao.delPostCommentZan(commentZan.getCommunity_post_comment_zan_id());
                }
                //删除评论下所有回复
                ArrayList<CommunityPostCommentReply> replies = (ArrayList<CommunityPostCommentReply>) communityPostDao.getPostCommentReplies(comment.getCommunity_post_comment_id());
                for (CommunityPostCommentReply reply : replies) {
                    communityPostDao.delPostCommentReply(reply.getCommunity_post_comment_reply_id());
                }
            }
            //删除所有帖子点赞记录
            ArrayList<CommunityPostZan> postZans = (ArrayList<CommunityPostZan>) communityPostDao.getPostZans(postId);
            for (CommunityPostZan postZan : postZans) {
                communityPostDao.delPostZan(postZan.getCommunity_post_zan_id());
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
                post1.setU_id(u_id);
                post1.setCommunity_post_id(post.getCommunity_post_id());
                post.setPostItems((ArrayList<CommunityPostItem>) communityPostDao.getAllCommunityPostItem(post.getCommunity_post_id()));
                post.setZan_state(communityPostDao.getPostZanState(post1));
                UserBean user=userDao.getUserById(post.getU_id());
                post.setU_name(user.getU_name());
                post.setU_nick_name(user.getU_nick_name());
                post.setU_head(user.getU_head_img());
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
                post.setPostItems((ArrayList<CommunityPostItem>) communityPostDao.getAllCommunityPostItem(post.getCommunity_post_id()));
                CommunityPost post1 = new CommunityPost();
                post1.setU_id(u_id);
                post1.setCommunity_post_id(post.getCommunity_post_id());
                post.setZan_state(communityPostDao.getPostZanState(post1));
                UserBean user=userDao.getUserById(post.getU_id());
                post.setU_name(user.getU_name());
                post.setU_nick_name(user.getU_nick_name());
                post.setU_head(user.getU_head_img());
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
            ArrayList<CommunityPostZan> postZans = (ArrayList<CommunityPostZan>) communityPostDao.getPostZans(postId);
            for(CommunityPostZan postZan:postZans) {
                if(postZan.getUser_id()==u_id) {
                    //已经点过赞了
                    result.put("code", 500);
                    result.put("msg", "点赞帖子失败:您已点赞，请勿重复");
                    return result.toJSONString();
                }
            }
            post.setZanCount(post.getZanCount() + 1);
            post.setZan_state(1);
            CommunityPostZan zan = new CommunityPostZan();
            zan.setCreate_time(new Date());
            zan.setCommunity_post_id(postId);
            zan.setUser_id(u_id);
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
            post.setZan_state(0);
            post.setU_id(u_id);
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
            comment.setCommunity_post_comment_content(content);
            comment.setCommunity_post_id(postId);
            comment.setCreate_time(new Date());
            comment.setSend_u_id(u_id);
            comment.setSend_u_head(sendUser.getU_head_img());
            comment.setSend_u_name(sendUser.getU_name());
            comment.setSend_u_nick_name(sendUser.getU_nick_name());
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
            ArrayList<CommunityPostCommentZan> commentZans=
                    (ArrayList<CommunityPostCommentZan>)communityPostDao.getPostCommentZans(postCommentId);
            for (CommunityPostCommentZan commentZan : commentZans) {
                if(commentZan.getZan_u_id()==u_id) {
                    result.put("code", 500);
                    result.put("msg", "点赞评论失败:您已点赞，请勿重复");
                    return result.toJSONString();
                }
            }
            comment.setZan_num(comment.getZan_num()+1);
            comment.setHot_value(comment.getHot_value() + 1);
            CommunityPostCommentZan zan = new CommunityPostCommentZan();
            zan.setZan_u_id(u_id);
            zan.setCommunity_post_comment_id(postCommentId);
            UserBean zanUser = userDao.getUserById(u_id);
            zan.setZan_u_head(zanUser.getU_head_img());
            zan.setZan_u_name(zanUser.getU_nick_name());
            zan.setCreate_time(new Date());
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
            comment.setZan_num(comment.getZan_num()-1);
            comment.setHot_value(comment.getHot_value() - 1);
            CommunityPostCommentZan zan=new CommunityPostCommentZan();
            zan.setZan_u_id(u_id);
            zan.setCommunity_post_comment_id(postCommentId);
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
            if (comment != null && comment.getSend_u_id() == u_id) {
                ArrayList<CommunityPostCommentReply> replies = (ArrayList<CommunityPostCommentReply>) communityPostDao.getPostCommentReplies(comment.getCommunity_post_comment_id());
                for (CommunityPostCommentReply reply : replies) {
                    communityPostDao.delPostCommentReply(reply.getCommunity_post_comment_reply_id());
                }
                ArrayList<CommunityPostCommentZan> zans = (ArrayList<CommunityPostCommentZan>) communityPostDao.getPostCommentZans(comment.getCommunity_post_comment_id());
                for (CommunityPostCommentZan zan : zans) {
                    communityPostDao.delPostZan(zan.getCommunity_post_comment_zan_id());
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
    public String replyPostComment(int postCommentId,int replyId,String content, int u_id) {
        JSONObject result = new JSONObject();
        CommunityPostComment comment = communityPostDao.getPostComment(postCommentId);
        if (comment != null) {
            UserBean sendUser = userDao.getUserById(u_id);
            comment.setHot_value(comment.getHot_value() + 1);
            CommunityPostCommentReply reply = new CommunityPostCommentReply();
            reply.setCommunity_post_comment_id(postCommentId);
            reply.setCreate_time(new Date());
            reply.setReply_content(content);
            if(replyId>0) {
                //回复回复
                CommunityPostCommentReply beReply=communityPostDao.getReplyById(replyId);
                UserBean beReplyUser = userDao.getUserById(beReply.getSend_u_id());
                reply.setReply_u_id(beReplyUser.getU_id());
                reply.setReply_u_name(beReplyUser.getU_nick_name());
            }else {
                //回复评论
                UserBean commentUser=userDao.getUserById(comment.getSend_u_id());
                reply.setReply_u_id(comment.getSend_u_id());
                reply.setReply_u_name(commentUser.getU_nick_name());
            }
            reply.setSend_u_id(u_id);
            reply.setSend_u_head(sendUser.getU_head_img());
            reply.setSend_u_name(sendUser.getU_name());
            reply.setSend_u_nick_name(sendUser.getU_nick_name());
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

                ArrayList<CommunityPostItem> items = (ArrayList<CommunityPostItem>) communityPostDao.getAllCommunityPostItem(postId);
                post.setPostItems(items);

                ArrayList<CommunityPostComment> comments = (ArrayList<CommunityPostComment>) communityPostDao.getPostComments(postId);
                for (CommunityPostComment comment : comments) {
                    UserBean commentUser=userDao.getUserById(comment.getSend_u_id());
                    if(commentUser!=null) {
                        comment.setSend_u_head(commentUser.getU_head_img());
                        comment.setSend_u_nick_name(commentUser.getU_nick_name());
                        comment.setSend_u_name(commentUser.getU_name());
                    }else {
                        comment.setSend_u_nick_name("该用户已注销");
                        comment.setSend_u_head("http://www.foxluo.cn/FlyMessage/images/userHeads/none.jpg");
                    }
                    ArrayList<CommunityPostCommentReply> replies = (ArrayList<CommunityPostCommentReply>) communityPostDao.getPostCommentReplies(comment.getCommunity_post_comment_id());
                    for (CommunityPostCommentReply reply : replies) {
                        UserBean replyUser=userDao.getUserById(reply.getSend_u_id());
                        if(replyUser!=null) {
                            reply.setSend_u_name(replyUser.getU_name());
                            reply.setSend_u_nick_name(replyUser.getU_nick_name());
                            reply.setSend_u_head(replyUser.getU_head_img());
                        }else {
                            reply.setSend_u_nick_name("该用户已注销");
                        }
                        UserBean repliedUser=userDao.getUserById(reply.getReply_u_id());
                        if(repliedUser!=null) {
                            reply.setReply_u_name(repliedUser.getU_nick_name());
                        }else{
                            reply.setReply_u_name("该用户已注销");
                        }
                    }
                    ArrayList<CommunityPostCommentZan> commentZans=(ArrayList<CommunityPostCommentZan>)communityPostDao.getPostCommentZans(comment.getCommunity_post_comment_id());
                    for (CommunityPostCommentZan commentZan : commentZans) {
                        if(commentZan.getZan_u_id()==u_id) {
                            comment.setZan_state(1);
                            break;
                        }
                    }
                    comment.setZans(commentZans);
                    comment.setReplies(replies);
                }
                post.setComments(comments);
                post.setCommentCount(comments.size());
                ArrayList<CommunityPostZan> postZans = (ArrayList<CommunityPostZan>) communityPostDao.getPostZans(postId);
                post.setZans(postZans);
                for(CommunityPostZan postZan:postZans) {
                    if(postZan.getUser_id()==u_id) {
                        post.setZan_state(1);
                        break;
                    }
                }
                UserBean user=userDao.getUserById(post.getU_id());
                post.setU_name(user.getU_name());
                post.setU_nick_name(user.getU_nick_name());
                post.setU_head(user.getU_head_img());
                result.put("code", 200);
                result.put("post", JSONObject.toJSON(post));
                result.put("msg", "获取帖子详情成功");
                communityPostDao.updatePost(post);
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

    @Override
    public String delCommentReply(int replyId, int u_id) {
        // TODO Auto-generated method stub
        JSONObject result = new JSONObject();
        try {
            CommunityPostCommentReply reply = communityPostDao.getReplyById(replyId);
            if (reply != null && reply.getSend_u_id() == u_id) {
                if (communityPostDao.delPostCommentReply(replyId) > 0) {
                    result.put("code", 200);
                    result.put("msg", "删除评论回复成功");
                } else {
                    result.put("code", 500);
                    result.put("msg", "删除评论回复失败");
                }
            } else {
                result.put("code", 500);
                result.put("msg", "删除评论回复失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "删除评论回复失败");
        }
        return result.toJSONString();
    }
}
