package com.onlineshoppingmall.error;

public interface Error {
    public int getErrorCode();
    public String getErrorMassage();
    public Error setErrorMassage(String errorMassage);
}
