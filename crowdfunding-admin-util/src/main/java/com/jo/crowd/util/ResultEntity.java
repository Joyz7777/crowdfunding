package com.jo.crowd.util;

public class ResultEntity<T> {

    private static final String SUCCESS = "SUCCESS";

    private String result;
    private String message;
    private T date;


    public ResultEntity() {
    }

    public static <E> ResultEntity<E> successWithData(E date) {

        return new ResultEntity<E>(SUCCESS, null, date);


    }

    public static  ResultEntity successWithNoData() {
        ResultEntity<String> resultEntity = new ResultEntity<>();
        resultEntity.setResult(SUCCESS);

        return resultEntity;


    }


    public ResultEntity(String result, String message, T date) {
        this.result = result;
        this.message = message;
        this.date = date;
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

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
