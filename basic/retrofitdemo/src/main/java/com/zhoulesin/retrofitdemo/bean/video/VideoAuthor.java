package com.zhoulesin.retrofitdemo.bean.video;

/**
 * Created by zhoul on 2018/10/8.
 */

public class VideoAuthor {
    private AuthorShield shield;
    private int approvedNotReadyVideoCount;
    private String icon;
    private String name;
    private String link;
    private boolean isPgc;
    private String description;
    private long leatestReleaseTime;
    private int id;
    private int videoNum;
    private AuthorFollow follow;
    private String adTrack;

    public AuthorShield getShield() {
        return shield;
    }

    public void setShield(AuthorShield shield) {
        this.shield = shield;
    }

    public int getApprovedNotReadyVideoCount() {
        return approvedNotReadyVideoCount;
    }

    public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
        this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isPgc() {
        return isPgc;
    }

    public void setPgc(boolean pgc) {
        isPgc = pgc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getLeatestReleaseTime() {
        return leatestReleaseTime;
    }

    public void setLeatestReleaseTime(long leatestReleaseTime) {
        this.leatestReleaseTime = leatestReleaseTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVideoNum() {
        return videoNum;
    }

    public void setVideoNum(int videoNum) {
        this.videoNum = videoNum;
    }

    public AuthorFollow getFollow() {
        return follow;
    }

    public void setFollow(AuthorFollow follow) {
        this.follow = follow;
    }

    public String getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(String adTrack) {
        this.adTrack = adTrack;
    }

    public VideoAuthor(AuthorShield shield, int approvedNotReadyVideoCount, String icon, String name, String link, boolean isPgc, String description, long leatestReleaseTime, int id, int videoNum, AuthorFollow follow, String adTrack) {

        this.shield = shield;
        this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
        this.icon = icon;
        this.name = name;
        this.link = link;
        this.isPgc = isPgc;
        this.description = description;
        this.leatestReleaseTime = leatestReleaseTime;
        this.id = id;
        this.videoNum = videoNum;
        this.follow = follow;
        this.adTrack = adTrack;
    }

    public VideoAuthor() {

    }

    private class AuthorShield {
        private int itemId;
        private String itemType;
        private boolean shielded;

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public boolean isShielded() {
            return shielded;
        }

        public void setShielded(boolean shielded) {
            this.shielded = shielded;
        }

        public AuthorShield(int itemId, String itemType, boolean shielded) {

            this.itemId = itemId;
            this.itemType = itemType;
            this.shielded = shielded;
        }

        public AuthorShield() {

        }
    }

    private class AuthorFollow {
        private int itemId;
        private String itemType;
        private boolean followed;

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public AuthorFollow(int itemId, String itemType, boolean followed) {

            this.itemId = itemId;
            this.itemType = itemType;
            this.followed = followed;
        }

        public AuthorFollow() {

        }
    }
}
