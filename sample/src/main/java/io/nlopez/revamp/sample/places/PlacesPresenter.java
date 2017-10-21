// Copyright (c) 2016-present Revamp

package io.nlopez.revamp.sample.places;

import android.support.annotation.NonNull;

import revamp.base.RevampPresenter;

public class PlacesPresenter extends RevampPresenter<PlacesBO, PlacesViewComponent> {
  public PlacesPresenter(@NonNull PlacesBO businessObject) {
    super(businessObject);
  }

  public void loadData() {
    int sectionNumber = bo().getSectionNumber();
    view().showSectionNumber(sectionNumber);
  }
}
