package com.example.popic.ui.educlass.listview;

public class ListViewItem {
    private String titleStr;
    private String descStr;
    private int pos_edu;

    public void setTitle(String title) {
        titleStr = title;
    }

    public void setDesc(String desc) {
        descStr = desc;
    }

    public void setPos_edu(int pos) {
        pos_edu = pos;
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
}
