package com.example.Server.service;

import com.example.Server.entity.Url;

import java.util.List;

public interface UrlService {

    //save the new Url
    void saveUrl(Url url);

    Url findById(String key);

    //check if the fullUrl is saved before
    Url getUrlInfoWithFullUrl(String fullUrl);

    List<String> getAllKeys();
}
