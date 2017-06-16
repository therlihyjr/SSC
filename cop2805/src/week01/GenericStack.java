/**
@author Tim Herlihy Jr.
*/

package week01;

import java.util.Arrays;

@SuppressWarnings("unchecked") //should probably ask about these warnings

public class GenericStack<T>
{
	/** private class variables */ 
	private int maxArraySize = 10; /** default array size */ 
	private int size; /** current array size */
	private Object[] stack;
	
	/** constructor */
	GenericStack()
	{
		stack = new Object[maxArraySize];
	}
	
	/** peek method to return top object of stack
	 * 
	 * @return top object
	 */
	public T peek()
	{
		if (size == 0)
			return (T)stack[0];
		else
			return (T)stack[size - 1];
	}
	
	/** method to determine whether array is empty
	 * 
	 * @return true/false
	 */
	public boolean isEmpty()
	{
		if (size == 0)
			return true;
		else
			return false;
	}

	/** Method to add a new item to array
	 * 
	 * @param o		new item to add
	 * 	You should check the array size before adding a new element to the stack. 
	 *	If the array is full, create a new array that doubles the current array size 
	 *	and copy the elements from the current array to the new array. 
	*/
    public void push(T o) {
    	if (stack.length == size)
    	{
    		Object[] placeholder = new Object[stack.length * 2];
    		System.arraycopy(stack, 0, placeholder, 0, stack.length);
    	}
    	stack[size++] = o;
    }
    
	/** method to remove and return top item in array
	 * 
	 * @return top item in array
	 */
	public T pop()
	{
		if (size == 0)
			return (T)stack[0];
		else 
			return (T)stack[size - 1];
	}
	
	/** method to return the size of the array
	 * 
	 * @return array size
	 */
	public int getSize() {
		return size;
	}

	/** default to string method
	 * 
	 * @return object toString
	 */
	@Override
	public String toString() {
		return "GenericStack [maxArraySize=" + maxArraySize + ", size=" + size + ", stack="
				+ Arrays.toString(stack) + "]";
	}

}