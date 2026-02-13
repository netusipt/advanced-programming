package dk.dtu.compute.course02324.assignment3.lists.implementations;

import dk.dtu.compute.course02324.assignment3.lists.types.List;
import dk.dtu.compute.course02324.assignment3.lists.types.SortedList;

import jakarta.validation.constraints.NotNull;
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
            throw new IllegalArgumentException();
        }
        int pos = findIndexToInsert(e);
        return super.add(pos, e);
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
        // TODO implementing and using this method might help you with
        //      a simple implementation of the add(E e) method.
        //      (Assignment 3b)


        if (e == null) {
            throw new IllegalArgumentException();
        }
        int index = 0;
        while (index < size() && get(index).compareTo(e) < 0) {
            index++;
        }
        return index;
    }

    @Override
    public void sort(@NotNull Comparator<? super E> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Operation sort(Comparator<? super E> c) not allowed on SortedLists");
    }

}
