package com.po;

import java.util.ArrayList;
import java.util.Date;

public class CommunityPost {
    private Integer communityPostId;
    private Integer uId;
    /**
     * 帖子文章内容
     */
    private String communityPostContent;

    /**
     * 0:未审核,1:审核完成,2:审核失败,3:已封禁
     */
    private Integer communityPostState;

    /**
     * 当前用户对该贴的点赞状态
     * 0:未点赞,1:已点赞
     */
    private Integer zanState;

    private Integer shareCount;

    private Integer zanCount;

    private Integer showCount;

    private Integer commentCount;

    private Date createTime;

    /**
     * 帖子附带资源项(九宫格图片/视频)
     */
    private ArrayList<CommunityPostItem> postItems;

    /**
     * 帖子回复
     */
    private ArrayList<CommunityPostComment> comments;

    /**
     * 帖子赞
     */
    private ArrayList<CommunityPostZan> zans;

    /**
     * @return community_post_id
     */
    public Integer getCommunityPostId() {
        return communityPostId;
    }

    /**
     * @param communityPostId
     */
    public void setCommunityPostId(Integer communityPostId) {
        this.communityPostId = communityPostId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getZanState() {
        return zanState;
    }

    public void setZanState(Integer zanState) {
        this.zanState = (zanState > 0 ? 1 : 0);
    }

    /**
     * 获取帖子文章内容
     *
     * @return community_post_content - 帖子文章内容
     */
    public String getCommunityPostContent() {
        return communityPostContent;
    }

    /**
     * 设置帖子文章内容
     *
     * @param communityPostContent 帖子文章内容
     */
    public void setCommunityPostContent(String communityPostContent) {
        this.communityPostContent = communityPostContent;
    }

    /**
     * 获取0:未审核,1:审核完成,2:审核失败,3:已封禁
     *
     * @return community_post_state - 0:未审核,1:审核完成,2:审核失败,3:已封禁
     */
    public Integer getCommunityPostState() {
        return communityPostState;
    }

    /**
     * 设置0:未审核,1:审核完成,2:审核失败,3:已封禁
     *
     * @param communityPostState 0:未审核,1:审核完成,2:审核失败,3:已封禁
     */
    public void setCommunityPostState(Integer communityPostState) {
        this.communityPostState = communityPostState;
    }

    /**
     * @return share_count
     */
    public Integer getShareCount() {
        return shareCount;
    }

    /**
     * @param shareCount
     */
    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    /**
     * @return zan_count
     */
    public Integer getZanCount() {
        return zanCount;
    }

    /**
     * @param zanCount
     */
    public void setZanCount(Integer zanCount) {
        this.zanCount = zanCount;
    }

    /**
     * @return show_count
     */
    public Integer getShowCount() {
        return showCount;
    }

    /**
     * @param showCount
     */
    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public ArrayList<CommunityPostItem> getPostItems() {
        return postItems;
    }

    public void setPostItems(ArrayList<CommunityPostItem> postItems) {
        this.postItems = postItems;
    }

    public ArrayList<CommunityPostComment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommunityPostComment> comments) {
        this.comments = comments;
    }

    public ArrayList<CommunityPostZan> getZans() {
        return zans;
    }

    public void setZans(ArrayList<CommunityPostZan> zans) {
        this.zans = zans;
    }
}