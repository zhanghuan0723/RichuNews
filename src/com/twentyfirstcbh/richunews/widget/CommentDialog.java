package com.twentyfirstcbh.richunews.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.twentyfirstcbh.richunews.R;

public class CommentDialog extends Dialog implements OnClickListener{
	private Button cancel,submit;
	private EditText et;
	private Context context;

	public CommentDialog(Context context) {
		super(context);
		this.context=context;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.comment_dialog);
		
		cancel=(Button) findViewById(R.id.cancelBtn);
		cancel.setOnClickListener(this);
		submit=(Button) findViewById(R.id.submitBtn);
		submit.setOnClickListener(this);
		et=(EditText) findViewById(R.id.comment);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.cancelBtn:
			//关闭软键盘
			View view = getWindow().peekDecorView();
	        if (view != null) {
	            InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
	            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
	        }
	        
			this.dismiss();
			break;
		case R.id.submitBtn:
			break;
		}
	}

}
