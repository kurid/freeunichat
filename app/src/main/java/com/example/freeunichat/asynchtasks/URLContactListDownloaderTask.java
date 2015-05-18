package com.example.freeunichat.asynchtasks;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.example.freeunichat.model.Contact;
import com.example.freeunichat.model.ContactsHolder;
import com.google.gson.Gson;


public class URLContactListDownloaderTask extends ContactListDownloaderTask {
    public static final String URL = "https://dl.dropboxusercontent.com/u/28030891/FreeUni/Android/assinments/contacts.json";
	@Override
	protected List<Contact> doInBackground(Void... params) {
        String jsonStr = null;
        try {
            jsonStr = downloadJsonStringFromURL(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        ContactsHolder holder = gson.fromJson(jsonStr, ContactsHolder.class);
        Log.d("AAAA", "" + holder.getContactList().size());
		return holder.getContactList();
	}

    private String downloadJsonStringFromURL(String myURL) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(myURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();
            String contentAsString = new String(readIt(is));
            return contentAsString;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    private byte[] readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[5000];
        int size = 0;
        while ((size = stream.read(buffer)) > 0) {
            bos.write(buffer, 0, size);
        }
        return bos.toByteArray();
    }



    @Override
    protected void onPostExecute(List<Contact> contacts) {
        super.onPostExecute(contacts);
    }
}
