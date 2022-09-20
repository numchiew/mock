package com.refinitiv.mockdacs.rest.repository;

import com.refinitiv.mockdacs.rest.entity.License;
import com.refinitiv.mockdacs.rest.entity.Po;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {

  Set<License> findByIdIn(Set<Long> ids);

}
