/**
 * A class to represent a binary search tree.
 *
 * @param <E> The element type
 * @author
 */
public class BinarySearchTree<E extends Comparable<E>> // add comparable later
		extends BinaryTree<E> implements SearchTree<E> {
	// Data Fields

	/**
	 * Return value from the public add method.
	 */
	protected boolean addReturn;
	/**
	 * Return value from the public delete method.
	 */
	protected E deleteReturn;

	// Methods
	/**
	 * Starter method find.
	 *
	 * @param target The Comparable object being sought
	 * @return The object, if found, otherwise null
	 */
	@Override
	public E find(E target) {
		return find(root, target);
	}

	/**
	 * Recursive find method.
	 *
	 * @param localRoot The local subtrees root
	 * @param target    The object being sought
	 * @return The object, if found, otherwise null
	 */
	private E find(Node<E> localRoot, E target) {

		// if the localRoot is null, or not found
		if (localRoot == null)
			return null;

		// Compare the target with the data field at the root.
		// you cannot use < or > or = as the type of the data is E
		// think about what you can do?
		int result = target.compareTo(localRoot.data);

		// if target equal to localRoot.data then return localRoot.data
		if (result == 0)
			return localRoot.data; // found
		// if target less than local root data, then find from left subtree
		else if (result < 0)
			return find(localRoot.left, target);
		// if target greater than local root data, then find from right subtree
		else {
			return find(localRoot.left, target);
		}
	}

	/**
	 * Starter method add.
	 *
	 * @param item The object being inserted
	 * @return true if the object is inserted, false if the object already exists in
	 *         the tree
	 */
	@Override
	public boolean add(E item) {
		root = add(root, item);
		return addReturn;
	}

	/**
	 * Recursive add method.
	 *
	 * @post The data field addReturn is set true if the item is added to the tree,
	 *       false if the item is already in the tree.
	 * @param localRiof the subtree
	 * @param item      The object to be inserted
	 * @return The new local root that now contains the inserted item
	 */
	private Node<E> add(Node<E> localRoot, E item) {

		// item is not in the tree insert it.return a new node
		// int result = item.compareTo(localRoot.data);

		if (localRoot == null) {
			addReturn = true;
			return new Node<E>(item);
		}
		// item is equal to localRoot.data

		else if (item.compareTo(localRoot.data) == 0) {
			addReturn = false;
			return localRoot;
		}
		// item is less than localRoot.data add to the left subtree

		else if (item.compareTo(localRoot.data) < 0) {
			localRoot.left = add(localRoot.left, item);
			return localRoot;
		}
		// item is greater than localRoot.data add to the right subtree
		else {

			localRoot.right = add(localRoot.right, item);
			return localRoot;
		}

	}

	/**
	 * Starter method delete.
	 *
	 * @post The object is not in the tree.
	 * @param target The object to be deleted
	 * @return The object deleted from the tree or null if the object was not in the
	 *         tree
	 */
	@Override
	public E delete(E target) {
		root = delete(root, target);
		return deleteReturn;
	}

	/**
	 * Recursive delete method.
	 *
	 * @post The item is not in the tree; deleteReturn is equal to the deleted item
	 *       as it was stored in the tree or null if the item was not found.
	 * @param localRoot The root of the current subtree
	 * @param item      The item to be deleted
	 * @return The modified local root that does not contain the item
	 */
	private Node<E> delete(Node<E> localRoot, E item) {

		// 1. item is not in the tree.
		if (localRoot == null) {
			deleteReturn = null;
			return localRoot;
		}

		// 2. Search for item to delete.
		int compResult = item.compareTo(localRoot.data);

		// 2.1 item is smaller than localRoot.data.
		if (compResult < 0) { // delete from leftSubTree
			localRoot.left = delete(localRoot.left, item);
			return localRoot;
		}

		// 2.2 item is larger than localRoot.data.
		else if (compResult > 0) { // delete from right subtree
			localRoot.right = delete(localRoot.right, item);
			return localRoot;
		}

		// 2.3 item is at local root.

		else {
			deleteReturn = localRoot.data;

			// 2.3.1 localRoot has one child

			// 2.3.1.1 If there is no left child, return right child
			// which can also be null.
			if (localRoot.left == null) {
				return localRoot.right;
			}

			// 2.3.1.2 If there is no right child, return left child.
			else if (localRoot.right == null) {
				return localRoot.left;
			}

			// 2.3.2 Node being deleted has 2 children, replace the data
			// with inorder predecessor.
			else {
				
				// 2.3.2.1 The left child has no right child.
				// Replace the data with the data in the
				// left child.
				if (localRoot.left.right == null) {
					localRoot.data = localRoot.left.data;

					// Replace the left child with its left child.
					localRoot.left = localRoot.left.left;
					return localRoot;
				}
				
				// 2.3.2.2 Search for the inorder predecessor (ip) and
				// replace deleted node's data with ip.
				else {
					localRoot.data = findLargestChild(localRoot.left);
					return localRoot;
				}
			}
		}
	}

	/**
	 * Removes target from tree.
	 *
	 * @param target Item to be removed
	 * @return true if the object was in the tree, false otherwise
	 * @post target is not in the tree
	 */
	@Override
	public boolean remove(E target) {
		return delete(target) != null;
	}

	/**
	 * Determine if an item is in the tree
	 *
	 * @param target Item being sought in tree
	 * @return true If the item is in the tree, false otherwise
	 * @throws ClassCastException if target is not Comparable
	 */
	@Override
	public boolean contains(E target) {
		return find(target) != null;
	}

	/**
	 * Find the node that is the in-order predecessor and replace it with its left
	 * child (if any).
	 *
	 * @post The in-order predecessor is removed from the tree.
	 * @param parent The parent of possible in-order predecessor (ip)
	 * @return The data in the ip
	 */
	private E findLargestChild(Node<E> parent) {
		// If the right child has no right child, it is
		// the inorder predecessor.
		if (parent.right.right == null) {
			E returnValue = parent.right.data;
			parent.right = parent.right.left;
			return returnValue;
		} else {
			return findLargestChild(parent.right);
		}
	}

	/**
	 * Please write another delete method, called deletePrime. Starter method
	 * deletePrime is as follows. deletePrime is the same as delete except that when
	 * a value is deleted that has two children the in-order successor replaces it
	 * in the tree.
	 *
	 * @post The object is not in the tree.
	 * @param target The object to be deleted
	 * @return The object deleted from the tree or null if the object was not in the
	 *         tree
	 * @throws ClassCastException if target does not implement Comparable
	 */
	public E deletePrime(E target) {
		root = deletePrime(root, target);
		return deleteReturn;
	}

	/**
	 * Recursive deletePrime method.
	 *
	 * @post The item is not in the tree; deleteReturn is equal to the deleted item
	 *       as it was stored in the tree or null if the item was not found.
	 * @param localRoot The root of the current subtree
	 * @param item      The item to be deleted
	 * @return The modified local root that does not contain the item
	 */
	private Node<E> deletePrime(Node<E> localRoot, E item) {

		// 1. item is not in the tree.
		if (localRoot == null) {
			deleteReturn = null;
			return localRoot;
		}

		// 2. Search for item to delete.
		int compResult = item.compareTo(localRoot.data);

		// 2.1 item is smaller than localRoot.data.
		if (compResult < 0) { // delete from leftSubTree
			localRoot.left = delete(localRoot.left, item);
			return localRoot;
		}

		// 2.2 item is larger than localRoot.data.
		else if (compResult > 0) { // delete from right subtree
			localRoot.right = delete(localRoot.right, item);
			return localRoot;
		}

		// 2.3 item is at local root.

		else {
			deleteReturn = localRoot.data;

			// 2.3.1 localRoot has one child

			// 2.3.1.1 If there is no left child, return right child
			// which can also be null.
			if (localRoot.left == null) {
				return localRoot.right;
			}

			// 2.3.1.2 If there is no right child, return left child.
			else if (localRoot.right == null) {
				return localRoot.left;
			}

			// 2.3.2 Node being deleted has 2 children, replace the data
			// with inorder predecessor.
			else {
				
				// 2.3.2.1 The left child has no right child.
				// Replace the data with the data in the
				// left child.
				if (localRoot.left.right == null) {
					localRoot.data = localRoot.left.data;

					// Replace the left child with its left child.
					localRoot.left = localRoot.left.left;
					return localRoot;
				}
				
				// 2.3.2.2 Search for the inorder predecessor (ip) and
				// replace deleted node's data with ip.
				else {
					localRoot.data = findSmallestChild(localRoot.right);
					return localRoot;
				}
			}
		}
	}

	/**
	 * Find the node that is the in-order successor and replace it with its right
	 * child (if any).
	 *
	 * @post The in-order successor is removed from the tree.
	 * @param parent The parent of possible in-order successor (is)
	 * @return The data in the is
	 */
	private E findSmallestChild(Node<E> parent) {
		if (parent.left.left == null) {
			E returnValue = parent.left.data;
			parent.left = parent.left.left;
			return returnValue;
		} else {
			return findLargestChild(parent.left);
		}
	}
	/* </exercise> */

}
