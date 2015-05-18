package com.example.freeunichat.ui;

import android.os.Bundle;

import com.example.freeunichat.App;
import com.example.freeunichat.model.Message;
import com.example.freeunichat.transport.ChatEventListener;

public class ChatActivity extends CustomActivity implements ChatEventListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		App.getChatTransport().addChatEventListsner(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		App.getChatTransport().removeChatEventListsner(this);
	}
	
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

}
