package com.tanuj.balance;

import android.app.Activity; 
import android.content.Intent; 
import android.content.SharedPreferences;
import android.net.Uri; 
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener
{ 
    private Button b1;
    private Button b2;
    private Button b3;
    public static String s1 = "*141#";
    public static String s2 = "*157#";
    public static String s3 = "*156#";
    public static boolean s;
    
    /** Called when the activity is first created. */ 
    @Override 
    public void onCreate(Bundle savedInstanceState)
    { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.main); 
        b1 = (Button) findViewById(R.id.b1); 
        b2 = (Button) findViewById(R.id.b2); 
        b3 = (Button) findViewById(R.id.b3); 
        b1.setTag("b1");
        b2.setTag("b2");
        b3.setTag("b3");
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this); 
    } 

	@Override
	protected void onResume() 
	{
		super.onResume();
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		s = preferences.getBoolean("button", false);
		
		if(s == true)
		{
			b1.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
			b2.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
			b3.setBackgroundDrawable(getResources().getDrawable(R.drawable.button));
		}
		else
		{
			b1.setBackgroundDrawable(null);
			b2.setBackgroundDrawable(null);
			b3.setBackgroundDrawable(null);
			b1.setBackgroundColor(android.graphics.Color.GRAY);
			b2.setBackgroundColor(android.graphics.Color.GRAY);
			b3.setBackgroundColor(android.graphics.Color.GRAY);
		}
	}

	public void onClick(View v) 
	{	
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		s1 = preferences.getString("etp1", s1);
		s2 = preferences.getString("etp2", s2);
		s3 = preferences.getString("etp3", s3);
		String val = "", ss[];
		String encodedHash = Uri.encode("#");
		String tag = (String)v.getTag();
		if(tag.equals("b1"))
		{
			ss = s1.split("#");
			for(int i=0 ; i<ss.length ; i++)
			{
				val += ss[i];
				val += encodedHash;
			}
			if(s1.charAt(s1.length()-1) != '#')
				val = val.substring(0, val.length()-1-encodedHash.length());
		}
		else if(tag.equals("b2"))
		{
			ss = s2.split("#");
			for(int i=0 ; i<ss.length ; i++)
			{
				val += ss[i];
				val += encodedHash;
			}
			if(s2.charAt(s2.length()-1) != '#')
				val = val.substring(0, val.length()-1-encodedHash.length());
		}
		else if(tag.equals("b3"))
		{
			 ss = s3.split("#");
			for(int i=0 ; i<ss.length ; i++)
			{
				val += ss[i];
				val += encodedHash;
			}
			if(s3.charAt(s3.length()-1) != '#')
				val = val.substring(0, val.length()-1-encodedHash.length());
		}
		call(val);
	} 
	
	protected void call(String phoneNumber)
	{ 
        try
        { 
        	startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber)), 1); 
        } 
        catch(Exception e) 
        { 
        	(Toast.makeText(this, "Error Occured\n"+e.toString(), Toast.LENGTH_SHORT)).show();
        } 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
	    // Handle item selection
		String title = (String)item.getTitle();
		Intent intent;
		
		if(title.equals("About"))
		{
			intent = new Intent("org.openintents.action.SHOW_ABOUT_DIALOG");
			startActivityForResult(intent, 0);
		}
		else
		{
			intent = new Intent(getBaseContext(), Settings.class);
			startActivity(intent);
		}
		
		return true;
	}
}