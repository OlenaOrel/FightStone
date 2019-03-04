package service;

import collections.WaitUsers;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaitService {

    private final WaitUsers waitUsers;

    @Autowired
    public WaitService(WaitUsers waitUsers) {
        this.waitUsers = waitUsers;
    }

    public boolean isWaitListEmpty() {
        return waitUsers.getWaitList().isEmpty();
    }

    public User getUserForBattle() {
        return waitUsers.getWaitList()
                .remove(waitUsers.getWaitList().keySet().iterator().next());
    }

    public boolean waitUsersListContainsUserByLogin(String login) {
        return waitUsers.getWaitList().containsKey(login);
    }
}
