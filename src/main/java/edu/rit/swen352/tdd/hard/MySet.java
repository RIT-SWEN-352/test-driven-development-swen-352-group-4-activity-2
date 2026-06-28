package edu.rit.swen352.tdd.hard;

/**
 * MySet is a flexible-sized, unordered collection of elements.
 * All elements must be non-{@code null}.
 *
 * <p>
 *   The {@link Object#equals(Object)} method is used to determine if two values are equal.
 * </p>
 *
 * <p>
 * You must implement these features:
 * <ul>
 *   <li>constructor: a ctor that supplies an initial capacity</li>
 *   <li>{@code isEmpty()}: queries if the set is empty</li>
 *   <li>{@code size():int}: queries how many elements in the set</li>
 *   <li>{@code contains(element:T):boolean}: queries whether the supplied object exists (by equality) in the set</li>
 *   <li>{@code add(element:T)}: add an element to the set; no-op if the element is already in the set (by equality)</li>
 *   <li>{@code remove(element:T)}: remove an element by equality</li>
 *   <li>{@code map(Function<T,F>):MySet<F>}: creates a new set where each element is transformed by a {@link java.util.function.Function}</li>
 *   <li>NFR: the {@code contains} method executes in O(1) (<em>constant</em>) time</li>
 * </ul>
 *
 * @param <T> the type of elements in the set.
 */
public class MySet<T> {
    private Object[] elements;
    private int size;

    public MySet(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be greater than 0");
        }

        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }
    
    public boolean add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        if (contains(element)) {
            return false;
        }

        if (size == elements.length) {
            // Resize the array if necessary
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }

        elements[size++] = element;
        return true;
    }

    public boolean contains(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }
}
