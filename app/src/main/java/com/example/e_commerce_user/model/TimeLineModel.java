package com.example.e_commerce_user.model;

public class TimeLineModel {
    String Title;
    String detail;
    int status;


    public TimeLineModel(String title, String detail, int status) {
        Title = title;
        this.detail = detail;
        this.status = status;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getStatus() {
        return status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
