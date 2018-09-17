package com.valjapan.l4s_sns_app;

public class UserData {
    public String title;
    public String content;
    public int likeNum;

    public String fireBaseKey;

    public UserData(String title, String content, int likeNum, String fireBaseKey) {
        this.title = title;
        this.content = content;
        this.likeNum = likeNum;
        this.fireBaseKey = fireBaseKey;
    }
}
