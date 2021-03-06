package com.po;

import java.util.ArrayList;
import java.util.Date;

public class CommunityPost {
    private int community_post_id;
    private int u_id;
    /**
     * ������������
     */
    private String community_post_content;

    /**
     * 0:δ���,1:������,2:���ʧ��,3:�ѷ��
     */
    private int community_post_state;

    /**
     * ��ǰ�û��Ը����ĵ���״̬
     * 0:δ����,1:�ѵ���
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
     * ���Ӹ�����Դ��(�Ź���ͼƬ/��Ƶ)
     */
    private ArrayList<CommunityPostItem> postItems;

    /**
     * ���ӻظ�
     */
    private ArrayList<CommunityPostComment> comments;

    /**
     * ������
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
     * ��ȡ������������
     *
     * @return community_post_content - ������������
     */
    public String getCommunityPostContent() {
        return community_post_content;
    }

    /**
     * ����������������
     *
     * @param communityPostContent ������������
     */
    public void setCommunityPostContent(String communityPostContent) {
        this.community_post_content = communityPostContent;
    }

    /**
     * ��ȡ0:δ���,1:������,2:���ʧ��,3:�ѷ��
     *
     * @return community_post_state - 0:δ���,1:������,2:���ʧ��,3:�ѷ��
     */
    public int getCommunityPostState() {
        return community_post_state;
    }

    /**
     * ����0:δ���,1:������,2:���ʧ��,3:�ѷ��
     *
     * @param communityPostState 0:δ���,1:������,2:���ʧ��,3:�ѷ��
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