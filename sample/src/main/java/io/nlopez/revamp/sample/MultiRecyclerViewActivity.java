package io.nlopez.revamp.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.nlopez.revamp.SmartAdapter;
import io.nlopez.revamp.sample.model.Place;
import io.nlopez.revamp.sample.model.User;
import io.nlopez.revamp.sample.util.DataGenerator;
import io.nlopez.revamp.sample.view.PlaceView;
import io.nlopez.revamp.sample.view.UserView;

public class MultiRecyclerViewActivity extends Activity {

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_recyclerview);

        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        List mixedList = DataGenerator.generateMix(100);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SmartAdapter.items(mixedList)
                .map(User.class, UserView.class)
                .map(Place.class, PlaceView.class)
                .into(recyclerView);
    }

}
