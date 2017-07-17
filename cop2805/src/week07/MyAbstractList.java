package week07;

/**
 * @author Tim Herlihy Jr. 
 * @param <E> generic E
 */

public abstract class MyAbstractList<E> implements MyList<E> {

protected int size = 0; // The size of the list

/** 
 * Create a default list 
 * */
protected MyAbstractList() {
}

/** 
 * Create a list from an array of objects 
 * @param objects objects to add to the list
 */
protected MyAbstractList(E[] objects) {
for (int i = 0; i < objects.length; i++)
add(objects[i]);
}

@Override 
/** 
 * Add a new element at the end of this list 
 * @param e element to add to the end of the list 
 */
public void add(E e) {
add(size, e);
}

@Override 
/** 
 * Return true if this list doesn't contain any elements
 * @return true if this list is empty, else false 
*/
public boolean isEmpty() {
return size == 0;
}

@Override 
/** 
 * Return the number of elements in this list
 * @return size return the size of the list 
 */
public int size() {
return size;
}

@Override 
/** Remove the first occurrence of the element e
* from this list. Shift any subsequent elements to the left.
* @Return true if the element is removed. */
public boolean remove(E e) {
if (indexOf(e) >= 0) {
remove(indexOf(e));
return true;
}
else
return false;
}

}

	

