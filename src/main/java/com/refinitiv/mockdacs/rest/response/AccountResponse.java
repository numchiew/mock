package com.refinitiv.mockdacs.rest.response;

import com.refinitiv.mockdacs.rest.entity.Account;
import com.refinitiv.mockdacs.rest.entity.License;
import java.io.Serializable;
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
public class AccountResponse implements Serializable {

  private Long id;
  private String name;
  private Set<License> licenseList;
}