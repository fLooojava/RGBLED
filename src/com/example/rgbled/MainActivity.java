package com.example.rgbled;


import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

   private Button Red,Green,Blue,BToff,Fade,White,Dark,Bright,Regular,Orange;
   private BluetoothAdapter BA;
   private BluetoothSocket socket;
   private String output;
   private String status;
   boolean socketconn;
   private int	bright;
   String[] ColorArray = new String[] {
		  "red","green","blue",
		  "orange","white",
		  "fade","btoff",
   };
   @Override
   protected void onCreate(Bundle savedInstancestatus) {
      
	   super.onCreate(savedInstancestatus);
      setContentView(R.layout.activity_main);
      BA = BluetoothAdapter.getDefaultAdapter();
      Red = (Button)findViewById(R.id.btnR);
      Green =(Button)findViewById(R.id.btnG);
      Blue =(Button)findViewById(R.id.btnB);
      BToff =(Button)findViewById(R.id.btnOff);
      White =(Button)findViewById(R.id.btnWhite);
      Dark = (Button)findViewById(R.id.btndark);
      Regular = (Button)findViewById(R.id.btnregular);
      Bright = (Button)findViewById(R.id.btnbright);
      Orange = (Button)findViewById(R.id.btnorange);
      Fade =(Button)findViewById(R.id.btnF);


      
      BluetoothOn();
      
      Red.setOnClickListener(new View.OnClickListener(){
   		public void onClick(View v) {
   			sendValue(ColorArray[0]);
   			setstatus("red");
   	     
   		}
       });
      Green.setOnClickListener(new View.OnClickListener(){
   		public void onClick(View v) {
   			sendValue(ColorArray[1]);
   			setstatus("green");
   		}
       });
      Blue.setOnClickListener(new View.OnClickListener(){
   		public void onClick(View v) {
   			sendValue(ColorArray[2]);
   			setstatus("blue");
   		}
       });
      Orange.setOnClickListener(new View.OnClickListener(){
   		public void onClick(View v) {
   			sendValue(ColorArray[3]);
   			setstatus("orange");
   		}
       });
      White.setOnClickListener(new View.OnClickListener(){
     		public void onClick(View v) {
     			sendValue(ColorArray[4]);
     			setstatus("white");
     		}
         });
      Fade.setOnClickListener(new View.OnClickListener() {
	
    	 
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
   			sendValue(ColorArray[5]);
		}
		});
      BToff.setOnClickListener(new View.OnClickListener(){
   		public void onClick(View v) {
   			sendValue(ColorArray[6]);

   		}
       });
      

      Dark.setOnClickListener(new View.OnClickListener(){
     		public void onClick(View v) {
     		setBrightness(1);
     		for (int i=0; i<ColorArray.length; i++) {
     		if(getstatus().equals(ColorArray[i])){
            	sendValue(ColorArray[i]+String.valueOf(getBrightnessvalue()));
            }
            }
     		}
         });
      Regular.setOnClickListener(new View.OnClickListener(){
   		public void onClick(View v) {
   		setBrightness(2);
 		for (int i=0; i<ColorArray.length; i++) {
 		if(getstatus().equals(ColorArray[i])){
        	sendValue(ColorArray[i]+String.valueOf(getBrightnessvalue()));
        }
        }
   		}
       });
      Bright.setOnClickListener(new View.OnClickListener(){
   		public void onClick(View v) {
   		setBrightness(3);
 		for (int i=0; i<ColorArray.length; i++) {
 		if(getstatus().equals(ColorArray[i])){
        	sendValue(ColorArray[i]+String.valueOf(getBrightnessvalue()));
        }
        }
   		}
       });
    }

     public void SocketConnection(View view){
    	socketconn = true;
    	SharedPreferences sharedpreferences;
		final String MyPREFERENCES = "MyPrefs" ;
 	    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
 	    String address = sharedpreferences.getString("mac", "");

    	// hardcoded string - String address ="20:13:12:04:07:25";
		BluetoothDevice device = BA.getRemoteDevice(address);
      try {
		socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
		socket.connect();
		Toast.makeText(getApplicationContext(),"Connection established!" ,Toast.LENGTH_LONG).show();
  	   Log.d("MAC#",address);
      } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		Toast.makeText(getApplicationContext(),"Connection refused, cannot connect!" ,Toast.LENGTH_LONG).show();
		Log.d("MAC#",address);

	}
    }
     public void BluetoothOn(){
    	 if (!BA.isEnabled()) {
             Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
             startActivityForResult(turnOn, 0);
             Toast.makeText(getApplicationContext(),"Turned on" 
             ,Toast.LENGTH_LONG).show();
          }
          else{
             Toast.makeText(getApplicationContext(),"Already on",
             Toast.LENGTH_LONG).show();
             }
     }
     public void BluetoothOff(View view){
       BA.disable();
       Toast.makeText(getApplicationContext(),"Turned off" ,
       Toast.LENGTH_LONG).show();
    }
     public void sendValue(String value) {
    	if(socketconn==true){
    	setOutput(value+"\n");
    	getOutputStream_and_sendData();} 
    	else Toast.makeText(getApplicationContext(),"Please connect to the RGB Strip first." , Toast.LENGTH_LONG).show();
    }
     public void getOutputStream_and_sendData(){
    	Thread connection = new Thread() {
			public void run() {
  
				try {
		
					OutputStream outputStream = socket.getOutputStream();
					System.out.println(getOutputvalue());
					outputStream.write(getOutputvalue().getBytes());
					outputStream.flush();
					
				} catch (IOException e) {
				} 
			};
		};
	connection.start();
    }
     // GET and SET methods  \\
     public void setBrightness(int progress){
 		bright =progress;
 	}
     public int getBrightnessvalue(){
  		return bright;
    }
     public void setstatus(String statuscolor){
    	status = statuscolor;
    }
     public String getstatus(){
    	return status;
    }
     public void setOutput(String out){
   		output =out;
   	}
     public String getOutputvalue(){
	  	return output;
    }
 
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {

	   switch (item.getItemId()) {
	   
	   case R.id.action_settings:
		   Intent Intentsettings = new Intent(this,SettingsActivity.class);
		   this.startActivity(Intentsettings);
		   return true;
	   case R.id.menu_about:
		   Intent Intentabout = new Intent(this,AboutActivity.class);
		   this.startActivity(Intentabout);
	   return true;
	   
	   
	    
	   }
	return true;


   }
}