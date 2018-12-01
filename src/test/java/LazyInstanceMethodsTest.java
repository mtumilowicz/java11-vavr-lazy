import io.vavr.Lazy;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mtumilowicz on 2018-12-01.
 */
public class LazyInstanceMethodsTest {
    @Test
    public void isEvaluated_true() {
        Lazy<String> lazy = Lazy.of(() -> "evaluated");

        assertFalse(lazy.isEvaluated());

        lazy.get();

        assertTrue(lazy.isEvaluated());
    }

    @Test
    public void map_returns_notEvaluated() {
        Lazy<String> lazy = Lazy.of(() -> "evaluated");
        lazy.get();
        assertTrue(lazy.isEvaluated());

        Lazy<String> mapped = lazy.map(str -> str + "");
        assertFalse(mapped.isEvaluated());
    }
}
