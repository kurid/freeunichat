package com.example.freeunichat.ui;

import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.freeunichat.App;
import com.example.freeunichat.R;
import com.example.freeunichat.model.Contact;
import com.example.freeunichat.model.Message;
import com.example.freeunichat.transport.ChatEventListener;
import com.example.freeunichat.transport.NetworkEventListener;

import java.util.List;

public class ContactListActivity extends CustomActivity {
    private ListView contactsListView;
    private BaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        App.addNetworkListener(this);
        setContentView(R.layout.contact_list);
        contactsListView = (ListView)findViewById(R.id.contact_list_view);
        adapter = new ContactListAdapter(this, App.getContactList());
        contactsListView.setAdapter(adapter);
    }
}
