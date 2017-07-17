package week08;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Tests the BST implementation
 * 			
 * <pre>								                                                George
                                                  |---------------------------------------|----------------------------------------------|
						                        Daniel                                                                                 Ringo
                        |-------------------------|------------------|                                                     |-------------|----------------------|                  
		     Alice                                         David                                                 Paul                                 Stevie
         |-------------|------------|                    |-----------|-----------|                             |-----------|----------|                |--------|---------|  
         e                        Bruce                  e                       e                         John                     Peter              e                 Zach
   |-----|------|            |------|-----|         |----|------|           |----|----|           |---------|--------|           |----|----|       |---|---|        |-----|-----|
   e            e            e            e         e           e           e          e        Horatio             Mary         e         e       e       e      Vanessa       e
|--|--|      |--|--|       |-|-|        |-|-|     |-|-|       |-|-|       |-|-|      |-|-|   |----|---|           |---|--|     |-|-|     [-|-|   |-|-|   |-|-|   |---|--|     |-|-|
e     e      e     e       e    e       e   e     e   e       e   e       e   e      e   e   e      James       June    Nick   e   e     e   e   e   e   e   e   e       e    e   e

 * </pre>
 * 
 * @author Scott LaChance
 *
 */
public class JUnitBstTest
{

	@Test
	public void test()
	{
		BST<String> tree = new BST<String>();

		tree.insert("George");
		tree.insert("Ringo");
		tree.insert("Paul");
		tree.insert("John");
		tree.insert("Peter");
		tree.insert("Mary");
		tree.insert("Stevie");
		tree.insert("Daniel");
		tree.insert("Zach");
		tree.insert("Horatio");
		tree.insert("James");
		tree.insert("David");
		tree.insert("June");
		tree.insert("Alice");
		tree.insert("Bruce");
		tree.insert("Nick");
		tree.insert("Vanessa");
		
		List<String> leaves = tree.getLeafData();
		
		validateList(leaves);
//		prettyPrintTree(tree);
	}

//	private void prettyPrintTree(BST<String> tree)
//	{
//		m_treeByDepth = new HashMap<Integer, List<TreeNode<String>>>();
//		int depth = tree.getDepth();
//		int bucketCount = (int)Math.pow(2, depth);
//		int maxWidth = bucketCount * 2;
//		int center = maxWidth / 2;
//
//		TreeNode<String> root = tree.getRoot();
//		List<TreeNode<String>> rootList = new ArrayList<TreeNode<String>>();
//		rootList.add(root);
//		m_treeByDepth.put(0, rootList);
//		for(int i = 1; i < depth; i++)
//		{
//			List<TreeNode<String>> depthList = new ArrayList<TreeNode<String>>();
//			m_treeByDepth.put(i, depthList);
//		}
//
//		buildDepthLMap(root, m_treeByDepth, 1, depth);
//
//		int size = m_treeByDepth.size();
//		center = maxWidth / 2;
//		int leftStart = center - root.element.length() / 2;
//		String leftPad = generatePad(leftStart);
//		StringBuilder builder = new StringBuilder();
//		trace("maxwdith: " + maxWidth);
//		for(int index = 1; index < size; index++)
//		{
//			int power = index -1;
//			int gapCount = (int)Math.pow(2, power)+1;
//			
//			List<TreeNode<String>> depthList = m_treeByDepth.get(index);
//			if(depthList != null)
//			{
//				int listSize = depthList.size();
//				int gapWidth =  maxWidth / gapCount;//(listSize+1);
//				trace(String.format("depth: %d, gap width: %d ",index, gapWidth));
//				leftPad = generatePad(gapWidth);
//				for(int i = 0; i < listSize; i++)
//				{
//					TreeNode<String> node = depthList.get(i);
//					String name = node.element;
//					builder.append(leftPad + name);
//					leftPad = generatePad(gapWidth);
//				}
//
//				builder.append(System.lineSeparator());
//			}
//		}
//
//		trace(builder.toString());
//	}

	private void buildDepthLMap(TreeNode<String> node, Map<Integer, List<TreeNode<String>>> map, int depth, int maxDepth)
	{
		List<TreeNode<String>> depthList = map.get(depth);
		if(node == null)
		{
			if(depthList != null)
			{
				TreeNode<String> emptyNode = new TreeNode<String>("e");
				depthList.add(emptyNode);

				if(depth <= maxDepth)
				{
					buildDepthLMap(emptyNode.left, map, depth + 1, maxDepth);
					buildDepthLMap(emptyNode.right, map, depth + 1, maxDepth);
				}
			}
			return;
		}

		depthList = map.get(depth);
		depthList.add(node);

		buildDepthLMap(node.left, map, depth + 1, maxDepth);
		buildDepthLMap(node.right, map, depth + 1, maxDepth);
	}

	private void walkPrettyPrint(TreeNode<String> node, int depth, int center)
	{
		if(node == null)
		{
			return;
		}
		String name = node.getElement().toString();
		int nameLen = name.length();
		int halfName = nameLen / 2;
		int nodeCount = 2 ^ depth;
		int nCount = depth << 1;
		// int spaceCenter = (center / nCount);
		int leftStart = center - halfName;
		int rightStart = center + halfName;

		StringBuilder builder = new StringBuilder();
		// for(int index = 0; index < nCount; index++)
		// {
		TreeNode<String> left = node.left;
		TreeNode<String> right = node.right;

		if(left != null)
		{
			leftStart = leftStart - left.getElement().toString().length();
			builder.append(generatePad(leftStart) + left.getElement());
		}

		if(right != null)
		{
			// rightStart = rightStart + right.getElement().toString().length();
			int newPad = rightStart - leftStart;
			builder.append(generatePad(newPad) + right.getElement());
		}

		if(left != null || right != null)
		{
			trace(builder.toString());
		}
		// leftStart = index+(8*index);
		// rightStart = index+(8*index);
		walkPrettyPrint(left, depth + 1, leftStart);
		walkPrettyPrint(right, depth + 1, rightStart);

		// }

	}

	private String generatePad(int spaces)
	{
		StringBuilder pad = new StringBuilder();
		for(int i = 0; i < spaces; i++)
		{
			pad.append(" ");
		}

		return pad.toString();
	}

	private void validateList(List<String> leaves)
	{
		assertEquals(
				"Size mismatch in leaves: expected " + expectedLeaves.length
						+ " actual: " + leaves.size(),
				expectedLeaves.length, leaves.size());
		for(String name : expectedLeaves)
		{
			if(!leaves.contains(name))
			{
				trace(name + " not found in leaves list");
				fail(name + " not found in leaves list");
			}
		}
	}

	private void trace(String msg)
	{
		System.out.println(msg);
	}

	private static String[] expectedLeaves = { "Bruce", "David", "James",
			"June", "Nick", "Peter", "Vanessa" };

	private Map<Integer, List<TreeNode<String>>> m_treeByDepth;
}
