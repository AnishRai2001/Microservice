package com.example.demo.responseStructure;



public class ResponseStructure<T> {

    private boolean status;
    private String message;
    private T data;

    // Constructors
    public ResponseStructure() {
    }

    public ResponseStructure(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean b) {
        this.status = b;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
