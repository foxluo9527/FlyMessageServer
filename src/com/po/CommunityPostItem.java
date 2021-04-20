package com.po;

public class CommunityPostItem {
    private int communityPostItemId;

    private int communityPostId;

    /**
     * 0:图片，1:视频
     */
    private int communityPostItemType;

    private String communityPostItemUrl;

    /**
     * @return community_post_item_id
     */
    public int getCommunityPostItemId() {
        return communityPostItemId;
    }

    /**
     * @param communityPostItemId
     */
    public void setCommunityPostItemId(int communityPostItemId) {
        this.communityPostItemId = communityPostItemId;
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
     * 获取0:图片，1:视频
     *
     * @return community_post_item_type - 0:图片，1:视频
     */
    public int getCommunityPostItemType() {
        return communityPostItemType;
    }

    /**
     * 设置0:图片，1:视频
     *
     * @param communityPostItemType 0:图片，1:视频
     */
    public void setCommunityPostItemType(int communityPostItemType) {
        this.communityPostItemType = communityPostItemType;
    }

    /**
     * @return community_post_item_url
     */
    public String getCommunityPostItemUrl() {
        return communityPostItemUrl;
    }

    /**
     * @param communityPostItemUrl
     */
    public void setCommunityPostItemUrl(String communityPostItemUrl) {
        this.communityPostItemUrl = communityPostItemUrl;
    }
}