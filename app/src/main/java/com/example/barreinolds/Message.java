package com.example.barreinolds;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 4L;
    private String request;

    public Message(String request){
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
