package dk.dtu.compute.course02324.assignment3.lists.implementations;


import dk.dtu.compute.course02324.assignment3.lists.types.List;
import dk.dtu.compute.course02324.assignment3.lists.types.SortedList;

import javax.validation.constraints.NotNull;
import java.util.Comparator;

/**
 * An implementation of the interface {@link SortedList} based on the
 * {@link ArrayList} implementation of the interface{@link List}
 * arrays, which dynamically are adapted in size when needed.
 *
 * @param <E> the type of the list's elements.
 */
public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> implements SortedList<E> {

    @Override
    public boolean add(@NotNull E e) {
        if (e == null) {
            throw new IllegalArgumentException("Null elements are not allowed");
        }

        int index = findIndexToInsert(e);
        super.add(index, e);
        return true;
    }


    @Override
    public void sort(Comparator<? super E> c) {
        throw new UnsupportedOperationException("SortedArrayList does not support explicit sorting.");
    }

    /**
     * Finds the position at which a given element should be inserted
     * to the sorted list. The element must not be <code>null</code>.
     * The implementation finds this position by linearly going through
     * the array, stopping at the first element greater or equal to
     * this element.
     *
     * @param e the given element to be inserted
     * @return the position at which the element should be inserted
     */
    private int findIndexToInsert(@NotNull E e) {
        for (int i = 0; i < size(); i++) {
            if (get(i).compareTo(e) >= 0) { // e is less than or equal to get(i)
                return i;
            }
        }
        return size(); // Insert at the end if no element is greater
    }

}
