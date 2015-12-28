package binaryTree;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTree<T extends Comparable<T>> {
	private BNode<T> root;
	private boolean found; // used by remove methods

	public BinaryTree() {
		this.root = null; // empty tree

	}

	public boolean isEmpty() {
		if (this.root == null) {
			return true;
		}
		return false;
	}

	public boolean insert(T data) {
		BNode<T> curr = this.root;
		BNode<T> parent = this.root;
		BNode<T> newNode;
		// iterative approach to inserting data
		if (this.root == null) {
			// nothing in tree yet
			this.root = new BNode<T>(data);

		} else {
			while (curr != null) {
				if (data.compareTo(curr.getData()) < 0) {
					// data is less than data in current Node
					// go down left branch
					parent = curr;
					curr = curr.getLC();

				} else if (data.compareTo(curr.getData()) > 0) {
					// data is greater than data in current Node
					// go down right branch
					parent = curr;
					curr = curr.getRC();

				} else
					return false; // duplicate value

			}
			// found the right place
			newNode = new BNode<T>(data);
			// should it be a left child or a right child?
			if (newNode.compareTo(parent) < 0) {
				parent.setLC(newNode);

			} else
				parent.setRC(newNode);

		}
		return true;

	}

	public T getRoot() {
		return this.root.getData();
	}

	public ArrayList<T> traverse(ArrayList<T> array) {
		Stack<BNode<T>> stack = new Stack<BNode<T>>();
		BNode<T> tree; // sub tree of the binary tree
		if (this.root == null)
			return null;
		tree = this.root;
		stack.push(tree);
		while (tree != null) {
			tree = tree.getLC();
			while (tree != null) {
				stack.push(tree);
				tree = tree.getLC();
			}
			if (!stack.empty()) {
				tree = stack.pop();
				array.add(tree.getData());
			}

			tree = tree.getRC();
			if (tree != null)
				stack.push(tree);
			else {
				while (!stack.empty() && tree == null) {
					tree = stack.pop();
					array.add(tree.getData());
					tree = tree.getRC();

				}
				if (tree != null)
					stack.push(tree);
			}

		}
		return array;
	}

	// recursive insert method
	public boolean insertRecur(T data) {
		BNode<T> tree = this.root;
		if (this.root == null) {
			this.root = new BNode<T>(data);
			return true;
		} else {
			return insertTryAgain(tree, data);
		}
	}

	private boolean insertTryAgain(BNode<T> root, T data) {
		if (data.compareTo(root.getData()) < 0) {
			// this data value belongs in left branch
			if (root.getLC() == null) {
				// left child is empty, insert data right there
				root.setLC(new BNode<T>(data));
				return true;
			} else {// find next available spot along left branch
				return insertTryAgain(root.getLC(), data);

			}
		} else

		if (data.compareTo(root.getData()) > 0) {
			// this data value belongs in right branch
			if (root.getRC() == null) {
				root.setRC(new BNode<T>(data));
				return true;

			} else { // find next available spot along right branch
				return insertTryAgain(root.getRC(), data);

			}

		}

		else
			return false; // duplicate value

	}

	public boolean removeVal(T value) {
		// to remove a value must start searching for it at the root
		root = removeNode(value, root);
		return found; // return value in instance variable set by private method
	}

	private BNode<T> removeNode(T value, BNode<T> tree) {
		// looks for value in the subtree
		if (tree == null)
			found = false;
		else if (value.compareTo(tree.getData()) < 0)
			// recursive call further down the left side of tree
			// might have to reset links if a node further down
			// is set to null
			tree.setLC(removeNode(value, tree.getLC()));
		else if (value.compareTo(tree.getData()) > 0)
			// recursive call further down the right side of the tree
			// might have to reset links if a node further down
			// is set to null
			tree.setRC(removeNode(value, tree.getRC()));
		else { // found the value , now remove that data from
				// the tree
			tree = removeData(tree);
			found = true;

		}
		return tree;
	}

	private BNode<T> removeData(BNode<T> tree) {
		// case 1 and 2: subtree just has one child branch so return that
		// branch and link that branch to previous
		// part of tree, basically eliminating the BNode
		// in which the data was found
		if (tree.getLC() == null)
			return tree.getRC();
		else if (tree.getRC() == null)
			return tree.getLC();
		else { // data is in a BNode that has two children.
				// It is too complicated to remove this type of Node
				// Instead do the following:
				// a. Replace the data in that BNode with data that
				// logically precedes that data - this data will be found in
				// a leaf BNode
				// b. eliminate the leaf BNode by reinvoking the
				// removeNode() method
			T data = findPredecessor(tree.getLC());
			tree.setData(data);
			tree.setLC(removeNode(data, tree.getLC()));
			return tree;

		}

	}

	private T findPredecessor(BNode<T> tree) {
		// the Node that contains data that precedes a Node
		// can be found by going down till one hits the right most leaf
		// of its left branch
		while (tree.getRC() != null) {
			tree = tree.getRC();

		}
		return tree.getData();
	}

	// recursive traversals
	public void traversePreOrder() {
		System.out.println(this.root.getData());
		traverseP(this.root.getLC());
		traverseP(this.root.getRC());

	}

	private void traverseP(BNode<T> root) {
		if (root == null)
			return; // anchor case
		System.out.println(root.getData());
		traverseP(root.getLC());
		traverseP(root.getRC());

	}

	public ArrayList<T> traverseInOrder(ArrayList<T> array) {
		array = traverseI(this.root.getLC(), array);
		array.add(this.root.getData());
		array = traverseI(this.root.getRC(), array);
		return array;
	}

	private ArrayList<T> traverseI(BNode<T> cRoot, ArrayList<T> array) {
		if (cRoot == null)
			return array; // anchor case
		array = traverseI(cRoot.getLC(), array);
		array.add(cRoot.getData());
		array = traverseI(cRoot.getRC(), array);
		return array;
	}

	public boolean get(T data) {
		return check(this.root, data);
	}

	private boolean check(BNode<T> cRoot, T data) {
		if (cRoot == null) {
			return false;
		}
		if (cRoot.getData().equals(data)) {
			return true;
		}
		boolean found = check(cRoot.getLC(), data);
		if (found == true) {
			return true;
		}
		found = check(cRoot.getRC(), data);
		if (found == true) {
			return true;
		}
		return false;
	}

	public void reorganizeR() {
		ArrayList<T> array = new ArrayList<T>();
		array = traverseInOrder(array);
		T mid = array.remove(array.size() / 2);
		BNode<T> newRoot = new BNode<T>(mid);
		this.root = newRoot;
		for (int i = 0; i < array.size(); i++) {
			insertRecur(array.get(i));
		}
	}

	public void reorganize() {
		ArrayList<T> array = new ArrayList<T>();
		array = traverse(array);
		T mid = array.remove(array.size() / 2);
		BNode<T> newRoot = new BNode<T>(mid);
		this.root = newRoot;
		for (int i = 0; i < array.size(); i++) {
			insert(array.get(i));
		}
	}
}
