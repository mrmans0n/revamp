package io.nlopez.revamp.sample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import drebin.Drebin;
import io.nlopez.revamp.sample.binders.ClickableElementEnvironment;
import io.nlopez.revamp.sample.binders.UserBinder;
import io.nlopez.revamp.sample.model.User;
import io.nlopez.revamp.sample.users.UsersBO;
import io.nlopez.revamp.sample.users.UsersPresenter;
import io.nlopez.revamp.sample.users.UsersViewComponent;
import revamp.android.PresenterActivity;

/**
 * It's recommended to use the Activity / Fragment as a ViewComponent. We can handle all UI refresh
 * and data visualization in here and we don't have to deal with sending contexts and widgets back and forth.
 * <p/>
 * It is forced by default if you use the already set up PresenterActivity or similar.
 * If you want to implement your own though, you can do it by calling directly the methods of the delegates.
 * Just do it as it is in PresenterActivity for example.
 */
public class MainActivity extends PresenterActivity<UsersPresenter, UsersViewComponent> implements UsersViewComponent {
  @BindView(R.id.list) RecyclerView mRecyclerView;

  private final UserBinder mUserBinder = new UserBinder();
  private final ClickableElementEnvironment<User> mEnvironment =
          new ClickableElementEnvironment<>(new ClickableElementEnvironment.Listener<User>() {
            @Override
            public void onClick(View view, User user) {
              presenter().userSelected(user);
            }
          });

  @Override
  public void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    setContentView(R.layout.activity_recyclerview);

    ButterKnife.bind(this);
    initView();

    // We give control to the mPresenter and load the data
    presenter().loadData();
  }

  private void initView() {
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
  }

  @Override
  public UsersPresenter buildPresenter() {
    // This is cached by one of the inherited classes so you have only to think about creating
    // a new one, and the framework will deal with reuse.
    return new UsersPresenter(new UsersBO());
  }

  @Override
  public void fillListWithUsers(List<User> users) {
    Drebin.with(this)
            .items(users)
            .binder(User.class, mUserBinder)
            .environment(mEnvironment)
            .into(mRecyclerView);
  }

  @Override
  public void highlightUserSelection(User user) {
    startActivity(PlacesActivity.createIntent(this));
  }
}
