package io.nlopez.revamp.sample.users;

import java.util.List;

import io.nlopez.revamp.sample.model.User;
import revamp.base.ViewComponent;

/**
 * Created by mrm on 4/6/15.
 */
public interface UsersViewComponent extends ViewComponent {
    void fillListWithUsers(List<User> users);
    void highlightUserSelection(User user);
}
