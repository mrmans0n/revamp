package io.nlopez.revamp.sample.users;

import android.util.Log;

import java.util.List;

import io.nlopez.revamp.sample.model.User;
import io.nlopez.revamp.sample.util.DataGenerator;
import revamp.base.BusinessObject;

/**
 * Created by mrm on 4/6/15.
 */
public class UsersBO implements BusinessObject {

    public UsersBO() {
    }

    public List<User> getUsers() {
        return DataGenerator.generateUsers(100);
    }

    public void storeUser(User user) {
        // TODO we would store this data in prefs or somewhere we like
        Log.d(UsersBO.class.getCanonicalName(), "Stored user " + user);
    }
}
