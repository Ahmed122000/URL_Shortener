package com.example.Server.entity;


import jakarta.persistence.*;

@Entity
@Table(name="url", indexes = {
        @Index(name = "idx_url_key", columnList = "url_key"),
        @Index(name = "index_short_url", columnList = "short_url")
})
public class Url {

    @Id
    @Column(name="url_key", unique = true, nullable = false)
    private String key;

    @Column(name = "full_url", unique = true, nullable = false)
    private String fullUrl;

    @Column(name = "short_url", unique = true, nullable = false)
    private String shortUrl;

    public Url() {

    }

    public Url(String key, String realUrl, String shortUrl) {
        this.key = key;
        this.fullUrl = realUrl;
        this.shortUrl = shortUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String realUrl) {
        this.fullUrl = realUrl;
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
                "key='" + key + '\'' +
                ", fullUrl='" + fullUrl + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
