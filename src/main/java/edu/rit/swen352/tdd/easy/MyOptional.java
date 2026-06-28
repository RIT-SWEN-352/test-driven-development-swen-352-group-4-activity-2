package edu.rit.swen352.tdd.easy;

/**
 * MyOptional contains a single value, of any type, or nothing at all.
 *
 * <p>
 * This must be an immutable
 * <a href='https://en.wikipedia.org/wiki/Value_object'>Value Object</a>.
 * </p>
 *
 * <p>
 *   For example:
 *   <ul>
 *     <li><code>MyOptional.of(42)</code> is a non-empty optional containing an integer: 42.</li>
 *     <li><code>MyOptional.empty()</code> is an empty optional.</li>
 *     <li><code>MyOptional.of("My string")</code> is a non-empty optional containing a string: "My string".</li>
 *   </ul>
 * </p>
 *
 * <p>
 * You must implement these features:
 * <ul>
 *   <li>factories:
 *     <ul>
 *       <li>{@code empty()} -- return an empty optional</li>
 *       <li>{@code of(value:T)} -- return a non-empty optional</li>
 *       <li>{@code ofNullable(value:T)} -- return an optional that might be empty</li>
 *       <li>NFR: hide any constructors; forces clients to use a factory function</li>
 *     </ul>
 *   </li>
 *   <li>{@code isPresent():boolean}: queries if the optional is not empty</li>
 *   <li>{@code get():T}: returns the value; throws {@link java.util.NoSuchElementException} if empty</li>
 *   <li>{@code ifPresent(Consumer)}: executes a {@link java.util.function.Consumer} if a value is present</li>
 * </ul>
 *
 * @param <T> the type of value.
 */
public class MyOptional<T> {
    private final T value;
    
    private MyOptional(T value) {
        this.value = value;
    }

    public static <T> MyOptional<T> empty() {
        return new MyOptional<>(null);
    }

    public static <T> MyOptional<T> of(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        return new MyOptional<>(value);
    }
    
    public boolean isPresent() {
        return value != null;
    }
}
