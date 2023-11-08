package com.tripsplit.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="finalsplit")
public class FinalSplit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long finalPayTo;
    private Long finalPayBy;
    private Float finalAmt;

    @ManyToOne
    @JoinColumn(name = "split_list")
    private Group finalSplitGrp;

}
