package com.codes.hshah.urlshortner.repository;

import com.codes.hshah.urlshortner.entity.ClientConfiguration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientConfigurationRepository extends CrudRepository<ClientConfiguration, Long> {
    Optional<ClientConfiguration> findByHostName(String name);
}
