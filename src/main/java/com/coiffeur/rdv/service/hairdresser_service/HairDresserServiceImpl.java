package com.coiffeur.rdv.service.hairdresser_service;

import com.coiffeur.rdv.dto.hairdresserDTO.HairDresserFetch;
import com.coiffeur.rdv.dto.hairdresserDTO.HairDresserRequest;
import com.coiffeur.rdv.dto.hairdresserDTO.HairDresserResponse;
import com.coiffeur.rdv.entity.HairDresser;
import com.coiffeur.rdv.repository.HairDresserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class HairDresserServiceImpl implements HairDresserService{
    @Autowired
    private HairDresserRepository hairDresserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public HairDresserServiceImpl(HairDresserRepository hairDresserRepository){
        this.hairDresserRepository = hairDresserRepository;
    }

    @Override
    public HairDresserResponse addHairDresser(HairDresserRequest req) {

        HairDresser hairDresser = new HairDresser(
                req.FirstName(),
                req.LastName(),
                req.Email(),
                passwordEncoder.encode(req.Password())
        );

        HairDresser savedHairDresser = hairDresserRepository.save(hairDresser);

        return new HairDresserResponse(
                savedHairDresser.getId(),
                savedHairDresser.getFirstName(),
                savedHairDresser.getLastName(),
                savedHairDresser.getFirstName() + " " + savedHairDresser.getLastName()
        );

    }

    @Override
    public List<HairDresserFetch> fetchHairDresserList() {
        return hairDresserRepository.findAll()
                .stream()
                .map(hairdresser -> new HairDresserFetch(
                        hairdresser.getId(),
                        hairdresser.getFirstName(),
                        hairdresser.getLastName(),
                        hairdresser.getEmail()
                ))
                .toList();
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
