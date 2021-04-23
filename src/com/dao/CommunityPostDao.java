package com.dao;

import com.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CommunityPostDao")
@Mapper
public interface CommunityPostDao {
    public int addPost(CommunityPost post);

    public CommunityPost getPost(int postId);

    public List<CommunityPost> getPosts(UserPageQuery query);

    public List<CommunityPost> getUserPosts(UserPageQuery query);

    public int updatePost(CommunityPost post);

    public int delPost(CommunityPost post);

    public int zanPost(CommunityPostZan zan);

    public int getPostZanState(CommunityPost post);

    public List<CommunityPostZan> getPostZans(int postId);

    public int cancelZanPost(CommunityPost post);

    public int delPostZan(int postZanId);

    public int addPostItem(CommunityPostItem item);

    public CommunityPostItem getPostItem(int itemId);

    public List<CommunityPostItem> getAllCommunityPostItem(int postId);

    public int delCommunityPostItem(int itemId);

    public int addPostComment(CommunityPostComment comment);

    public CommunityPostComment getPostComment(int commentId);

    public List<CommunityPostComment> getPostComments(int postId);

    public int updatePostComment(CommunityPostComment comment);

    public int delPostComment(int postCommentId);

    public int addPostCommentReply(CommunityPostCommentReply reply);

    public List<CommunityPostCommentReply> getPostCommentReplies(int commentId);

    public CommunityPostCommentReply getReplyById(int replyId);
    
    public int delPostCommentReply(int postCommentReplyId);

    public int zanPostComment(CommunityPostCommentZan zan);

    public List<CommunityPostCommentZan> getPostCommentZans(int commentId);

    public int cancelZanPostComment(CommunityPostCommentZan zan);

    public int delPostCommentZan(int postCommentZanId);
}
