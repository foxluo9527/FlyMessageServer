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
    public int updatePost(CommunityPost post);
    public int delPost(int postId,int u_id);
    public List<CommunityPost> getPosts(UserPageQuery query);
    public List<CommunityPostItem> getAllCommunityPostItem(int postId);
    public int addPostItem(CommunityPostItem item);
    public CommunityPostItem getCommunityPostItem(int itemId);
    public int delCommunityPostItem(int itemId,int u_id);
    public List<CommunityPostZan> getPostZans(int postId);
    public List<CommunityPostComment> getPostComments(int postId);
    public List<CommunityPostCommentReply> getPostCommentReplies(int commentId);
    public List<CommunityPostCommentZan> getPostCommentZans(int commentId);
}
