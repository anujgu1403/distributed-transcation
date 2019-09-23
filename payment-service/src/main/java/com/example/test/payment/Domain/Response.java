package com.example.test.payment.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
