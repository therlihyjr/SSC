package week07;

/**
 * @author Tim Herlihy Jr. 
 * @param <E> generic E
 */
public interface MyList<E> extends java.lang.Iterable<E> {


/**
 * Add a new element at the end of this list 
 * @param e element to add
 */
public void add(E e);

/**
 * Add a new element at the specified index in this list
 * @param index lcoation of element to add
 * @param e element to add
 */
public void add(int index, E e);

/**
 * Clear the list
 */
public void clear();

/**
 * Return true if this list contains the element
 * @param e element to search for
 * @return true if list contains element
 */
public boolean contains(E e);

/**
 * Return the element from this list at the specified index
 * @param index of element to return
 * @return element at index
 */
public E get(int index);

/**
 * Return the index of the first matching element in this list.
 * @param e element to match
 * @return -1 if no match.
 */
public int indexOf(E e);

/**
 * Return true if this list doesn't contain any elements
 * @return true if this list doesn't contain any elements
 */
public boolean isEmpty();

/**
 * Return the index of the last matching element in this list
 * @param e element to match
 * @return -1 if no match
 */
public int lastIndexOf(E e);

/**
 * Remove the first occurrence of the element e from this list.
 * Shift any subsequent elements to the left.
 * @param e element to remove
 * @return true if element is removed
 */
public boolean remove(E e);

/**
 * Remove the element at the specified position in this list.
 * Shift any subsequent elements to the left.
 * @param index of element to remove 
 * @return last element that was removed
 */
public E remove(int index);

/**
 * Replace the element at the specified position in this list
 * @param index location of object
 * @param e new object
 * @return the old element
 */
public E set(int index, E e);

/**
 * Return the number of elements in this list
 * @return int size of list
 */
public int size();

}
