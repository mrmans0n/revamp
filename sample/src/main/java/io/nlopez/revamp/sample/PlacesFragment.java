// Copyright (c) 2016-present Revamp

package io.nlopez.revamp.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.nlopez.revamp.sample.places.PlacesBO;
import io.nlopez.revamp.sample.places.PlacesPresenter;
import io.nlopez.revamp.sample.places.PlacesViewComponent;
import revamp.android.PresenterFragment;

public class PlacesFragment extends PresenterFragment<PlacesPresenter, PlacesViewComponent> implements PlacesViewComponent {
  private static final String ARG_SECTION_NUMBER = "section_number";

  @BindView(R.id.section_label)
  TextView mTextView;

  public PlacesFragment() {
  }

  public static PlacesFragment newInstance(int sectionNumber) {
    PlacesFragment fragment = new PlacesFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setRetainInstance(false);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_pager, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    presenter().loadData();
  }

  @Override
  public void showSectionNumber(int sectionNumber) {
    mTextView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
  }

  @Override
  public PlacesPresenter buildPresenter() {
    return new PlacesPresenter(new PlacesBO(getArguments().getInt(ARG_SECTION_NUMBER)));
  }
}
