package com.example.popic.ui.uploadPicture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    private int type; // 0 : 펜, 1 : 지우개

    public void setType(int type) {
        this.type = type;
    }

    private Bitmap bitmap;

    private Paint paint = new Paint();
    private Paint paintEraser = new Paint();

    //여러가지의 그리기 명령을 모았다가 한번에 출력해주는
    //버퍼역할을 담당한다..
    private Path path = new Path();
    private Path pathEraser = new Path();

    private int x, y;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (type) {
            case 0: // 펜
                paint.setColor(Color.BLACK);
                //STROKE속성을 이용하여 테두리...선...
                paint.setStyle(Paint.Style.STROKE);
                //두께
                paint.setStrokeWidth(3);
                canvas.drawPath(path, paint);
                break;
            case 1: // 지우개
                paintEraser.setColor(Color.TRANSPARENT);
                //STROKE속성을 이용하여 테두리...선...
                paintEraser.setStyle(Paint.Style.STROKE);
                //두께
                paintEraser.setStrokeWidth(3);
                canvas.drawPath(pathEraser, paintEraser);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                x = (int) event.getX();
                y = (int) event.getY();
                moveTouch(x, y);
                break;
        }

        //View의 onDraw()를 호출하는 메소드...
        invalidate();

        return true;
    }

    private void startTouch(int x, int y) {
        switch (type) {
            case 0: // 펜
                path.moveTo(x, y);
                break;
            case 1: // 지우개
                pathEraser = new Path();
                pathEraser.moveTo(x, y);
                break;
        }
    }

    private void moveTouch(int x, int y) {
        switch (type) {
            case 0: // 펜
                path.lineTo(x, y);
                break;
            case 1: // 지우개
                pathEraser.lineTo(x, y);
                break;
        }
    }

    public void reset() {
        path.reset();
        invalidate();
    }
}