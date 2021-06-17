package com.example.popic.ui.educlass.listview;

public class ListViewItem {
    private String titleStr;
    private String descStr;
    private int pos_edu;
    String edu_id;
    public void setTitle(String title) {
        titleStr = title;
    }

    public void setDesc(String desc) {
        descStr = desc;
    }

    public void setPos_edu(int pos) {
        pos_edu = pos;
    }

    public void setEdu_id(String id) {
        edu_id = id;
    }
    public String getTitle() {
        return this.titleStr;
    }

    public String getDesc() {
        return this.descStr;
    }

    public int getPos_edu() {
        return this.pos_edu;
    }

    public String getEdu_id() {
        return this.edu_id;
    }
}
