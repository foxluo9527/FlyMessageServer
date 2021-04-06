package com.po;

import java.util.ArrayList;
import java.util.Date;

public class CommunityPost {
    private Integer communityPostId;

    private String communityId;

    /**
     * 帖子文章内容
     */
    private String communityPostContent;

    /**
     * 0:未审核,1:审核完成,2:审核失败,3:已封禁
     */
    private Integer communityPostState;

    private Integer shareCount;

    private Integer zanCount;

    private Integer showCount;

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

    /**
     * @return community_id
     */
    public String getCommunityId() {
        return communityId;
    }

    /**
     * @param communityId
     */
    public void setCommunityId(String communityId) {
        this.communityId = communityId;
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