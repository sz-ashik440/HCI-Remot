package com.HCI.remote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText input_ip, input_port;
	Button btn_connenct;
	String ip, port;
	
	private boolean haveNetworkConnection() {
	    boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;

	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected())
	                haveConnectedWifi = true;
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected())
	                haveConnectedMobile = true;
	    }
	    return haveConnectedWifi || haveConnectedMobile;
	}

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
				if(haveNetworkConnection())
				{
					ip = input_ip.getText().toString();
					port = input_port.getText().toString();
					if(ip.length()!=0 && port.length()!=0)
					{
						Intent intent = new Intent(getApplicationContext(), Remote.class);
						intent.putExtra("ip", ip);
						intent.putExtra("port", port);
						startActivity(intent);
					}
					else Toast.makeText(getApplicationContext(), "Please check your IP and Port number", Toast.LENGTH_SHORT).show();
				}
				else Toast.makeText(getApplicationContext(), "Network is un available", Toast.LENGTH_SHORT).show();
			}		
		});
	}

		
}
