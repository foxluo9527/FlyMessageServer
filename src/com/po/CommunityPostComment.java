package com.po;

import java.util.ArrayList;
import java.util.Date;

public class CommunityPostComment {
    private Integer communityPostCommentId;

    private Integer communityPostId;

    private Integer sendUId;

    private String sendUName;

    private String sendUHead;

    private String communityPostCommentContent;

    /**
     * 评论赞
     */
    private ArrayList<CommunityPostCommentZan> zans;

    /**
     * 评论回复
     */
    private ArrayList<CommunityPostCommentReply> replies;

    /**
     * 热度值:点赞+1，回复+1
     */
    private Integer hotValue;

    private Integer zanNum;

    /**
     * 0:待审核,1:通过,2:未通过
     */
    private Integer state;

    private Date createTime;

    /**
     * @return community_post_comment_id
     */
    public Integer getCommunityPostCommentId() {
        return communityPostCommentId;
    }

    /**
     * @param communityPostCommentId
     */
    public void setCommunityPostCommentId(Integer communityPostCommentId) {
        this.communityPostCommentId = communityPostCommentId;
    }

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
     * @return send_u_id
     */
    public Integer getSendUId() {
        return sendUId;
    }

    /**
     * @param sendUId
     */
    public void setSendUId(Integer sendUId) {
        this.sendUId = sendUId;
    }

    /**
     * @return send_u_name
     */
    public String getSendUName() {
        return sendUName;
    }

    /**
     * @param sendUName
     */
    public void setSendUName(String sendUName) {
        this.sendUName = sendUName;
    }

    /**
     * @return send_u_head
     */
    public String getSendUHead() {
        return sendUHead;
    }

    /**
     * @param sendUHead
     */
    public void setSendUHead(String sendUHead) {
        this.sendUHead = sendUHead;
    }

    /**
     * @return community_post_comment_content
     */
    public String getCommunityPostCommentContent() {
        return communityPostCommentContent;
    }

    /**
     * @param communityPostCommentContent
     */
    public void setCommunityPostCommentContent(String communityPostCommentContent) {
        this.communityPostCommentContent = communityPostCommentContent;
    }

    /**
     * 获取热度值:点赞+1，回复+1
     *
     * @return hot_value - 热度值:点赞+1，回复+1
     */
    public Integer getHotValue() {
        return hotValue;
    }

    /**
     * 设置热度值:点赞+1，回复+1
     *
     * @param hotValue 热度值:点赞+1，回复+1
     */
    public void setHotValue(Integer hotValue) {
        this.hotValue = hotValue;
    }

    /**
     * @return zan_num
     */
    public Integer getZanNum() {
        return zanNum;
    }

    /**
     * @param zanNum
     */
    public void setZanNum(Integer zanNum) {
        this.zanNum = zanNum;
    }

    /**
     * 获取0:待审核,1:通过,2:未通过
     *
     * @return state - 0:待审核,1:通过,2:未通过
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0:待审核,1:通过,2:未通过
     *
     * @param state 0:待审核,1:通过,2:未通过
     */
    public void setState(Integer state) {
        this.state = state;
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

    public ArrayList<CommunityPostCommentZan> getZans() {
        return zans;
    }

    public void setZans(ArrayList<CommunityPostCommentZan> zans) {
        this.zans = zans;
    }

    public ArrayList<CommunityPostCommentReply> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<CommunityPostCommentReply> replies) {
        this.replies = replies;
    }
}