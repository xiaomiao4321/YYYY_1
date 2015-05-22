package com.activity;

import com.model.street.CreateMessageBean;
import com.model.street.PublishConnecter;
import com.yyyy.yyyy.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bean.StreetMessageBean;
public class Publish_Activity extends Activity {
	private TextView fs;
	private TextView pjz;
	private EditText content;
	private EditText tag;
	private EditText price;
	private TextView getAddress;
    private LocationClient locationClient = null;  
   private static final int UPDATE_TIME = 50000;  
	public static Activity publish_Activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_publish);
		fs = (TextView) this.findViewById(R.id.fs);
		pjz = (TextView) this.findViewById(R.id.pjz);
		content = (EditText) this.findViewById(R.id.content);
		tag = (EditText) this.findViewById(R.id.tag);
		price = (EditText) this.findViewById(R.id.price);
		getAddress = (TextView) this.findViewById(R.id.myaddress);
		publish_Activity = this;

		/**
		 * ��λ
		 */
		getAddress.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				locationClient = new LocationClient(Publish_Activity.this);  
		        //���ö�λ����  
		        LocationClientOption option = new LocationClientOption();  
		        option.setOpenGps(true);                                //�Ƿ��GPS  
		        option.setCoorType("bd09ll");                           //���÷���ֵ���������͡� 
		        option.setIsNeedAddress(true);
		        option.setProdName("LocationDemo");                     //���ò�Ʒ�����ơ�ǿ�ҽ�����ʹ���Զ���Ĳ�Ʒ�����ƣ����������Ժ�Ϊ���ṩ����Ч׼ȷ�Ķ�λ����  
		        option.setScanSpan(UPDATE_TIME);                        //���ö�ʱ��λ��ʱ��������λ����  
		        locationClient.setLocOption(option);  
		          
		        //ע��λ�ü�����  
		        locationClient.registerLocationListener(new BDLocationListener() {  
		              
		            @Override  
		            public void onReceiveLocation(BDLocation location) {  
		                // TODO Auto-generated method stub  
		                if (location == null) {  
		                    return;  
		                }
		                System.out.println("��ַΪ:"+location.getAddrStr() +"  �ֵ���" +location.getStreet());
		                getAddress.setText(location.getStreet());
		            }  
		              
		        });  
		        locationClient.start();
		        locationClient.requestLocation(); 
		        locationClient.stop();
			}
		});

		/**
		 * �����¼�
		 */
		fs.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String conntentString = content.getText().toString();
				String tagString = tag.getText().toString();
				float priceNumber = Float
						.parseFloat(price.getText().toString());
				if (conntentString.length() > 0) {
					CreateMessageBean creater = new CreateMessageBean();// ��װ��Ϣ
					StreetMessageBean messageBean = creater.create(
							conntentString, tagString, priceNumber);
					PublishConnecter connecter = new PublishConnecter();
					connecter.send(messageBean);
					content.setText("");
				} else {
					Toast.makeText(Publish_Activity.this, "�ף�д�������ٷ���~!",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		pjz.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Publish_Activity.this,
						Index_Activity.class);
				startActivity(intent);
			}
		});
	}
}
