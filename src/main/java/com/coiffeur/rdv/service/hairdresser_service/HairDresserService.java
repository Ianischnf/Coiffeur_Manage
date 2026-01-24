package com.coiffeur.rdv.service.hairdresser_service;

import com.coiffeur.rdv.dto.hairdresserDTO.HairDresserFetch;
import com.coiffeur.rdv.dto.hairdresserDTO.HairDresserRequest;
import com.coiffeur.rdv.dto.hairdresserDTO.HairDresserResponse;
import com.coiffeur.rdv.entity.HairDresser;

import java.util.List;

public interface HairDresserService {

    HairDresserResponse addHairDresser(HairDresserRequest req);
    List<HairDresserFetch> fetchHairDresserList();
    HairDresser updateHairDresser(HairDresser hairDresser, Long HairDresserId);
    void deleteHairDresserById(Long HairDresserId);
}
