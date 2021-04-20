package com.po;

import java.util.Date;

public class CommunityPostCommentZan {
    private int communityPostCommentZanId;

    private int communityPostCommentId;

    /**
     * 回复人id
     */
    private int zanUId;

    private String zanUName;

    private String zanUHead;

    private Date createTime;

    /**
     * @return community_post_comment_zan_id
     */
    public int getCommunityPostCommentZanId() {
        return communityPostCommentZanId;
    }

    /**
     * @param communityPostCommentZanId
     */
    public void setCommunityPostCommentZanId(int communityPostCommentZanId) {
        this.communityPostCommentZanId = communityPostCommentZanId;
    }

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
     * 获取回复人id
     *
     * @return zan_u_id - 回复人id
     */
    public int getZanUId() {
        return zanUId;
    }

    /**
     * 设置回复人id
     *
     * @param zanUId 回复人id
     */
    public void setZanUId(int zanUId) {
        this.zanUId = zanUId;
    }

    /**
     * @return zan_u_name
     */
    public String getZanUName() {
        return zanUName;
    }

    /**
     * @param zanUName
     */
    public void setZanUName(String zanUName) {
        this.zanUName = zanUName;
    }

    /**
     * @return zan_u_head
     */
    public String getZanUHead() {
        return zanUHead;
    }

    /**
     * @param zanUHead
     */
    public void setZanUHead(String zanUHead) {
        this.zanUHead = zanUHead;
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