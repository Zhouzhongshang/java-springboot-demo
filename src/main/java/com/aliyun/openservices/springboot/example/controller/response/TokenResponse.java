package com.aliyun.openservices.springboot.example.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 响应信息
 */
public class TokenResponse implements Serializable {
    private static final long serialVersionUID = -8914890207238281486L;

    /**
     * traceId : 92d859d6-20bb-45ad-9dd4-f212d33b8f56
     * code : null
     * data : {"accessToken":{"accessToken":"95ab70cf77107e99ae34486218f1880f","tokeType":2,"invalidTime":"2021-03-14 14:39:27","registerTime":"2021-03-14 13:23:45"}}
     * errorDesc : null
     * solution : null
     * opers : null
     * success : true
     * exStack : null
     * errorCode : null
     * resultMsg : null
     */

    @JsonProperty("traceId")
    private String traceId;
    @JsonProperty("code")
    private String code;
    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("errorDesc")
    private String errorDesc;
    @JsonProperty("solution")
    private String solution;
    @JsonProperty("opers")
    private String opers;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("exStack")
    private String exStack;
    @JsonProperty("errorCode")
    private String errorCode;
    @JsonProperty("resultMsg")
    private String resultMsg;

    public TokenResponse() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getOpers() {
        return opers;
    }

    public void setOpers(String opers) {
        this.opers = opers;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getExStack() {
        return exStack;
    }

    public void setExStack(String exStack) {
        this.exStack = exStack;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public static class DataDTO {
        public DataDTO() {
        }

        public AccessTokenDTO getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(AccessTokenDTO accessToken) {
            this.accessToken = accessToken;
        }

        /**
         * accessToken : {"accessToken":"95ab70cf77107e99ae34486218f1880f","tokeType":2,"invalidTime":"2021-03-14 14:39:27","registerTime":"2021-03-14 13:23:45"}
         */

        @JsonProperty("accessToken")
        private AccessTokenDTO accessToken;

        public static class AccessTokenDTO {
            public String getAccessToken() {
                return accessToken;
            }

            public void setAccessToken(String accessToken) {
                this.accessToken = accessToken;
            }

            public Integer getTokeType() {
                return tokeType;
            }

            public void setTokeType(Integer tokeType) {
                this.tokeType = tokeType;
            }

            public String getInvalidTime() {
                return invalidTime;
            }

            public void setInvalidTime(String invalidTime) {
                this.invalidTime = invalidTime;
            }

            public String getRegisterTime() {
                return registerTime;
            }

            public void setRegisterTime(String registerTime) {
                this.registerTime = registerTime;
            }

            public AccessTokenDTO() {
            }

            /**
             * accessToken : 95ab70cf77107e99ae34486218f1880f
             * tokeType : 2
             * invalidTime : 2021-03-14 14:39:27
             * registerTime : 2021-03-14 13:23:45
             */

            @JsonProperty("accessToken")
            private String accessToken;
            @JsonProperty("tokeType")
            private Integer tokeType;
            @JsonProperty("invalidTime")
            private String invalidTime;
            @JsonProperty("registerTime")
            private String registerTime;
        }
    }
}
