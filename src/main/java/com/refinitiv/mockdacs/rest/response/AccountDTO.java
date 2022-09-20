package com.refinitiv.mockdacs.rest.response;

import com.refinitiv.mockdacs.rest.entity.Account;
import com.refinitiv.mockdacs.rest.entity.License;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO implements Serializable {

  private Long id;
  private String name;
  private Set<Long> licenseList;

  public static AccountDTO toDto(Account account) {
    return AccountDTO.builder()
        .id(account.getId())
        .name(account.getName())
        .licenseList(account.getLicenseIds())
        .build();
  }

  public static Account toEntity(AccountDTO accountDTO) {
    Long id = accountDTO.getId() == null ? null : accountDTO.getId();
    return Account.builder()
        .id(id)
        .name(accountDTO.getName())
        .licenseIds(accountDTO.getLicenseList())
        .build();
  }
}
