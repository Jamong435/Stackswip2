package com.kim9212.stackswip;

public class TeamItem {

    String title;
    String msg;
    String email;
    String member;

    public TeamItem(String title, String msg, String email, String member, String period, String area) {
        this.title = title;
        this.msg = msg;
        this.email = email;
        this.member = member;
        this.period = period;
        this.area = area;
    }

    String period;
    String area;



    public TeamItem() {
    }
}


