package org.alex.springapp.controller;

/**
 *
 * @author zamdirit
 */
public class ResponseMessage {
    
    private final int type;
    private final String message;
    private final Object contextObject;
    
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    public ResponseMessage(int type, String message, Object contextObject) {
        this.type = type;
        this.message = message;
        this.contextObject = contextObject;
    }
    
    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Object getContextObject() {
        return contextObject;
    }
    
}
