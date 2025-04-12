package binarySearchTree;

class BST {

	Node root;

	public BST() {
	}

	public void insert(int key) {
		if (root == null)
			root = new Node(key);
		else {
			insert(root, key);
		}
	}

	private Node insert(Node node, int key) {

		// REGULAR INSETION
		if (node == null)
			return (new Node(key));

		if (key < node.key)
			node.left = insert(node.left, key);
		else if (key > node.key)
			node.right = insert(node.right, key);
		else // Duplicate keys not allowed
			return node;

		// BALANCING
		// Update node height
		node.height = 1 + max(height(node.left), height(node.right));

		// Gets the balance factor
		int balance = balance(node);

		// Left-Left case
		if (balance > 1 && key < node.left.key)
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && key > node.right.key)
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && key > node.left.key) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && key < node.right.key) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		return node;
	}

	// Calls on search
	public boolean search(int key) {
		return search(key, root);
	}
	
	private boolean search(int key, Node node) {
		if (node == null)
			return false;
		if (node.key > key)
			return search(key, node.left);
		if (node.key < key)
			return search(key, node.right);
		return true;
	}
	
	Node deleteNode(Node root, int item) {

	    // Find the node to be deleted and remove it
	    if (root == null)
	      return root;
	    if (item < root.key)
	      root.left = deleteNode(root.left, item);
	    else if (item > root.key)
	      root.right = deleteNode(root.right, item);
	    else {
	      if ((root.left == null) || (root.right == null)) {
	        Node temp = null;
	        if (temp == root.left)
	          temp = root.right;
	        else
	          temp = root.left;
	        if (temp == null) {
	          temp = root;
	          root = null;
	        } else
	          root = temp;
	      } else {
	        Node temp = getLast(root.right);
	        root.key = temp.key;
	        root.right = deleteNode(root.right, temp.key);
	      }
	    }
	    if (root == null)
	      return root;

	    // Update the balance factor of each node and balance the tree
	    root.height = max(height(root.left), height(root.right)) + 1;
	    int balanceFactor = balance(root);
	    if (balanceFactor > 1) {
	      if (balance(root.left) >= 0) {
	        return rightRotate(root);
	      } else {
	        root.left = leftRotate(root.left);
	        return rightRotate(root);
	      }
	    }
	    if (balanceFactor < -1) {
	      if (balance(root.right) <= 0) {
	        return leftRotate(root);
	      } else {
	        root.right = rightRotate(root.right);
	        return leftRotate(root);
	      }
	    }
	    return root;
	  }
	

	public void remove(int key) {
		remove(root, key);
	}

	private Node remove(Node node, int key) {
		if (node == null)
			return null;
		if (key < node.key) {
			node.left = remove(node.left, key);
		} else if (key > node.key) {
			node.right = remove(node.right, key);
		} else {
			if (node.left == null || node.right == null) {
				Node temp = null;
				if (node.left == temp)
					temp = node.right;
				else
					temp = node.left;
				if (temp == null) {
					temp = root;
					root = null;
					}
				else
					return temp;
			} else {
				Node last = getLast(node);
				node.key = last.key;
				node.right = remove(root, last.key);
			}
		}

		if (node == null)
			return node;

		// BALANCING
		// Update node height
		node.height = max(height(node.left), height(node.right)) + 1;

		// Get the balance factor
		int balance = balance(node);

		// Left Left Case
		if (balance > 1 && balance(node.left) >= 0)
			return rightRotate(node);

		// Left Right Case
		if (balance > 1 && balance(node.left) < 0) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Right Case
		if (balance < -1 && balance(node.right) <= 0)
			return leftRotate(node);

		// Right Left Case
		if (balance < -1 && balance(node.right) > 0) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		return node;
	}

	private Node getLast(Node node) {

		Node temp = node.right;
		while (temp.left != null)
			temp = temp.left;

		return temp;
	}

	public Node rightRotate(Node node) {
		// Pointers so we garbage collector doesn't remove nodes
		Node left = node.left;
		Node lRight = left.right;
		// Does the rotation
		left.right = node;
		node.left = lRight;
		// Updates height, don't know how ask www.programiz.com
		node.height = max(height(node.left), height(node.right)) + 1;
		left.height = max(height(left.left), height(left.right)) + 1;
		// Returns the new parent
		return left;
	}

	public Node leftRotate(Node node) {
		// Pointers so we garbage collector doesn't remove nodes
		Node right = node.right;
		Node rLeft = right.left;
		// Does the rotation
		right.left = node;
		node.right = rLeft;
		// Updates height, don't know how ask www.programiz.com
		node.height = max(height(node.left), height(node.right)) + 1;
		right.height = max(height(right.left), height(right.right)) + 1;
		// Returns the new root
		return right;
	}


	int max(int x, int y) {
		if (x > y)
			return x;
		else
			return y;
	}

	public int height(Node node) {
		if (node == null)
			return 0;

		int nodeR = height(node.right);
		int nodeL = height(node.left);

		if (nodeR > nodeL)
			return nodeR + 1;
		else
			return nodeL + 1;
	}


	private int balance(Node node) {
		if (node == null)
			return 0;

		return height(node.left) - height(node.right);
	}

	public Node rebalance(Node node) {
		int balance = balance(node);

		if (balance > 2) {
			if (balance(node.right) >= 0) {
				node = leftRotate(node);
			} else {
				node.right = rightRotate(node.right);
				node = leftRotate(node);
			}
		}

		return node;
	}

	// Calls inOrder
	// Prints tree in order of left to right
	public void toStringInOrder() {
		inOrder(root);
	}

	// Need at least one node
	// Print out node in order from left to right
	private void inOrder(Node node) {
		if (node == null)
			return;
		inOrder(node.left);
		System.out.print(" " + node.key);
		inOrder(node.right);
	}

	public String toString() {
		if (root == null)
			return "";
		else {
			String p1 = (root.left == null ? "" : Integer.toString(root.left.key));
			String p2 = (root.right == null ? "" : Integer.toString(root.right.key));
			return root.key + p1 + p2 + strHelper(root.left) + strHelper(root.right);
		}

	}

	private String strHelper(Node n) {

		if (n == null)
			return "";
		String p1 = (n.left == null ? "" : Integer.toString(n.left.key));
		String p2 = (n.right == null ? "" : Integer.toString(n.right.key));
		return p1 + p2 + strHelper(n.left) + strHelper(n.right);

	}

