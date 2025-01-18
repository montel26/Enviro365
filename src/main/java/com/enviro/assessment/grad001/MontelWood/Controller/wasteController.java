package com.enviro.assessment.grad001.MontelWood.Controller;

import com.enviro.assessment.grad001.MontelWood.Service.getWasteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class wasteController {

    @Autowired
    //Inject service class
    private getWasteInfoService getwasteInfo;

    //make them return response entity so we can see status code
    @GetMapping
    public ResponseEntity<String> getWaste(){

        return getwasteInfo.execute();
    }

    @PostMapping
    public ResponseEntity<String> postWaste(){

        return ResponseEntity.status(HttpStatus.CREATED).body("stop posing and do something");
    }

    @PutMapping
    public ResponseEntity<String> putWaste(){
        return ResponseEntity.status(HttpStatus.OK).body("Im proud of you");
    }


    @DeleteMapping
    public ResponseEntity<String> deleteWaste(){

        //body added just for the sake of it.
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("niceeeee");
    }
}
