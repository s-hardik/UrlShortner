package com.codes.hshah.urlshortner.manager;

import com.codes.hshah.urlshortner.entity.ClientConfiguration;
import com.codes.hshah.urlshortner.entity.LongUrl;
import com.codes.hshah.urlshortner.model.ShortUrlRequest;
import com.codes.hshah.urlshortner.repository.ClientConfigurationRepository;
import com.codes.hshah.urlshortner.repository.LongUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class UrlManagerImpl implements UrlManager{
    @Autowired
    private ClientConfigurationRepository clientConfigurationRepository;
    @Autowired
    private LongUrlRepository longUrlRepository;
    @Override
    public String createShortUrl(ShortUrlRequest shortUrlRequest) {
        LongUrl longUrl = LongUrl.builder()
                .url(shortUrlRequest.getLongurl())
                .createdAt(LocalDateTime.now())
                .isexpired(false).build();
        ClientConfiguration clientConfiguration = clientConfigurationRepository.findByHostName(shortUrlRequest.getHostName())
                .orElse(null);
        if(clientConfiguration == null){
            clientConfiguration = ClientConfiguration.builder()
                    .hostName(shortUrlRequest.getHostName())
                    .longUrls(new ArrayList<>()).build();
        }
        for(LongUrl url : clientConfiguration.getLongUrls())
        {
            if(url.getUrl().equals(shortUrlRequest.getLongurl()))
            {
                return buildShortUrl(clientConfiguration.getHostName(),encryptID(url.getId()));
            }
        }
        clientConfiguration.getLongUrls().add(longUrl);
        ClientConfiguration savedClientConfiguration = clientConfigurationRepository.save(clientConfiguration);
        Long id = null;
       for(LongUrl url : savedClientConfiguration.getLongUrls())
       {
           if(url.getUrl().equals(shortUrlRequest.getLongurl()))
           {
               id = url.getId();
           }
       }

        return buildShortUrl(clientConfiguration.getHostName(),encryptID(id));

    }
    String encryptID(Long id){
        String encrID = Base64.getEncoder().encodeToString(String.valueOf(id).getBytes(StandardCharsets.UTF_8));
        return encrID;
    }
    private String buildShortUrl(String hostName, String UrlId){
        return "http://".concat(hostName).concat("/").concat(UrlId);

    }

    @Override
    public String getUrl(String encrp_id) throws Exception {
        Long id = decryptID(encrp_id);
        LongUrl longUrl = longUrlRepository.findById(id)
                .orElseThrow(()-> new Exception("no url found"));
        return longUrl.getUrl();
    }

    Long decryptID(String encid) throws UnsupportedEncodingException {
       String decrptedID = new String(Base64.getDecoder().decode(encid), "UTF-8");
        return Long.parseLong(decrptedID);
    }
}

