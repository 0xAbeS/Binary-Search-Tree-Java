package binarySearchTree;

public class Main {
public static void main(String[]args) {
	/*
	BST x = new BST();
	x.insert(4);
	x.insert(2);
	x.insert(5);
	x.insert(1);
	x.insert(3);
	
	
	x.printTree();
	x.rightRotate(x.root);
	x.printTree();
	x.leftRotate(x.root);
	x.printTree();

	*/
	
	BST t = new BST();
	t.insert(6);
	t.insert(-2);
	t.insert(9);
	t.insert(10);
	t.insert(-10);
	t.insert(4);
	t.insert(-3);
	t.insert(6);
	t.insert(-6);
	t.insert(7);
	t.insert(-16);
	t.insert(8);
	t.insert(2);
	t.insert(1);
	t.insert(11);
	t.insert(8);
	t.insert(-5);
	t.insert(5);
	t.insert(3);
	
	System.out.println(t.isBSTOrNot());
	t.toStringInOrder();
	System.out.println(t.toString());
	t.printTree();
	t.deleteNode(t.root, 8);
	t.deleteNode(t.root, 7);
	//System.out.print(t.search(-2));
	
	System.out.println(t.isBSTOrNot());
	System.out.println(t.toString());
	t.toStringInOrder();
	t.printTree();

	
	/*

	//Check remove function
	//tree.toStringInOrder(); // Should print tree in order going from left to right.
	tree.printTree();
	tree.remove(4);
	tree.printTree();
	System.out.println("");
	
	
	// Check search function
	System.out.println("");
	System.out.println(tree.search(5)); //Should return true
	System.out.println(tree.search(0));	//Should return false
	System.out.println(tree.search(1));	//Should return true
	System.out.println(tree.search(4));	//Should return false
*/
}

}
