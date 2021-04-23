package com.service;

import com.po.CommunityPost;
import com.po.UserPageQuery;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
    String addPost(CommunityPost post);

    String editPostContent(int postId, String content, int u_id);

    String zanPost(int postId, int u_id);

    String cancelZanPost(int postId, int u_id);

    String delPost(int postId, int u_id);

    String addPostItem(int postId, MultipartFile file, int u_id);

    String getPosts(UserPageQuery query, int u_id);

    String getUserPost(UserPageQuery query,int u_id);

    String delPostItem(int postId, int postItemId, int u_id);

    String addPostComment(int postId, String content, int u_id);

    String delPostComment(int postCommentId, int u_id);

    String delCommentReply(int replyId, int u_id);

    String zanPostComment(int postCommentId, int u_id);

    String cancelZanPostComment(int postCommentId, int u_id);

    String replyPostComment(int postCommentId,int replyId, String content, int u_id);

    String getPost(int postId, int u_id);
}
