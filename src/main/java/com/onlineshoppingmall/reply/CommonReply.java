package com.onlineshoppingmall.reply;

public class CommonReply {
    //请求的状态
    private String status;
    private Object data;
    public static CommonReply create(Object result){
     return CommonReply.create(result,"success");
    }
    public static CommonReply create( Object result,String status){
    CommonReply replytype = new CommonReply();
        replytype.setStatus(status);
        replytype.setData(result);
    return replytype;
}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
