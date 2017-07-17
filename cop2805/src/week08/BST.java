package week08;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Binary Search Tree implementation
 *
 * The generic type supported must implement Comparable<E>
 *
 * @author Scott LaChance
 */
public class BST<E extends Comparable<E>> extends AbstractTree<E> implements Cloneable
{
	protected TreeNode<E> root;
	protected int size = 0;
	List<E> leafDataList = new ArrayList<E>();

	/**
	 * Create a default binary tree
	 */
	public BST()
	{
	}

	/**
	 * Parameterized constructor
	 * Create a binary tree from an array of objects
	 *
	 * @param objects Array of objects
	 */
	public BST(E[] objects)
	{
		for(int i = 0; i < objects.length; i++)
		{
			insert(objects[i]);
		}
	}

	/**
	 * search the whole tree and identify the leaf nodes, put the data from the nodes into a list and return it.
	 * @return the list of leaf notes
	 */
	public List<E> getLeafData()
	{
		TreeNode<E> root = this.getRoot();
		getLeaves(root);
		return leafDataList;
	}

	/**
	 * getLeaves 
	 * recursively go through the tree to pull out the leaves and add them to
	 * an arraylist from getLeafData()
	 */
	public void getLeaves(TreeNode<E> a)
	{
		if(a == null)
		{
			return;
		}
		if(a.left == null && a.right == null)
		{
			leafDataList.add(a.getElement());
		}
		
		getLeaves(a.left);
		getLeaves(a.right);

	}
	
	
	/**
	 * Returns true if the element is in the tree
	 * @param e to search for
	 * @return true if found, otherwise false
	 */
	public boolean search(E e)
	{
		TreeNode<E> current = root; // Start from the root

		while(current != null)
		{
			if(e.compareTo(current.element) < 0)
			{
				current = current.left;
			}
			else if(e.compareTo(current.element) > 0)
			{
				current = current.right;
			}
			else // element matches current.element
			{
				return true; // Element is found
			}
		}

		return false;
	}

	/**
	 * Insert element o into the binary tree Return true if the element is
	 * inserted successfully
	 *
	 * @param e to insert for
	 * @return true if successful, otherwise false
	 */
	public boolean insert(E e)
	{
		if(root == null)
		{
			root = createNewNode(e); // Create a new root
		}
		else
		{
			// Locate the parent node
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while(current != null)
				if(e.compareTo(current.element) < 0)
				{
					parent = current;
					current = current.left;
				}
				else if(e.compareTo(current.element) > 0)
				{
					parent = current;
					current = current.right;
				}
				else
					return false; // Duplicate node not inserted

			// Create the new node and attach it to the parent node
			if(e.compareTo(parent.element) < 0)
				parent.left = createNewNode(e);
			else
				parent.right = createNewNode(e);
		}

		size++;
		return true; // Element inserted
	}

	/**
	 * Creates a new tee node
	 * @param e Element to create the ndoe for
	 * @return New TreeNode instance
	 */
	protected TreeNode<E> createNewNode(E e)
	{
		return new TreeNode<E>(e);
	}

	/**
	 * Inorder traversal from the root
	 */
	public void inorder()
	{
		inorder(root);
	}

	/**
	 * Inorder traversal from a subtree
	 * @param root TreeNode to start from
	 */
	protected void inorder(TreeNode<E> root)
	{
		if(root == null)
			return;
		inorder(root.left);
		System.out.print(root.element + " ");
		inorder(root.right);
	}

	/**
	 * Postorder traversal from the root
	 */
	public void postorder()
	{
		postorder(root);
	}

