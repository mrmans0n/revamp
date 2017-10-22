package revamp.base;

import android.support.annotation.NonNull;

/**
 * Basic presenter that can attach and detach {@link ViewComponent} classes.
 */
public interface Presenter<V extends ViewComponent> {
  void takeView(@NonNull V view);

  void dropView();

  void release();
}
