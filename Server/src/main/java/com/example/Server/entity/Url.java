package com.example.Server.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="url")
public class Url {

    @Id
    @Column(name = "full_url")
    private String realUrl;
    @Column(name = "short_url")
    private String shortUrl;

    public Url() {

    }

    public Url(String realUrl, String shortUrl) {
        this.realUrl = realUrl;
        this.shortUrl = shortUrl;
    }

    public String getRealUrl() {
        return realUrl;
    }

    public void setRealUrl(String realUrl) {
        this.realUrl = realUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "Url{" +
                "realUrl='" + realUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
