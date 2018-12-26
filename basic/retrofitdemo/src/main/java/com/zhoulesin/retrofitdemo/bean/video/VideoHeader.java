package com.zhoulesin.retrofitdemo.bean.video;

/**
 * Created by zhoul on 2018/10/8.
 */

class VideoHeader {
    /**
     *
     "textAlign": "left",
     "subTitleFont": null,
     "actionUrl": "eyepetizer://pgc/detail/2162/?title=å¼ç¼å¹¿åç²¾é&userType=PGC&tabIndex=1",
     "icon": "http://img.kaiyanapp.com/98beab66d3885a139b54f21e91817c4f.jpeg",
     "description": "#广告 / 收录于 每日编辑精选",
     "showHateVideo": false,
     "label": null,
     "title": "开眼广告精选",
     "cover": null,
     "rightText": null,
     "labelList": null,
     "subTitle": null,
     "iconType": "round",
     "id": 125747,
     "time": 1538784003000,
     "font": null

     */
    private String textAlign;
    private String subTitleFont;
    private String actionUrl;
    private String icon;
    private String description;
    private boolean showHateVideo;
    private String label;
    private String title;
    private String cover;
    private String rightText;
    private String labelList;
    private String subTitle;
    private String iconType;
    private int id;
    private long time;
    private String font;

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    public String getSubTitleFont() {
        return subTitleFont;
    }

    public void setSubTitleFont(String subTitleFont) {
        this.subTitleFont = subTitleFont;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isShowHateVideo() {
        return showHateVideo;
    }

    public void setShowHateVideo(boolean showHateVideo) {
        this.showHateVideo = showHateVideo;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getRightText() {
        return rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public String getLabelList() {
        return labelList;
    }

    public void setLabelList(String labelList) {
        this.labelList = labelList;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public VideoHeader(String textAlign, String subTitleFont, String actionUrl, String icon, String description, boolean showHateVideo, String label, String title, String cover, String rightText, String labelList, String subTitle, String iconType, int id, long time, String font) {

        this.textAlign = textAlign;
        this.subTitleFont = subTitleFont;
        this.actionUrl = actionUrl;
        this.icon = icon;
        this.description = description;
        this.showHateVideo = showHateVideo;
        this.label = label;
        this.title = title;
        this.cover = cover;
        this.rightText = rightText;
        this.labelList = labelList;
        this.subTitle = subTitle;
        this.iconType = iconType;
        this.id = id;
        this.time = time;
        this.font = font;
    }

    public VideoHeader() {

    }
}
