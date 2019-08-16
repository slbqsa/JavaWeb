package com.onlineshoppingmall.error;

public class IllegalException extends Exception implements Error{
    private Error error;

    public IllegalException(Error error){
        super();
        this.error = error;
    }
    public IllegalException(Error error,String errorMassage){
        super();
        this.error = error;
        this.error.setErrorMassage(errorMassage);
    }
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int getErrorCode() {
        return this.error.getErrorCode();
    }

    @Override
    public String getErrorMassage() {
        return this.error.getErrorMassage();
    }

    @Override
    public Error setErrorMassage(String errorMassage) {
//        this.error.setErrorMassage(errorMassage);
//        return this;
        return error.setErrorMassage(errorMassage);

    }
}
