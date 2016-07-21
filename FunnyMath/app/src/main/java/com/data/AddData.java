package com.data;

import java.util.Random;

public class AddData {

	Random random = new Random();
	String question;
	int answer;
	int answer1;
	int answer2;
	int answer3;

	public AddData(int stander) {

		if (stander == 0) {
			int n=3;
			int[] ques = new int[n];
			ques[0] = random.nextInt(100);
			ques[1] = random.nextInt(100);
			ques[2] = random.nextInt(100);
			question = ques[0]+"+"+ques[1]+"+"+ques[2]+"=?";
			answer = ques[0]+ques[1]+ques[2];
			
			//随机生成给定范围内N个不重复的数，并且不能和正确答案重复
			int[] ans = new int[n];
			int count = 0;  
		    while(count < n) {  
		     //int num = (int) (Math.random() * (max-min)) + min;  
		        int num = (int) (Math.random() * 20) + (answer-10);  
		        boolean flag = true;  
		        for (int i = 0; i < n; i++) {  
		            if((num == ans[i]) || (num == answer)){  
		                flag = false;  
		                break;  
		            }  
		        }  
		        if(flag){  
		            ans[count] = num;  
		            count++;  
		        }  
		    }  
			answer1 = ans[0];
			answer2 = ans[1];
			answer3 = ans[2];
		} else if (stander == 1) {
			int n=5;
			int[] ques = new int[n];
			ques[0] = random.nextInt(100);
			ques[1] = random.nextInt(100);
			ques[2] = random.nextInt(100);
			ques[3] = random.nextInt(100);
			ques[4] = random.nextInt(100);
			question = ques[0]+"+"+ques[1]+"+"+ques[2]+"+"+ques[3]+"+"+ques[4]+"=?";
			answer = ques[0]+ques[1]+ques[2]+ques[3]+ques[4];

			//随机生成给定范围内N个不重复的数，并且不能和正确答案重复
			int[] ans = new int[n];
			int count = 0;
			while(count < n) {
				//int num = (int) (Math.random() * (max-min)) + min;
				int num = (int) (Math.random() * 20) + (answer-10);
				boolean flag = true;
				for (int i = 0; i < n; i++) {
					if((num == ans[i]) || (num == answer)){
						flag = false;
						break;
					}
				}
				if(flag){
					ans[count] = num;
					count++;
				}
			}
			answer1 = ans[0];
			answer2 = ans[1];
			answer3 = ans[2];
		}
	}
	
	public String getQuestion() {
		return question;
	}

	public int getAnswer() {
		return answer;
	}

	public int getAnswer1() {
		return answer1;
	}

	public int getAnswer2() {
		return answer2;
	}

	public int getAnswer3() {
		return answer3;
	}

}
