package com.HCI.remote;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.HCI.remote.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

public class Remote extends Activity implements SimpleGestureListener {
	
	private SimpleGestureFilter detector;
	private Socket client;
	private PrintWriter printwriter;
	private String msg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remote);
		
		detector = new SimpleGestureFilter(this,this);
	}
	
	@Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
         this.detector.onTouchEvent(me);
       return super.dispatchTouchEvent(me);
    }

	@Override
	public void onSwipe(int direction) {
		// TODO Auto-generated method stub
		switch (direction) {
	      case SimpleGestureFilter.SWIPE_RIGHT : msg = "Swipe Right";
	                                               break;
	      case SimpleGestureFilter.SWIPE_LEFT : msg = "Swipe Left";
	                                                     break;
	      case SimpleGestureFilter.SWIPE_DOWN : msg = "Swipe Down";
	                                                     break;
	      case SimpleGestureFilter.SWIPE_UP : msg = "Swipe Up";
	                                                     break;
	      }
		
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		
		SendMessage sendMessageTask = new SendMessage(msg);
		sendMessageTask.execute();
		
	}

	@Override
	public void onDoubleTap() {
		// TODO Auto-generated method stub
		msg = "DoubleTap";
		
		SendMessage sendMessageTask = new SendMessage(msg);
		sendMessageTask.execute();
		
	}

	@Override
	public void onLongPress() {
		// TODO Auto-generated method stub
		msg = "LongPress";
		SendMessage sendMessageTask = new SendMessage(msg);
		sendMessageTask.execute();
	}
	
	private class SendMessage extends AsyncTask<Void, Void, Void> {
		
		public String something = "";

		public SendMessage(String msg) {
			// TODO Auto-generated constructor stub
			something = msg;
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Intent intent = getIntent();
				String ip = intent.getStringExtra("ip");
				int port = Integer.parseInt(intent.getStringExtra("port"));
				client = new Socket(ip, port); 
				printwriter = new PrintWriter(client.getOutputStream(), true);
				printwriter.write(something);
				printwriter.flush();
				printwriter.close();
				client.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
