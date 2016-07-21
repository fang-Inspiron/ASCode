package com.data;

import java.util.Random;

public class MultiplyData {

    Random random = new Random();
    StringBuffer question1 = new StringBuffer();
    StringBuffer question2 = new StringBuffer();
    String answer;
    String answer1;
    String answer2;
    String answer3;

    public MultiplyData() {
        //n代表生成的乘数个数
        int n = 6;
        //ran代表生成的乘数（30以内）
        int ran = random.nextInt(30)+1;
        //result代表最终的乘积
        int result = 0;
        //初始化question1，question2，answer
        for (int i = 0; i < n; i++) {
            question1 = question1.append(ran + "+");
            result += ran;
        }
        question1.deleteCharAt(question1.length() - 1);
        question1.append("=" + result);
        question2.append("x*y=" + result);
        answer = "x=" + ran + "   y=" + n;

        //随机生成给定范围内N个不重复的数，并且不能和正确答案重复
        int[] x_arr = new int[3];
        int[] y_arr = new int[3];
        int count1 = 0;
        int count2 = 0;
        while (count1 < 3) {
            //int num = (int) (Math.random() * (max-min)) + min;
            int num1 = (int) (Math.random() * 9) + (ran - 3);
            boolean flag = true;
            for (int i = 0; i < 3; i++) {
                if ((num1 == x_arr[i]) || (num1 == ran) || (num1 <= 0)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                x_arr[count1] = num1;
                count1++;
            }
        }

        while (count2 < 3) {
            //int num = (int) (Math.random() * (max-min)) + min;
            int num2 = (int) (Math.random() * 7) + (n - 5);
            boolean flag = true;
            for (int i = 0; i < 3; i++) {
                if ((num2 == y_arr[i]) || (num2 == n) || (num2 <= 0)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                y_arr[count2] = num2;
                count2++;
            }
        }
        answer1 = "x=" + x_arr[0] + "   y=" + y_arr[0];
        answer2 = "x=" + x_arr[1] + "   y=" + y_arr[1];
        answer3 = "x=" + x_arr[2] + "   y=" + y_arr[2];
    }

    public StringBuffer getQuestion1() {
        return question1;
    }

    public StringBuffer getQuestion2() {
        return question2;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

}
