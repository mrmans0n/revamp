package revamp.base;

/**
 * Marks the directors for the orchestra
 */
public interface Presenter<V extends ViewComponent> {
  void takeView(V view);

  void dropView();
}
