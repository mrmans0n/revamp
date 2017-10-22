package io.nlopez.revamp.sample.users;

import android.support.annotation.NonNull;

import java.util.List;

import io.nlopez.revamp.sample.model.User;
import revamp.base.RevampPresenter;

/**
 * Created by mrm on 4/6/15.
 */
public class UsersPresenter extends RevampPresenter<UsersModel, UsersViewComponent> {
    public UsersPresenter(@NonNull UsersModel model) {
        super(model);
    }

    public void loadData() {
        List<User> users = model().getUsers();
        view().fillListWithUsers(users);
    }

    public void userSelected(User user) {
        // This is responsibility for the BO as we deal with persistence / network / whatever
        // so this kind of code doesn't belong to the mPresenter
        model().storeUser(user);

        // We deal with what to do after storing -- now it's showing some feedback to the user.
        view().highlightUserSelection(user);
    }

}
