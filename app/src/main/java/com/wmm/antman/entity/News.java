package com.wmm.antman.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：wangmingming on 2015/8/25 10:42
 * 邮箱：cy_wangming@163.com
 * 文化预告实体
 */
public class News implements Parcelable {

    private String activityId;
    private String activityIconUrl;
    private String activityAbleCount;
    private String activityName;
    private String activityStartTime;
    private String activityAddress;
    private String activitySalesOnline;
    private String activityIsReservation;
    private String activityHost;

    protected News(Parcel in) {
        activityId = in.readString();
        activityIconUrl = in.readString();
        activityAbleCount = in.readString();
        activityName = in.readString();
        activityStartTime = in.readString();
        activityAddress = in.readString();
        activitySalesOnline = in.readString();
        activityIsReservation = in.readString();
        activityHost = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getActivityHost() {
        return activityHost;
    }

    public void setActivityHost(String activityHost) {
        this.activityHost = activityHost;
    }

    public String getActivityIsReservation() {
        return activityIsReservation;
    }

    public void setActivityIsReservation(String activityIsReservation) {
        this.activityIsReservation = activityIsReservation;
    }

    public String getActivitySalesOnline() {
        return activitySalesOnline;
    }

    public void setActivitySalesOnline(String activitySalesOnline) {
        this.activitySalesOnline = activitySalesOnline;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public String getActivityIconUrl() {
        return activityIconUrl;
    }

    public void setActivityIconUrl(String activityIconUrl) {
        this.activityIconUrl = activityIconUrl;
    }

    public String getActivityAbleCount() {
        return activityAbleCount;
    }

    public void setActivityAbleCount(String activityAbleCount) {
        this.activityAbleCount = activityAbleCount;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(activityId);
        dest.writeString(activityIconUrl);
        dest.writeString(activityAbleCount);
        dest.writeString(activityName);
        dest.writeString(activityStartTime);
        dest.writeString(activityAddress);
        dest.writeString(activitySalesOnline);
        dest.writeString(activityIsReservation);
        dest.writeString(activityHost);
    }
}
