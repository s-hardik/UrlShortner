package com.codes.hshah.urlshortner.repository;

import com.codes.hshah.urlshortner.entity.LongUrl;
import org.springframework.data.repository.CrudRepository;

public interface LongUrlRepository extends CrudRepository<LongUrl, Long> {
}
