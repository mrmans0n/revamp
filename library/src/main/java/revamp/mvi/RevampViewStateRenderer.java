package revamp.mvi;

import android.support.annotation.NonNull;
import revamp.base.ViewComponent;

/**
 * Defines the MVI View that will be responsible to render the view state
 */
public interface RevampViewStateRenderer<STATE extends RevampViewState> extends ViewComponent {
  void render(@NonNull STATE model);
}
