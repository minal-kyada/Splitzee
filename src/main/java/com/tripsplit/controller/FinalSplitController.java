package com.tripsplit.controller;

import com.tripsplit.model.FinalSplitModel;
import com.tripsplit.service.FinalSplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/FinalSplit")
@CrossOrigin(origins = "*")
public class FinalSplitController {
    @Autowired
    private FinalSplitService finalSplitService;

    @GetMapping("/{id}")
    public List<FinalSplitModel> getFinalSplit(@PathVariable("id") Long groupID){
        return finalSplitService.getFinalSplit(groupID);
    }


}
