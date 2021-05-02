package com.codes.hshah.urlshortner.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String hostName = "http://localhost:8080//";
    private LocalDateTime createdAt;
    @OneToMany(cascade =  CascadeType.ALL)
    private List<LongUrl> longUrls;
}
