package com.example.Server.service;

import com.example.Server.repository.UrlRepository;
import com.example.Server.entity.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService{

    private final UrlRepository urlRepository;

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Override
    public void saveUrl(Url url) {
        urlRepository.save(url);
    }

    @Override
    public Url findById(String key) {
        Optional<Url> url = urlRepository.findById(key);
        return url.orElse(null);
    }

    @Override
    public Url getUrlInfoWithFullUrl(String fullUrl) {
        return urlRepository.findByFullUrl(fullUrl);
    }

    @Override
    public List<String> getAllKeys() {
        return urlRepository.findAllUrlKeys();
    }


}
