/*  Author: B. Liam Rethore
    Originally Written: 10/22/2019 
    Last Edited On: 11/05/2019

    Project 2 - CS1501

    Description:
    This is a class to optimize a BST. It can add keys to
    an array that will be used to generate an optimal binary tree.
    It can also optimize a binary tree givening the user the most
    effcient orientation of a binary tree. 
*/

// PATH cd Documents\2019-2020 School Year\CS 1501\Project 2
import java.util.*;

public class BSTOptimizer {
    private BinaryTree[] keys = new BinaryTree[100];
    private int numKeys = 0;
    private static BinaryTree[][] memo;

    public boolean MEMOIZE = false;
    public int CALLS = 0;

    /**
    * Constuctor for this method, but
    * there are no values intialized here
    * so it does absolutley nothing.
	*/
	public BSTOptimizer() { 
    }

    /**
    * Adds a key to an array of key values
    * that will be used in order to generate an 
    * optimal binary tree.
	*/
	public void addKey(String key, int frequency) {
        BinaryTree newNode = new BinaryTree(key, frequency);

        for (int i = 0; i < 100; i++) {
            if (keys[i] == null) {
                keys[i] = newNode;
                numKeys++;
                break;
            }
        }
	}

    /**
    * Fills a given array of object type BinaryTree
    * with null as an initilzation. 
    */
    private BinaryTree[][] fillNull(BinaryTree[][] memo) {

        for (int i = 0; i < numKeys + 1; i++) {
            for (int j = 0; j < numKeys + 1; j++) {
                memo[i][j] = null;
                //System.out.printf("memo[%d][%d] = %d\n", i, j, memo[i][j]);
            }
        }
        return memo;
    }

    /**
    * Given the size of a keys array (i as first value and j as last),
    * this method with return an optimal BST.
    * It does this by iterating over the keys array to try each indivual
    * key as the root node. Then the tree that has the lowest cost
    * is returned. NOTE: Can be called with no inputs becasue it is an 
    * overloaded function.
    */
    public BinaryTree optimize() {
        memo = new BinaryTree[numKeys + 1][numKeys + 1];
        memo = fillNull(memo);
        return optimize(0, numKeys - 1); // 0 is the starting address numkeys -1 is the ending one
    }
	private BinaryTree optimize(int i, int j) { 
        BinaryTree optimalBST = null;
        int currCost = 0;

        CALLS++; // Counts number of recursive calls made

        if (i > j) // Base case
            return null;

        if (MEMOIZE) { // Check to see if this order of keys has already been tried to save time
            if (memo[i][j] != null)
                return memo[i][j]; 
        }

        for (int k = i; k <= j; k++) {
            BinaryTree newTree = new BinaryTree(keys[k]._key, keys[k]._value);

            BinaryTree leftTree = optimize(i, k - 1); // Find the left subtree
            BinaryTree rightTree = optimize(k + 1, j); // Find the right subtree

            newTree._left = leftTree; 
            newTree._right = rightTree;
            
            currCost = newTree.cost(); // Find for cost of the newtree
            if (optimalBST == null) 
                optimalBST = newTree; // This happens for the root
            else  if (optimalBST.cost() > currCost) 
                optimalBST = newTree; // This happens for all the children
        }

        if (MEMOIZE)
            memo[i][j] = optimalBST; // Fill in new value to memory for later use
            
        return optimalBST;
    }
}

// End of File
