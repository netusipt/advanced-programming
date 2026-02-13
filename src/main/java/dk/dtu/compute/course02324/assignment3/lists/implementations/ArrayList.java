package dk.dtu.compute.course02324.assignment3.lists.implementations;

import dk.dtu.compute.course02324.assignment3.lists.types.List;

import jakarta.validation.constraints.NotNull;
import java.util.Comparator;

/**
 * An implementation of the interface {@link List} based on basic Java
 * arrays, which dynamically are adapted in size when needed.
 *
 * @param <E> the type of the list's elements.
 */
public class ArrayList<E> implements List<E> {

    /**
     * Constant defining the default size of the array when the
     * list is created. The value can be any (strictly) positive
     * number. Here, we have chosen <code>10</code>, which is also
     * Java's default for some array-based collection implementations.
     */
    final private int DEFAULT_SIZE = 10;

    /**
     * Current size of the list.
     */
    private int size = 0;

    /**
     *  The array for storing the elements of the
     */
    protected E[] list = createEmptyArray(DEFAULT_SIZE);

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
        // TODO needs implementation (Assignment 3a)

        if (pos >= this.size || pos < 0) {
            throw new IndexOutOfBoundsException();
        }
        return this.list[pos];
    }

    @Override
    public E set(int pos, @NotNull E e) throws IndexOutOfBoundsException {
        // TODO needs implementation (Assignment 3a)

        if (e == null) {
            throw new IllegalArgumentException();
        }
        if (pos >= this.size || pos < 0) {
            throw new IndexOutOfBoundsException();
        }

        E prev = this.list[pos];
        this.list[pos] = e;
        return prev;
    }

    @Override
    public boolean add(@NotNull E e)  {
        // TODO needs implementation (Assignment 3a)
        if (e == null) {
            throw new IllegalArgumentException();
        }

        if(this.size == this.list.length) {
            this.expandList();
        }
        this.list[this.size++] = e;
        return true;
    }

    @Override
    public boolean add(int pos, @NotNull E e) throws IndexOutOfBoundsException {
        // TODO needs implementation (Assignment 3a)
        if (e == null) {
            throw new IllegalArgumentException();
        }

        if(this.size == this.list.length) {
            this.expandList();
        }

        if(pos > this.size || pos < 0) {
            throw new IndexOutOfBoundsException();
        } else if (pos == this.size) {
            this.list[pos] = e;
        } else {
            E next = this.list[pos];
            this.list[pos] = e;
            E newNext;

            for (int i = pos + 1; i < this.size; i++) {
                newNext = this.list[i];
                this.list[i] = next;
                next = newNext;
            }
            this.list[size] = next;
        }

        this.size++;
        return true;
    }

    @Override
    public E remove(int pos) throws IndexOutOfBoundsException {
        // TODO needs implementation (Assignment 3a)
        if (pos >= this.size || pos < 0) {
            throw new IndexOutOfBoundsException();
        }
        E removed = list[pos];
        for (int i = pos; i < size - 1; i++) {
            this.list[i] = this.list[i + 1];
        }
        this.list[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public boolean remove(E e) {
        // TODO needs implementation (Assignment 3a)
        int index;
        if((index = this.indexOf(e)) != -1) {
            this.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(E e) {
        // TODO needs implementation (Assignment 3a)
        for (int i = 0; i < this.size; i++) {
            if(this.list[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void sort(@NotNull Comparator<? super E> c) throws UnsupportedOperationException {
        // TODO needs implementation (Assignment 3b)
        BubbleSort.sort(c, this);
    }

    /**
     * Creates a new array of type <code>E</code> with the given size.
     *
     * @param length the size of the array
     * @return a new array of type <code>E</code> and the given length
     */
    private E[] createEmptyArray(int length) {
        // there is unfortunately no really easy and elegant way to initialize
        // an array with a type coming in as a generic type parameter, but
        // the following is simple enough. And it is OK, since the array
        // is never passed out of this class.
        return (E[]) new Object[length];
    }

    // TODO probably some private helper methods here (avoiding duplicated code)
    //      (Assignment 3a)

    /**
     * Doubles the array size
     */
    private void expandList()
    {
        E[] newList = (E[]) new Object[list.length * 2];
        System.arraycopy(this.list, 0, newList, 0, this.list.length);
        this.list = newList;
    }
}
