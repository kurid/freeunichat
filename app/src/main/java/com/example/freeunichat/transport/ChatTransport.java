package com.example.freeunichat.transport;

import java.util.ArrayList;
import java.util.List;

import com.example.freeunichat.model.Message;


public abstract class ChatTransport {
	private List<ChatEventListener> listeners = new ArrayList<ChatEventListener>();
	
	public abstract void start();
	public abstract void sendMessage(Message m);
	
	public void addChatEventListsner(ChatEventListener listener) {
		if (!listeners.contains(listener))
			listeners.add(listener);
	}
	
	public void removeChatEventListsner(ChatEventListener listener) {
		if (listeners.contains(listener))
			listeners.remove(listener);
	}
}
