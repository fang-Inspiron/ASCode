package com.function;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.data.InEquationData;
import com.example.funnymath.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.RecursiveTask;

public class InEquationActivity extends Activity implements TextToSpeech.OnInitListener {

	private ViewPager mPager;// 页卡内容
	private List<View> listViews; // Tab页面列表
	private TextView textView_head;// 页卡头标
	private int offset = 0;// 动画图片偏移量
	private int currIndex = 0;// 当前页卡编号
	private int bmpW;// 动画图片宽度
	private View page0;
	private View page1;
	private View page2;
	private View page3;
	private View page4;
	private View page5;
	private View page6;
	private View page7;
	private View page8;
	private View page9;

	private TextView textView;
	private RadioGroup radioGroup;
	private RadioButton radioButtonA;
	private RadioButton radioButtonB;
	private RadioButton radioButtonC;
	private int rightFlag;	//判断选项是否正确
	private ImageView  imageView_difficulty;
	private ImageView  imageView_voice;
	private ImageView  imageView_help;
	private ImageView  imageView_teach;
	private TextToSpeech tts;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inequation);
		tts = new TextToSpeech(this,this);

		InitTextView(); // 初始化头标
		InitViewPager(); // 初始化ViewPager
	}

	/**
	 * 初始化头标
	 */
	private void InitTextView() {
		textView_head = (TextView) findViewById(R.id.text);
		textView_head.setText("NO.1");
		textView_head.setOnClickListener(new MyOnClickListener(0));
	}

	/**
	 * 初始化ViewPager
	 */
	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.vPager);
		listViews = new ArrayList<View>();
		LayoutInflater mInflater = getLayoutInflater();
		page0 = mInflater.inflate(R.layout.inequation_pager, null);
		page1 = mInflater.inflate(R.layout.inequation_pager, null);
		page2 = mInflater.inflate(R.layout.inequation_pager, null);
		page3 = mInflater.inflate(R.layout.inequation_pager, null);
		page4 = mInflater.inflate(R.layout.inequation_pager, null);
		page5 = mInflater.inflate(R.layout.inequation_pager, null);
		page6 = mInflater.inflate(R.layout.inequation_pager, null);
		page7 = mInflater.inflate(R.layout.inequation_pager, null);
		page8 = mInflater.inflate(R.layout.inequation_pager, null);
		page9 = mInflater.inflate(R.layout.inequation_pager, null);
		listViews.add(page0);
		listViews.add(page1);
		listViews.add(page2);
		listViews.add(page3);
		listViews.add(page4);
		listViews.add(page5);
		listViews.add(page6);
		listViews.add(page7);
		listViews.add(page8);
		listViews.add(page9);
		mPager.setAdapter(new MyPagerAdapter(listViews));
		mPager.setCurrentItem(0);
		// 对add_page0中的数据初始化
		initAddPage(page0);
		setData();
		//对viewPager添加页面监听
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			Toast.makeText(InEquationActivity.this, "成功输出语音", Toast.LENGTH_SHORT).show();
			// 设置使用美式英语朗读
			int result = tts.setLanguage(Locale.US);
			//int result = tts.setLanguage(Locale.CHINA);//指定当前阅读的是中文

			// 如果不支持所设置的语言
			if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
					&& result != TextToSpeech.LANG_AVAILABLE) {
				Toast.makeText(InEquationActivity.this, "TTS暂时不支持这种语言的朗读",
						Toast.LENGTH_LONG).show();
			}
		}
	}

	/**
	 * ViewPager适配器
	 */
	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	/**
	 * 头标点击监听
	 */
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};

	/**
	 * 页卡切换监听
	 */
	public class MyOnPageChangeListener implements OnPageChangeListener {
		//计算偏移量
		int one = offset * 2 + bmpW;// 页卡0 -> 页卡1 偏移量
		int two = one * 2;// 页卡0 -> 页卡2 偏移量
		int three = one * 3;// 页卡0 -> 页卡3 偏移量
		int four = one * 4;// 页卡0 -> 页卡4 偏移量
		int five = one * 5;// 页卡0 -> 页卡5 偏移量
		int six = one * 6;// 页卡0 -> 页卡6 偏移量
		int seven = one * 7;// 页卡0 -> 页卡7 偏移量
		int eight = one * 8;// 页卡0 -> 页卡8 偏移量
		int nine = one * 9;// 页卡0 -> 页卡9 偏移量

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			//arg0：将要切换的目的页面
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					textView_head.setText("NO.1");
					animation = new TranslateAnimation(one, 0, 0, 0);
				}
				break;
			case 1:
				if (currIndex == 0) {
					textView_head.setText("NO.2");
					animation = new TranslateAnimation(offset, one, 0, 0);
					//从页面0切换到页面1（向左滑）
					initAddPage(page1);
					setData();
				} else if (currIndex == 2) {
					textView_head.setText("NO.2");
					animation = new TranslateAnimation(two, one, 0, 0);
				}
				break;
			case 2:
				if (currIndex == 1) {
					textView_head.setText("NO.3");
					animation = new TranslateAnimation(one, two, 0, 0);
					//从页面1切换到页面2（向左滑）
					initAddPage(page2);
					setData();
				} else if (currIndex == 3) {
					textView_head.setText("NO.3");
					animation = new TranslateAnimation(three, two, 0, 0);
				}
				break;
			case 3:
				if (currIndex == 2) {
					textView_head.setText("NO.4");
					animation = new TranslateAnimation(two, three, 0, 0);
					//从页面2切换到页面3（向左滑）
					initAddPage(page3);
					setData();
				} else if (currIndex == 4) {
					textView_head.setText("NO.4");
					animation = new TranslateAnimation(four, three, 0, 0);
				}
				break;
			case 4:
				if (currIndex == 3) {
					textView_head.setText("NO.5");
					animation = new TranslateAnimation(three, four, 0, 0);
					//从页面3切换到页面4（向左滑）
					initAddPage(page4);
					setData();
				} else if (currIndex == 5) {
					textView_head.setText("NO.5");
					//从页面5切换到页面4（向右滑）
					animation = new TranslateAnimation(five, four, 0, 0);
				}
				break;
			case 5:
				if (currIndex == 4) {
					textView_head.setText("NO.6");
					animation = new TranslateAnimation(four, five, 0, 0);
					//从页面4切换到页面5（向左滑）
					initAddPage(page5);
					setData();
				} else if (currIndex == 6) {
					textView_head.setText("NO.6");
					//从页面6切换到页面5（向右滑）
					animation = new TranslateAnimation(six, five, 0, 0);
				}
				break;
			case 6:
				if (currIndex == 5) {
					textView_head.setText("NO.7");
					animation = new TranslateAnimation(five, six, 0, 0);
					//从页面5切换到页面6（向左滑）
					initAddPage(page6);
					setData();
				} else if (currIndex == 7) {
					textView_head.setText("NO.7");
					//从页面7切换到页面6（向右滑）
					animation = new TranslateAnimation(seven, six, 0, 0);
				}
				break;
			case 7:
				if (currIndex == 6) {
					textView_head.setText("NO.8");
					animation = new TranslateAnimation(six, seven, 0, 0);
					//从页面6切换到页面7（向左滑）
					initAddPage(page7);
					setData();
				} else if (currIndex == 8) {
					textView_head.setText("NO.8");
					//从页面8切换到页面7（向右滑）
					animation = new TranslateAnimation(eight, seven, 0, 0);
				}
				break;
			case 8:
				if (currIndex == 7) {
					textView_head.setText("NO.9");
					animation = new TranslateAnimation(seven, eight, 0, 0);
					//从页面7切换到页面8（向左滑）
					initAddPage(page8);
					setData();
				} else if (currIndex == 9) {
					textView_head.setText("NO.9");
					//从页面9切换到页面8（向右滑）
					animation = new TranslateAnimation(nine, eight, 0, 0);
				}
				break;
			case 9:
				if (currIndex == 8) {
					textView_head.setText("NO.10");
					animation = new TranslateAnimation(eight, nine, 0, 0);
					//从页面8切换到页面9（向左滑）
					initAddPage(page9);
					setData();
				} 
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:图片停在动画结束位置
			animation.setDuration(300);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	public void changeToNext() {
		final int nowItem = mPager.getCurrentItem();
		if (nowItem < listViews.size()-1) {
			mPager.setCurrentItem(nowItem+1);
		} 
	}
	
	class myCheckedChangeListener implements OnCheckedChangeListener {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.inequation_radioButtonA:
				if (getRightFlag() == 1) {
					Toast.makeText(getApplicationContext(), "恭喜你，答对了！",
							Toast.LENGTH_SHORT).show();
					changeToNext();
				} else {
					Toast.makeText(getApplicationContext(), "不对，再想想~",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.inequation_radioButtonB:
				if (getRightFlag() == 2) {
					Toast.makeText(getApplicationContext(), "恭喜你，答对了！",
							Toast.LENGTH_SHORT).show();
					changeToNext();
				} else {
					Toast.makeText(getApplicationContext(), "不对，再想想~",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.inequation_radioButtonC:
				if (getRightFlag() == 3) {
					Toast.makeText(getApplicationContext(), "恭喜你，答对了！",
							Toast.LENGTH_SHORT).show();
					changeToNext();
				} else {
					Toast.makeText(getApplicationContext(), "不对，再想想~",
							Toast.LENGTH_SHORT).show();
				}
				break;
			default:
				break;
			}
		}

	}

	public int getRightFlag() {
		return rightFlag;
	}

	public void setRightFlag(int rightFlag) {
		this.rightFlag = rightFlag;
	}

	public void setData() {
		InEquationData inEquationData = new InEquationData();
		textView.setText(inEquationData.getQuestion());
		if (inEquationData.getAnswer() == '<') {
			setRightFlag(1);
		} else if (inEquationData.getAnswer() == '=') {
			setRightFlag(2);
		} else {
			setRightFlag(3);
		}

		radioButtonA.setText("<");
		radioButtonB.setText("=");
		radioButtonC.setText(">");
		//给radioGroup添加监听器
		radioGroup.setOnCheckedChangeListener(new myCheckedChangeListener());
	}

	private void initAddPage(View view) {
		textView = (TextView) view.findViewById(R.id.inequation_textView);
		radioGroup = (RadioGroup) view.findViewById(R.id.inequation_radioGroup);
		radioButtonA = (RadioButton) view.findViewById(R.id.inequation_radioButtonA);
		radioButtonB = (RadioButton) view.findViewById(R.id.inequation_radioButtonB);
		radioButtonC = (RadioButton) view.findViewById(R.id.inequation_radioButtonC);
		imageView_difficulty = (ImageView) view.findViewById(R.id.imageView_difficulty);
		imageView_voice = (ImageView) view.findViewById(R.id.imageView_voice);
		imageView_help = (ImageView) view.findViewById(R.id.imageView_help);
		imageView_teach = (ImageView) view.findViewById(R.id.imageView_teach);
		imageView_difficulty.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(InEquationActivity.this);

				builder.setTitle("提示：");
				builder.setMessage("当前已是最高难度了！");
				builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});

		imageView_help.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(InEquationActivity.this);

				builder.setTitle("提示：");
				builder.setMessage("再想想吧，不要放弃哦！");
				builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				AlertDialog dialog = builder.create();
				dialog.show();

			}
		});
		imageView_teach.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						InEquationActivity.this);
				builder.setTitle("不等式：");
				builder.setMessage("通过观察，运用对应方法找出两个算式的异同点，然后判断不同部分的大小，这样既对又快。");
				builder.setPositiveButton("好的",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
												int which) {
								dialog.dismiss();
							}
						});
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});
		imageView_voice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				tts.speak(textView.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
			}
		});
	}
}
