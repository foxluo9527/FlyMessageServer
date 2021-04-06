package com.po;

public class CommunityPostItem {
    private Integer communityPostItemId;

    private Integer communityPostId;

    /**
     * 0:图片，1:视频
     */
    private Integer communityPostItemType;

    private String communityPostItemUrl;

    /**
     * @return community_post_item_id
     */
    public Integer getCommunityPostItemId() {
        return communityPostItemId;
    }

    /**
     * @param communityPostItemId
     */
    public void setCommunityPostItemId(Integer communityPostItemId) {
        this.communityPostItemId = communityPostItemId;
    }

    /**
     * @return community_post_id
     */
    public Integer getCommunityPostId() {
        return communityPostId;
    }

    /**
     * @param communityPostId
     */
    public void setCommunityPostId(Integer communityPostId) {
        this.communityPostId = communityPostId;
    }

    /**
     * 获取0:图片，1:视频
     *
     * @return community_post_item_type - 0:图片，1:视频
     */
    public Integer getCommunityPostItemType() {
        return communityPostItemType;
    }

    /**
     * 设置0:图片，1:视频
     *
     * @param communityPostItemType 0:图片，1:视频
     */
    public void setCommunityPostItemType(Integer communityPostItemType) {
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