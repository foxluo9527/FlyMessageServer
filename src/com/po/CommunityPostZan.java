package com.po;

import java.util.Date;

public class CommunityPostZan {
    private int communityPostZanId;

    private int communityPostId;

    private int userId;

    private Date createTime;

    /**
     * @return community_post_zan_id
     */
    public int getCommunityPostZanId() {
        return communityPostZanId;
    }

    /**
     * @param communityPostZanId
     */
    public void setCommunityPostZanId(int communityPostZanId) {
        this.communityPostZanId = communityPostZanId;
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
     * @return user_id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
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