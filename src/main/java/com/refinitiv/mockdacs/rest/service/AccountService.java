package com.refinitiv.mockdacs.rest.service;

import com.refinitiv.mockdacs.exception.AAABadRequestException;
import com.refinitiv.mockdacs.exception.LicenseBadRequestException;
import com.refinitiv.mockdacs.rest.entity.Account;
import com.refinitiv.mockdacs.rest.entity.License;
import com.refinitiv.mockdacs.rest.entity.Po;
import com.refinitiv.mockdacs.rest.repository.AccountRepository;
import com.refinitiv.mockdacs.rest.repository.LicenseRepository;
import com.refinitiv.mockdacs.rest.repository.PoRepository;
import com.refinitiv.mockdacs.rest.response.AccountDTO;
import com.refinitiv.mockdacs.rest.response.AccountDetailResponse;
import com.refinitiv.mockdacs.rest.response.AccountResponse;
import com.refinitiv.mockdacs.rest.response.LicenseDTO;
import com.refinitiv.mockdacs.rest.response.LicenseResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private LicenseRepository licenseRepository;

  @Autowired
  private PoRepository poRepository;

  public String getPoForUser(String namespace, String userName) {
    Optional<Account> account = accountRepository.findByName(userName);
    if (account.isEmpty()) {
      return  "DENIED:" + userName + ", unknown to system";
    }

    Account acc = account.get();
    Set<License> licenses = licenseRepository.findByIdIn(acc.getLicenseIds());
    Set<Long> poIds = new HashSet<>();
    licenses.forEach(l -> {
      poIds.addAll(l.getPoIds());
    });

    Set<Po> pos = poRepository.findByNameSpaceAndIdIn(namespace, poIds);

//    List<Long> poEntityId = new ArrayList<>();
//    licenses.stream().filter(l -> l.get)
    List<String> collect = pos.stream().map(p -> p.getId().toString()).collect(Collectors.toList());
    return "SUCCESS:\n" + String.join("\n", collect);
  }

  public String getAllPOForNameSpace(String namespace) {
    Set<Po> pos = poRepository.findByNameSpace(namespace);
    if (pos.isEmpty()) {
      return "DENIED:LOOKUP_FAILURE";
    }

    List<String> poEntity = new ArrayList<>();
    pos.forEach(po -> {
      String toString = "\'" + po.getId() + "=" + po.getName() + "\'";
      poEntity.add(toString);
    });
    return "SUCCESS:\n" + String.join("\n", poEntity);
  }

  public AccountResponse save(AccountDTO accountDTO) {
    if (accountDTO.getLicenseList() == null || accountDTO.getLicenseList().isEmpty()) {
      Account save = accountRepository.save(AccountDTO.toEntity(accountDTO));
      return AccountResponse.builder()
          .id(save.getId())
          .name(save.getName())
          .licenseList(null)
          .build();
    }

    Set<License> licenses = licenseRepository.findByIdIn(accountDTO.getLicenseList());

    if (accountDTO.getLicenseList().size() != licenses.size()) {
      throw new AAABadRequestException("some id not exist");
    }

    Account save = accountRepository.save(AccountDTO.toEntity(accountDTO));


    return AccountResponse.builder()
        .id(save.getId())
        .name(save.getName())
        .licenseList(licenses)
        .build();

  }

  public List<AccountResponse> findAll() {
    List<Account> all = accountRepository.findAll();

    return  all.stream().map(a ->
       AccountResponse.builder()
          .id(a.getId())
          .name(a.getName())
          .licenseList(licenseRepository.findByIdIn(a.getLicenseIds()))
          .build()
    ).collect(Collectors.toList());
  }

  public void deleteById(Long id) {
    Optional<Account> byId = accountRepository.findById(id);
    if (byId.isPresent()) {
      accountRepository.deleteById(id);
    } else {
      throw new LicenseBadRequestException("id not found");
    }
  }

  public AccountDetailResponse findAccountByName(String name) {
    Optional<Account> acc = accountRepository.findByName(name);

    if (acc.isEmpty()) {
      throw new AAABadRequestException("not found account with name " + name);
    }

    Account account = acc.get();

    Set<LicenseResponse> licenses = account.getLicenseIds().stream().map(license -> {
      Optional<License> licenseById = licenseRepository.findById(license);
      License license1 = licenseById.get();
      Set<Po> pos = poRepository.findByIdIn(license1.getPoIds());

      return LicenseResponse.builder()
          .id(license1.getId())
          .name(license1.getName())
          .poList(pos)
          .build();
    }).collect(Collectors.toSet());

    return AccountDetailResponse.builder()
        .id(account.getId())
        .name(account.getName())
        .licenseList(licenses)
        .build();

  }

}
