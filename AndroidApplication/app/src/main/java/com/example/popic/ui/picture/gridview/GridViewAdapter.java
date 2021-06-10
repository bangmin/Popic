package com.example.popic.ui.picture.gridview;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.popic.R;
import com.example.popic.ui.main.listview.ListViewItem;
import com.example.popic.ui.modifyPicture.ModifyPictureActivity;
import com.example.popic.ui.picture.PictureActivity;

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

        // https://www.programmersought.com/article/93024276727/
        convertView.setOnClickListener(v -> {
            //Define PopupMenu object
            PopupMenu popupMenu = new PopupMenu(context, v);
            //Set the layout of PopupMenu object
            popupMenu.getMenuInflater().inflate(R.menu.menu_picture, popupMenu.getMenu());
            //Set the PopupMenu click event
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_picture_modifyPicture:
                            context.startActivity(new Intent(context, ModifyPictureActivity.class));
                            break;
                        case R.id.menu_picture_deletePicture:
                            Toast toast = Toast.makeText(context.getApplicationContext(),"삭제했습니다.", Toast.LENGTH_LONG);
                            toast.show();
                            break;
                    }
                    return true;
                }
            });

            //Show menu
            popupMenu.show();
        });

        return convertView;
    }

    public void addItem(Drawable picture, String name) {
        GridViewItem item = new GridViewItem();

        item.setPictureDrawable(picture);
        item.setNameString(name);

        gridViewItemArrayList.add(item);
    }
}
