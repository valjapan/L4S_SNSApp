package com.valjapan.l4s_sns_app;

public class UserData {
    public String title;
    public String content;
    public int likeNum = 0;

    public String fireBaseKey;

    public UserData(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
