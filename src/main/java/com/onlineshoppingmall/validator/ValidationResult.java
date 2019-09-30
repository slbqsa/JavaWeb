package com.onlineshoppingmall.validator;

import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    //校验结果判断
    private boolean hasErrors = false;

    private Map<String,String> errorMagMap = new HashMap<>();

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMagMap() {
        return errorMagMap;
    }

    public void setErrorMagMap(Map<String, String> errorMagMap) {
        this.errorMagMap = errorMagMap;
    }



    public String getErrMsg(){
      return  StringUtils.join(errorMagMap.values().toArray(),",");
    }
}
