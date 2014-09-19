package com.twentyfirstcbh.richunews.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import com.twentyfirstcbh.richunews.R;
/*
 * 评论页面
 */
import com.twentyfirstcbh.richunews.activity.base.BaseActivity;
import com.twentyfirstcbh.richunews.adapter.CommentAdapter;
import com.twentyfirstcbh.richunews.object.Comment;
import com.twentyfirstcbh.richunews.widget.CommentDialog;

/**
 * 评论页面
 * @author Demongs
 *
 */

public class GeneralComment extends BaseActivity implements OnClickListener{
	private ListView clv;
	private List<Comment> cList;
	private EditText et;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		init();
	}
	
	private void init(){
		clv = (ListView) findViewById(R.id.comment_ListView);
		et = (EditText) findViewById(R.id.comment_EditText);
		et.setOnClickListener(this);
		
		View view = LayoutInflater.from(this).inflate(R.layout.textview_comment_head, null);
		clv.addHeaderView(view);
		
		cList = new ArrayList<Comment>();
		
		Comment comment1 = new Comment();
		comment1.setContent("地方就是浪费就是老地方就是大解放了双方就类似的纠纷类似的借款方老师的纠纷类似的加分了速度加福禄寿地方就是的浪费就类似的纠纷类似");
		comment1.setPraiseCount(3213);
		comment1.setPublishTime(System.currentTimeMillis());
		comment1.setUserName("好了吗啊");
		cList.add(comment1);
		
		Comment comment2 = new Comment();
		comment2.setContent("法第三方斯蒂芬");
		comment2.setPraiseCount(6564);
		comment2.setPublishTime(System.currentTimeMillis());
		comment2.setUserName("广告费地方");
		cList.add(comment2);
		
		Comment comment3 = new Comment();
		comment3.setContent("法第三方斯蒂芬");
		comment3.setPraiseCount(45345);
		comment3.setPublishTime(System.currentTimeMillis());
		comment3.setUserName("特瑞特");
		cList.add(comment3);
		
		Comment comment4 = new Comment();
		comment4.setContent("大放送");
		comment4.setPraiseCount(45345);
		comment4.setPublishTime(System.currentTimeMillis());
		comment4.setUserName("而额外人");
		cList.add(comment4);
		
		Comment comment5 = new Comment();
		comment5.setContent("fsdfsdfsdfsd");
		comment5.setPraiseCount(45345);
		comment5.setPublishTime(System.currentTimeMillis());
		comment5.setUserName("佛挡杀佛");
		cList.add(comment5);
		
		Comment comment6 = new Comment();
		comment6.setContent("月投入与认同和规范");
		comment6.setPraiseCount(45345);
		comment6.setPublishTime(System.currentTimeMillis());
		comment6.setUserName("refrigerator");
		cList.add(comment6);
		
		clv.setAdapter(new CommentAdapter(this, cList));
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.comment_EditText:
			CommentDialog cd = new CommentDialog(this);
			cd.show();
			Window win = cd.getWindow();
			WindowManager.LayoutParams params = win.getAttributes();
			win.setGravity(Gravity.BOTTOM);
			win.setWindowAnimations(R.style.mystyle);  //添加动画  
//			params.x = -80;//设置x坐标 
//			params.y = -60;//设置y坐标
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			params.width = dm.widthPixels; // 宽度
			win.setAttributes(params);
			cd.setCanceledOnTouchOutside(false);
			break;
		case R.id.returnBtn:
			break;
		}
	}

}
