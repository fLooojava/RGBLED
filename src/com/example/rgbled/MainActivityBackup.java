package com.example.rgbled;


import java.io.IOException;
import java.io.OutputStream;

import java.util.UUID;


import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;

import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.RadioButton;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup;

public class MainActivityBackup extends Activity {

   private Button BTOn,BTOff,Red,Green,Blue,Off,Fade,White;
   private RadioButton RadioF,RadioN,RadioS;
   private RadioGroup RadioNroup;
   private BluetoothAdapter BA;
   String address ="20:13:12:04:07:25";
   //BluetoothDevice device = BA.getRemoteDevice(address);
   BluetoothSocket socket;
   OutputStream mmOutputStream;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      BA = BluetoothAdapter.getDefaultAdapter();
      Red = (Button)findViewById(R.id.btnR);
      Green =(Button)findViewById(R.id.btnG);
      Blue =(Button)findViewById(R.id.btnB);
      Off =(Button)findViewById(R.id.btnOff);
      White =(Button)findViewById(R.id.btnWhite);

      RadioF =(RadioButton)findViewById(R.id.radioButtonFast);
      RadioN =(RadioButton)findViewById(R.id.radioButtonNormal);
      RadioS =(RadioButton)findViewById(R.id.radioButtonSlow);
      Fade =(Button)findViewById(R.id.btnF);
      
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

		

