package io.nlopez.revamp.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.nlopez.revamp.sample.model.User;
import io.nlopez.revamp.sample.util.DataGenerator;
import io.nlopez.revamp.sample.view.UserView;
import io.nlopez.smartadapters.SmartAdapter;

public class SingleListViewActivity extends Activity {

    @InjectView(R.id.list_view)
    ListView listView;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_listview);

        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        List<User> userList = DataGenerator.generateUsers(100);
        SmartAdapter.items(userList).map(User.class, UserView.class).into(listView);
    }

}
