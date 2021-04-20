package com.controller;

import com.po.CommunityPost;
import com.po.Token;
import com.po.UserPageQuery;
import com.service.PostService;
import com.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Controller
@RequestMapping("/community")
public class PostController {
    @Autowired
    PostService postService;

    /**
     * 发布帖子（暂无审核）
     *
     * @param content    帖子内容
     * @param loginToken 登录令牌
     * @return 发帖结果
     */
    @RequestMapping(value = "/addPost",
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addPost(String content, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        CommunityPost post = new CommunityPost();
        post.setCommunityPostContent(content);
        post.setCommunityPostState(1);//不用审核
        post.setuId(token.getUser().getU_id());
        post.setCreateTime(new Date(System.currentTimeMillis()));
        return postService.addPost(post);
    }

    /**
     * 编辑帖子内容
     *
     * @param postId
     * @param content
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/editPostContent",
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String editPostContent(int postId, String content, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.editPostContent(postId, content, token.getUser().getU_id());
    }

    /**
     * 点赞帖子
     *
     * @param postId
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/zanPost",
            method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String zanPost(int postId, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.zanPost(postId, token.getUser().getU_id());
    }

    /**
     * 取消点赞帖子
     *
     * @param postId
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/cancelZanPost",
            method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String cancelZanPost(int postId, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.cancelZanPost(postId, token.getUser().getU_id());
    }

    /**
     * 删除帖子
     *
     * @param postId
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/delPostItem",
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delPost(int postId, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.delPost(postId, token.getUser().getU_id());
    }

    /**
     * 添加帖子附图
     *
     * @param postId     对应帖子id
     * @param loginToken 登录令牌，核对帖子发帖人是否匹配
     * @return
     */
    @RequestMapping(value = "/addPostItem",
            method = RequestMethod.POST,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addPostItem(int postId, MultipartFile file, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.addPostItem(postId,file, token.getUser().getU_id());
    }

    /**
     * 用户获取社区列表
     *
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/getPosts",
            method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPosts(@ModelAttribute UserPageQuery query, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.getPosts(query,token.getUser().getU_id());
    }

    /**
     * 获取用户社区主页列表
     *
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/getUserPost",
            method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUserPost(@ModelAttribute UserPageQuery query,  String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.getUserPost(query, token.getUser().getU_id());
    }

    /**
     * 获取帖子详情
     * @param postId
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/getPost",
            method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getPost(int postId,  String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.getPost(postId, token.getUser().getU_id());
    }

    /**
     * 删除帖子附图
     *
     * @param postId
     * @param postItemId
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/delPostItem",
            method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delPostItem(int postId, int postItemId, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.delPostItem(postId, postItemId, token.getUser().getU_id());
    }

    /**
     * 添加帖子评论
     *
     * @param postId
     * @param content
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/addPostComment",
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addPostComment(int postId, String content, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.addPostComment(postId, content, token.getUser().getU_id());
    }
    /**
     * 删除帖子评论
     *
     * @param postCommentId
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/delPostComment",
            method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delPostComment(int postCommentId, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.delPostComment(postCommentId, token.getUser().getU_id());
    }
    /**
     * 点赞帖子评论
     *
     * @param postCommentId
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/zanPostComment",
            method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String zanPostComment(int postCommentId, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.zanPostComment(postCommentId, token.getUser().getU_id());
    }

    /**
     * 取消点赞帖子评论
     *
     * @param postCommentId
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/cancelZanPostComment",
            method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String cancelZanPosCommentt(int postCommentId, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.cancelZanPostComment(postCommentId, token.getUser().getU_id());
    }
    /**
     * 回复帖子评论
     *
     * @param postCommentId
     * @param content
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/replyPostComment",
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String replyPostComment(int postCommentId, String content, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.replyPostComment(postCommentId, content, token.getUser().getU_id());
    }
}
