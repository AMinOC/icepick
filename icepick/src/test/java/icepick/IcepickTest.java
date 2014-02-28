package icepick;

import android.os.Bundle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest(Bundle.class) @RunWith(PowerMockRunner.class)
public class IcepickTest {

  static final String KEY = "key";
  static final String VALUE = "value";
  static final String ANOTHER_VALUE = "anotherValue";

  final Bundle state = PowerMockito.mock(Bundle.class);
  final ClassToInject classToInject = new ClassToInject();

  @Test public void saveState() throws Exception {
    Icepick.saveInstanceState(classToInject, state);

    verify(state).putString(KEY, VALUE);
  }

  @Test public void restoreState() throws Exception {
    when(state.getString(KEY)).thenReturn(ANOTHER_VALUE);

    Icepick.restoreInstanceState(classToInject, state);

    assertEquals(ANOTHER_VALUE, classToInject.string);
  }

  static class ClassToInject {
    String string = VALUE;
  }

  @SuppressWarnings("unused")
  static class ClassToInject$$Icicle implements StateHelper<Bundle> {

    @Override public Bundle saveInstanceState(Object obj, Bundle outState) {
      ClassToInject target = (ClassToInject) obj;
      outState.putString(KEY, target.string);
      return outState;
    }

    @Override public Bundle restoreInstanceState(Object obj, Bundle saveInstanceState) {
      ClassToInject target = (ClassToInject) obj;
      target.string = saveInstanceState.getString(KEY);
      return saveInstanceState;
    }
  }
}
