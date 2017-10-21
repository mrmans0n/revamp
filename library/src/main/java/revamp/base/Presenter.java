package revamp.base;

/**
 * Basic presenter that can attach and detach {@link ViewComponent} classes.
 */
public interface Presenter<V extends ViewComponent> {
  void takeView(V view);

  void dropView();

  void release();
}
