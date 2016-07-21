package com.offlinecache.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.offlinecache.R;

public class LocalityFragment extends Fragment implements
		AbsListView.OnScrollListener {
	private View rootView;
	// 存放父列表数据
	List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
	// 放子列表列表数据
	List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
	ExpandableAdapter expandAdapter;
	ExpandableListView expandableList;
	private int indicatorGroupHeight;
	private int the_group_expand_position = -1;
	private int count_expand = 0;
	private Map<Integer, Integer> ids = new HashMap<Integer, Integer>();
	private LinearLayout view_flotage = null;
	private TextView group_content = null;
	private ImageView tubiao;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


	rootView = inflater.inflate(R.layout.fragment_locality, container, false);
		initData();
		expandAdapter = new ExpandableAdapter(LocalityFragment.this);
		expandableList = (ExpandableListView) rootView.findViewById(R.id.list);
		View v = new View(getContext());
		expandableList.addHeaderView(v);
		expandableList.setAdapter(expandAdapter);
		expandableList.setGroupIndicator(null);
		initView();
		return rootView;
	}

	private void initData() {
		Map<String, String> curGroupMap = new HashMap<String, String>();
		groupData.add(curGroupMap);
		curGroupMap.put("group_text", "未缓存 ");

		Map<String, String> curGroupMap2 = new HashMap<String, String>();
		groupData.add(curGroupMap2);
		curGroupMap2.put("group_text", "已缓存 ");

//每次先从本地请求，若无则从从服务器获取数据

		List<Map<String, String>> children = new ArrayList<Map<String, String>>();
			Map<String, String> curChildMap = new HashMap<String, String>();
			children.add(curChildMap);
			curChildMap.put("child_text1", "http://www.sina.com.cn/");
		Map<String, String> curChildMap2 = new HashMap<String, String>();
		children.add(curChildMap2);
		curChildMap2.put("child_text1", "http://www.sfge.com.cn/");


		List<Map<String, String>> children2 = new ArrayList<Map<String, String>>();
		Map<String, String> curChildMap3 = new HashMap<String, String>();
		children2.add(curChildMap3);
		curChildMap3.put("child_text1", "https://www.baidu.com/");
		Map<String, String> curChildMap4 = new HashMap<String, String>();
		children2.add(curChildMap4);
		curChildMap4.put("child_text1", "https://www.hao123.com/");
		childData.add(children);
		childData.add(children2);
	}

	public void initView() {
		/**
		 * 监听父节点打开的事件
		 */
		expandableList.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int groupPosition) {
				the_group_expand_position = groupPosition;
				ids.put(groupPosition, groupPosition);
				count_expand = ids.size();
			}
		});
		/**
		 * 监听父节点关闭的事件
		 */
		expandableList
				.setOnGroupCollapseListener(new OnGroupCollapseListener() {
					@Override
					public void onGroupCollapse(int groupPosition) {
						ids.remove(groupPosition);
						expandableList.setSelectedGroup(groupPosition);
						count_expand = ids.size();
					}
				});
		view_flotage = (LinearLayout) rootView.findViewById(R.id.topGroup);
		view_flotage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				view_flotage.setVisibility(View.GONE);
				expandableList.collapseGroup(the_group_expand_position);
				expandableList.setSelectedGroup(the_group_expand_position);
			}
		});
		group_content = (TextView) rootView.findViewById(R.id.content_001);
		tubiao = (ImageView) rootView.findViewById(R.id.tubiao);
		tubiao.setBackgroundResource(R.drawable.btn_browser2);
		//设置滚动事件
		expandableList.setOnScrollListener(this);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		//防止三星,魅族等手机第一个条目可以一直往下拉,父条目和悬浮同时出现的问题
		if(firstVisibleItem==0){
			view_flotage.setVisibility(View.GONE);
		}
		// 控制滑动时TextView的显示与隐藏
		int npos = view.pointToPosition(0, 0);
		if (npos != AdapterView.INVALID_POSITION) {
			long pos = expandableList.getExpandableListPosition(npos);
			int childPos = ExpandableListView.getPackedPositionChild(pos);
			final int groupPos = ExpandableListView.getPackedPositionGroup(pos);
			if (childPos == AdapterView.INVALID_POSITION) {
				View groupView = expandableList.getChildAt(npos
						- expandableList.getFirstVisiblePosition());
				indicatorGroupHeight = groupView.getHeight();
			}

			if (indicatorGroupHeight == 0) {
				return;
			}
			// if (isExpanded) {
			if (count_expand > 0) {
				the_group_expand_position = groupPos;
				group_content.setText(groupData.get(the_group_expand_position)
						.get("group_text"));
				if (the_group_expand_position != groupPos||!expandableList.isGroupExpanded(groupPos)) {
					view_flotage.setVisibility(View.GONE);
				} else {
					view_flotage.setVisibility(View.VISIBLE);
				}
			}
			if (count_expand == 0) {
				view_flotage.setVisibility(View.GONE);
			}
		}

		if (the_group_expand_position == -1) {
			return;
		}
		/**
		 * calculate point (0,indicatorGroupHeight)
		 */
		int showHeight = getHeight();
		MarginLayoutParams layoutParams = (MarginLayoutParams) view_flotage
				.getLayoutParams();
		// 得到悬浮的条滑出屏幕多少
		layoutParams.topMargin = -(indicatorGroupHeight - showHeight);
		view_flotage.setLayoutParams(layoutParams);
	}

	class ExpandableAdapter extends BaseExpandableListAdapter {
		LocalityFragment exlistview;
		@SuppressWarnings("unused")
		private int mHideGroupPos = -1;

		public ExpandableAdapter(LocalityFragment elv) {
			super();
			exlistview = elv;
		}

		// **************************************
		public Object getChild(int groupPosition, int childPosition) {
			return childData.get(groupPosition).get(childPosition)
					.get("child_text1").toString();
		}

		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		public int getChildrenCount(int groupPosition) {
			return childData.get(groupPosition).size();
		}

		public View getChildView(final int groupPosition,final int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(R.layout.childitem, null);
			}
			final TextView title = (TextView) view.findViewById(R.id.child_text);
			title.setText(childData.get(groupPosition).get(childPosition).get("child_text1").toString());
			title.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
                        System.out.println("groupPosition"+groupPosition);
                        System.out.println("childPosition"+childPosition);
                        if (groupPosition == 1) {
                            Toast.makeText(getContext(), "单击", Toast.LENGTH_SHORT).show();
                            //childSecond表示已缓存，点击可查看，添加监听器
                            //此处需要创建SQLite数据库来标识num，不然每次都新启动App，num都从0开始
                            //        Intent intent = new Intent();
                            //        intent.putExtra("num", MainActivity.num);
                            //        intent.setClass(getContext(), WebActivity.class);
                            //        startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "未缓存", Toast.LENGTH_SHORT).show();
                        }
				}
			});
			return view;
		}

		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				LayoutInflater inflaterGroup = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflaterGroup.inflate(R.layout.groupitem, null);
			}
			TextView title = (TextView) view.findViewById(R.id.content_001);
			title.setText(getGroup(groupPosition).toString());
			ImageView image = (ImageView) view.findViewById(R.id.tubiao);

			System.out.println("isExpanded----->" + isExpanded);
			if (isExpanded) {
				image.setBackgroundResource(R.drawable.btn_browser2);
			} else {
				image.setBackgroundResource(R.drawable.btn_browser);
			}
			return view;
		}

		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		public Object getGroup(int groupPosition) {
			return groupData.get(groupPosition).get("group_text").toString();
		}

		public int getGroupCount() {
			return groupData.size();

		}

		public boolean hasStableIds() {
			return true;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return false;
		}

		public void hideGroup(int groupPos) {
			mHideGroupPos = groupPos;
		}
	}


	private int getHeight() {
		int showHeight = indicatorGroupHeight;
		int nEndPos = expandableList.pointToPosition(0, indicatorGroupHeight);
		if (nEndPos != AdapterView.INVALID_POSITION) {
			long pos = expandableList.getExpandableListPosition(nEndPos);
			int groupPos = ExpandableListView.getPackedPositionGroup(pos);
			if (groupPos != the_group_expand_position) {
				View viewNext = expandableList.getChildAt(nEndPos
						- expandableList.getFirstVisiblePosition());
				showHeight = viewNext.getTop();
			}
		}
		return showHeight;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}
}
