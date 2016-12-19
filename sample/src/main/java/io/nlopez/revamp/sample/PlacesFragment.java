// Copyright (c) 2016-present Revamp

package io.nlopez.revamp.sample;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlacesFragment extends Fragment {
  private static final String ARG_SECTION_NUMBER = "section_number";

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
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_pager, container, false);
    TextView textView = (TextView) rootView.findViewById(R.id.section_label);
    textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
    return rootView;
  }
}
