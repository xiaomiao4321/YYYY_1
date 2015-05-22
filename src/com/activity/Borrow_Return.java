package com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.yyyy.yyyy.R;

public class Borrow_Return extends Activity {
	private TextView additem;//Ìí¼Ó½è´ûÏîÄ¿£»
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_borrow__return);
		additem = (TextView)this.findViewById(R.id.add_borrow_item);
		additem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(Borrow_Return.this, Add_Borrow_item.class);
				startActivity(intent);
			}
		});
	}
}
