package com.valjapan.l4s_sns_app;

public class UserData {
    public String title;
    public String content;
    public int likeNum = 0;

    public String fireBaseKey;

    public UserData(String fireBaseKey, String title, String content, int likeNum) {
        this.fireBaseKey = fireBaseKey;
        this.title = title;
        this.content = content;
        this.likeNum = likeNum;

    }

    public UserData() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFireBaseKey() {
        return fireBaseKey;
    }

    public void setFireBaseKey(String fireBaseKey) {
        this.fireBaseKey = fireBaseKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }
}