// Code to verify your tree integrity
	public boolean isBSTOrNot() {
		return isBSTOrNot(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBSTOrNot(Node root, int minValue, int maxValue) {
// check for root is not null or not
		if (root == null) {
			return true;
		}
// check for current node value with left node value and right node value and
// recursively check for left sub tree and right sub tree
		if (root.key >= minValue && root.key <= maxValue && isBSTOrNot(root.left, minValue, root.key)
				&& isBSTOrNot(root.right, root.key, maxValue)) {
			return true;
		}
		return false;
	}

	public static void showTrunks(Trunk p) {
		if (p == null) {
			return;
		}

		showTrunks(p.prev);
		System.out.print(p.str);
	}

	public void printTree() {
		printTree(root, null, false);
	}

	private void printTree(Node root, Trunk prev, boolean isLeft) {
		if (root == null) {
			return;
		}

		String prev_str = "    ";
		Trunk trunk = new Trunk(prev, prev_str);

		printTree(root.right, trunk, true);

		if (prev == null) {
			trunk.str = "———";
		} else if (isLeft) {
			trunk.str = ".———";
			prev_str = "   |";
		} else {
			trunk.str = "`———";
			prev.str = prev_str;
		}

		showTrunks(trunk);
		System.out.println(" " + root.key + "(" + height(root) + ")" + balance(root));

		if (prev != null) {
			prev.str = prev_str;
		}
		trunk.str = "   |";

		printTree(root.left, trunk, false);
	}

// this goes into it's own file
	class Trunk {
		Trunk prev;
		String str;

		Trunk(Trunk prev, String str) {
			this.prev = prev;
			this.str = str;
		}
	};

}