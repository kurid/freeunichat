package com.example.freeunichat.asynchtasks;

import java.util.ArrayList;
import java.util.List;

import com.example.freeunichat.model.Contact;
import com.example.freeunichat.transport.NetworkEventListener;

import android.os.AsyncTask;

public abstract class ContactListDownloaderTask extends AsyncTask<Void, Void, List<Contact>>{
	private List<NetworkEventListener> networkEventListeners;

    protected ContactListDownloaderTask() {
        networkEventListeners = new ArrayList<NetworkEventListener>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    public void setNetworkEventListener(NetworkEventListener networkEventListener) {
		this.networkEventListeners.add( networkEventListener);
	}
	
	@Override
	protected void onPostExecute(List<Contact> contacts) {
		super.onPostExecute(contacts);
        for(NetworkEventListener n : networkEventListeners){
            n.onContactListDownloaded(contacts);
        }
	}
}
