package pyreymo.test.fallingblocks;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;


public class GameView extends View implements View.OnClickListener {

    private static final int level = 4;

    private int RectHeight;
    private int RectWidth;
    private int ScrHeight;
    private int ScrWidth;
    private int speed;
    private int score;

    private Random random;

    private boolean onStart = false;
    private boolean onEnd = false;

    private Paint BlackPaint;
    private Paint GrayPaint;
    private Paint RedPaint;
    private Paint LTGrayPaint;

    private ArrayList<RectPosition> RectList;
    private ArrayList<RectPosition> RectList1;

    private RectPosition rectPosition;
    private RectPosition rectPosition1;

    private Activity activity;


    public GameView(Context context) {
        super(context);
        activity = (Activity) context;
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        activity = (Activity) context;
    }


    public void DrawStart(Canvas canvas) {
        RedPaint.setTextSize(200);
        canvas.drawText("开始", ScrWidth / 2 - 200, 500, RedPaint);
    }

    public void OpenEndDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setCancelable(false);
        if (score < 150) alertDialog.setMessage("分数:" + score);
        else if (score < 200) alertDialog.setMessage("太强了。\n\n分数:" + score);
        else alertDialog.setMessage("这么高分我的天啊快快快截图给我看看！\n\n你居然得了" + score + "分！");

        alertDialog.setPositiveButton("再来", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onEnd = false;
                onStart = true;
                init();
                invalidate();
            }
        });

        alertDialog.setNegativeButton("返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                activity.finish();
            }
        });

        alertDialog.show();


    }

    public void DrawScore(Canvas canvas) {
        RedPaint.setTextSize(200);
        if (score < 10) {
            canvas.drawText(String.valueOf(score), ScrWidth / 2 - 50, 200, RedPaint);
        } else if (score < 100) {
            canvas.drawText(String.valueOf(score), ScrWidth / 2 - 100, 200, RedPaint);
        } else {
            canvas.drawText(String.valueOf(score), ScrWidth / 2 - 150, 200, RedPaint);
        }
    }

    public void DrawLine(Canvas canvas) {
        for (int i = 1; i < level; i++) {
            canvas.drawLine(ScrWidth / level * i, 0,
                    ScrWidth / level * i, ScrHeight * 2, GrayPaint);
        }
    }

    public void DrawRect(Canvas canvas) {
        for (int i = 0; i < RectList.size(); i++) {
            rectPosition = new RectPosition();
            rectPosition = RectList.get(i);
            canvas.drawLine(0, rectPosition.getTopPos(), ScrWidth,
                    rectPosition.getTopPos() + 1, GrayPaint);
            canvas.drawRect(rectPosition.getLeftPos(), rectPosition.getTopPos(),
                    rectPosition.getRightPos(),
                    rectPosition.getTopPos() + ScrHeight / level, BlackPaint);
        }
        for (int i = 0; i < RectList1.size(); i++) {
            rectPosition1 = new RectPosition();
            rectPosition1 = RectList1.get(i);
            canvas.drawLine(0, rectPosition1.getTopPos(), ScrWidth,
                    rectPosition1.getTopPos() + 1, GrayPaint);
            canvas.drawRect(rectPosition1.getLeftPos(), rectPosition1.getTopPos(),
                    rectPosition1.getRightPos(),
                    rectPosition1.getTopPos() + ScrHeight / level, LTGrayPaint);
        }
    }

    public void MoveRect() {
        if (RectList.get(RectList.size() - 1).getTopPos() >= ScrHeight - 60) {
            //结束判定
            RectList.remove(RectList.size() - 1);
            onEnd = true;

        }
        if (RectList.get(0).getTopPos() >= -speed) {
            int temp = random.nextInt(level);
            rectPosition = new RectPosition();
            rectPosition.setLeftPos(temp * RectWidth);
            rectPosition.setTopPos(-RectHeight);
            rectPosition.setRightPos(RectWidth * (temp + 1));
            rectPosition.setBottomPos(0);

            RectList.add(0, rectPosition);
        }
        for (int i = 0; i < RectList.size(); i++) {
            RectList.get(i).setBottomPos(RectList.get(i).getBottomPos() + speed);
            RectList.get(i).setTopPos(RectList.get(i).getTopPos() + speed);
        }

        for (int i = 0; i < RectList1.size(); i++) {
            RectList1.get(i).setBottomPos(RectList1.get(i).getBottomPos() + speed);
            RectList1.get(i).setTopPos(RectList1.get(i).getTopPos() + speed);
        }
    }

    public int getScore() {
        return score;
    }

    public void getInfo(int height, int width) {
        this.RectHeight = height / level;
        this.RectWidth = width / level;
        this.ScrHeight = height;
        this.ScrWidth = width;
    }

    public void init() {
        score = 0;
        speed = 30;

        BlackPaint = new Paint();
        BlackPaint.setColor(0xFF222222);
        BlackPaint.setStrokeJoin(Paint.Join.ROUND);
        BlackPaint.setStrokeCap(Paint.Cap.ROUND);
        BlackPaint.setStrokeWidth(2);

        GrayPaint = new Paint();
        GrayPaint.setColor(Color.GRAY);
        GrayPaint.setStrokeJoin(Paint.Join.ROUND);
        GrayPaint.setStrokeCap(Paint.Cap.ROUND);
        GrayPaint.setStrokeWidth(2);

        RedPaint = new Paint();
        RedPaint.setColor(Color.RED);
        RedPaint.setStrokeJoin(Paint.Join.ROUND);
        RedPaint.setStrokeCap(Paint.Cap.ROUND);
        RedPaint.setStrokeWidth(1);

        LTGrayPaint = new Paint();
        LTGrayPaint.setColor(Color.LTGRAY);
        LTGrayPaint.setStrokeJoin(Paint.Join.ROUND);
        LTGrayPaint.setStrokeCap(Paint.Cap.ROUND);
        LTGrayPaint.setStrokeWidth(1);

        random = new Random();
        RectList = new ArrayList<>();
        RectList1 = new ArrayList<>();

        int temp = random.nextInt(level);

        rectPosition = new RectPosition();
        rectPosition.setLeftPos(temp * (ScrWidth / level));
        rectPosition.setRightPos((temp + 1) * (ScrWidth / level));
        rectPosition.setTopPos(-2 * (ScrWidth / level));
        rectPosition.setBottomPos(0);

        RectList.add(rectPosition);

    }

    @Override
    public void onDraw(Canvas canvas) {

        DrawLine(canvas);
        DrawRect(canvas);
        if (onStart && !onEnd) DrawScore(canvas);

        if (onEnd) {
            OpenEndDialog();
        } else {
            if (onStart) {
                MoveRect();
            } else {
                DrawStart(canvas);
            }
            invalidate();
        }
        super.onDraw(canvas);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float X = event.getX();
        float Y = event.getY();

        for (int i = 0; i < RectList.size(); i++) {
            if (RectList.get(i).getLeftPos() <= X
                    && RectList.get(i).getRightPos() >= X
                    && RectList.get(i).getTopPos() <= Y
                    && RectList.get(i).getBottomPos() >= Y - ScrHeight / (level * 4)
                    && RectList.size() != 1) {
                RectList1.add(RectList.get(i));
                RectList.remove(i);

                score++;
                speed = (int) (20 + score * 0.2);
                //速度与分数线性相关
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        if (!Utils.isFastClick()) {
            if (onEnd) {

                onEnd = false;
                onStart = false;
                init();
                invalidate();
            }
        }

        if (!onStart) {
            onStart = true;

        }
    }


}

