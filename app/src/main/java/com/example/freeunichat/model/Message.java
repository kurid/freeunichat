package com.example.freeunichat.model;

public class Message {
	private boolean incoming;
	private String toId;
    private String fromId;
    private String msg;

    public boolean isIncoming() {
        return incoming;
    }

    public String getToId() {
        return toId;
    }

    public String getFromId() {
        return fromId;
    }

    public String getMsg() {
        return msg;
    }
}
