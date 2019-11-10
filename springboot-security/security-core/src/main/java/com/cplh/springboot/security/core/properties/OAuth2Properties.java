package com.cplh.springboot.security.core.properties;

import java.util.ArrayList;
import java.util.List;

public class OAuth2Properties {

    private List<OAuth2ClientProperties> client = new ArrayList<>();

    private String jwtSigningKey = "Sohigh";

    public List<OAuth2ClientProperties> getClient() {
        return client;
    }

    public void setClient(List<OAuth2ClientProperties> client) {
        this.client = client;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}
