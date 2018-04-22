package revamp.mvi;

import revamp.base.Model;

/**
 * Simple implementation of a MVI {@link Model}, that will define the view state we want to render on our {@link RevampViewStateRenderer}.
 * This class must be immutable.
 */
public interface RevampViewState extends Model {
}
