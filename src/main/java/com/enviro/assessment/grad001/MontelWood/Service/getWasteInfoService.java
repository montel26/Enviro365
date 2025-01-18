package com.enviro.assessment.grad001.MontelWood.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class getWasteInfoService {


    public ResponseEntity<String> execute(){
        return ResponseEntity.status(HttpStatus.CREATED).body("You disgust me");
    }
}
