package com.zhoulesin.retrofitdemo.bean.video;

import java.util.List;

/**
 * Created by zhoul on 2018/10/8.
 */

public class VideoCategory {
    private boolean adExist;
    private int total;
    private String nextPageUrl;
    private int count;
    private List<Category> itemList;

    public boolean isAdExist() {
        return adExist;
    }

    public void setAdExist(boolean adExist) {
        this.adExist = adExist;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Category> getItemList() {
        return itemList;
    }

    public void setItemList(List<Category> itemList) {
        this.itemList = itemList;
    }

    public VideoCategory(boolean adExist, int total, String nextPageUrl, int count, List<Category> itemList) {

        this.adExist = adExist;
        this.total = total;
        this.nextPageUrl = nextPageUrl;
        this.count = count;
        this.itemList = itemList;
    }

    public VideoCategory() {

    }

    public static class Category {
        private CategoryData data;
        private int adIndex;
        private String tag;
        private int id;
        private String type;

        public CategoryData getData() {
            return data;
        }

        public void setData(CategoryData data) {
            this.data = data;
        }

        public int getAdIndex() {
            return adIndex;
        }

        public void setAdIndex(int adIndex) {
            this.adIndex = adIndex;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Category(CategoryData data, int adIndex, String tag, int id, String type) {

            this.data = data;
            this.adIndex = adIndex;
            this.tag = tag;
            this.id = id;
            this.type = type;
        }

        public Category() {

        }
    }

    public static class CategoryData {
        private String subTitle;
        private String dataType;
        private String iconType;
        private String icon;
        private String actionUrl;
        private boolean ifPgc;
        private String description;
        private int id;
        private String title;
        private Object follow;
        private String adTrack;

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getIconType() {
            return iconType;
        }

        public void setIconType(String iconType) {
            this.iconType = iconType;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getActionUrl() {
            return actionUrl;
        }

        public void setActionUrl(String actionUrl) {
            this.actionUrl = actionUrl;
        }

        public boolean isIfPgc() {
            return ifPgc;
        }

        public void setIfPgc(boolean ifPgc) {
            this.ifPgc = ifPgc;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getFollow() {
            return follow;
        }

        public void setFollow(Object follow) {
            this.follow = follow;
        }

        public String getAdTrack() {
            return adTrack;
        }

        public void setAdTrack(String adTrack) {
            this.adTrack = adTrack;
        }

        public CategoryData(String subTitle, String dataType, String iconType, String icon, String actionUrl, boolean ifPgc, String description, int id, String title, Object follow, String adTrack) {

            this.subTitle = subTitle;
            this.dataType = dataType;
            this.iconType = iconType;
            this.icon = icon;
            this.actionUrl = actionUrl;
            this.ifPgc = ifPgc;
            this.description = description;
            this.id = id;
            this.title = title;
            this.follow = follow;
            this.adTrack = adTrack;
        }

        public CategoryData() {

        }
    }
}
