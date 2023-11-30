package com.tripsplit.controller;

import com.tripsplit.entity.Expense;
import com.tripsplit.entity.FinalSplit;
import com.tripsplit.entity.Group;
import com.tripsplit.entity.User;
import com.tripsplit.exception.GroupUserException;
import com.tripsplit.model.GroupModel;
import com.tripsplit.service.GroupService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin(origins = "*")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private MeterRegistry meterRegistry;

    @PostMapping("/create")
    public Group createGroup(@RequestBody GroupModel groupModel){

        try {
            return groupService.createGroup(groupModel);
        } catch (GroupUserException e) {
            meterRegistry.counter("CreateGroupErrorCounter","error_message", e.getMessage()).increment();
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/adduser/{id}")
    public List<User> addUser(@PathVariable("id") Long GrpId, @RequestBody List<User> user){
        return groupService.addUser(GrpId, user);
    }


    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable("id") Long GroupId ){
        return groupService.getGroupById(GroupId);
    }

    @GetMapping("/users/{id}")
    public List<User> getGroupUsers(@PathVariable("id") Long GroupId){
        return groupService.getGroupUsers(GroupId);
    }

    @GetMapping("/expenses/{id}")
    public List<Expense> getGroupExpenses(@PathVariable("id") Long groupId){
        return groupService.getGroupExpenses(groupId);
    }

    @GetMapping("/finalsplit/{id}")
    public List<FinalSplit> getGroupFinal(@PathVariable("id") Long groupId){
        return groupService.getGroupFinal(groupId);
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable("id") Long groupId){
        return groupService.deleteGroup(groupId);
    }
}
