package com.twentyfirstcbh.richunews.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;
import android.widget.TextView;

import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.activity.base.BaseActivity;
import com.twentyfirstcbh.richunews.adapter.FavoritieAdapter;
import com.twentyfirstcbh.richunews.listener.DrawableClickListener;
import com.twentyfirstcbh.richunews.object.TextArticle;
import com.twentyfirstcbh.richunews.utils.ScreenUtils;
import com.twentyfirstcbh.richunews.widget.CustomEditText;

public class Search extends BaseActivity {

	private CustomEditText searchET;
	private ListView searchLV;
	private ArrayList<TextArticle> articles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		init();
	}

	private void init() {
		searchET = (CustomEditText) findViewById(R.id.searchET);
		searchET.setDrawableClickListener(new DrawableClickListener() {
			@Override
			public void onClick(DrawablePosition target) {
				switch (target) {
				case RIGHT:
					handleQuery();
					break;
				default:
					break;
				}
			}
		});
		searchET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					handleQuery();
					return true;
				}
				return false;
			}
		});
		searchLV = (ListView) findViewById(R.id.searchLV);
	}

	private void handleQuery() {
		// 隐藏键盘
		ScreenUtils.hide(Search.this, searchET);

		// 显示查询结果
		if (!"".equals(searchET.getText().toString())) {
			queryList();
		}
	}

	private void queryList() {
		articles = new ArrayList<TextArticle>();
		TextArticle article = new TextArticle();
		article.setTitle("2014夏季达沃斯10日开幕，届时李克强将出席并致辞");
		article.setUpdateTime("09-11 14:31");
		articles.add(article);

		article = new TextArticle();
		article.setTitle("习近平近日上午到北师大看望教师");
		article.setUpdateTime("09-11 14:31");
		articles.add(article);

		article = new TextArticle();
		article.setTitle("达赖声称：我将是最后一位拉赖喇嘛");
		article.setUpdateTime("09-11 14:31");
		articles.add(article);

		article = new TextArticle();
		article.setTitle("广东发生枪击案三女童被误伤 一人全身中弹");
		article.setUpdateTime("09-11 14:31");
		articles.add(article);

		article = new TextArticle();
		article.setTitle("呼和浩特政协原主席张彭慧中秋夜自杀");
		article.setUpdateTime("09-11 14:31");
		articles.add(article);

		article = new TextArticle();
		article.setTitle("刘翔与女友相亲认识数月就闪婚");
		article.setUpdateTime("09-11 14:31");
		articles.add(article);

		article = new TextArticle();
		article.setTitle("一名美国空军司令在尼日利亚被针筒刺伤");
		article.setUpdateTime("09-11 14:31");
		articles.add(article);

		article = new TextArticle();
		article.setTitle("白领买彩票中31万 买新车犒劳自己");
		article.setUpdateTime("09-11 14:31");
		articles.add(article);

		searchLV.setAdapter(new FavoritieAdapter(Search.this, articles));
	}
}
