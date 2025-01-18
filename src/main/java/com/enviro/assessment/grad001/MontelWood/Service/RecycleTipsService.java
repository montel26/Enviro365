package com.enviro.assessment.grad001.MontelWood.Service;

import com.enviro.assessment.grad001.MontelWood.Model.DisposalGuidelineEntity;
import com.enviro.assessment.grad001.MontelWood.Model.RecyclingTipEntity;
import com.enviro.assessment.grad001.MontelWood.Repository.RecyclingTipsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecycleTipsService {

    //Simple dependency injection
    @Autowired
    RecyclingTipsRepository recyclingTips;

    public List<RecyclingTipEntity> getRecyclingTips(){
        return  recyclingTips.findAll();
    }

    public Optional<RecyclingTipEntity> getRecyclingTipsById(Long Id){
        return recyclingTips.findById(Id);
    }

    public RecyclingTipEntity saveRecyclingTips(RecyclingTipEntity recyclingTip){
        return recyclingTips.save(recyclingTip);
    }

    public void deleteRecyclingTips(Long Id){
        if (!recyclingTips.existsById(Id)) {
            throw new RuntimeException("Recycling tip not found with ID: " + Id);
        }recyclingTips.deleteById(Id);
    }
}
