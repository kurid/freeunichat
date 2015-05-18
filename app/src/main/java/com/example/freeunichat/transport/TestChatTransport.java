package com.example.freeunichat.transport;

import com.example.freeunichat.model.Message;

public class TestChatTransport extends ChatTransport {

    private ChatEventListener chatEventListener;
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

    /**
     * გამოიძახება ყოველთვის როცა მომხმარებელი გზავნის შეტყობინებას რომელიმე მეგობართან
     */
	@Override
	public void sendMessage(Message m) {
		// TODO Auto-generated method stub

	}

    public void setChatEventListener(ChatEventListener chatEventListener) {
        this.chatEventListener = chatEventListener;
    }


}
