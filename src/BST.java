import java.util.ArrayDeque;
import java.util.ArrayList;

public class BST<E> implements Tree<E> {
    protected TreeNode<E> root;
    protected int size = 0;
    protected java.util.Comparator<E> c;

    /**
     * Create a default BST with a natural order comparator
     */
    public BST() {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
    }

    /**
     * Create a BST with a specified comparator
     */
    public BST(java.util.Comparator<E> c) {
        this.c = c;
    }

    /**
     * Create a binary tree from an array of objects
     */
    public BST(E[] objects) {
        this.c = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    @Override
    /** Returns true if the element is in the tree */
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root

        boolean found = false;
        while (current != null && !found) {
            if (c.compare(e, current.element) < 0) {
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                current = current.right;
            } else // element matches current.element
                found = true; // Element is found
        }

        return found;
    }

    @Override
    /** Insert element e into the binary tree
     * Return true if the element is inserted successfully */
    public boolean insert(E e) {
        boolean inserted = true;
        if (root == null)
            root = createNewNode(e); // Create a new root
        else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null && inserted)
                if (c.compare(e, current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (c.compare(e, current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else
                    inserted = false; // Duplicate node not inserted

            // Create the new node and attach it to the parent node
            if (c.compare(e, parent.element) < 0)
                parent.left = createNewNode(e);
            else
                parent.right = createNewNode(e);
        }

        size++;
        return inserted; // Element inserted successfully
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    //BinaryTrees opgave01-3
    @Override
    public void inorder() {
        inorder(root);
    }

    private void inorder(TreeNode<E> node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.element + " ");
        inorder(node.right);
    }


    //BinaryTrees opgave01-3
    @Override
    public void postorder() {
        postorder(root);
    }

    private void postorder(TreeNode<E> node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.element + " ");
    }


    //BinaryTrees opgave01-3
    @Override
    public void preorder() {
        preorder(root);
    }

    private void preorder(TreeNode<E> node) {
        if (node == null) return;
        System.out.print(node.element + " ");
        preorder(node.left);
        preorder(node.right);
    }


    /**
     * This inner class is static, because it does not access
     * any instance members defined in its outer class
     */
    public static class TreeNode<E> {
        protected E element;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override
    /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     */
    public TreeNode<E> getRoot() {
        return root;
    }

    //BinaryTrees opgave02
    public boolean isLeaf(E e) {
        TreeNode<E> node = findNode(e);
        return node != null && node.left == null && node.right == null;
    }

    //BinaryTrees opgave02
    public boolean isInternal(E e) {
        TreeNode<E> node = findNode(e);
        return node != null && (node.left != null || node.right != null);
    }

    //BinaryTrees opgave02
    @Override
    public int height() {
        return height(root);
    }

    private int height(TreeNode<E> node) {
        if (node == null) return -1;
        int hl = height(node.left);
        int hr = height(node.right);
        return 1 + Math.max(hl, hr);
    }


    //BinaryTrees opgave03
    @Override
    public int sum() {
        return sum(root);
    }

    private int sum(TreeNode<E> node) {
        if (node == null) return 0;
        int left = sum(node.left);
        int right = sum(node.right);
        int val = (node.element instanceof Integer) ? (Integer) node.element : 0;
        return left + val + right;
    }

    //BinaryTrees opgave04 --
    //Tidskompleksiteten er O(log n) hvis det er et balanceret træ, i et degeneret træ vil den være O(n)
    public E findMin() {
        if (root == null) return null;
        TreeNode<E> current = root;
        while (current.left != null) current = current.left;
        return current.element;
    }

    //BinaryTrees opgave04 --
    //Tidskompleksiteten er O(log n) hvis det er et balanceret træ, i et degeneret træ vil den være O(n)
    public E findMax() {
        if (root == null) return null;
        TreeNode<E> current = root;
        while (current.right != null) current = current.right;
        return current.element;
    }

    //BinaryTreesFortsat opgave 2
    public E removeMin() {
        if (root == null) return null;
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current.left != null) {
            parent = current;
            current = current.left;
        }
        E min = current.element;

        if (parent == null) {

            root = root.right;
        } else {
            parent.left = current.right;
        }
        size--;
        return min;
    }

    //BinaryTreesFortsat opgave 2
    public E removeMax() {
        if (root == null) return null;
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        while (current.right != null) {
            parent = current;
            current = current.right;
        }
        E max = current.element;

        if (parent == null) {

            root = root.left;
        } else {
            parent.right = current.left;
        }
        size--;
        return max;
    }

    //BinaryTreesFortsat opgave 2
    public ArrayList<E> greaterThan(E element) {
        ArrayList<E> result = new ArrayList<>();
        TreeNode<E> node = root;
        ArrayDeque<TreeNode<E>> stack = new ArrayDeque<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (c.compare(node.element, element) > 0) {
                result.add(node.element);
            }
            node = node.right;
        }
        return result;
    }

    private TreeNode<E> findNode(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            int cmp = c.compare(e, current.element);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else return current;
        }
        return null;
    }

    @Override
    /** Delete an element from the binary tree.
     * Return true if the element is deleted successfully
     * Return false if the element is not in the tree */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        boolean found = false;
        while (current != null && !found) {
            if (c.compare(e, current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (c.compare(e, current.element) > 0) {
                parent = current;
                current = current.right;
            } else
                found = true; // Element is in the tree pointed at by current
        }

        if (found) {
            // Case 1: current has no left child
            if (current.left == null) {
                // Connect the parent with the right child of the current node
                if (parent == null) {
                    root = current.right;
                } else {
                    if (c.compare(e, parent.element) < 0)
                        parent.left = current.right;
                    else
                        parent.right = current.right;
                }
            } else {
                // Case 2: The current node has a left child
                // Locate the rightmost node in the left subtree of
                // the current node and also its parent
                TreeNode<E> parentOfRightMost = current;
                TreeNode<E> rightMost = current.left;

                while (rightMost.right != null) {
                    parentOfRightMost = rightMost;
                    rightMost = rightMost.right; // Keep going to the right
                }

                // Replace the element in current by the element in rightMost
                current.element = rightMost.element;

                // Eliminate rightmost node
                if (parentOfRightMost.right == rightMost)
                    parentOfRightMost.right = rightMost.left;
                else
                    // Special case: parentOfRightMost == current
                    parentOfRightMost.left = rightMost.left;
            }
            size--; // Reduce the size of the tree
        }
        return found; // Element deleted successfully
    }

    //BinaryTreesFortsat opgave 4
    public int numberOfLeaves() {
        return numberOfLeaves(root);
    }
    private int numberOfLeaves(TreeNode<E> node) {
        if (node == null)
            return 0;

        // Hvis noden er et blad (ingen børn) returner 1
        if (node.left == null && node.right == null)
            return 1;

        // Ellers fortsæt søgningen i både venstre og højre subtree
        return numberOfLeaves(node.left) + numberOfLeaves(node.right);
    }

    //BinaryTreesFortsat opgave 4
    public int heightNodeCount(int targetHeight) {
        return heightNodeCount(root, 0, targetHeight);
    }
    private int heightNodeCount(TreeNode<E> node, int currentHeight, int targetHeight) {
        if (node == null)
            return 0;

        // Hvis der nåes den ønskede højde, returner 1
        if (currentHeight == targetHeight)
            return 1;

        // Ellers fortsæt rekursivt
        return heightNodeCount(node.left, currentHeight + 1, targetHeight) +
                heightNodeCount(node.right, currentHeight + 1, targetHeight);
    }

    //-------------------------------------------------------------------
}
