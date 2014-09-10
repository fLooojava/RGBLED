package com.example.rgbled;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends Activity
{
	   public static final String MyPREFERENCES = "MyPrefs" ;
	   private Button btnSaveMAC;
	   private EditText mactxt;
	   private TextView textViewMAC,txtViewcurrentmac;
	   SharedPreferences sharedpreferences = null;
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_settings);
	        btnSaveMAC = (Button)findViewById(R.id.btnSaveMAC);
	 	    mactxt = (EditText)findViewById(R.id.mactxt);
	 	    textViewMAC = (TextView)findViewById(R.id.textViewMAC);
	 	   String s = textViewMAC.getText().toString();
	 	   
	 	    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
	 	      if (sharedpreferences.contains("mac"))
	 	      {
	 	    	 mactxt.setText(sharedpreferences.getString("mac",""));
	 	    	 textViewMAC.setText(" "+sharedpreferences.getString("mac",""));
	 	      }
	        
	        btnSaveMAC.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
			        String mac = mactxt.getText().toString();
			        textViewMAC.setText(mac);
			        saveMAC(mac);
		            //Neues Intent anlegen
	                Intent mainScreen = new Intent(getApplicationContext(), MainActivity.class);
	                //Intent mit den Daten füllen
	                mainScreen.putExtra("macaddress", mac);
	                // Intent starten und zur zweiten Activity wechseln
	                startActivity(mainScreen);
	                
				}
			});
	       
	    }
	   public void saveMAC(String s){
		  
		   Editor editor = sharedpreferences.edit();
		   editor.putString("mac", s);
		   editor.commit();
		   
	   };
	  
	  
}


