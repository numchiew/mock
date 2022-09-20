package com.refinitiv.mockdacs.rest.service;

import com.refinitiv.mockdacs.exception.AAABadRequestException;
import com.refinitiv.mockdacs.exception.LicenseBadRequestException;
import com.refinitiv.mockdacs.exception.PoBadRequestException;
import com.refinitiv.mockdacs.rest.entity.Account;
import com.refinitiv.mockdacs.rest.entity.License;
import com.refinitiv.mockdacs.rest.entity.Po;
import com.refinitiv.mockdacs.rest.repository.LicenseRepository;
import com.refinitiv.mockdacs.rest.repository.PoRepository;
import com.refinitiv.mockdacs.rest.response.AccountDTO;
import com.refinitiv.mockdacs.rest.response.AccountResponse;
import com.refinitiv.mockdacs.rest.response.LicenseDTO;
import com.refinitiv.mockdacs.rest.response.LicenseResponse;
import com.refinitiv.mockdacs.rest.response.PoDTO;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LicenseService {

  @Autowired
  private LicenseRepository licenseRepository;

  @Autowired
  private PoRepository poRepository;

  public LicenseResponse save(LicenseDTO licenseDTO) {
    if (licenseDTO.getPoList() == null || licenseDTO.getPoList().isEmpty()) {
      License save = licenseRepository.save(LicenseDTO.toEntity(licenseDTO));
      return LicenseResponse.builder()
          .id(save.getId())
          .name(save.getName())
          .poList(null)
          .build();
    }

    Set<Po> pos = poRepository.findByIdIn(licenseDTO.getPoList());

    if (licenseDTO.getPoList().size() != pos.size()) {
      throw new LicenseBadRequestException("some id not exist");
    }

    License save = licenseRepository.save(LicenseDTO.toEntity(licenseDTO));


    return LicenseResponse.builder()
        .id(save.getId())
        .name(save.getName())
        .poList(pos)
        .build();

  }

  public List<LicenseResponse> findAll() {
    List<License> all = licenseRepository.findAll();

    return  all.stream().map(a ->
        LicenseResponse.builder()
            .id(a.getId())
            .name(a.getName())
            .poList(poRepository.findByIdIn(a.getPoIds()))
            .build()
    ).collect(Collectors.toList());
  }

  public void deleteById(Long id) {
    Optional<License> byId = licenseRepository.findById(id);
    if (byId.isPresent()) {
      licenseRepository.deleteById(id);
    } else {
      throw new LicenseBadRequestException("id not found");
    }
  }

}
