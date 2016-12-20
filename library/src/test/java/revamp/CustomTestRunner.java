package revamp;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;

public class CustomTestRunner extends RobolectricTestRunner {
  public CustomTestRunner(Class<?> testClass) throws InitializationError {
    super(testClass);
  }
}
