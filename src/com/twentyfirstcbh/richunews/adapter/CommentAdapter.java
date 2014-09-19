package com.twentyfirstcbh.richunews.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.twentyfirstcbh.richunews.R;
import com.twentyfirstcbh.richunews.enums.TimeType;
import com.twentyfirstcbh.richunews.object.Comment;
import com.twentyfirstcbh.richunews.widget.CircleImageView;

public class CommentAdapter extends BaseAdapter {
	private Context context;
	private List<Comment> cList;
	private LayoutInflater mInflater;
	
	public CommentAdapter(Context context,List<Comment> cList){
		this.context=context;
		this.cList=cList;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return cList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return cList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder vh=null;
		if(arg1==null){
			vh=new ViewHolder();
			arg1 = mInflater.inflate(R.layout.comment_item, null);
			vh.name = (TextView) arg1.findViewById(R.id.name_TextView);
			vh.time = (TextView) arg1.findViewById(R.id.item_time_TextView);
			vh.face = (CircleImageView) arg1.findViewById(R.id.circleImageView);
			vh.praiseCount = (TextView) arg1.findViewById(R.id.count_TextView);
			vh.praise = (ImageView) arg1.findViewById(R.id.praise_ImageView);
			vh.content = (TextView) arg1.findViewById(R.id.content_TextView);
			
			arg1.setTag(vh);
		}else{
			vh=(ViewHolder) arg1.getTag();
		}
		
		Comment comment = cList.get(arg0);
		vh.name.setText(comment.getUserName());
		vh.time.setText(comment.getPublishTime(TimeType.FROMNOW));
		vh.praiseCount.setText(String.valueOf(comment.getPraiseCount()));
		vh.content.setText(comment.getContent());
		
//		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);  
//		lp.setMargins(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher).getWidth(), 0, 0, 0);
//		vh.content.setLayoutParams(lp);
		return arg1;
	}
	
	class ViewHolder{
		TextView name;
		TextView time;
		CircleImageView face;
		TextView praiseCount;
		ImageView praise;
		TextView content;
	}

}
