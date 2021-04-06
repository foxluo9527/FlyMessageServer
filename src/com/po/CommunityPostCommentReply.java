package com.po;

import java.util.Date;

public class CommunityPostCommentReply {
    private Integer communityPostCommentReplyId;

    private Integer communityPostCommentId;

    /**
     * 回复人id
     */
    private Integer sendUId;

    private String sendUName;

    private String sendUHead;

    /**
     * 被回复人id
     */
    private Integer replyUId;

    private String replyUName;

    private String replyContent;

    private String state;

    private Date createTime;

    /**
     * @return community_post_comment_reply_id
     */
    public Integer getCommunityPostCommentReplyId() {
        return communityPostCommentReplyId;
    }

    /**
     * @param communityPostCommentReplyId
     */
    public void setCommunityPostCommentReplyId(Integer communityPostCommentReplyId) {
        this.communityPostCommentReplyId = communityPostCommentReplyId;
    }

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
     * 获取回复人id
     *
     * @return send_u_id - 回复人id
     */
    public Integer getSendUId() {
        return sendUId;
    }

    /**
     * 设置回复人id
     *
     * @param sendUId 回复人id
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
     * 获取被回复人id
     *
     * @return reply_u_id - 被回复人id
     */
    public Integer getReplyUId() {
        return replyUId;
    }

    /**
     * 设置被回复人id
     *
     * @param replyUId 被回复人id
     */
    public void setReplyUId(Integer replyUId) {
        this.replyUId = replyUId;
    }

    /**
     * @return reply_u_name
     */
    public String getReplyUName() {
        return replyUName;
    }

    /**
     * @param replyUName
     */
    public void setReplyUName(String replyUName) {
        this.replyUName = replyUName;
    }

    /**
     * @return reply_content
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * @param replyContent
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
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
}