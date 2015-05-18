package com.example.freeunichat;

import java.util.ArrayList;
import java.util.List;

import com.example.freeunichat.asynchtasks.ContactImageDownloaderTask;
import com.example.freeunichat.asynchtasks.ContactListDownloaderTask;
import com.example.freeunichat.asynchtasks.DBContactListDownloaderTask;
import com.example.freeunichat.asynchtasks.URLContactListDownloaderTask;
import com.example.freeunichat.db.MyDBHelper;
import com.example.freeunichat.model.Contact;
import com.example.freeunichat.model.Message;
import com.example.freeunichat.transport.ChatEventListener;
import com.example.freeunichat.transport.ChatTransport;
import com.example.freeunichat.transport.NetworkEventListener;
import com.example.freeunichat.transport.TestChatTransport;
import com.google.gson.Gson;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


public class App extends Application implements NetworkEventListener, ChatEventListener {
	private static ChatTransport chatTransport;
	private static ArrayList<Contact> contacts;
    private static MyDBHelper dbHelper;

    private Context context;
    private static SharedPreferences sharedPref;
    private static ContactListDownloaderTask contactListDownloaderTask;
    private static ContactImageDownloaderTask contactImageDownloaderTask;
    private static Application instance;
    private static final String JSON_STRING = "jsonStr";


	@Override
	public void onCreate() {
		super.onCreate();
		initApp();
        instance = this;
	}
	
	private void initApp() {
		chatTransport = new TestChatTransport();
		chatTransport.addChatEventListsner(this);
        sharedPref = getSharedPreferences(getString(R.string.preferences),Context.MODE_PRIVATE);
        contacts = new ArrayList<Contact>();
        contactImageDownloaderTask = new ContactImageDownloaderTask();
        downloadContacts();
	}


    private void downloadContacts() {
        if(isFirstExecution() ){
            Log.d("AAAA", "is first execution");
            contactListDownloaderTask = new URLContactListDownloaderTask();
        }else{
            Log.d("AAAA", "is NOT first execution");
            contactListDownloaderTask = new DBContactListDownloaderTask();
        }
        contactListDownloaderTask.setNetworkEventListener(this);
        contactListDownloaderTask.execute();
    }

    private boolean isFirstExecution() {
        String firstRun = getString(R.string.first_run);
        boolean isFirst = sharedPref.getBoolean(firstRun,true);
        return isFirst;
    }

    public static ChatTransport getChatTransport() {
		return chatTransport;
	}
	
	public static ArrayList<Contact> getContactList() {
		return contacts;
	}

    public  static void addNetworkListener(NetworkEventListener nel){
        contactListDownloaderTask.setNetworkEventListener(nel);
        contactImageDownloaderTask.setNetworkEventListener(nel);
    }

    public static String getJSONStr(){
        return sharedPref.getString(JSON_STRING, null);
    }

    public static Application getInstance(){
        return instance;
    }


    //=====================================================================================
	@Override
	public void onIncomingMsg(Message m) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onOutgoingMsg(Message m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String contactId, boolean isOnline) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onContactListDownloaded(List<Contact> contacts) {
        this.contacts = (ArrayList)contacts;
        contactImageDownloaderTask.setNetworkEventListener(this);
        contactImageDownloaderTask.execute(contacts);
        if(isFirstExecution()){
            Gson gson = new Gson();
            String jsonStr = gson.toJson(contacts);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(JSON_STRING,jsonStr);
            editor.putBoolean(getString(R.string.first_run), false);
            editor.commit();
        }
	}

	@Override
	public void onAvatarDownloaded(byte[] imgData, String contactId) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onError(int errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		
	}
}
