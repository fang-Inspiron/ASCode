package com.data;

public class MeterData {

    String[] questions = new String[10];
    String question, answer, ans1, ans2, ans3;

    public MeterData(int count) {
        questions[0] = "测量某东西长度，东西一端在4米23厘米刻度上，另一端在9米4厘米刻度上，这东西长多少？";
        questions[1] = "小明与小青拿了一段开头断了一截的卷尺测量一根木材的长度，木材一端在8厘米的刻度上，另一端在72厘米的刻度上，这根木材长多少？";
        questions[2] = "用卷尺测量一根木材的长度，木材的一端在6米18厘米刻度上，另一端在7米刻度上，这根木材长多少米？";
        questions[3] = "陈燕与李萍量一个花坛长度，她们将卷尺的2米14厘米的刻度对准花坛的一端，另一端是6米35厘米，这个花坛长多少？";
        questions[4] = "李玲一个人用自己的卷尺测量绳子的长度用脚踩着卷尺的一端，正好踩去4米68厘米长，在另一头的刻度是6米73厘米，李玲绳子的长度是多少？";
        questions[5] = "测量某东西长度，东西一端在1米15厘米刻度上，另一端在1米51厘米刻度上，这东西长多少？";
        questions[6] = "小明与小青拿了一段开头断了一截的卷尺测量一根木材的长度，木材一端在3米26厘米的刻度上，另一端在5米7厘米的刻度上，这根木材长多少？";
        questions[7] = "用卷尺测量一根木材的长度，木材的一端在6米18厘米刻度上，另一端在7米13厘米刻度上，这根木材长多少米？";
        questions[8] = "陈燕与李萍量一个花坛长度，她们将卷尺的3米51厘米的刻度对准花坛的一端，另一端是5米28厘米，这个花坛长多少？";
        questions[9] = "李玲一个人用自己的卷尺测量绳子的长度用脚踩着卷尺的一端，正好踩去5米72厘米长，在另一头的刻度是8米81厘米，李玲绳子的长度是多少？";

        switch (count) {
            case 0:
                question = questions[0];
                answer = "4米81厘米";
                ans1 = "4米91厘米";
                ans2 = "4米79厘米";
                ans3 = "4米71厘米";
                break;
            case 1:
                question = questions[1];
                answer = "64厘米";
                ans1 = "63厘米";
                ans2 = "65厘米";
                ans3 = "62厘米";
                break;
            case 2:
                question = questions[2];
                answer = "82厘米";
                ans1 = "83厘米";
                ans2 = "81厘米";
                ans3 = "80厘米";
                break;
            case 3:
                question = questions[3];
                answer = "4米21厘米";
                ans1 = "4米31厘米";
                ans2 = "4米11厘米";
                ans3 = "3米21厘米";
                break;
            case 4:
                question = questions[4];
                answer = "2米5厘米";
                ans1 = "1米5厘米";
                ans2 = "1米95厘米";
                ans3 = "2米15厘米";
                break;
            case 5:
                question = questions[5];
                answer = "36厘米";
                ans1 = "37厘米";
                ans2 = "32厘米";
                ans3 = "35厘米";
                break;
            case 6:
                question = questions[6];
                answer = "1米81厘米";
                ans1 = "1米71厘米";
                ans2 = "1米91厘米";
                ans3 = "1米83厘米";
                break;
            case 7:
                question = questions[7];
                answer = "95厘米";
                ans1 = "97厘米";
                ans2 = "96厘米";
                ans3 = "94厘米";
                break;
            case 8:
                question = questions[8];
                answer = "1米77厘米";
                ans1 = "1米67厘米";
                ans2 = "1米87厘米";
                ans3 = "2米77厘米";
                break;
            case 9:
                question = questions[9];
                answer = "3米9厘米";
                ans1 = "3米19厘米";
                ans2 = "2米99厘米";
                ans3 = "4米9厘米";
                break;
        }

    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getAns1() {
        return ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public String getAns3() {
        return ans3;
    }

}
