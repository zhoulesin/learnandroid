package com.zhoulesin.retrofitdemo.bean.video;

/**
 * Created by zhoul on 2018/10/8.
 */

public class HomeTab {
    private int nameType;
    private String apiUrl;
    private String name;
    private int tabType;
    private int id;
    private String adTrack;

    public int getNameType() {
        return nameType;
    }

    public void setNameType(int nameType) {
        this.nameType = nameType;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTabType() {
        return tabType;
    }

    public void setTabType(int tabType) {
        this.tabType = tabType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdTrack() {
        return adTrack;
    }

    public void setAdTrack(String adTrack) {
        this.adTrack = adTrack;
    }

    public HomeTab(int nameType, String apiUrl, String name, int tabType, int id, String adTrack) {

        this.nameType = nameType;
        this.apiUrl = apiUrl;
        this.name = name;
        this.tabType = tabType;
        this.id = id;
        this.adTrack = adTrack;
    }

    public HomeTab() {

    }
}
