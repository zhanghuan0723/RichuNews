package com.twentyfirstcbh.richunews.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.activity.base.BaseActivity;
import com.twentyfirstcbh.richunews.adapter.AtlasPagerAdapter;
import com.twentyfirstcbh.richunews.object.Photo;
import com.twentyfirstcbh.richunews.object.PhotoArticle;
import com.twentyfirstcbh.richunews.utils.ScreenUtils;

/**
 * 图集
 * 
 * @author Simon
 * 
 */
public class Atlas extends BaseActivity implements OnPageChangeListener, OnClickListener {

	private ArrayList<PhotoArticle> photoItems;

	private ViewPager mViewPager;
	private TextView titleTV, currPageTV, descTV;
	private LinearLayout bottomRL;
	private ImageView backIV, commentIV, shareIV, downIV;
	private TextView commentCountTV;

	private String commentCount = "4698";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_atlas);

		init();
	}

	private void init() {
		mViewPager = (ViewPager) findViewById(R.id.viewPager);
		mViewPager.setAdapter(new AtlasPagerAdapter(this, getData()));
		mViewPager.setOnPageChangeListener(this);

		titleTV = (TextView) findViewById(R.id.titleTV);
		currPageTV = (TextView) findViewById(R.id.currPageTV);
		descTV = (TextView) findViewById(R.id.descTV);

		bottomRL = (LinearLayout) findViewById(R.id.bottomRL);
		backIV = (ImageView) findViewById(R.id.backIV);
		backIV.setOnClickListener(this);
		commentIV = (ImageView) findViewById(R.id.commentIV);
		commentIV.setOnClickListener(this);
		shareIV = (ImageView) findViewById(R.id.shareIV);
		shareIV.setOnClickListener(this);
		downIV = (ImageView) findViewById(R.id.downIV);
		downIV.setOnClickListener(this);

		commentCountTV = (TextView) findViewById(R.id.commentCountTV);
		commentCountTV.setTextSize(11.0f);
		setMarginPadding();
		commentCountTV.setText(commentCount);

		setData(0, photoItems.get(0));
	}

	private void setMarginPadding() {
		int dp_3 = ScreenUtils.dpToPx(this, 3);
		int left_dp_10 = ScreenUtils.dpToPx(this, 10);
		int bottom_dp_20 = ScreenUtils.dpToPx(this, 20);

		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) commentCountTV.getLayoutParams();
		params.leftMargin = left_dp_10;
		params.bottomMargin = bottom_dp_20;
		switch (commentCount.length()) {
		case 1:
		case 2:
		case 3:
			commentCountTV.setPadding(dp_3, 0, dp_3, 0);
			break;
		}
	}

	private ArrayList<PhotoArticle> getData() {
		photoItems = new ArrayList<PhotoArticle>();
		PhotoArticle item = new PhotoArticle();
		item.setThumbUrl("http://a.hiphotos.baidu.com/news/w%3D638/sign=139691e8b44543a9f51bf9cf26168a7b/1e30e924b899a901ec87142a1e950a7b0208f578.jpg");
		item.setTitle("苹果发布会，你想知道的都在这里");
		item.setDesc("大号的iPhone，移动支付，以及一款手表——苹果最终发布了人们长期以来期待的三款产品，构成了未来移动业务的支柱。4.7英寸iPhone 6搭配5.5英寸的6 Plus，两款型号都有近场通讯技术，以及Apple Pay点击支付系统。Apple Watch也准备和谷歌的Android Wear竞争，有各种自定义的设计以及完全更新的界面。");
		photoItems.add(item);

		item = new PhotoArticle();
		item.setThumbUrl("http://f.hiphotos.baidu.com/news/w%3D638/sign=2c55fbe73adbb6fd255be6253125aba6/f636afc379310a55ce9691e8b44543a98226107f.jpg");
		item.setTitle("苹果发布会，你想知道的都在这里");
		item.setDesc("Apple Watch——“需要在晚上进行充电”的电池寿命是怎样的？这意味着只能撑一天吗？如果没有指纹识别，如何使用Apple Pay呢？手表被偷就可以进行支付了吗？如果离开了手机本身，不与手机绑定的话这款手表能干什么呢？最便宜的售价是349元起，最贵的是怎样？");
		photoItems.add(item);

		item = new PhotoArticle();
		item.setThumbUrl("http://g.hiphotos.baidu.com/super/whfpf%3D425%2C260%2C50/sign=c06b329a0c2442a7ae5baee5b77e9979/5d6034a85edf8db19529747d0a23dd54564e74a7.jpg");
		item.setTitle("郑希怡香港办婚礼");
		item.setDesc("9月5日，女星郑希怡与丈夫梁学储在香港办婚礼，阿娇阿Sa与容祖儿当伴娘。郑希怡双手挂满金镯，颈上戴8只金猪造型的项链，十分富贵。");
		photoItems.add(item);

		item = new PhotoArticle();
		item.setThumbUrl("http://b.hiphotos.baidu.com/super/whfpf%3D425%2C260%2C50/sign=f6e62df6aaec8a13144f04a0913ea5bd/d1160924ab18972b75cd09d3e5cd7b899e510a37.jpg");
		item.setTitle("各式飞机空中炫技助阵俄罗斯航空博览会");
		item.setDesc("当地时间2014年9月4日，俄罗斯格连吉克，当地举办航空博览会。");
		photoItems.add(item);

		item = new PhotoArticle();
		item.setThumbUrl("http://b.hiphotos.baidu.com/super/whfpf%3D425%2C260%2C50/sign=94ddf03a4a540923aa3c303ef465e53b/359b033b5bb5c9ea0ca20dfad639b6003af3b351.jpg");
		item.setTitle("淘宝征税，将成阿里股价恶梦");
		item.setDesc("不论上市后阿里的市值能冲到多高，只要未来国家向淘宝征税，其市值必将应声下滑。");
		photoItems.add(item);

		item = new PhotoArticle();

		ArrayList<Photo> atlasItems = new ArrayList<Photo>();
		Photo atlasItem = new Photo();
		atlasItem.setTitle("一居室  4万/全包");
		atlasItem.setOriginalPicUrl("http://img1n.soufunimg.com/viewimage/zxb/2013_03/28/5/59/pic/004177816100/175x131c.jpg");
		atlasItem.setLinkUrl("1");
		atlasItems.add(atlasItem);

		atlasItem = new Photo();
		atlasItem.setTitle("二居室  6万/全包");
		atlasItem.setOriginalPicUrl("http://img1n.soufunimg.com/viewimage/zxb/2013_10/08/21/70/pic/009721302500/175x131c.jpg");
		atlasItem.setLinkUrl("2");
		atlasItems.add(atlasItem);

		atlasItem = new Photo();
		atlasItem.setTitle("三居室  10万/全包");
		atlasItem.setOriginalPicUrl("http://img1n.soufunimg.com/viewimage/zxb/2013_06/21/0/3/pic/007889953100/175x131c.jpg");
		atlasItem.setLinkUrl("3");
		atlasItems.add(atlasItem);

		atlasItem = new Photo();
		atlasItem.setTitle("四居室 12万/全包");
		atlasItem.setOriginalPicUrl("http://img1n.soufunimg.com/viewimage/zxb/2014_08/14/45/88/pic/001029891700/175x131c.jpg");
		atlasItem.setLinkUrl("4");
		atlasItems.add(atlasItem);

		atlasItem = new Photo();
		atlasItem.setTitle("客厅装修效果图");
		atlasItem.setOriginalPicUrl("http://img1n.soufunimg.com/viewimage/zxb/2014_08/09/2/46/pic/005367806900/175x131c.jpg");
		atlasItem.setLinkUrl("5");
		atlasItems.add(atlasItem);

		item.setPhotoList(atlasItems);
		photoItems.add(item);

		return photoItems;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backIV:
			Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
			break;
		case R.id.commentIV:
			break;
		case R.id.shareIV:
			showShare();
			break;
		case R.id.downIV:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	}

	@Override
	public void onPageSelected(int position) {
		PhotoArticle photoItem = photoItems.get(position);
		setData(position, photoItem);
	}

	private void setData(int position, PhotoArticle photoItem) {
		if (position != photoItems.size() - 1) {
			titleTV.setVisibility(View.VISIBLE);
			currPageTV.setVisibility(View.VISIBLE);
			descTV.setVisibility(View.VISIBLE);
			bottomRL.setVisibility(View.VISIBLE);

			titleTV.setText(photoItem.getTitle());
			currPageTV.setText((position + 1) + "/" + (photoItems.size() - 1));
			descTV.setText(photoItem.getDesc());
		} else {
			titleTV.setVisibility(View.GONE);
			currPageTV.setVisibility(View.GONE);
			descTV.setVisibility(View.GONE);
			bottomRL.setVisibility(View.GONE);
		}
	}

}
