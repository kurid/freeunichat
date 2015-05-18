package com.example.freeunichat.asynchtasks;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.List;

import com.example.freeunichat.App;
import com.example.freeunichat.R;
import com.example.freeunichat.model.Contact;
import com.example.freeunichat.model.ContactsHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DBContactListDownloaderTask extends ContactListDownloaderTask {


	@Override
	protected List<Contact> doInBackground(Void... params) {
		// TODO Auto-generated method stub
        String jsonStr = App.getJSONStr();
        Gson gson = new Gson();
        List<Contact> contacts = gson.fromJson(jsonStr, new TypeToken<List<Contact>>(){ }.getType());
        return contacts;
	}

}
