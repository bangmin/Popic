package com.example.popic.ui.uploadPicture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    private int type; // 0 : 펜, 1 : 지우개
    private Bitmap bitmap;
    private Canvas canvas;

    public void setType(int type) {
        this.type = type;
    }

    public Bitmap getBitmap() {
        draw(canvas);
        return bitmap;
    }

    private Paint paint = new Paint();

    //여러가지의 그리기 명령을 모았다가 한번에 출력해주는
    //버퍼역할을 담당한다..
    private Path path = new Path();

    private int x, y;

    public DrawView(Context context) {
        super(context);
        paint.setColor(Color.BLACK);
        //STROKE속성을 이용하여 테두리...선...
        //두께
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
        this.canvas.drawPath(path, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawBitmap(bitmap, 0, 0, null);
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

        invalidate();

        return true;
    }

    private void startTouch(int x, int y) {
        path.moveTo(x, y);
    }

    private void moveTouch(int x, int y) {
        path.lineTo(x, y);
    }

    public void reset() {
        path.reset();
        invalidate();
    }
}