package io.nlopez.revamp.sample.binders;

import android.view.View;

import drebin.core.BinderEnvironment;

public class ClickableElementEnvironment<M> implements BinderEnvironment {

  private final Listener mListener;

  public ClickableElementEnvironment(Listener<M> listener) {
    mListener = listener;
  }

  public void performClick(View view, M model) {
    mListener.onClick(view, model);
  }

  public interface Listener<MODEL> {
    void onClick(View view, MODEL model);
  }
}
