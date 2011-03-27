package com.tanuj.balance;

import android.app.Activity; 
import android.content.Intent; 
import android.net.Uri; 
import android.os.Bundle;
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

	public void onClick(View v) 
	{
		String val = "";
		String encodedHash = Uri.encode("#"); 
		String tag = (String)v.getTag();
		if(tag.equals("b1"))
			val = "*141";
		else if(tag.equals("b2"))
			val = "*157";
		else if(tag.equals("b3"))
			val = "*156";
		call(val+encodedHash);
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
		Intent intent = new Intent("org.openintents.action.SHOW_ABOUT_DIALOG");
		startActivityForResult(intent, 0);
		return true;
	}
}