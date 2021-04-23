package com.po;

import java.util.ArrayList;
import java.util.Date;

public class CommunityPost {
    private int community_post_id;
    private int u_id;
    /**
     * 帖子文章内容
     */
    private String community_post_content;

    /**
     * 0:未审核,1:审核完成,2:审核失败,3:已封禁
     */
    private int community_post_state;

    /**
     * 当前用户对该贴的点赞状态
     * 0:未点赞,1:已点赞
     */
    private int zan_state;

    private int share_count;

    private int zan_count;

    private int show_count;

    private int comment_count;
    
    private String u_name;
    
    private String u_nick_name;
    
    private String u_head;
    
    private Date create_time;

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
    public int getCommunity_post_id() {
        return community_post_id;
    }
    
    /**
     * @param communityPostId
     */
    public void setCommunity_post_id(int community_post_id) {
        this.community_post_id = community_post_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getZan_state() {
        return zan_state;
    }

    public void setZan_state(int zan_state) {
        this.zan_state = (zan_state > 0 ? 1 : 0);
    }

    /**
     * 获取帖子文章内容
     *
     * @return community_post_content - 帖子文章内容
     */
    public String getCommunityPostContent() {
        return community_post_content;
    }

    /**
     * 设置帖子文章内容
     *
     * @param communityPostContent 帖子文章内容
     */
    public void setCommunityPostContent(String communityPostContent) {
        this.community_post_content = communityPostContent;
    }

    /**
     * 获取0:未审核,1:审核完成,2:审核失败,3:已封禁
     *
     * @return community_post_state - 0:未审核,1:审核完成,2:审核失败,3:已封禁
     */
    public int getCommunityPostState() {
        return community_post_state;
    }

    /**
     * 设置0:未审核,1:审核完成,2:审核失败,3:已封禁
     *
     * @param communityPostState 0:未审核,1:审核完成,2:审核失败,3:已封禁
     */
    public void setCommunityPostState(int communityPostState) {
        this.community_post_state = communityPostState;
    }

    /**
     * @return share_count
     */
    public int getShareCount() {
        return share_count;
    }

    /**
     * @param shareCount
     */
    public void setShareCount(int shareCount) {
        this.share_count = shareCount;
    }

    /**
     * @return zan_count
     */
    public int getZanCount() {
        return zan_count;
    }

    /**
     * @param zanCount
     */
    public void setZanCount(int zanCount) {
        this.zan_count = zanCount;
    }

    /**
     * @return show_count
     */
    public int getShowCount() {
        return show_count;
    }

    /**
     * @param showCount
     */
    public void setShowCount(int showCount) {
        this.show_count = showCount;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return create_time;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.create_time = createTime;
    }

    public int getCommentCount() {
        return comment_count;
    }

    public void setCommentCount(int commentCount) {
        this.comment_count = commentCount;
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

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_head() {
		return u_head;
	}

	public void setU_head(String u_head) {
		this.u_head = u_head;
	}

	public String getU_nick_name() {
		return u_nick_name;
	}

	public void setU_nick_name(String u_nick_name) {
		this.u_nick_name = u_nick_name;
	}
    
}