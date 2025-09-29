package Dictionary;

public class DictionaryBST<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private Node root;
    private int size;

    public DictionaryBST() {
        root = null;
        size = 0;
    }

    @Override
    public V get(K key) {
        Node node = find(key);
        if (node == null) {
            return null;
        } else {
            return node.value;
        }
    }

    private Node find(K key) {
        Node current = root;
        boolean found = false;
        while (!found && current != null) {
            int d = current.key.compareTo(key);
            if (d == 0) {
                found = true;
            } else if (d > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        if (found) {
            return current;
        } else {
            return null;
        }

    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public V put(K key, V value) {
        // Tjek om key eller value er null
        if (key == null || value == null) {
            return null;
        }

        // Hvis træet er tomt opret roden
        if (root == null) {
            root = new Node(key, value);
            size++;
            return null;
        }

        Node current = root;
        Node parent = null;
        boolean found = false;

        // Søg efter key i træet
        while (!found && current != null) {
            int d = current.key.compareTo(key);
            if (d == 0) {
                found = true;
            } else {
                parent = current;
                if (d > 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }

        // Opdater value hvis nøglen findes, ellers opret ny node
        if (found) {
            V oldValue = current.value;
            current.value = value;
            return oldValue;
        } else {
            Node newNode = new Node(key, value);
            if (parent.key.compareTo(key) > 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
            size++;
            return null;
        }
    }

    @Override
    public V remove(K key) {
        // Tjek om træet er tomt
        if (root == null) {
            return null;
        }

        Node parent = null;
        Node current = root;
        boolean found = false;

        // Find noden der skal fjernes ved at compare med key
        while (!found && current != null) {
            int d = current.key.compareTo(key);
            if (d == 0) {
                found = true;
            } else {
                parent = current;
                if (d > 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
        }

        if (!found) {
            return null;
        }

        V valueToReturn = current.value;

        // Tilfælde 1: Node har intet venstre barn
        if (current.left == null) {
            if (parent == null) {
                root = current.right;
            } else {
                if (parent.key.compareTo(key) > 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        }
        // Tilfælde 2: Node har et venstre barn
        else {
            // Find den største node i venstre subtree
            Node parentOfRightmost = current;
            Node rightmost = current.left;

            while (rightmost.right != null) {
                parentOfRightmost = rightmost;
                rightmost = rightmost.right;
            }

            // Erstat current node nøgle og værdi med rightmost's
            current.key = rightmost.key;
            current.value = rightmost.value;

            // Fjern rightmost noden
            if (parentOfRightmost == current) {
                parentOfRightmost.left = rightmost.left;
            } else {
                parentOfRightmost.right = rightmost.left;
            }
        }
        size--;
        return valueToReturn;
    }

    @Override
    public int size() {
        return size;
    }

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
