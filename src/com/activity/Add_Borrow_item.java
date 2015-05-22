package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.yyyy.yyyy.R;

public class Add_Borrow_item extends Activity {
	private TextView add_friend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__borrow_item);
		add_friend = (TextView)this.findViewById(R.id.add_friend);
		add_friend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Add_Borrow_item.this, User_List.class);
				startActivity(intent);
			}
		});
	}
}
