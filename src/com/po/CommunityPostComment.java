package com.po;

import java.util.ArrayList;
import java.util.Date;

public class CommunityPostComment {
    private int community_post_comment_id;

    private int community_post_id;

    private int send_u_id;

    private String send_u_name;

    private String send_u_head;

    private String send_u_nick_name;
    
    private String community_post_comment_content;

    private int zan_state;
    
    
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
    private int hot_value;

    private int zan_num;

    /**
     * 0:待审核,1:通过,2:未通过
     */
    private int state;

    private Date create_time;

    /**
     * @return community_post_comment_id
     */
    public int getCommunity_post_comment_id() {
        return community_post_comment_id;
    }

    /**
     * @param communityPostCommentId
     */
    public void setCommunity_post_comment_id(int community_post_comment_id) {
        this.community_post_comment_id = community_post_comment_id;
    }

    /**
     * @return community_post_id
     */
    public int getCommunity_post_id() {
        return community_post_id;
    }

    /**
     * @param communityPostId
     */
    public void setCommunity_post_id(int community_post_id) {
        this.community_post_id = community_post_id;
    }

    /**
     * @return send_u_id
     */
    public int getSend_u_id() {
        return send_u_id;
    }

    /**
     * @param sendUId
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
     * @param sendUName
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
     * @param sendUHead
     */
    public void setSend_u_head(String send_u_head) {
        this.send_u_head = send_u_head;
    }

    /**
     * @return community_post_comment_content
     */
    public String getCommunity_post_comment_content() {
        return community_post_comment_content;
    }

    /**
     * @param communityPostCommentContent
     */
    public void setCommunity_post_comment_content(String community_post_comment_content) {
        this.community_post_comment_content = community_post_comment_content;
    }

    /**
     * 获取热度值:点赞+1，回复+1
     *
     * @return hot_value - 热度值:点赞+1，回复+1
     */
    public int getHot_value() {
        return hot_value;
    }

    public int getZan_state() {
		return zan_state;
	}

	public void setZan_state(int zan_state) {
		this.zan_state = zan_state;
	}

	/**
     * 设置热度值:点赞+1，回复+1
     *
     * @param hotValue 热度值:点赞+1，回复+1
     */
    public void setHot_value(int hot_value) {
        this.hot_value = hot_value;
    }

    /**
     * @return zan_num
     */
    public int getZan_num() {
        return zan_num;
    }

    /**
     * @param zanNum
     */
    public void setZan_num(int zan_num) {
        this.zan_num = zan_num;
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

    public String getSend_u_nick_name() {
		return send_u_nick_name;
	}

	public void setSend_u_nick_name(String send_u_nick_name) {
		this.send_u_nick_name = send_u_nick_name;
	}

	/**
     * @return create_time
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * @param createTime
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
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