
public class Homework6 {
	public static void main(String[] args)  {
		System.out.println("Tree One:");
		BinaryTree<Integer> tree1 = new BinaryTree<Integer>();
		tree1.insertRoot(1);
		tree1.getRoot().insertLeft(2).insertLeft(4).insertLeft(7);
		tree1.getRoot().insertRight(3).insertRight(6).insertRight(8).insertRight(9);
		tree1.getRoot().getRight().insertLeft(5);
		//count leaves
		System.out.println(countLeaves(tree1));
		//count non leaves
		System.out.println(countNonLeaves(tree1));
		//get height
		System.out.println(getHeight(tree1));
		//print the three orders
		printPreOrder(tree1);
		printInOrder(tree1);
		printPostOrder(tree1);
		//remove leaves and reprint
		System.out.println("After Leaf Removal: ");
		removeLeaves(tree1);
		printPreOrder(tree1);
		printInOrder(tree1);
		printPostOrder(tree1);
		//Repeat with tree two
		System.out.println("Tree Two:");
		BinaryTree<Integer> tree2 = new BinaryTree<Integer>();
		tree2.insertRoot(6);
		tree2.getRoot().insertLeft(4).insertLeft(2).insertLeft(1);
		tree2.getRoot().getLeft().insertRight(5);
		tree2.getRoot().getLeft().getLeft().insertRight(3);
		tree2.getRoot().insertRight(8).insertRight(9);
		tree2.getRoot().getRight().insertLeft(7);
		System.out.println(countLeaves(tree2));
		System.out.println(countNonLeaves(tree2));
		System.out.println(getHeight(tree2));
		printPreOrder(tree2);
		printInOrder(tree2);
		printPostOrder(tree2);
		System.out.println("After Leaf Removal: ");
		removeLeaves(tree2);
		printPreOrder(tree2);
		printInOrder(tree2);
		printPostOrder(tree2);
	}
	
	public static int countLeaves (BinaryTree tree) {
		BinaryTree.Node node = tree.getRoot();
		return countLeaves(node);
	}
	//count the leave node.
	private static int countLeaves (BinaryTree.Node node) {
		if(node == null) {
			return 0;
			}
		if(node.isLeaf()) {
			return 1;
			} 
		else {
			return countLeaves(node.getLeft()) + countLeaves(node.getRight());
		}
		}
	
	public static int countNonLeaves(BinaryTree tree) {
		BinaryTree.Node node = tree.getRoot();
		return countNonLeaves(node);
	}
	// count the non-leaves nodes. 
	private static int countNonLeaves (BinaryTree.Node node) {
		if(node == null || node.isLeaf()) {
			return 0;
		}
		else {
			return countNonLeaves(node.getLeft()) + countNonLeaves(node.getRight()) + 1;
		}
	}
	public static int getHeight (BinaryTree tree) {
		BinaryTree.Node node = tree.getRoot();
		return getHeight(node);
	}
	//to count the height of the tree.
	private static int getHeight (BinaryTree.Node node) {
		if(node == null) {
			return 0;
		}
		else {
			return Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1;
		}
	}
	public static void printPreOrder (BinaryTree tree) {
		BinaryTree.Node node = tree.getRoot();
		printPreOrder(node);
		System.out.println();
	}
	//print the elements of the tree using a pre-order traversal.
	private static void printPreOrder (BinaryTree.Node node) {
		if(node == null) {
			return ;
		}
		System.out.printf("%s ", node.getData());
		printPreOrder(node.getLeft());
		printPreOrder(node.getRight());
	}
	
	
	public static void printInOrder (BinaryTree tree) {
		BinaryTree.Node node = tree.getRoot();
		printInOrder(node);
		System.out.println();
	}
	// Prints the elements of the tree using an in-order traversal. 
	private static void printInOrder (BinaryTree.Node node) {
		if(node == null) {
			return ;
		}
		printInOrder(node.getLeft());
		System.out.printf("%s ", node.getData());
		printInOrder(node.getRight());
	}
	
