package revamp.mvp;

import revamp.base.Model;
import revamp.base.ReleasableModel;

/**
 * Simple implementation of a {@link Model}
 */
public abstract class RevampMvpModel implements ReleasableModel {
  @Override
  public void release() {
    // no-op
  }
}
