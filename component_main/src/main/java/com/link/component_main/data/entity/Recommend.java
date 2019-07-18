package com.link.component_main.data.entity;

import cn.bmob.v3.BmobObject;

public class Recommend extends BmobObject {

    private String banner;

    private String today;

    private String more;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }


}
