package com.po;

import java.util.Date;

public class CommunityPostCommentReply {
    private int community_post_comment_reply_id;

    private int community_post_comment_id;

    /**
     * 回复人id
     */
    private int send_u_id;

    private String send_u_nick_name;

    private String send_u_head;

    private String send_u_name;
    
    /**
     * 被回复人id
     */
    private int reply_u_id;

    private String reply_u_name;

    private String reply_content;

    private String state;

    private Date create_time;

    /**
     * @return community_post_comment_reply_id
     */
    public int getCommunity_post_comment_reply_id() {
        return community_post_comment_reply_id;
    }

    /**
     * @param community_post_comment_reply_id
     */
    public void setCommunity_post_comment_reply_id(int community_post_comment_reply_id) {
        this.community_post_comment_reply_id = community_post_comment_reply_id;
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

    public String getSend_u_nick_name() {
		return send_u_nick_name;
	}

	public void setSend_u_nick_name(String send_u_nick_name) {
		this.send_u_nick_name = send_u_nick_name;
	}

	/**
     * 获取回复人id
     *
     * @return send_u_id - 回复人id
     */
    public int getSend_u_id() {
        return send_u_id;
    }

    /**
     * 设置回复人id
     *
     * @param send_u_id 回复人id
     */
    public void setSend_u_id(int send_u_id) {
        this.send_u_id = send_u_id;
    }

    /**
     * @return send_u_name
     */
    public String getSend_u_name() {
        return send_u_name;
    }

    /**
     * @param send_u_name
     */
    public void setSend_u_name(String send_u_name) {
        this.send_u_name = send_u_name;
    }

    /**
     * @return send_u_head
     */
    public String getSend_u_head() {
        return send_u_head;
    }

    /**
     * @param send_u_head
     */
    public void setSend_u_head(String send_u_head) {
        this.send_u_head = send_u_head;
    }

    /**
     * 获取被回复人id
     *
     * @return reply_u_id - 被回复人id
     */
    public int getReply_u_id() {
        return reply_u_id;
    }

    /**
     * 设置被回复人id
     *
     * @param reply_u_id 被回复人id
     */
    public void setReply_u_id(int reply_u_id) {
        this.reply_u_id = reply_u_id;
    }

    /**
     * @return reply_u_name
     */
    public String getReply_u_name() {
        return reply_u_name;
    }

    /**
     * @param reply_u_name
     */
    public void setReply_u_name(String reply_u_name) {
        this.reply_u_name = reply_u_name;
    }

    /**
     * @return reply__content
     */
    public String getReply_content() {
        return reply_content;
    }

    /**
     * @param reply_content
     */
    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
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