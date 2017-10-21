package io.nlopez.revamp.sample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlacesActivity extends Activity {

  private SectionsPagerAdapter mSectionsPagerAdapter;

  @BindView(R.id.container)
  ViewPager mViewPager;

  public static Intent createIntent(Context context) {
    return new Intent(context, PlacesActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pager);
    getActionBar().setDisplayHomeAsUpEnabled(true);

    ButterKnife.bind(this);
    mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
    mViewPager.setAdapter(mSectionsPagerAdapter);
  }

  public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      // getItem is called to instantiate the fragment for the given page.
      // Return a PlaceholderFragment (defined as a static inner class below).
      return PlacesFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
      // Show 3 total pages.
      return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      switch (position) {
        case 0:
          return "SECTION 1";
        case 1:
          return "SECTION 2";
        case 2:
          return "SECTION 3";
      }
      return null;
    }
  }
}
