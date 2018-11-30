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
