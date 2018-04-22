// Copyright (c) 2016-present Revamp

package io.nlopez.revamp.sample.places;

import android.support.annotation.NonNull;

import revamp.mvp.RevampMvpPresenter;

public class PlacesPresenter extends RevampMvpPresenter<PlacesModel, PlacesViewComponent> {
  public PlacesPresenter(@NonNull PlacesModel model) {
    super(model);
  }

  public void loadData() {
    int sectionNumber = model().getSectionNumber();
    view().showSectionNumber(sectionNumber);
  }
}
