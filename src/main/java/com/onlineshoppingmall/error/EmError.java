package com.onlineshoppingmall.error;

public enum EmError implements Error{
    ILLEGAL_INPUT(10001,"输入不合法"),
    ILLEGAL_VALUED(00002,"未知错误"),
    USER_NOT_EXIST(21111,"用户不存在");
    private int errorCode;
    private String errorMassage;
private EmError(int errorCode,String errorMassage){
    this.errorCode = errorCode;
    this.errorMassage = errorMassage;
}

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMassage() {
        return this.errorMassage;
    }

    @Override
    public Error setErrorMassage(String errorMassage) {
        this.errorMassage = errorMassage;
        return this;
    }
}
