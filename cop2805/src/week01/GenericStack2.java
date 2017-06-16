/**
 * @author Tim Herlihy Jr.
 * (Implement GenericStack using inheritance) In Listing 19.1, GenericStack is
implemented using composition. Define a new stack class that extends ArrayList.
Draw the UML diagram for the classes and then implement GenericStack.
Write a test program that prompts the user to enter five strings and displays them in
reverse order.
 */

package week01;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class GenericStack2<T> extends ArrayList<T> {
	
	/**
	 * method determines if array is empty 
	 */
	public boolean isEmpty()
	{
		if (getSize() == 0)
			return true;
		else			
			return false;
	}
	
	/**
	 * adds new new object to array
	 * @param o
	 */
	public void push(T o) {
		add(o);	
	}

	/** 
	 * get size
	 * @return int size
	 */
	public int getSize() {
		return super.size();
	}

    /**
     * return and remove object on top of stack
     * @return object 
     */
	public T pop() {
        T o = get(size() - 1);
        remove(size() - 1);
        return o;
	}

	/**
	 * return object on top of stack
	 * @return object
	 */
	public T peek() {
        return get(size() - 1);
	}

}
