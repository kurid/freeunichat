package com.example.freeunichat.transport;

import com.example.freeunichat.model.Message;

public interface ChatEventListener {
	public void onIncomingMsg(Message m);
	public void onOutgoingMsg(Message m);
	public void onStatusChanged(String contactId, boolean isOnline);
}
