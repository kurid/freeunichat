package com.example.freeunichat.asynchtasks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;

import com.example.freeunichat.App;
import com.example.freeunichat.model.Contact;
import com.example.freeunichat.transport.NetworkEventListener;

public class ContactImageDownloaderTask extends AsyncTask<List<Contact>, Object, Void>{
	private List<NetworkEventListener> networkEventListeners;
	
	public ContactImageDownloaderTask() {
        networkEventListeners = new ArrayList<NetworkEventListener>();
	}

	@Override
	protected Void doInBackground(List<Contact>... params) {
		for(Contact contact : App.getContactList()){
            try {
                byte[] newImg = downloadImgFromURL(contact.getAvatarImg());
                contact.setImg(newImg);

                publishProgress(newImg,contact.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		return null;
	}


    private byte[] downloadImgFromURL(String myURL) throws IOException {
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
            return readIt(is);
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



    public void setNetworkEventListener(NetworkEventListener networkEventListener) {
        this.networkEventListeners.add( networkEventListener);
	}
	
	@Override
	protected void onProgressUpdate(Object... values) {
		super.onProgressUpdate(values);
		if (networkEventListeners != null)
            for(NetworkEventListener n : networkEventListeners){
                n.onAvatarDownloaded((byte[])values[0], (String)values[1]);
            }
	}
	
}
