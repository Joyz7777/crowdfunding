package com.jo.crowd.util;

public class ResultEntity<T> {

    private static final String SUCCESS = "SUCCESS";

    private String result;
    private String message;
    private T data;


    public ResultEntity() {
    }

    public static <E> ResultEntity<E> successWithData(E data) {

        return new ResultEntity<E>(SUCCESS, null, data);


    }

    public static  ResultEntity successWithNoData() {
        ResultEntity<String> resultEntity = new ResultEntity<>();
        resultEntity.setResult(SUCCESS);

        return resultEntity;


    }


    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public void setData(T date) {
        this.data = data;
    }
}
