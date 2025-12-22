package com.coiffeur.rdv.service;

import com.coiffeur.rdv.dto.HairDresserRequest;
import com.coiffeur.rdv.entity.HairDresser;
import com.coiffeur.rdv.repository.HairDresserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HairDresserServiceImpl implements HairDresserService{
    @Autowired
    private HairDresserRepository hairDresserRepository;

    public HairDresserServiceImpl(HairDresserRepository hairDresserRepository){
        this.hairDresserRepository = hairDresserRepository;
    }

    @Override
    public HairDresser addHairDresser(HairDresserRequest req) {

        HairDresser hairDresser = new HairDresser(
                req.FirstName(),
                req.LastName()
        );

        return hairDresserRepository.save(hairDresser);
    }

    @Override
    public List<HairDresser> fetchHairDresserList() {
        return (List<HairDresser>)
                hairDresserRepository.findAll();
    }

    @Override
    public HairDresser updateHairDresser(HairDresser hairDresser, Long HairDresserId) {
        HairDresser hairDresserDB = hairDresserRepository.findById(HairDresserId).get();

        if (Objects.nonNull(hairDresser.getFirstName())
                && !"".equalsIgnoreCase(hairDresser.getFirstName())) {

            hairDresserDB.setFirstName(hairDresser.getFirstName());
        }

        if (Objects.nonNull(hairDresser.getLastName())
                && !"".equalsIgnoreCase(hairDresser.getLastName())) {

            hairDresserDB.setLastName(hairDresser.getLastName());
        }

        return hairDresserRepository.save(hairDresserDB);
    }

    @Override
    public void deleteHairDresserById(Long HairDresserId) {
        hairDresserRepository.deleteById(HairDresserId);
    }
}