     Red.setOnClickListener(new View.OnClickListener(){
   		public void onClick(View v) {
   			String value = "red";
   			sendValue(value);
   			Toast.makeText(getApplicationContext(), "red r",
    				   Toast.LENGTH_LONG).show();
   		
   		}
       });
      Green.setOnClickListener(new View.OnClickListener(){
   		public void onClick(View v) {
   			String value = "green";
   			sendValue(value);
   			Toast.makeText(getApplicationContext(), "green g",
   				   Toast.LENGTH_LONG).show();
   		}
       });
      Blue.setOnClickListener(new View.OnClickListener(){
   		public void onClick(View v) {
   			String value = "blue";
   			sendValue(value);
   			Toast.makeText(getApplicationContext(), "blue b",
   				   Toast.LENGTH_LONG).show();
   		}
       });
      White.setOnClickListener(new View.OnClickListener(){
     		public void onClick(View v) {
     			String value = "white";
     			sendValue(value);
     			Toast.makeText(getApplicationContext(), "white w",
      				   Toast.LENGTH_LONG).show();
     		
     		}
         });
      Fade.setOnClickListener(new View.OnClickListener() {
	
    	 
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(RadioF.isChecked()){String value = "fadered"; sendValue(value);    			Toast.makeText(getApplicationContext(), "fr",
	   				   Toast.LENGTH_LONG).show();}
			else if(RadioN.isChecked()){String value = "fadegreen"; sendValue(value); Toast.makeText(getApplicationContext(), "fg",
	   				   Toast.LENGTH_LONG).show();}
			else if(RadioS.isChecked()){String value = "fadeblue";  sendValue(value); Toast.makeText(getApplicationContext(), "fb",
	   				   Toast.LENGTH_LONG).show();}
			else {Toast.makeText(getApplicationContext(), "Please choose a color to fade.",
	   				   Toast.LENGTH_LONG).show();}
		}
	});
      Off.setOnClickListener(new View.OnClickListener(){
   		public void onClick(View v) {
   			String value = "off";
   			sendValue(value);
   			Toast.makeText(getApplicationContext(), "off o",
   				   Toast.LENGTH_LONG).show();
   		}
       });

      
      
    }
 
    public void on(View view){
    	String address ="20:13:12:04:07:25";
		BluetoothDevice device = BA.getRemoteDevice(address);
      try {
		socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
		socket.connect();

      } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    
    public void off(View view){

       BA.disable();
       Toast.makeText(getApplicationContext(),"Turned off" ,
       Toast.LENGTH_LONG).show();
    }
private String output;
  public void setOutput(String out){
this.output =out;
  }
  public String getOutputvalue(){
	  return this.output;
  }
    public void sendValue(String value) {
    	
    	if(value=="red"){			setOutput("r");
    	Thread connection = new Thread() {
			public void run() {
				
				String outputstring = "r";

				try {
					//Create a Socket connection: need the server's UUID number of registered
					
								
					OutputStream outputStream = socket.getOutputStream();
				
					 outputStream.write(outputstring.getBytes());
						outputStream.flush();
						//socket.close();

				
					
				} catch (IOException e) {
				} 
			};
		};
	connection.start();
	
    	}else if(value=="green"){	setOutput("g");
    	Thread connection = new Thread() {
			public void run() {
    			String address ="20:13:12:04:07:25";
				BluetoothDevice device = BA.getRemoteDevice(address);
				String outputstring = "g";
				try {
					//Create a Socket connection: need the server's UUID number of registered

					//socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
					
					//socket.connect();			
					OutputStream outputStream = socket.getOutputStream();
					
				
					outputStream.write(	outputstring.getBytes());
					outputStream.flush();
					
				} catch (IOException e) {
					Log.e("EF-BTBee", "", e);
				} 
			};
		};
	connection.start();
	
    	}else if(value=="blue"){	setOutput("b"); 
    	Thread connection = new Thread() {
			public void run() {
    			String address ="20:13:12:04:07:25";
				BluetoothDevice device = BA.getRemoteDevice(address);
				String outputstring = "b";
				try {
					//Create a Socket connection: need the server's UUID number of registered

					//socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
					
					//socket.connect();			
					OutputStream outputStream = socket.getOutputStream();
					
				
					outputStream.write(	outputstring.getBytes());
					outputStream.flush();
					
				} catch (IOException e) {
					Log.e("EF-BTBee", "", e);
				} 
			};
		};
	connection.start();
    	}else if(value=="white"){	setOutput("w");
    	Thread connection = new Thread() {
			public void run() {
    			String address ="20:13:12:04:07:25";
				BluetoothDevice device = BA.getRemoteDevice(address);
				String outputstring = "w";
				try {
					//Create a Socket connection: need the server's UUID number of registered

					//socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
					
					//socket.connect();			
					OutputStream outputStream = socket.getOutputStream();
					
				
					outputStream.write(	outputstring.getBytes());
					outputStream.flush();
					
				} catch (IOException e) {
					Log.e("EF-BTBee", "", e);
				} 
			};
		};
	connection.start();
	
    	}
    	
    	else if(value=="fadered"){	setOutput("fadered");
    	Thread connection = new Thread() {
			public void run() {
    			String address ="20:13:12:04:07:25";
				BluetoothDevice device = BA.getRemoteDevice(address);
				String outputstring = "x";
				try {
					//Create a Socket connection: need the server's UUID number of registered

					//socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
					
					//socket.connect();			
					OutputStream outputStream = socket.getOutputStream();
					
				
					outputStream.write(	outputstring.getBytes());
					outputStream.flush();
					
				} catch (IOException e) {
					Log.e("EF-BTBee", "", e);
				} 
			};
		};
	connection.start();
    	}else if(value=="fadegreen"){	setOutput("fadegreen");
    	Thread connection = new Thread() {
			public void run() {
    			String address ="20:13:12:04:07:25";
				BluetoothDevice device = BA.getRemoteDevice(address);
				String outputstring = "y";
				try {
					//Create a Socket connection: need the server's UUID number of registered

					//socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
					
					//socket.connect();			
					OutputStream outputStream = socket.getOutputStream();
					
				
					outputStream.write(	outputstring.getBytes());
					outputStream.flush();
					
				} catch (IOException e) {
					Log.e("EF-BTBee", "", e);
				} 
			};
		};
	connection.start();
    	}else if(value=="fadeblue"){	setOutput("fadeblue");
    	Thread connection = new Thread() {
			public void run() {
    			String address ="20:13:12:04:07:25";
				BluetoothDevice device = BA.getRemoteDevice(address);
				String outputstring = "z";
				try {
					//Create a Socket connection: need the server's UUID number of registered

					//socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
					
					//socket.connect();			
					OutputStream outputStream = socket.getOutputStream();
					
				
					outputStream.write(	outputstring.getBytes());
					outputStream.flush();
					
				} catch (IOException e) {
					Log.e("EF-BTBee", "", e);
				} 
			};
		};
	connection.start();
    	}else if(value=="off"){		setOutput("o"); Thread connection = new Thread() {
			public void run() {
    			String address ="20:13:12:04:07:25";
				BluetoothDevice device = BA.getRemoteDevice(address);
				String outputstring = "o";
				try {
					//Create a Socket connection: need the server's UUID number of registered

					//socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"));
					
					//socket.connect();			
					OutputStream outputStream = socket.getOutputStream();
					
				
					outputStream.write(	outputstring.getBytes());
					outputStream.flush();
					
				} catch (IOException e) {
					Log.e("EF-BTBee", "", e);
				} 
			};
		};
	connection.start();}
    	
		
    }
	
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
}