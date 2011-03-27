package com.tanuj.balance;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Settings extends PreferenceActivity
{	
	/** Called when the activity is first created. */ 
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.settings);
    }
}
