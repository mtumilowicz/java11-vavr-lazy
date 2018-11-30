# java11-vavr-lazy

_Reference_: https://static.javadoc.io/io.vavr/vavr/0.9.2/io/vavr/Lazy.html  
_Reference_: https://www.vavr.io/vavr-docs/#_lazy

# preface
Lazy is a monadic container type which represents a 
lazy evaluated value. 

**Compared to a Supplier, 
Lazy is memoizing, i.e. it evaluates only once and 
therefore is referentially transparent.**

You may also create a real lazy value 
(works only with interfaces):
```
CharSequence chars = Lazy.val(() -> "Yay!", CharSequence.class);
```

# project description
Please refer to my other vavr github projects, because we
will not analyze similar methods:
* https://github.com/mtumilowicz/java11-vavr-option
* https://github.com/mtumilowicz/java11-vavr-try
* https://github.com/mtumilowicz/java11-vavr-either

## static methods
* `Lazy<T>	narrow(Lazy<? extends T> lazy)`
* `Lazy<T>	of(Supplier<? extends T> supplier)`
    ```
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
    ```
* `Lazy<Seq<T>>	sequence(Iterable<? extends Lazy<? extends T>> values)`
* `T	val(Supplier<? extends T> supplier, Class<T> type)` - 
Creates a real lazy value of type T, backed by a Proxy which delegates to a Lazy instance.
    ```
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
    ```
## instance methods
* `boolean	equals(Object o)`
* `Option<T>	filter(Predicate<? super T> predicate)`
* `T	get()` - 
Evaluates this lazy value and caches it, when called the 
first time.
* `int	hashCode()`
* `boolean	isEmpty()`
    ```
    @Override
    public boolean isEmpty() {
        return false;
    }
    ```
* `boolean	isEvaluated()`
* `Lazy<U>	map(Function<? super T,? extends U> mapper)`
    ```
    @Override
    public <U> Lazy<U> map(Function<? super T, ? extends U> mapper) {
        return Lazy.of(() -> mapper.apply(get()));
    }
    ```
* `Lazy<T>	peek(Consumer<? super T> action)`
    ```
    @Override
    public Lazy<T> peek(Consumer<? super T> action) {
        action.accept(get());
        return this;
    }
    ```
* `U	transform(Function<? super Lazy<T>,? extends U> f)`