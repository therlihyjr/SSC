package week08;

/** Inner class tree node */
class TreeNode<E extends Comparable<E>>
{
    E element;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E e)
    {
        element = e;
    }
    
    public E getElement()
    {
    	return element;
    }
}
