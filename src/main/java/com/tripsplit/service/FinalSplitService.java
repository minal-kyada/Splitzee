package com.tripsplit.service;

import com.tripsplit.model.FinalSplitModel;

import java.util.List;

public interface FinalSplitService {
    List<FinalSplitModel> getFinalSplit(Long groupId);
}
