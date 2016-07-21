package com.data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Point;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by jianfang on 2016/4/11.
 */
public class CountAngleData extends dataBaseObject {
    private static final String TAG = "shujiaodata";
    private Bitmap QuestionBitmap;
    private int answer;
    private int sqranswer00;
    private int sqranswer01;
    private int sqranswer10;
    private int sqranswer11;
    private int sqranswer20;
    private int sqranswer21;
    private int sqranswer30;
    private int sqranswer31;
    private int type;
    private int triNum;


    public void setType(int type) {
        this.type = type;
    }

    public void setTriNum(int triNum) {
        this.triNum = triNum;
    }

    public Bitmap getQuestionBitmap() {
        return QuestionBitmap;
    }

    public void setQuestionBitmap() {
        switch (type) {
            case 0:
                triShape(triNum, type);
                break;
            case 1:
                triShape(triNum, type);
                break;
            default:
                break;
        }

    }

    public void getRAnswer() {
        if (type == 0) {
            answer = jiecheng(triNum) / (jiecheng(2) * jiecheng(triNum - 2));
        } else {
            answer = jiecheng(triNum + 2) / (jiecheng(2) * jiecheng(triNum))
                    + 2 * triNum;
        }
    }

    private Point pointO;
    private Point point1;
    private Point point2;

    private void triShape(int triNum, int type) {
        PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
        QuestionBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        if (type == 0) {
            pointO = new Point(10, 90);
            point1 = new Point(90, 90);
            point2 = getPointOne(pointO, point1, 80, 60);
            Canvas canvas = new Canvas(QuestionBitmap);

            Paint mPaint = new Paint();
            mPaint.setAntiAlias(true);
            canvas.setDrawFilter(pfd);
            mPaint.setStrokeWidth(1);
            canvas.drawLine(pointO.x, pointO.y, point1.x, point1.y, mPaint);
            canvas.drawLine(pointO.x, pointO.y, point2.x, point2.y, mPaint);
            int a = 60 / triNum;
            int b = a;
            for (int i = 0; i < (triNum - 2); i++) {
                point2 = getPointOne(pointO, point1, 80, b + a * i);
                canvas.drawLine(10, 90, point2.x, point2.y, mPaint);
            }
            canvas.save();
            canvas.restore();
        } else {
            pointO = new Point(50, 10);
            point1 = new Point(10, 90);
            point2 = new Point(90, 90);
            Canvas canvas = new Canvas(QuestionBitmap);
            Paint mPaint = new Paint();
            mPaint.setAntiAlias(true);
            canvas.setDrawFilter(pfd);
            mPaint.setStrokeWidth(1);
            canvas.drawLine(pointO.x, pointO.y, point1.x, point1.y, mPaint);
            canvas.drawLine(pointO.x, pointO.y, point2.x, point2.y, mPaint);
            canvas.drawLine(point1.x, point1.y, point2.x, point2.y, mPaint);
            int b = 460;
            Set set = new TreeSet();
            Random mRandom = new Random();
            Point mPoint1 = new Point();
            while (set.size() < triNum) {
                int a = 8 * (mRandom.nextInt(8)+2);
                if (!set.contains(a)) {
                    set.add(a);
                }
            }
            Iterator<Integer> aaa = set.iterator();
            while (aaa.hasNext()) {
                b = aaa.next();
                mPoint1.set(b, 90);
                canvas.drawLine(pointO.x, pointO.y, mPoint1.x, mPoint1.y,
                        mPaint);
            }
            canvas.save();
            canvas.restore();
        }

    }

    public String getAnswer() {
        if (type > 1) {
            return "直角个数为：" + sqranswer01 + " 角的个数为：" + sqranswer00;
        } else {
            return "" + answer;
        }
    }

    public String getAnswer1() {

        if (type > 1) {
            return "直角个数为：" + sqranswer11 + " 角的个数为：" + sqranswer10;
        } else {
            return "" + (answer + 2);
        }
    }

    public String getAnswer2() {

        if (type > 1) {
            return "直角个数为：" + sqranswer21 + " 角的个数为：" + sqranswer20;
        } else {
            return "" + (answer + 1);
        }

    }