	/**
	 * Postorder traversal from a subtree
	 *
	 * @param root TreeNode to start from
	 */
	protected void postorder(TreeNode<E> root)
	{
		if(root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}

	/**
	 * Preorder traversal from the root
	 */
	public void preorder()
	{
		preorder(root);
	}

	/**
	 *  Preorder traversal from a subtree
	 * @param root TreeNode to start from
	 */
	protected void preorder(TreeNode<E> root)
	{
		if(root == null)
			return;
		System.out.print(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}

	/**
	 * Get the number of nodes in the tree
	 * @return number of nodes in the tree
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * Returns the root of the tree
	 * @return TreeNode refenence for the root
	 */
	public TreeNode<E> getRoot()
	{
		return root;
	}

	public int getDepth()
	{
		return walkDepth(root, 1);
	}

	/**
	 * Returns a path from the root leading to the specified element
	 * @param e Element to find in the tree
	 * @return List of TreeNode elements in the path
	 */
	public java.util.ArrayList<TreeNode<E>> path(E e)
	{
		java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<TreeNode<E>>();
		TreeNode<E> current = root; // Start from the root

		while(current != null)
		{
			list.add(current); // Add the node to the list
			if(e.compareTo(current.element) < 0)
			{
				current = current.left;
			}
			else if(e.compareTo(current.element) > 0)
			{
				current = current.right;
			}
			else
				break;
		}

		return list; // Return an array of nodes
	}

	/**
	 * Delete an element from the binary tree. Return true if the element is
	 * deleted successfully Return false if the element is not in the tree
	 *
	 * @param e Element to delete
	 * @return truee if successful, otherwise false
	 */
	public boolean delete(E e)
	{
		// Locate the node to be deleted and also locate its parent node
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		while(current != null)
		{
			if(e.compareTo(current.element) < 0)
			{
				parent = current;
				current = current.left;
			}
			else if(e.compareTo(current.element) > 0)
			{
				parent = current;
				current = current.right;
			}
			else
				break; // Element is in the tree pointed by current
		}

		if(current == null)
			return false; // Element is not in the tree

		// Case 1: current has no left children
		if(current.left == null)
		{
			// Connect the parent with the right child of the current node
			if(parent == null)
			{
				root = current.right;
			}
			else
			{
				if(e.compareTo(parent.element) < 0)
					parent.left = current.right;
				else
					parent.right = current.right;
			}
		}
		else
		{
			// Case 2: The current node has a left child
			// Locate the rightmost node in the left subtree of
			// the current node and also its parent
			TreeNode<E> parentOfRightMost = current;
			TreeNode<E> rightMost = current.left;

			while(rightMost.right != null)
			{
				parentOfRightMost = rightMost;
				rightMost = rightMost.right; // Keep going to the right
			}

			// Replace the element in current by the element in rightMost
			current.element = rightMost.element;

			// Eliminate rightmost node
			if(parentOfRightMost.right == rightMost)
				parentOfRightMost.right = rightMost.left;
			else
				// Special case: parentOfRightMost == current
				parentOfRightMost.left = rightMost.left;
		}

		size--;
		return true; // Element inserted
	}

	/**
	 * Obtain an iterator. Use inorder.
	 *
	 *  @return The in-order Iterator for the tree
	 */
	public java.util.Iterator<E> iterator()
	{
		return inorderIterator();
	}

	/**
	 * Obtain an inorder iterator
	 *
	 *  @return The in-order Iterator for the tree
	 */
	public java.util.Iterator<E> inorderIterator()
	{
		return new InorderIterator<E>(root);
	}

	/**
	 * Remove all elements from the tree
	 */
	public void clear()
	{
		root = null;
		size = 0;
	}

	/**
	 * Clones the tree and returns it
	 *
	 * @return the cloned tree object
	 */
	public Object clone()
	{
		BST<E> tree1 = new BST<E>();

		copy(root, tree1);

		return tree1;
	}

	private int walkDepth(TreeNode<E> node, int curDepth)
	{
		if(node == null)
		{
			return curDepth;
		}

		curDepth++;

		int leftDepth = walkDepth(node.left, curDepth);
		int rightDepth = walkDepth(node.right, curDepth);


		return leftDepth > rightDepth ? leftDepth : rightDepth;
	}

	/**
	 * Copies a tree node and its children into the tree
	 * @param root The TreeNode to copy
	 * @param tree The tree to receive the copy
	 */
	private void copy(TreeNode<E> root, BST<E> tree)
	{
		if(root != null)
		{
			tree.insert(root.element);
			copy(root.left, tree);
			copy(root.right, tree);
		}
	}
	
}

