package com.po;

import java.util.Date;

public class CommunityPostZan {
    private int community_post_zan_id;

    private int community_post_id;

    private int user_id;

    private Date create_time;

    /**
     * @return community_post_zan_id
     */
    public int getCommunity_post_zan_id() {
        return community_post_zan_id;
    }

    /**
     * @param community_post_zan_id
     */
    public void setCommunity_post_zan_id(int community_post_zan_id) {
        this.community_post_zan_id = community_post_zan_id;
    }

    /**
     * @return community_post_id
     */
    public int getCommunity_post_id() {
        return community_post_id;
    }

    /**
     * @param community_post_id
     */
    public void setCommunity_post_id(int community_post_id) {
        this.community_post_id = community_post_id;
    }

    /**
     * @return user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    /**
     * @return create_time
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * @param create_time
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}