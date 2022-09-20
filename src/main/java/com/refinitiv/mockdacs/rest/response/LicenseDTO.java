package com.refinitiv.mockdacs.rest.response;

import com.refinitiv.mockdacs.rest.entity.Account;
import com.refinitiv.mockdacs.rest.entity.License;
import com.refinitiv.mockdacs.rest.entity.Po;
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
public class LicenseDTO implements Serializable {

  private Long id;
  private String name;
  private Set<Long> poList;

  public static LicenseDTO toDto(License license) {
    return LicenseDTO.builder()
        .id(license.getId())
        .name(license.getName())
        .poList(license.getPoIds())
        .build();
  }

  public static License toEntity(LicenseDTO licenseDTO) {
    Long id = licenseDTO.getId() == null ? null : licenseDTO.getId();
    return License.builder()
        .id(id)
        .name(licenseDTO.getName())
        .poIds(licenseDTO.getPoList())
        .build();
  }
}
