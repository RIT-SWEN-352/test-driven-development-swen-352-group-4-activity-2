package edu.rit.swen352.tdd.hard;

import java.beans.Transient;
import java.util.function.Consumer;

/**
 * MyList is a flexible-sized sequence of elements with no gaps.
 * All elements must be non-{@code null}.
 *
 * <p>
 * You must implement these features:
 * <ul>
 *   <li>constructor: a ctor that supplies an initial capacity</li>
 *   <li>{@code isEmpty()}: queries if the list is empty</li>
 *   <li>{@code size():int}: queries how many elements in the list</li>
 *   <li>{@code get(index):T}: returns the element at a specific index;
 *     throw {@link java.util.NoSuchElementException} if the index is outside the size of the list</li>
 *   <li>{@code add(element:T)}: add an element to the end of the list</li>
 *   <li>{@code remove(element:T)}: remove an element by index</li>
 *   <li>{@code forEach(Consumer)}: iterates over each element and executes the {@link java.util.function.Consumer} parameter</li>
 * </ul>
 *
 * @param <T> the type of elements in the list.
 */
public class MyList<T> {
    private Object[] elements;
    private int size;

    public MyList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity cannot be non-positive");
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
        return true;
    }

    private void resize() {
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new java.util.NoSuchElementException("Index out of bounds");
        }
        return (T) elements[index];
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new java.util.NoSuchElementException("Index out of bounds");
        }
        T indexValue = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return indexValue;
    }

    public boolean removeFirst(T element) {
         for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                // Shift elements to the left to fill the gap
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[--size] = null; // Clear the last element
                return true;
            }
        }
        return false;
    }

    public void forEach(Consumer<T> c) {
        if (c == null)
            throw new IllegalArgumentException("Consumer can not be null");
        for (int i = 0; i < size; i++) {
            c.accept((T) elements[i]);
        }
    }
}
