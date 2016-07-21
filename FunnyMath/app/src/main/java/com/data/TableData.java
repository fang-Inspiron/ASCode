package com.data;

import java.util.Random;

public class TableData {

    Random random = new Random();
    String question;
    String answer;
    String answer1;
    String answer2;
    String answer3;

    public TableData() {
        int x = random.nextInt(9) + 2;
        int y = random.nextInt(9) + 2;
        int z = random.nextInt(50) + 1;
        int sum = x * y + z;

        question = "请在横线处填上合适的符号："+x+"__"+y+"__"+z+"__"+sum;
        answer = "*   +   =";
        answer1 = "+   *   =";
        answer2 = "+   =   *";
        answer3 = "=   +   *";
    }

    public String getQuestion() {
        return question;
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
