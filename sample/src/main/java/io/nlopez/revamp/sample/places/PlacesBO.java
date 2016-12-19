// Copyright (c) 2016-present Revamp

package io.nlopez.revamp.sample.places;

import revamp.base.BusinessObject;

public class PlacesBO implements BusinessObject {
  private final int mSectionNumber;

  public PlacesBO(int sectionNumber) {
    mSectionNumber = sectionNumber;
  }

  public int getSectionNumber() {
    return mSectionNumber;
  }
}
