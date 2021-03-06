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
     * ???????ӣ????????ˣ?
     *
     * @param content    ????????
     * @param loginToken ??¼????
     * @return ????????
     */
    @RequestMapping(value = "/addPost",
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addPost(String content, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        CommunityPost post = new CommunityPost();
        post.setCommunityPostContent(content);
        post.setCommunityPostState(1);//????????
        post.setU_id(token.getUser().getU_id());
        post.setCreateTime(new Date(System.currentTimeMillis()));
        return postService.addPost(post);
    }

    /**
     * ?༭????????
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
     * ????????
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
     * ȡ??????????
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
     * ɾ??????
     *
     * @param postId
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/delPost",
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delPost(int postId, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.delPost(postId, token.getUser().getU_id());
    }

    /**
     * ???????Ӹ?ͼ
     *
     * @param postId     ??Ӧ????id
     * @param loginToken ??¼???ƣ??˶????ӷ??????Ƿ?ƥ??
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
     * ?û???ȡ?????б?
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
     * ??ȡ?û???????ҳ?б?
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
     * ??ȡ????????
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
     * ɾ?????Ӹ?ͼ
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
     * ????????????
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
     * ɾ??????????
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
     * ɾ?????ۻظ?
     *
     * @param replyId
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/delCommentReply",
            method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delCommentReply(int replyId, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.delCommentReply(replyId, token.getUser().getU_id());
    }
    /**
     * ????????????
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
     * ȡ??????????????
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
     * ?ظ?????????
     *
     * @param postCommentId
     * @param replyId >0Ϊ?Իظ??Ļظ?
     * @param content
     * @param loginToken
     * @return
     */
    @RequestMapping(value = "/replyPostComment",
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String replyPostComment(int postCommentId,int replyId, String content, String loginToken) {
        Token token = TokenUtil.getTokenUser(loginToken);
        return postService.replyPostComment(postCommentId,replyId,content, token.getUser().getU_id());
    }
}
