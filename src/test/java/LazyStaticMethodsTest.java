import io.vavr.Lazy;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2018-12-01.
 */
public class LazyStaticMethodsTest {

    @Test
    public void of_invokedOnlyOnce_memoize() {
        var counter = new LongAdder();

        Lazy<Integer> lazyInt = Lazy.of(() -> {
            counter.increment();
            return new Random().nextInt();
        });
        
        assertThat(counter.sum(), is(0L));

        Integer firstGet = lazyInt.get();

        assertThat(lazyInt.get(), is(firstGet));
        assertThat(counter.sum(), is(1L));

        assertThat(lazyInt.get(), is(firstGet));
        assertThat(counter.sum(), is(1L));
    }

    @Test
    public void val_invokedOnlyOnce_memoize() {
        var counter = new LongAdder();

        CharSequence lazyString = Lazy.val(() -> {
            counter.increment();
            return String.valueOf(new Random().nextInt());
        }, CharSequence.class);

        assertThat(counter.sum(), is(0L));
        
        var temp = lazyString + "";

        assertThat(counter.sum(), is(1L));
        assertThat(lazyString, is(temp));

        temp = lazyString + "";
        assertThat(counter.sum(), is(1L));
        assertThat(lazyString, is(temp));
    }
}
