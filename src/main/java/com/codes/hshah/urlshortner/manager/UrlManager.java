package com.codes.hshah.urlshortner.manager;

import com.codes.hshah.urlshortner.model.ShortUrlRequest;

public interface UrlManager {
    String createShortUrl(ShortUrlRequest shortUrlRequest);
    String getUrl(String encrp_id) throws Exception;
}
