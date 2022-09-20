package com.refinitiv.mockdacs.rest.response;

import com.refinitiv.mockdacs.rest.entity.License;
import com.refinitiv.mockdacs.rest.entity.Po;
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
public class LicenseResponse implements Serializable {

  private Long id;
  private String name;
  private Set<Po> poList;
}
