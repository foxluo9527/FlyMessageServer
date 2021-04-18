package com.service;

import com.po.CommunityPost;
import com.po.UserPageQuery;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface PostService {
    String addPost(CommunityPost post);

    String addPostItem(int postId, MultipartFile file, int u_id);

    String delPostItem(int postId, int postItemId, int u_id);

    String delPost(int postId, int u_id);

    String getPosts(UserPageQuery pageQuery,int u_id);

    String getUserPost(int userId, int u_id);

    String editPostContent(int postId, String content, int u_id);

    String zanPost(int postId, int u_id);

    String cancelZanPost(int postId, int u_id);

    String addPostComment(int postId, String content, int u_id);

    String zanPostComment(int postCommentId, int u_id);

    String cancelZanPostComment(int postCommentId, int u_id);

    String delPostComment(int postCommentId, int u_id);

    String replyPostComment(int postCommentId, String content, int u_id);
}
