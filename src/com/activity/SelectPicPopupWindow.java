package com.activity;

/**
 * ����Ϊ����ѡ��ʱ������������ʾ����
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.logic.ConsumKindManager;
import com.yyyy.yyyy.R;

public class SelectPicPopupWindow extends Activity implements OnClickListener {

	private TextView yi;
	private TextView shi;
	private TextView zhu;
	private TextView xing;
	private TextView kind;
	private LinearLayout layout;
	public static Activity selectPicPopupWindow;
	private ConsumKindManager consumKindManager;

	@Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.alert);  
        selectPicPopupWindow = this;
        yi = (TextView)this.findViewById(R.id.yi);
        shi = (TextView)this.findViewById(R.id.shi);
        zhu = (TextView)this.findViewById(R.id.zhu);
        xing = (TextView)this.findViewById(R.id.xing);
        layout = (LinearLayout)this.findViewById(R.id.two);
        kind = (TextView)JZ_Activity.jzActivity.findViewById(R.id.kind);
        consumKindManager = new ConsumKindManager();
        
        /**
         * �� ��ť�¼�
         */
        yi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("��  > ");
				JZ_Activity.consumekind = 1;
				consumKindManager.freshButton(1, layout);
			}
		});
        
        /**
         * ʳ ��ť�¼�
         */
        shi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("ʳ  > ");
				JZ_Activity.consumekind = 2;
				consumKindManager.freshButton(2, layout);
			}
		});
        
        /**
         * ס ��ť�¼�
         */
        zhu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("ס  > ");
				JZ_Activity.consumekind = 3;
				consumKindManager.freshButton(3, layout);
			}
		});
        
        /**
         * �� ��ť�¼�
         */
        xing.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kind.setText("��  > ");
				JZ_Activity.consumekind = 4;
				consumKindManager.freshButton(4, layout);
			}
		});
    }	// ʵ��onTouchEvent���������������Ļʱ���ٱ�Activity

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		finish();
		return true;
	}

	public void onClick(View v) {
		
	}

}
