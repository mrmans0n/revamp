package revamp.testing;

import revamp.base.BusinessObject;

public class TestBO implements BusinessObject {

  public String getPersistedString() {
    return "something";
  }
}
