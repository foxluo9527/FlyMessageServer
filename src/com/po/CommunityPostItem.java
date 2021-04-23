package com.po;

public class CommunityPostItem {
    private int community_post_item_id;

    private int community_post_id;

    /**
     * 0:Õº∆¨£¨1: ”∆µ
     */
    private int community_post_item_type;

    private String community_post_item_url;

    /**
     * @return community_post_item_id
     */
    public int getCommunity_post_item_id() {
        return community_post_item_id;
    }

    /**
     * @param communityPostItemId
     */
    public void setCommunity_post_item_id(int community_post_item_id) {
        this.community_post_item_id = community_post_item_id;
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
     * ªÒ»°0:Õº∆¨£¨1: ”∆µ
     *
     * @return community_post_item_type - 0:Õº∆¨£¨1: ”∆µ
     */
    public int getCommunity_post_item_type() {
        return community_post_item_type;
    }

    /**
     * …Ë÷√0:Õº∆¨£¨1: ”∆µ
     *
     * @param communityPostItemType 0:Õº∆¨£¨1: ”∆µ
     */
    public void setCommunity_post_item_type(int community_post_item_type) {
        this.community_post_item_type = community_post_item_type;
    }

    /**
     * @return community_post_item_url
     */
    public String getCommunity_post_item_url() {
        return community_post_item_url;
    }

    /**
     * @param communityPostItemUrl
     */
    public void setCommunity_post_item_url(String community_post_item_url) {
        this.community_post_item_url = community_post_item_url;
    }
}