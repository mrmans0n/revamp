package io.nlopez.revamp.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.nlopez.revamp.sample.model.User;
import io.nlopez.revamp.sample.users.UsersBO;
import io.nlopez.revamp.sample.users.UsersPresenter;
import io.nlopez.revamp.sample.users.UsersViewComponent;
import io.nlopez.revamp.sample.util.Interactions;
import io.nlopez.revamp.sample.view.UserView;
import io.nlopez.smartadapters.SmartAdapter;
import io.nlopez.smartadapters.utils.ViewEventListener;
import revamp.android.PresenterActivity;

/**
 * It's recommended to use the Activity / Fragment as a ViewComponent. We can handle all UI refresh
 * and data visualization in here and we don't have to deal with sending contexts and widgets back and forth.
 */
public class MainActivity extends PresenterActivity<UsersPresenter, UsersViewComponent> implements UsersViewComponent, ViewEventListener<User> {
    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_recyclerview);

        ButterKnife.inject(this);
        initView();

        // We give control to the presenter and load the data
        presenter().loadData();
    }

    private void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public UsersPresenter buildPresenter() {
        // This is cached by one of the inherited classes so you have only to think about creating
        // a new one, and the framework will deal with reuse.
        return new UsersPresenter(new UsersBO());
    }

    @Override
    public void fillListWithUsers(List<User> users) {
        SmartAdapter.items(users).map(User.class, UserView.class).listener(this).into(recyclerView);
    }

    @Override
    public void onViewEvent(int actionId, User user, int position, View view) {
        if (actionId == Interactions.USER_CLICKED) {
            presenter().userSelected(user);
        }
    }

    @Override
    public void highlightUserSelection(User user) {
        Toast.makeText(this,
                "User = " + user.getFirstName() + " " + user.getLastName() + " | " + user.getRole(),
                Toast.LENGTH_LONG).show();
    }
}