	public static void printPostOrder (BinaryTree tree) {
		BinaryTree.Node node = tree.getRoot();
		printPostOrder(node);
		System.out.println();
	}
	//Prints the elements of the tree using a post-order traversal.  
	private static void printPostOrder (BinaryTree.Node node) {
		if(node == null) {
			return ;
		}
		printPostOrder(node.getLeft());
		printPostOrder(node.getRight());
		System.out.printf("%s ", node.getData());
	}
	
	public static void removeLeaves (BinaryTree tree) {
		BinaryTree.Node node = tree.getRoot();
		removeLeaves(node);
	}
	//Removes all leaf nodes from the tree. 
	private static void removeLeaves (BinaryTree.Node node) {
		if(node == null) {
			return;
		}
		else {
			if(node.getLeft() != null && node.getLeft().isLeaf()) {
				node.removeLeft();
			}
			if(node.getRight() != null && node.getRight().isLeaf()) {
				node.removeRight();
			}
			if(node.getLeft() != null) {
				removeLeaves(node.getLeft());
			}
			if(node.getRight() != null) {
				removeLeaves(node.getRight());
			}
		}
	}
}



class BinaryTree<E> {
    public class Node {
        E data;
        Node left;
        Node right;

        //Only Allow BinaryTree to create Nodes
        private Node(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public boolean hasLeft() {
            return left != null;
        }

        public boolean hasRight() {
            return right != null;
        }

        public boolean isLeaf() {
            return !hasLeft() && !hasRight();
        }

        public Node insertLeft (E data) throws IllegalStateException{
            if(left != null) {
                throw new IllegalStateException(
                        "Cannot insert because left child already exists");
            }

            left = new Node(data);
            ++size;

            return left;
        }

        public Node insertRight (E data) throws IllegalStateException{
            if(right != null) {
                throw new IllegalStateException(
                        "Cannot insert because right child already exists");
            }

            right = new Node(data);
            ++size;

            return right;
        }

        public E removeLeft() throws IllegalStateException {
            if (left == null) {
                throw new IllegalStateException(
                        "Cannot remove because left child does not exist");
            }
            if (!left.isLeaf()) {
                throw new IllegalStateException(
                        "Cannot remove because left child is not a leaf");
            }
            E data = left.data;
            left = null;
            --size;

            return data;
        }

        public E removeRight() throws IllegalStateException {
            if (right == null) {
                throw new IllegalStateException(
                        "Cannot remove because right child does not exist");
            }
            if (!right.isLeaf()) {
                throw new IllegalStateException(
                        "Cannot remove because right child is not a leaf");
            }
            E data = right.data;
            right = null;
            --size;

            return data;
        }

    }

    private int size;
    private Node root;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node getRoot() {
        return root;
    }

    public Node insertRoot (E data) throws IllegalStateException {
        if (!isEmpty()) {
            throw new IllegalStateException(
                    "Cannot insert root into non-empty tree");
        }

        root = new Node (data);
        ++size;

        return root;
    }
    public E removeRoot() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException(
                    "Cannot remove root of empty tree");
        }

        if(!root.isLeaf()) {
            throw new IllegalStateException(
                    "Cannot remove because root is not a leaf");
        }

        E data = root.data;
        root = null;
        --size;

        return data;
    }

    public BinaryTree<E> merge(BinaryTree<E> other, E data){
        BinaryTree<E> merged = new BinaryTree<E> ();

        // Merge this and other into new tree with data as root
        merged.root = new Node(data);
        merged.root.left = this.root;
        merged.root.right = other.root;
        merged.size = 1 + this.size + other.size;

        //Remove all nodes from this and other
        this.root = null;
        this.size = 0;
        other.root = null;
        other.size = 0;

        return merged;
    }
}
