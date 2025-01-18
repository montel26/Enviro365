package com.enviro.assessment.grad001.MontelWood.Service;

import com.enviro.assessment.grad001.MontelWood.Model.DisposalGuidelineEntity;
import com.enviro.assessment.grad001.MontelWood.Repository.DisposalGuidelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisposalService {

    //Simple dependency injection
    @Autowired
    DisposalGuidelineRepository disposalGuideline;

    public List<DisposalGuidelineEntity> getGuideLines(){
        return  disposalGuideline.findAll();
    }

    public Optional<DisposalGuidelineEntity> getGuideLinesById(Long Id){
        return disposalGuideline.findById(Id);
    }

    public DisposalGuidelineEntity saveGuideLine(DisposalGuidelineEntity guideline){
        return disposalGuideline.save(guideline);
    }

    public void deleteDisposalGuideline(Long Id){
        if (!disposalGuideline.existsById(Id)) {
            throw new RuntimeException("Recycling tip not found with ID: " + Id);
        }disposalGuideline.deleteById(Id);
    }
}
