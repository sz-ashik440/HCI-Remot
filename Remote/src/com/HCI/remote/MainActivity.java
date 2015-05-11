package com.HCI.remote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	EditText input_ip, input_port;
	Button btn_connenct;
	String ip, port;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		input_ip = (EditText) findViewById(R.id.edit_ip);
		input_port = (EditText) findViewById(R.id.edit_port);
		btn_connenct = (Button) findViewById(R.id.btn_connect);
		
		btn_connenct.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ip = input_ip.getText().toString();
				port = input_port.getText().toString();
				
				Intent intent = new Intent(getApplicationContext(), Remote.class);
				intent.putExtra("ip", ip);
				intent.putExtra("port", port);
				startActivity(intent);
				
			}
		});
	}

		
}
