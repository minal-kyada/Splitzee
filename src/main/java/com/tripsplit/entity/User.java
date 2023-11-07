package com.tripsplit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.MERGE,mappedBy = "groupUsers")
    @JsonIgnore
    private List<Group> userGroups;

    private String userFirstName;
    private String userLastName;
    private String userName;

    //    @Column(length = 60)
    private String userPassword;

    private Boolean enabled = false;
    private String role;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "usrSplitBtw")
    private List<Expense> expenses;


}