package com.example.popic.ui.main.listview;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable;
    private String titleStr;
    private String descStr;
    private String edu_id;

    public void setIcon(Drawable icon) {
        iconDrawable = icon;
    }

    public void setTitle(String title) {
        titleStr = title;
    }

    public void setDesc(String desc) {
        descStr = desc;
    }

    public void setEdu_id(String id) { this.edu_id = id; }

    public Drawable getIcon() {
        return this.iconDrawable;
    }

    public String getTitle() {
        return this.titleStr;
    }

    public String getDesc() {
        return this.descStr;
    }

    public String getEdu_id() {
        return this.edu_id;
    }
}
