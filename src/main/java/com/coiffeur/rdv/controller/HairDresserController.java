package com.coiffeur.rdv.controller;

import com.coiffeur.rdv.dto.HairDresserRequest;
import com.coiffeur.rdv.entity.HairDresser;
import com.coiffeur.rdv.repository.AppointmentRepository;
import com.coiffeur.rdv.service.HairDresserService;
import com.coiffeur.rdv.service.HairDresserServiceImpl;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/hairdresser")
public class HairDresserController {

    @Autowired
    private HairDresserService hairDresserService;
    private AppointmentRepository appointmentRepository;

    public HairDresserController(HairDresserServiceImpl hairDresserService) {
        this.hairDresserService = hairDresserService;
    }

    @PostMapping
    public HairDresser addHairdresser(@Valid @RequestBody HairDresserRequest req){
        return this.hairDresserService.addHairDresser(req);
    }

    @GetMapping
    public List<HairDresser> fetchHairDresserList(){
        return this.hairDresserService.fetchHairDresserList();
    }

    @PutMapping("/{id}")
    public HairDresser updateHairDresser(@RequestBody HairDresser hairDresser,
                                        @PathVariable("id") Long HairDresserId){

        return this.hairDresserService.updateHairDresser(hairDresser, HairDresserId);
    }

    @DeleteMapping("/{id}")
    public void deleteHairDresserById(@PathVariable("id") Long HairDresserId){

        hairDresserService.deleteHairDresserById(HairDresserId);
    }

}
