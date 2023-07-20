package FunctionalInterface;

import java.util.List;

public interface ListFilter<T> {
    boolean satisfiesCondition(T object);
}
