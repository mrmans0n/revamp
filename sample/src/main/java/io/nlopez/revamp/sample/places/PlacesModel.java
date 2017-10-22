// Copyright (c) 2016-present Revamp

package io.nlopez.revamp.sample.places;

import revamp.base.RevampModel;

public class PlacesModel extends RevampModel {
  private final int mSectionNumber;

  public PlacesModel(int sectionNumber) {
    mSectionNumber = sectionNumber;
  }

  public int getSectionNumber() {
    return mSectionNumber;
  }
}
