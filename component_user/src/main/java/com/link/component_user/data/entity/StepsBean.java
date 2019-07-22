package com.link.component_user.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class StepsBean implements Parcelable {

    /**
     * img : http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/s/44/4323_3adcc38469c97069.jpg
     * step : 1.用烤纸叠好28cmX28cm的方盒，还可以叠的再深一点哦!后面会比较方便.低粉和可可粉混合过筛。蛋黄和蛋白分开
     */

    private String img;
    private String step;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.img);
        dest.writeString(this.step);
    }

    public StepsBean() {
    }

    protected StepsBean(Parcel in) {
        this.img = in.readString();
        this.step = in.readString();
    }

    public static final Creator<StepsBean> CREATOR = new Creator<StepsBean>() {
        @Override
        public StepsBean createFromParcel(Parcel source) {
            return new StepsBean(source);
        }

        @Override
        public StepsBean[] newArray(int size) {
            return new StepsBean[size];
        }
    };
}
