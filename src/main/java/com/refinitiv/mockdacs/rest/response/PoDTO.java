package com.refinitiv.mockdacs.rest.response;

import com.refinitiv.mockdacs.rest.entity.Po;
import java.io.Serializable;
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
public class PoDTO implements Serializable {

  private Long id;
  private String name;
  private String nameSpace;

  public static PoDTO toDto(Po po) {
    return PoDTO.builder()
        .id(po.getId())
        .name(po.getName())
        .nameSpace(po.getNameSpace())
        .build();
  }

  public static Po toEntity(PoDTO poDTO) {
    Long id = poDTO.getId() == null ? null : poDTO.getId();
    return Po.builder()
        .id(id)
        .name(poDTO.getName())
        .nameSpace(poDTO.getNameSpace())
        .build();
  }
}
