package com.tripsplit.service;


import com.tripsplit.entity.Expense;
import com.tripsplit.entity.FinalSplit;
import com.tripsplit.entity.Group;
import com.tripsplit.entity.User;
import com.tripsplit.exception.GroupUserException;
import com.tripsplit.model.GroupModel;

import java.util.List;

public interface GroupService {
    Group createGroup(GroupModel groupModel) throws GroupUserException;

    Group getGroupById(Long groupId);

    List<User> addUser(Long grpId, List<User> user);

    List<User> getGroupUsers(Long groupId);

    List<Expense> getGroupExpenses(Long groupId);

    List<FinalSplit> getGroupFinal(Long groupId);

    String deleteGroup(Long groupId);
}
