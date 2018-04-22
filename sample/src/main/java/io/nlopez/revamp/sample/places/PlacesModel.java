// Copyright (c) 2016-present Revamp

package io.nlopez.revamp.sample.places;

import revamp.mvp.RevampMvpModel;

public class PlacesModel extends RevampMvpModel {
  private final int mSectionNumber;

  public PlacesModel(int sectionNumber) {
    mSectionNumber = sectionNumber;
  }

  public int getSectionNumber() {
    return mSectionNumber;
  }
}
