package dk.dtu.compute.course02324.assignment3.lists.implementations;

import dk.dtu.compute.course02324.assignment3.lists.types.List;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Comparator;

/**
 * An implementation of the interface {@link List} based on basic Java
 * arrays, which dynamically are adapted in size when needed.
 *
 * @param <E> the type of the list's elements.
 */
public class ArrayList<E> implements List<E> {

    final private int DEFAULT_SIZE = 10;
    private int size = 0;
    private E[] list = createEmptyArray(DEFAULT_SIZE);

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public @NotNull E get(int pos) throws IndexOutOfBoundsException {
        checkIndex(pos);
        return list[pos];
    }

    @Override
    public E set(int pos, @NotNull E e) throws IndexOutOfBoundsException {
        checkIndex(pos);
        E old = list[pos];
        list[pos] = e;
        return old;
    }

    @Override
    public boolean add(@NotNull E e) {
        if (e == null) {
            throw new IllegalArgumentException("Cannot add null element to the list");
        }
        ensureCapacity();
        list[size++] = e;
        return true;
    }

    @Override
    public boolean add(int pos, @NotNull E e) throws IndexOutOfBoundsException {
        if (e == null) {
            throw new IllegalArgumentException("Cannot add null element to the list");
        }
        checkIndexForAdd(pos);
        ensureCapacity();
        System.arraycopy(list, pos, list, pos + 1, size - pos);
        list[pos] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int pos) throws IndexOutOfBoundsException {
        checkIndex(pos);
        E removed = list[pos];
        System.arraycopy(list, pos + 1, list, pos, size - pos - 1);
        list[--size] = null;
        return removed;
    }

    @Override
    public boolean remove(E e) {
        int index = indexOf(e);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void sort(@NotNull Comparator<? super E> c) throws UnsupportedOperationException {
        if (c == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        Arrays.sort(list, 0, size, c);
    }

    private void ensureCapacity() {
        if (size == list.length) {
            list = Arrays.copyOf(list, list.length * 2);
        }
    }

    private void checkIndex(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + pos);
        }
    }

    private void checkIndexForAdd(int pos) {
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + pos);
        }
    }

    private E[] createEmptyArray(int length) {
        return (E[]) new Object[length];
    }
}
