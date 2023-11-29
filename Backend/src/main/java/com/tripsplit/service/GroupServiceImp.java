package com.tripsplit.service;

import com.tripsplit.entity.Expense;
import com.tripsplit.entity.FinalSplit;
import com.tripsplit.entity.Group;
import com.tripsplit.entity.User;
import com.tripsplit.exception.GroupUserException;
import com.tripsplit.model.GroupModel;
import com.tripsplit.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GroupServiceImp implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Override
    public Group createGroup(GroupModel groupModel) throws GroupUserException {
        Group group = new Group();
        String grpName = groupModel.getGrpName();

        String stringRegex = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$";

        Matcher grpNameMatch = Pattern.compile(stringRegex).matcher(grpName);
        if (grpName.isEmpty() || grpName.isBlank() || !grpNameMatch.matches()) {
            throw new GroupUserException("Invalid Group Name");
        }
        group.setGroupName(grpName);

        String grpType = groupModel.getGrpType();
        Matcher grpTypeMatch = Pattern.compile(stringRegex).matcher(grpType);
        if (grpType.isEmpty() || grpType.isBlank() || !grpTypeMatch.matches()) {
            throw new GroupUserException("Invalid Group Type");
        }
        group.setGroupType(grpType);

        group.setGroupBudget(groupModel.getGrpBudget());
        group.setGroupUsers(groupModel.getGrpUser());
        group.setExpenses(null);
        group.setFinalSplits(null);
        groupRepository.save(group);
        return group;
    }

    @Override
    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId).get();
    }

    @Override
    public List<User> addUser(Long grpId, List<User> user) {
        Optional<Group> grp1 = groupRepository.findById(grpId);
        if(grp1.isPresent()){
            grp1.get().getGroupUsers().addAll(user);
            groupRepository.save(grp1.get());
        }
        return grp1.get().getGroupUsers();
    }

    @Override
    public List<User> getGroupUsers(Long groupId) {
        return groupRepository.findById(groupId).get().getGroupUsers();
    }

    @Override
    public List<Expense> getGroupExpenses(Long groupId) {
        return groupRepository.findById(groupId).get().getExpenses();
    }

    @Override
    public List<FinalSplit> getGroupFinal(Long groupId) {
        return groupRepository.findById(groupId).get().getFinalSplits();
    }

    @Override
    public String deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
        return "Group Deleted";
    }
}
