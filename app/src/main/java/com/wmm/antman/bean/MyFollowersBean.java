package com.wmm.antman.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ming on 2016/4/13.
 */
public class MyFollowersBean implements Parcelable {


    /**
     * login : 4148
     * id : 5877145
     * avatar_url : https://avatars.githubusercontent.com/u/5877145?v=3
     * gravatar_id :
     * url : https://api.github.com/users/4148
     * html_url : https://github.com/4148
     * followers_url : https://api.github.com/users/4148/followers
     * following_url : https://api.github.com/users/4148/following{/other_user}
     * gists_url : https://api.github.com/users/4148/gists{/gist_id}
     * starred_url : https://api.github.com/users/4148/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/4148/subscriptions
     * organizations_url : https://api.github.com/users/4148/orgs
     * repos_url : https://api.github.com/users/4148/repos
     * events_url : https://api.github.com/users/4148/events{/privacy}
     * received_events_url : https://api.github.com/users/4148/received_events
     * type : User
     * site_admin : false
     */

    private String login;
    private int id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private boolean site_admin;

    protected MyFollowersBean(Parcel in) {
        login = in.readString();
        id = in.readInt();
        avatar_url = in.readString();
        gravatar_id = in.readString();
        url = in.readString();
        html_url = in.readString();
        followers_url = in.readString();
        following_url = in.readString();
        gists_url = in.readString();
        starred_url = in.readString();
        subscriptions_url = in.readString();
        organizations_url = in.readString();
        repos_url = in.readString();
        events_url = in.readString();
        received_events_url = in.readString();
        type = in.readString();
        site_admin = in.readByte() != 0;
    }

    public static final Creator<MyFollowersBean> CREATOR = new Creator<MyFollowersBean>() {
        @Override
        public MyFollowersBean createFromParcel(Parcel in) {
            return new MyFollowersBean(in);
        }

        @Override
        public MyFollowersBean[] newArray(int size) {
            return new MyFollowersBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeInt(id);
        dest.writeString(avatar_url);
        dest.writeString(gravatar_id);
        dest.writeString(url);
        dest.writeString(html_url);
        dest.writeString(followers_url);
        dest.writeString(following_url);
        dest.writeString(gists_url);
        dest.writeString(starred_url);
        dest.writeString(subscriptions_url);
        dest.writeString(organizations_url);
        dest.writeString(repos_url);
        dest.writeString(events_url);
        dest.writeString(received_events_url);
        dest.writeString(type);
        dest.writeByte((byte) (site_admin ? 1 : 0));
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }
}
