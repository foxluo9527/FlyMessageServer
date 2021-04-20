package com.po;

import java.util.ArrayList;
import java.util.Date;

public class CommunityPostComment {
    private int communityPostCommentId;

    private int communityPostId;

    private int sendUId;

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
    private int hotValue;

    private int zanNum;

    /**
     * 0:待审核,1:通过,2:未通过
     */
    private int state;

    private Date createTime;

    /**
     * @return community_post_comment_id
     */
    public int getCommunityPostCommentId() {
        return communityPostCommentId;
    }

    /**
     * @param communityPostCommentId
     */
    public void setCommunityPostCommentId(int communityPostCommentId) {
        this.communityPostCommentId = communityPostCommentId;
    }

    /**
     * @return community_post_id
     */
    public int getCommunityPostId() {
        return communityPostId;
    }

    /**
     * @param communityPostId
     */
    public void setCommunityPostId(int communityPostId) {
        this.communityPostId = communityPostId;
    }

    /**
     * @return send_u_id
     */
    public int getSendUId() {
        return sendUId;
    }

    /**
     * @param sendUId
     */
    public void setSendUId(int sendUId) {
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
    public int getHotValue() {
        return hotValue;
    }

    /**
     * 设置热度值:点赞+1，回复+1
     *
     * @param hotValue 热度值:点赞+1，回复+1
     */
    public void setHotValue(int hotValue) {
        this.hotValue = hotValue;
    }

    /**
     * @return zan_num
     */
    public int getZanNum() {
        return zanNum;
    }

    /**
     * @param zanNum
     */
    public void setZanNum(int zanNum) {
        this.zanNum = zanNum;
    }

    /**
     * 获取0:待审核,1:通过,2:未通过
     *
     * @return state - 0:待审核,1:通过,2:未通过
     */
    public int getState() {
        return state;
    }

    /**
     * 设置0:待审核,1:通过,2:未通过
     *
     * @param state 0:待审核,1:通过,2:未通过
     */
    public void setState(int state) {
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