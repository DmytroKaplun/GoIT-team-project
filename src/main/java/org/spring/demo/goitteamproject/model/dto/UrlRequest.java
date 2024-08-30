package org.spring.demo.goitteamproject.model.dto;

import lombok.Data;

@Data
public class UrlRequest {
    private String longUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
