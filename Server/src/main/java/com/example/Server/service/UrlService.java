package com.example.Server.service;

import com.example.Server.entity.Url;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UrlService {

    //save the new Url
    void saveUrl(Url url);

    //get the short url mapped to the full url
    Url getUrl(String fullUrl);

    //check if the fullUrl is saved before
    Url getOriginalUrl(String fullUrl);

}
