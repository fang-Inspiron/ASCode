package com.data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by jianfang on 2016/4/15.
 */
public class CommonVoid {
    private static final String TAG = "commomvoid";

    /**
     * 生成数角题目
     *
     * @return
     */
    public static CountAngleData makeShuJiaoQuestion() {
        CountAngleData mShujiaodata = new CountAngleData();
        Random mRandom = new Random();
        int state = mRandom.nextInt(2);
        int lineNum = mRandom.nextInt(5) + 2;
        ;
        switch (state) {
            case 0:
                mShujiaodata.setType(state);
                mShujiaodata.setTriNum(lineNum);
                mShujiaodata.setQuestionBitmap();
                mShujiaodata.getRAnswer();
                break;
            case 1:
                mShujiaodata.setType(state);
                mShujiaodata.setTriNum(lineNum);
                mShujiaodata.setQuestionBitmap();
                mShujiaodata.getRAnswer();
                break;
            default:
                break;
        }
        return mShujiaodata;
    }

    private static float base = 10;
    private static float formH = 100;
    private static float formW = 100;

    public static ResolveDigitalData makefenjieshuquestion() {
        ResolveDigitalData mFenjieshudata = new ResolveDigitalData();
        PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0,
                Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        Random mRandom = new Random();
        int tarNumber = mRandom.nextInt(5) + 2;
        mFenjieshudata.setBaseNumber(mRandom.nextInt(3) + 1);
        mFenjieshudata.setTarNumber(tarNumber);
        mFenjieshudata.getAnswer1();
        mFenjieshudata.getAnswer2();
        mFenjieshudata.getQuestion();
        Bitmap mbmpTest = Bitmap.createBitmap(((int) formW + 30),
                ((int) formH + 30), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mbmpTest);
        // canvas.drawARGB(255, 255, 255, 255);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.setDrawFilter(pfd);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(new Rect(((int) base), ((int) base),
                ((int) (formW + base)), ((int) (formH + base))), paint);
        canvas.drawLine(base, formH / 3, formW + base, formH / 3, paint);
        canvas.drawLine(base, formH / 3 * 2, formW + base, formH / 3 * 2, paint);
        float each = formW / tarNumber;
        for (int i = 0; i < (tarNumber); i++) {
            canvas.drawLine(base + each * (i + 1), formH / 3, base + each
                    * (i + 1), formH + base, paint);
            canvas.drawLine(base + each * (i + 1) - (each / 2), formH / 3 * 2,
                    base + each * (i + 1) - (each / 2), formH + base, paint);
        }
        paint.setColor(Color.RED);
        paint.setTextSize(7);
        canvas.drawText("" + mFenjieshudata.getQuestion(), formW / 2 + base,
                formH / 6 + base, paint);
        for (int i = 0; i < tarNumber; i++) {
            canvas.drawText("B", base + each * (i + 1) - (each / 2), formH / 2
                    + base, paint);
            canvas.drawText("A", base + each * (i + 1) - (each / 4 * 3), formH
                    / 6 * 5 + base, paint);
            canvas.drawText("A", base + each * (i + 1) - (each / 4), formH / 6
                    * 5 + base, paint);
        }
        canvas.save();
        canvas.restore();
        mFenjieshudata.setmBitmap(mbmpTest);
        return mFenjieshudata;
    }


    public static ArrayList<VerticalFormulaData> makeshushitianci(
            ArrayList<VerticalFormulaData> mDatalist) {
        ArrayList<VerticalFormulaData> mBaseDatalist = mDatalist;
        Random mRandom = new Random();
        Set set = new TreeSet();
        while (set.size() < 4) {
            int a = mRandom.nextInt(9) + 1;
            if (!set.contains(a)) {
                set.add(a);
            }
        }
        ArrayList<Integer> test = new ArrayList<Integer>(4);
        ArrayList<Integer> test1 = new ArrayList<Integer>(16);
        ArrayList<Integer> test2 = new ArrayList<Integer>(16);
        ArrayList<Integer> test3 = new ArrayList<Integer>(16);
        Iterator<Integer> aaa = set.iterator();
        while (aaa.hasNext()) {
            Integer b = aaa.next();
            test.add(b);
        }
        int a = test.get(0);
        int b = test.get(1);
        int c = test.get(2);
        int d = test.get(3);
        int aa = make10number(a, a);
        test1.add(aa);
        test2.add(aa);
        test3.add(aa);
        int ab = make10number(a, b);
        test1.add(ab);
        test2.add(ab);
        test3.add(ab);
        int ac = make10number(a, c);
        test1.add(ac);
        test2.add(ac);
        test3.add(ac);
        int ad = make10number(a, d);
        test1.add(ad);
        test2.add(ad);
        test3.add(ad);
        int ba = make10number(b, a);
        test1.add(ba);
        test2.add(ba);
        test3.add(ba);
        int bb = make10number(b, b);
        test1.add(bb);
        test2.add(bb);
        test3.add(bb);
        int bc = make10number(b, c);
        test1.add(bc);
        test2.add(bc);
        test3.add(bc);
        int bd = make10number(b, d);
        test1.add(bd);
        test2.add(bd);
        test3.add(bd);
        int ca = make10number(c, a);
        test1.add(ca);
        test2.add(ca);
        test3.add(ca);
        int cb = make10number(c, b);
        test1.add(cb);
        test2.add(cb);
        test3.add(cb);
        int cc = make10number(c, c);
        test1.add(cc);
        test2.add(cc);
        test3.add(cc);
        int cd = make10number(c, d);
        test1.add(cd);
        test2.add(cd);
        test3.add(cd);
        int da = make10number(d, a);
        test1.add(da);
        test2.add(da);
        test3.add(da);
        int db = make10number(d, b);
        test1.add(db);
        test2.add(db);
        test3.add(db);
        int dc = make10number(d, c);
        test1.add(dc);
        test2.add(dc);
        test3.add(dc);
        int dd = make10number(d, d);
        test1.add(dd);
        test2.add(dd);
        test3.add(dd);
        int puNum = 0;
        return testnumber(test1, test2, test3, mBaseDatalist);
    }

    private static ArrayList<VerticalFormulaData> testnumber(
            ArrayList<Integer> aaa, ArrayList<Integer> bbb,
            ArrayList<Integer> ccc, ArrayList<VerticalFormulaData> mBaseDatalist) {

        ArrayList<Integer> test1 = aaa;
        ArrayList<Integer> test2 = bbb;
        ArrayList<Integer> test3 = ccc;
        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        for (int i = 0; i < 16; i++) {
            num1 = test1.get(i);
            test2.remove(i);
            test3.remove(i);
            for (int j = 0; j < 15; j++) {
                num2 = test2.get(j);
                test3.remove(j);
                for (int k = 0; k < 13; k++) {
                    num3 = test3.get(k);
                    if ((num1 - num2) == num3) {
                        VerticalFormulaData mBaseData = makequestion1(num1,num2, num3, false);
                        if (mBaseData != null) {
                            mBaseDatalist.add(mBaseData);
                        }

                    } else if ((num1 + num2) == num3) {
                        VerticalFormulaData mBaseData = makequestion1(num1,num2, num3, true);
                        if (mBaseData != null) {
                            mBaseDatalist.add(mBaseData);
                        }

                    }
                }
                test3.add(j, num2);
            }
            test2.add(i, num1);
            test3.add(i, num1);
        }
        test1.clear();
        test1 = null;
        test2.clear();
        test1 = null;
        test3.clear();
        test1 = null;
        return mBaseDatalist;
    }

    private static VerticalFormulaData makequestion1(int num1, int num2,
                                                    int num3, boolean state) {
        VerticalFormulaData mBaseData = new VerticalFormulaData();
        ArrayList<Integer> aaa = new ArrayList<Integer>();
        Set testSet = new TreeSet();
        String question = "";
        int X = 0;
        int Y = 0;
        int a = 0;
        int b = 0;
        int c1 = 0;//
        int c2 = 0;
        int c3 = 0;
        int c4 = 0;
        int c5 = 0;
        int c6 = 0;
        c1 = num1 / 10;
        c2 = num1 % 10;
        c3 = num2 / 10;
        c4 = num2 % 10;
        c5 = num3 / 10;
        c6 = num3 % 10;
        aaa.add(c1);
        aaa.add(c2);
        aaa.add(c3);
        aaa.add(c4);
        aaa.add(c5);
        aaa.add(c6);
        aaa.add(0);
        if (state) {
            question = "" + num1 + " + " + "" + num2 + " == " + "" + num3;
        } else {
            question = "" + num1 + " - " + "" + num2 + " == " + "" + num3;
        }
        int isthree = 0;
        int smenumber = 0;
        if (!testSet.add(c1)) {

            if (smenumber == 0) {
                smenumber = c1;
                isthree++;
            } else {
                if (smenumber == c1) {
                    isthree++;
                }
            }

        }
        ;
        if (!testSet.add(c2)) {
            if (smenumber == 0) {
                smenumber = c1;
                isthree++;
            } else {
                if (smenumber == c2) {
                    isthree++;
                }
            }
        }
        ;
        if (!testSet.add(c3)) {
            if (smenumber == 0) {
                smenumber = c3;
                isthree++;
            } else {
                if (smenumber == c3) {
                    isthree++;
                }
            }
        }
        ;
        if (!testSet.add(c4)) {
            if (smenumber == 0) {
                smenumber = c4;
                isthree++;
            } else {
                if (smenumber == c4) {
                    isthree++;
                }
            }
        }
        ;
        if (!testSet.add(c5)) {
            if (smenumber == 0) {
                smenumber = c5;
                isthree++;
            } else {
                if (smenumber == c5) {
                    isthree++;
                }
            }
        }
        ;
        if (!testSet.add(c6)) {
            if (smenumber == 0) {
                smenumber = c6;
                isthree++;
            } else {
                if (smenumber == c6) {
                    isthree++;
                }
            }
        }
        ;
        if (testSet.size() == 4 && isthree == 1) {
            mBaseData.setNum1(num1);
            mBaseData.setNum2(num2);
            mBaseData.setNum3(num3);
            mBaseData.setState(state);
            ArrayList<Integer> aaa1 = new ArrayList<Integer>();
            ArrayList<Integer> aaa2 = new ArrayList<Integer>();
            ArrayList<Integer> aaa3 = new ArrayList<Integer>();
            ArrayList<Integer> aaa4 = new ArrayList<Integer>();
            ArrayList<Integer> aaa6 = new ArrayList<Integer>();
            ArrayList<Integer> aaa7 = new ArrayList<Integer>();
            ArrayList<Point> aaa5 = new ArrayList<Point>();
            Set mSet = new TreeSet();
            for (int i = 0; i < 7; i++) {
                for (int j = (i + 1); j < 7; j++) {
                    if (aaa.get(i) == aaa.get(j)) {
                        aaa1.add(aaa.get(i));
                        aaa2.add(i);
                        aaa2.add(j);
                        Point mPoint = new Point();
                        mPoint.x = aaa.get(i);
                        mPoint.y = j;
                        aaa5.add(mPoint);
                        Point mPoint1 = new Point();
                        mPoint1.x = aaa.get(i);
                        mPoint1.y = i;
                        aaa5.add(mPoint1);
                    } else {
                        aaa3.add(aaa.get(i));
                        aaa4.add(i);
                    }
                }
            }
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < aaa3.size(); j++) {
                    if (aaa1.get(i) == aaa3.get(j)) {
                        // aaa3.remove(j);
                        // aaa4.remove(j);
                        aaa6.add(aaa1.get(i));
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < aaa3.size(); j++) {
                    if (aaa2.get(i) == aaa4.get(j)) {
                        // aaa3.remove(j);
                        // aaa4.remove(j);
                        aaa7.add(aaa2.get(i));
                    }
                }
            }
            for (int i = 0; i < aaa6.size(); i++) {
                // int index = aaa6.get(i);
                aaa3.remove(aaa6.get(i));
            }
            for (int i = 0; i < aaa7.size(); i++) {
                // int index = aaa6.get(i);
                aaa4.remove(aaa7.get(i));
            }
            Point mPoint = new Point();
            mPoint.x = aaa3.get(0);
            mPoint.y = aaa4.get(0);
            aaa5.add(mPoint);
            int size = aaa3.size();
            Point mPoint1 = new Point();
            mPoint1.x = aaa3.get(size - 1);
            mPoint1.y = aaa4.get(size - 1);
            aaa5.add(mPoint1);
            mBaseData.setAp(aaa5.get(5));
            mBaseData.setBp(aaa5.get(4));
            mBaseData.setXp1(aaa5.get(0));
            mBaseData.setXp2(aaa5.get(1));
            mBaseData.setYp1(aaa5.get(2));
            mBaseData.setYp2(aaa5.get(3));
            aaa1.clear();
            aaa1 = null;
            aaa2.clear();
            aaa2 = null;
            aaa3.clear();
            aaa3 = null;
            aaa4.clear();
            aaa4 = null;
            aaa5.clear();
            aaa5 = null;
            aaa6.clear();
            aaa6 = null;
            aaa7.clear();
            aaa7 = null;
            mSet.clear();
            mSet = null;
        } else {
            mBaseData = null;
        }
        aaa.clear();
        aaa = null;
        testSet.clear();
        testSet = null;
        return mBaseData;
    }

    private static int make10number(int m10n, int mn) {
        int num = 0;
        num = m10n * 10 + mn;
        return num;
    }

}
