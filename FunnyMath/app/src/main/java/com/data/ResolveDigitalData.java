package com.data;

import android.graphics.Bitmap;

/**
 * Created by jianfang on 2016/4/15.
 */
public class ResolveDigitalData  extends dataBaseObject {

    private float baseNumber = 0;
    private float tarNumber = 0;
    private int question = 0;
    private String tishi;
    private int answer1;
    private int answer2;
    private Bitmap mBitmap;

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }


    public int getQuestion() {
        question = (int) (answer1 * 2 * tarNumber);
        return question;
    }

    public String getTishi() {
        tishi = "" + question + " = ";
        for (int i = 0; i <= tarNumber; i++) {
            tishi = tishi + "+ ( ? + ? )";
        }
        return tishi;
    }

    public int getAnswer1() {
        answer1 = (int) baseNumber;
        return answer1;
    }

    public int getAnswer2() {
        answer2 = (int) (baseNumber * 2);
        return answer2;
    }

    public String getAnswer(int state) {
        String answer = "";
        switch (state) {
            case 0:
                answer = " A="+answer1+"   B="+answer2;
                break;
            case 1:
                answer = " A="+answer2+"   B="+answer1;
                break;
            case 2:
                answer = " A="+(answer1+1)+"   B="+(answer2-1);
                break;
            case 3:
                answer = " A="+(answer1-1)+"   B="+(answer2+1);
                break;
            default:
                break;
        }
        return answer;
    }

    public void setBaseNumber(float baseNumber) {
        this.baseNumber = baseNumber;
    }

    public void setTarNumber(float tarNumber) {
        this.tarNumber = tarNumber;
    }
    @Override
    public void relice() {
        // TODO Auto-generated method stub
        if (mBitmap != null) {
            mBitmap.recycle();
        }
    }

    @Override
    public String getAnswer() {
        // TODO Auto-generated method stub
        String answer = "";
        return answer = " A  = "+answer1+"  B  = "+answer2;
    }
}