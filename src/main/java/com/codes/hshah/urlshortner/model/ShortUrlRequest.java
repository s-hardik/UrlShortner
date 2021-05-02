package com.codes.hshah.urlshortner.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ShortUrlRequest {
    private String longurl;
    private String hostName;
}
