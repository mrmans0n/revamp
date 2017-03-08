package io.nlopez.revamp.sample.binders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import drebin.core.Binder;
import drebin.core.BinderEnvironment;
import drebin.core.ViewFactory;
import drebin.core.ViewHost;
import io.nlopez.revamp.sample.R;
import io.nlopez.revamp.sample.model.User;

public class UserBinder implements Binder<LinearLayout, UserBinder.UserViewHost, User, ClickableElementEnvironment<User>> {

  @Override
  public ViewFactory<LinearLayout> getViewFactory() {
    return ViewFactory.INFLATE.fromLayout(R.layout.view_user);
  }

  @Override
  public UserViewHost createViewHost(LinearLayout view) {
    return new UserViewHost(view);
  }

  @Override
  public void bind(final User user, UserViewHost host, final ClickableElementEnvironment<User> binderEnvironment) {
    Picasso.with(host.rootView.getContext()).load(user.getAvatar()).into(host.userImage);
    host.userText.setText(user.getFirstName() + " " + user.getLastName() + "\n" + user.getRole());

    host.rootView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        binderEnvironment.performClick(v, user);
      }
    });
  }

  @Override
  public void unbind(UserViewHost host, ClickableElementEnvironment<User> binderEnvironment) {
    host.rootView.setOnClickListener(null);
  }

  static class UserViewHost extends ViewHost<LinearLayout> {
    @InjectView(R.id.user_image)
    ImageView userImage;

    @InjectView(R.id.user_text)
    TextView userText;

    public UserViewHost(LinearLayout view) {
      super(view);
      ButterKnife.inject(this, view);
    }
  }
}
