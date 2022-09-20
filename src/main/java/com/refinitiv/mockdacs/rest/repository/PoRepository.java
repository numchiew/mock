package com.refinitiv.mockdacs.rest.repository;

import com.refinitiv.mockdacs.rest.entity.License;
import com.refinitiv.mockdacs.rest.entity.Po;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoRepository extends JpaRepository<Po, Long> {
  Set<Po> findByIdIn(Set<Long> ids);
  Set<Po> findByNameSpaceAndIdIn(String namespace, Set<Long> ids);
  Set<Po> findByNameSpace(String namespace);
}
