package com.twentyfirstcbh.richunews.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;

import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.activity.base.BaseActivity;
import com.twentyfirstcbh.richunews.adapter.FavoritieAdapter;
import com.twentyfirstcbh.richunews.object.TextArticle;

public class Favoritie extends BaseActivity {

	private ListView favoritieLV;
	private ArrayList<TextArticle> articles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoritie);

		initData();

		init();
	}

	private void initData() {
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
	}

	private void init() {
		favoritieLV = (ListView) findViewById(R.id.favoritieLV);
		FavoritieAdapter adapter = new FavoritieAdapter(this, articles);
		favoritieLV.setAdapter(adapter);
	}

}
