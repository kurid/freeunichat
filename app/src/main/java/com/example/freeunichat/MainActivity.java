package com.example.freeunichat;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TabHost;

import com.example.freeunichat.model.Contact;
import com.example.freeunichat.transport.NetworkEventListener;
import com.example.freeunichat.ui.ContactListActivity;
import com.example.freeunichat.ui.HistoryActivity;
import com.example.freeunichat.ui.SettingActivity;

import java.util.List;

public class MainActivity extends Activity implements NetworkEventListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        App.addNetworkListener(this);

        // create the TabHost that will contain the Tabs
//        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
//
//
//        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
//        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
//        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third tab");
//
//        // Set the Tab name and Activity
//        // that will be opened when particular Tab will be selected
//        tab1.setIndicator("Tab1");
//        tab1.setContent(new Intent(this,HistoryActivity.class));
//
//        tab2.setIndicator("Tab2");
//        tab2.setContent(new Intent(this,ContactListActivity.class));
//
//        tab3.setIndicator("Tab3");
//        tab3.setContent(new Intent(this,SettingActivity.class));
//
//        /** Add the tabs  to the TabHost to display. */
//        tabHost.addTab(tab1);
//        tabHost.addTab(tab2);
//        tabHost.addTab(tab3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    @Override
    public void onContactListDownloaded(List<Contact> contacts) {
        try {
            Intent intent = new Intent(this, ContactListActivity.class);
            startActivity(intent);
        } catch ( ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAvatarDownloaded(byte[] imgData, String contactId) {

    }

    @Override
    public void onError(int errorCode, String errorMsg) {

    }
}
