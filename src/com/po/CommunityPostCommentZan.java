package com.po;

import java.util.Date;

public class CommunityPostCommentZan {
    private int community_post_comment_zan_id;

    private int community_post_comment_id;

    /**
     * 回复人id
     */
    private int zan_u_id;

    private String zan_u_name;

    private String zan_u_head;

    private Date create_time;

    /**
     * @return community_post_comment_zan_id
     */
    public int getCommunity_post_comment_zan_id() {
        return community_post_comment_zan_id;
    }

    /**
     * @param community_post_comment_zan_id
     */
    public void setCommunity_post_comment_zan_id(int community_post_comment_zan_id) {
        this.community_post_comment_zan_id = community_post_comment_zan_id;
    }

    /**
     * @return community_post_comment_id
     */
    public int getCommunity_post_comment_id() {
        return community_post_comment_id;
    }

    /**
     * @param community_post_comment_id
     */
    public void setCommunity_post_comment_id(int community_post_comment_id) {
        this.community_post_comment_id = community_post_comment_id;
    }

    /**
     * 获取回复人id
     *
     * @return zan_u_id - 回复人id
     */
    public int getZan_u_id() {
        return zan_u_id;
    }

    /**
     * 设置回复人id
     *
     * @param zan_u_id 回复人id
     */
    public void setZan_u_id(int zan_u_id) {
        this.zan_u_id = zan_u_id;
    }

    /**
     * @return zan_u_name
     */
    public String getZan_u_name() {
        return zan_u_name;
    }

    /**
     * @param zan_u_name
     */
    public void setZan_u_name(String zan_u_name) {
        this.zan_u_name = zan_u_name;
    }

    /**
     * @return zan_u_head
     */
    public String getZan_u_head() {
        return zan_u_head;
    }

    /**
     * @param zan_u_head
     */
    public void setZan_u_head(String zan_u_head) {
        this.zan_u_head = zan_u_head;
    }

    /**
     * @return create__time
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