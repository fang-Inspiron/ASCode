package com.data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Point;
import android.util.Log;

/**
 * Created by jianfang on 2016/4/15.
 */
public class VerticalFormulaData extends dataBaseObject {
    private static final String TAG = "zimushushiBaseData";

    private int num1;
    private int num2;
    private int num3;
    private boolean state;
    private Bitmap mQuestionBitmap;

    private Point xp1;
    private Point xp2;
    private Point yp1;
    private Point yp2;
    private Point ap;
    private Point bp;
    private int X;
    private int Y;
    private int A;
    private int B;
    private int XP1;
    private int XP2;
    private int YP1;
    private int YP2;
    private int AP;
    private int BP;
    private String question;

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getNum3() {
        return num3;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setXp1(Point xp1) {
        X = xp1.x;
        XP1 = xp1.y;
        this.xp1 = xp1;
    }

    public void setXp2(Point xp2) {
        X = xp2.x;
        XP2 = xp2.y;
        this.xp2 = xp2;
    }

    public void setYp1(Point yp1) {
        Y = yp1.x;
        YP1 = yp1.y;
        this.yp1 = yp1;
    }

    public void setYp2(Point yp2) {
        Y = yp2.x;
        YP2 = yp2.y;
        this.yp2 = yp2;
    }

    public void setAp(Point ap) {
        A = ap.x;
        AP = ap.y;
        this.ap = ap;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getB() {
        return B;
    }

    public int getXP1() {
        return XP1;
    }

    public int getXP2() {
        return XP2;
    }

    public int getBP() {
        return BP;
    }

    public int getA() {
        return A;
    }

    public int getAP() {
        return AP;
    }

    public void setBp(Point bp) {
        B = bp.x;
        BP = bp.y;
        this.bp = bp;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public void relice() {
        // TODO Auto-generated method stub
        if (mQuestionBitmap != null) {
            mQuestionBitmap.recycle();
            mQuestionBitmap = null;
        }
    }
    //
    Point p1 = new Point(20, 10);
    Point p2 = new Point(30, 10);
    Point p3 = new Point(20, 20);
    Point p4 = new Point(30, 20);
    Point p5 = new Point(20, 34);
    Point p6 = new Point(30, 34);

    //	Point p1 = new Point(20, 20);
//	Point p2 = new Point(30, 20);
//	Point p3 = new Point(20, 10);
//	Point p4 = new Point(30, 10);
//	Point p5 = new Point(20, 34);
//	Point p6 = new Point(30, 34);
    Point linPointS = new Point(10, 23);
    Point LinPointE = new Point(42, 23);
    Point markPoint = new Point(13, 20);

    public Bitmap getQuestionBitmap() {
        // TODO Auto-generated method stub
        PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0,
                Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        mQuestionBitmap = Bitmap.createBitmap(50, 50, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(mQuestionBitmap);
        // mCanvas.setDrawFilter(pfd);
        Paint mPaint = new Paint();
        // mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        mPaint.setTextSize(10);
        Log.i(TAG, " xp1 = " + XP1 + " xp2  = " + XP2 + " Yp1 = " + YP1 + "  YP2  = " + YP2 + "  AP = " + AP + " BP = " + BP);
        Log.i(TAG, " X = "+X+" Y  = "+Y+" A = "+A+"  B  = "+B);
        drawtext("X", XP1, mCanvas, mPaint);
        drawtext("X", XP2, mCanvas, mPaint);
        drawtext("Y", YP1, mCanvas, mPaint);
        drawtext("Y", YP2, mCanvas, mPaint);


//		drawtext(""+X, XP1, mCanvas, mPaint);
//		drawtext(""+X, XP2, mCanvas, mPaint);
//		drawtext(""+Y, YP1, mCanvas, mPaint);
//		drawtext(""+Y, YP2, mCanvas, mPaint);
        drawtext("" + A, AP, mCanvas, mPaint);
        drawtext("" + B, BP, mCanvas, mPaint);
        drawtext("" + B, 7, mCanvas, mPaint);
        mCanvas.drawLine(linPointS.x, linPointS.y, LinPointE.x, LinPointE.y,mPaint);
        mCanvas.save();
        mCanvas.restore();
        return mQuestionBitmap;
    }
    private void drawtext(String num, int pos, Canvas mCanvas, Paint mPaint) {
        switch (pos) {
            case 0:
                mCanvas.drawText(num, p1.x, p1.y, mPaint);
                break;

            case 1:
                mCanvas.drawText(num, p2.x, p2.y, mPaint);
                break;

            case 2:
                mCanvas.drawText(num, p3.x, p3.y, mPaint);
                break;

            case 3:
                mCanvas.drawText(num, p4.x, p4.y, mPaint);
                break;

            case 4:
                mCanvas.drawText(num, p5.x, p5.y, mPaint);
                break;

            case 5:
                mCanvas.drawText(num, p6.x, p6.y, mPaint);
                break;
            case 7:
                if (state) {
                    mCanvas.drawText("+", markPoint.x, markPoint.y, mPaint);
                } else {
                    mCanvas.drawText("-", markPoint.x, markPoint.y, mPaint);
                }

                break;
            default:
                break;
        }
    }

    public String getAnswer() {
        question = "X  = "+ X+"    Y  = "+Y;
        return question;
    }

    public String getAnswer1() {
        question = "X  = "+ (X+1)+"    Y  = "+(Y-1);
        return question;
    }

    public String getAnswer2() {
        question = "X  = "+ Y+"    Y  = "+X;
        return question;
    }

    public String getAnswer3() {
        question = "X  = "+ (X-1)+"    Y  = "+(Y+1);
        return question;
    }
}
