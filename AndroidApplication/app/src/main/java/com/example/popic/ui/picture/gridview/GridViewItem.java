package com.example.popic.ui.picture.gridview;

import android.graphics.drawable.Drawable;

public class GridViewItem {
    private Drawable pictureDrawable;
    private String nameString;

    public void setPictureDrawable(Drawable pictureDrawable) {
        this.pictureDrawable = pictureDrawable;
    }

    public void setNameString(String nameString) {
        this.nameString = nameString;
    }

    public Drawable getPictureDrawable() {
        return pictureDrawable;
    }

    public String getNameString() {
        return nameString;
    }
}
