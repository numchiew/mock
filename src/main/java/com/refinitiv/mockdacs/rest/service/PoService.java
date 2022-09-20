package com.refinitiv.mockdacs.rest.service;

import com.refinitiv.mockdacs.exception.PoBadRequestException;
import com.refinitiv.mockdacs.rest.entity.Po;
import com.refinitiv.mockdacs.rest.repository.PoRepository;
import com.refinitiv.mockdacs.rest.response.PoDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PoService {

  @Autowired
  private PoRepository poRepository;

  public PoDTO save(PoDTO poDTO) {
    Po save = poRepository.save(PoDTO.toEntity(poDTO));

    return PoDTO.toDto(save);
  }

  public List<PoDTO> findAll() {
    List<Po> all = poRepository.findAll();

    return all.stream().map(PoDTO::toDto).collect(Collectors.toList());
  }

  public void deleteById(Long id) {
    Optional<Po> byId = poRepository.findById(id);
    if (byId.isPresent()) {
      poRepository.deleteById(id);
    } else {
      throw new PoBadRequestException("id not found");
    }
  }

}
