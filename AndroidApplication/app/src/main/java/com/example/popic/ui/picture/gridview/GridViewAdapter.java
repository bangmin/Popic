package com.example.popic.ui.picture.gridview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.popic.R;
import com.example.popic.ui.main.listview.ListViewItem;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<GridViewItem> gridViewItemArrayList = new ArrayList<>();

    @Override
    public int getCount() {
        return gridViewItemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.gridview_item_picture, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        ImageView pictureImageView = (ImageView) convertView.findViewById(R.id.gridview_item_picture_image);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.gridview_item_picture_name);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        GridViewItem gridViewItem = gridViewItemArrayList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        pictureImageView.setImageDrawable(gridViewItem.getPictureDrawable());
        nameTextView.setText(gridViewItem.getNameString());

        return convertView;
    }

    public void addItem(Drawable picture, String name) {
        GridViewItem item = new GridViewItem();

        item.setPictureDrawable(picture);
        item.setNameString(name);

        gridViewItemArrayList.add(item);
    }
}
