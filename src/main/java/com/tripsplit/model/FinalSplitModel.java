package com.tripsplit.model;

import com.tripsplit.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinalSplitModel {
    private Long finalPayTo;
    private Long finalPayBy;
    private Float finalAmt;
    private Group finalSplitGrp;
}
