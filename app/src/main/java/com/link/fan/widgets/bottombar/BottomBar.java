package com.link.fan.widgets.bottombar;

import java.util.List;

public class BottomBar {

    /**
     * activeColor : #333333
     * inActiveColor : #666666
     * tabs : [{"size":24,"enable":true,"searchByCategory":0,"pageUrl":"main/tabs/getHomeDataList","title":"首页"},{"size":24,"enable":true,"searchByCategory":1,"pageUrl":"main/tabs/sofa","title":"沙发"},{"size":40,"enable":true,"searchByCategory":2,"tintColor":"#ff678f","pageUrl":"main/tabs/publish","title":""},{"size":24,"enable":true,"searchByCategory":3,"pageUrl":"main/tabs/find","title":"发现"},{"size":24,"enable":true,"searchByCategory":4,"pageUrl":"main/tabs/my","title":"我的"}]
     */

    public String activeColor;
    public String inActiveColor;
    public List<Tab> tabs;
    public int selectTab;//底部导航栏默认选中项

    public String getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(String activeColor) {
        this.activeColor = activeColor;
    }

    public String getInActiveColor() {
        return inActiveColor;
    }

    public void setInActiveColor(String inActiveColor) {
        this.inActiveColor = inActiveColor;
    }

    public List<Tab> getTabs() {
        return tabs;
    }

    public void setTabs(List<Tab> tabs) {
        this.tabs = tabs;
    }

    public int getSelectTab() {
        return selectTab;
    }

    public void setSelectTab(int selectTab) {
        this.selectTab = selectTab;
    }

    public static class Tab {
        /**
         * size : 24
         * enable : true
         * searchByCategory : 0
         * pageUrl : main/tabs/getHomeDataList
         * title : 首页
         * tintColor : #ff678f
         */

        public int size;
        public boolean enable;
        public int index;
        public String pageUrl;
        public String title;
        public String tintColor;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getPageUrl() {
            return pageUrl;
        }

        public void setPageUrl(String pageUrl) {
            this.pageUrl = pageUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTintColor() {
            return tintColor;
        }

        public void setTintColor(String tintColor) {
            this.tintColor = tintColor;
        }
    }
}
