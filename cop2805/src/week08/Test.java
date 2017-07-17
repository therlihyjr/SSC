package week08;

import java.util.Stack;
/* * Java Program to print all leaf nodes of given binary tree. 
 * * This program demonstrates both recursive and iterative solution 
 * * of this problem. * 
 * * input : 
 * 50 
 * / \ 
 * 25 65 
 * / \ / \ 
 * * 10 34 43 78 * * output: 10 34 43 78 */ 

public class Test 

{ 
	
	public static void main(String[] args) throws Exception 
{ 
	// let's create a binary tree 
	TreeNode n10 = new TreeNode(10); 
	TreeNode n34 = new TreeNode(34); 
	TreeNode n43 = new TreeNode(43); 
	TreeNode n78 = new TreeNode(78); 
	TreeNode n25 = new TreeNode(25, n10, n34); 
	TreeNode n65 = new TreeNode(65, n43, n78); 
	TreeNode root = new TreeNode(50, n25, n65); 
	
	// print all leaf nodes of binary tree using recursion 
	System.out .println("Printing all leaf nodes of a binary tree in Java (recursively)"); 
	printLeafNodes(root); 
	// insert a new line
	System.out.println(); 
	// printing all leaf nodes without recursion in binary tree 
	System.out .println("Printing all leaf nodes of binary tree in Java using stack"); 
	printLeafNodesIteratively(root); 
	}

/** * A class to represent a node in binary tree */ 
private static class TreeNode { 
int value; 
TreeNode left; 
TreeNode right; 
TreeNode(int data) 
	{ 
	this.value = data; 
	}

TreeNode(int data, TreeNode left, TreeNode right) 

{ 
	this.value = data; 
	this.left = left; 
	this.right = right; 
	} 
} 

/** * Java method to print leaf nodes using recursion * 
 * * @param root * */ 
public static void printLeafNodes(TreeNode node)

{ // base case

	if (node == null) 
	{ 
		return; 
		} 
	if (node.left == null && node.right == null) 
	{ 
		System.out.printf("%d ", node.value); 
	} 
	
	printLeafNodes(node.left); printLeafNodes(node.right); 
	
} 

/** * Java method to print leaf nodes using iteration * 
 * * @param root * */ 

public static void printLeafNodesIteratively(TreeNode root) 
{
	if (root == null) 
	{ 
		return; 
	}

Stack<TreeNode> stack = new Stack<>(); 

stack.push(root); 
while (!stack.isEmpty())
{ TreeNode node = stack.pop(); 
if (node.right != null) { stack.add(node.right); 
} if (node.left != null) { stack.add(node.left); } 
if (node.left == null && node.right == null)
{ System.out.printf("%d ", node.value); } 
}
} 
}


