/*  Author: B. Liam Rethore
    Originally  Written: 10/22/2019 
    Last Edited On: 10/27/2019

    Project 2 - CS1501
    
    Description:
    This is a node class confusing called BinaryTree becasue that 
    is how we were told to name it. It can create a node object and 
    evalute the cost of a set of nodes. It also implements a toString 
    method to make printing this object easier.
*/

import java.util.*;

public class BinaryTree { 
    public String _key;
    public int _value;
    public BinaryTree _left;
    public BinaryTree _right;

    /**
    * Node constructor used to initialize 
    * the fields of the node. All children
    * are intialized to null to begin with.
    */
    public BinaryTree(String key, int value) {
        this._key = key;
        this._value = value;
        _left = null;
        _right = null;
    }

    /**
    * Evaluates the weighted internal path length for a given 
    * root node. The equation that is implemnted here can be descibed by
    * cost = (sum of frequncies * depth) + (cost of left subtree) + (cost of right subtree).
    * NOTE: Can be called with no inputs becasue it is an 
    * overloaded function.
    */
    public int cost() {
        return cost(this, 1); // Set root to given node and level to 1
    }
    private int cost(BinaryTree node , int level) {
        int cost = 0;
        
        if (node == null) // Base case
            return 0;

        int costLeft = cost(node._left, level + 1); // Solve left subtree's cost
        int costRight = cost(node._right, level + 1); // Solve right subtree's cost

        cost = (level * node._value) + costRight + costLeft; // Equation to find cost
        
        return cost;
    }

    /**
    * Returns a string of a binary tree organized nicely.
    * NOTE: Can be called with no inputs becasue it is an 
    * overloaded function.
    */
    public String toString() {
        return toString(this); // Set root node for string generation
    }
	private String toString(BinaryTree node) {
        
        if (node == null)
            return "null"; // no nodes left here
        if (node._left == null && node._right == null)
            return node._key + "(" + node._value + ")" + " {{null} {null}}"; // no children left
        if (node._right == null)
            return node._key + "(" + node._value + ")" + " {" + toString(node._left) + "}";

        return node._key + "(" + node._value + ")" + " {" + toString(node._left) + "} {" + toString(node._right) + "}";   
    }
}

// End of File

   