    public String getAnswer3() {
        if (type > 1) {
            return "直角个数为：" + sqranswer31 + " 角的个数为：" + sqranswer30;
        } else {
            return "" + (answer - 1);
        }
    }

    @Override
    public void relice() {
        // TODO Auto-generated method stub
        if (QuestionBitmap != null) {
            this.QuestionBitmap.recycle();
        }
    }

    private int jiecheng(int a) {
        int b = 1;
        int c = a;
        for (int i = 0; i < (c - 1); i++) {
            a = (a - 1);
            b = b * a;
        }
        return b;
    }

    /**
     * 通过给定圆心O、半径R、圆上一点A、 返回以这个旋转某个角度后的
     *
     * @param pointO
     *            给定的圆心
     * @param pointA
     *            任意一点
     * @param R
     *            园半径
     * @param intAngel 移动角度
     * @return 以给定点旋转给定角度后的点坐标
     */
    public Point getPointOne(Point pointO, Point pointA, int R, double intAngel) {
        Point returnPoint = new Point();

        // 计算已知点与X轴的夹角
        int ang = (int) getAngleOne(pointO, pointA);
        intAngel += Math.abs(ang);

        // 开始象限判断
        float tmp = (float) (intAngel * 1.0f) + 360;
        float rf = 0;

        tmp = tmp % 360;
        if (tmp >= 0 && tmp < 90) {
            rf = (float) (tmp / 180.0f * Math.PI);
            returnPoint.x = (int) (pointO.x + R * Math.cos(rf));
            returnPoint.y = (int) (pointO.y - R * Math.sin(rf));
        }
        if (tmp >= 90 && tmp < 180) {
            tmp = 180 - tmp;
            rf = (float) (tmp / 180.0f * Math.PI);
            returnPoint.x = (int) (pointO.x - R * Math.cos(rf));
            returnPoint.y = (int) (pointO.y - R * Math.sin(rf));
        }
        if (tmp >= 180 && tmp < 270) {
            tmp = tmp - 180;
            rf = (float) (tmp / 180.0f * Math.PI);
            returnPoint.x = (int) (pointO.x - R * Math.cos(rf));
            returnPoint.y = (int) (pointO.y + R * Math.sin(rf));
        }
        if (tmp >= 270 && tmp < 360) {
            tmp = 360 - tmp;
            rf = (float) (tmp / 180.0f * Math.PI);
            returnPoint.x = (int) (pointO.x + R * Math.cos(rf));
            returnPoint.y = (int) (pointO.y + R * Math.sin(rf));
        }

        return returnPoint;
    }

    /**
     * 通过给定圆心O,任意一点 返回点和圆心与X轴形成的角度
     */
    private double getAngleOne(Point pointO, Point pointA) {

        double dblAngle = 0;

        // 计算已知点与X轴的夹角

        if (pointA.x >= pointO.x && pointA.y < pointO.y) {
            dblAngle = Math.atan(Math.abs(pointA.y - pointO.y) * 1.0f
                    / Math.abs(pointA.x - pointO.x) * 1.0f)
                    / Math.PI * 180;
        }

        if (pointA.x >= pointO.x && pointA.y >= pointO.y) {
            dblAngle = Math.atan(Math.abs(pointA.y - pointO.y) * 1.0f
                    / Math.abs(pointA.x - pointO.x) * 1.0f)
                    / Math.PI * 180;
            dblAngle = 360 - dblAngle;
        }

        if (pointA.x < pointO.x && pointA.y >= pointO.y) {
            dblAngle = Math.atan(Math.abs(pointA.y - pointO.y) * 1.0f
                    / Math.abs(pointA.x - pointO.x) * 1.0f)
                    / Math.PI * 180;
            dblAngle = 180 + dblAngle;
        }

        if (pointA.x < pointO.x && pointA.y < pointO.y) {
            dblAngle = Math.atan(Math.abs(pointA.y - pointO.y) * 1.0f
                    / Math.abs(pointA.x - pointO.x) * 1.0f)
                    / Math.PI * 180;
            dblAngle = 180 - dblAngle;
        }
        return dblAngle;
    }
}