package revamp.mvi;

import android.support.annotation.NonNull;

/**
 * Defines a reducer that will create an immutable model
 */
public interface RevampViewStateReducer<STATE extends RevampViewState, CHANGES extends RevampViewStateChanges> {
  @NonNull STATE reduce(@NonNull STATE previous, @NonNull CHANGES changes);
}
