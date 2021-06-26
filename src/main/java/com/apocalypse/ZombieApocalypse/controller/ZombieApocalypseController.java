package com.apocalypse.ZombieApocalypse.controller;

import com.apocalypse.ZombieApocalypse.exception.InvalidRequestException;
import com.apocalypse.ZombieApocalypse.model.ZombieApocalypseRequest;
import com.apocalypse.ZombieApocalypse.model.ZombieApocalypseResponse;
import com.apocalypse.ZombieApocalypse.service.ZombieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ZombieApocalypseController {

    @Autowired
    ZombieService zombieService;

    @RequestMapping(value="/apocalypse", headers="Accept=application/json", method= RequestMethod.POST)
    public ResponseEntity<List<ZombieApocalypseResponse>> triggerZombieApocalypses(@RequestBody List<ZombieApocalypseRequest> requests) throws InvalidRequestException {
        try {
            List<ZombieApocalypseResponse> responses= zombieService
                    .processZombieApocalypses(requests);
            return new ResponseEntity<>(responses, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
