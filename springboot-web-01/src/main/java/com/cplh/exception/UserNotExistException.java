package com.cplh.exception;

public class UserNotExistException extends RuntimeException  {

    private String id;

    public UserNotExistException(String id) {
        super("user not exist " + id);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
