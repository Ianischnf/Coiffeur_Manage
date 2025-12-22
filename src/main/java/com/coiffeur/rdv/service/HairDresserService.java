package com.coiffeur.rdv.service;

import com.coiffeur.rdv.dto.HairDresserRequest;
import com.coiffeur.rdv.entity.HairDresser;

import java.util.List;

public interface HairDresserService {

    HairDresser addHairDresser(HairDresserRequest req);
    List<HairDresser> fetchHairDresserList();
    HairDresser updateHairDresser(HairDresser hairDresser, Long HairDresserId);
    void deleteHairDresserById(Long HairDresserId);
}
