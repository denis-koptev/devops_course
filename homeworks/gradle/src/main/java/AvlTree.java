import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AvlTree<K extends Comparable<K>> extends AbstractSet<K> {

    private class AvlIterator implements Iterator<K> {

        private Node<K> curr, next, max;

        AvlIterator() {
            next = findMin(root);
            max = findMax(root);
        }

        @Override
        public boolean hasNext() {
            return (next != null);
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (next == max) {
                next = null;
                return max.key;
            }
            Node<K> node = next;
            curr = max;
            next(root);
            next = curr;
            return node.key;
        }

        private void next(Node<K> node) {
            if (node != null) {
                next(node.left);
                if (node.key.compareTo(next.key) > 0
                        && node.key.compareTo(curr.key) < 0) {
                    curr = node;
                }
                next(node.right);
            }
        }
    }

    private static class Node<K> {
        private K key;
        private int height;
        private Node<K> left, right;

        public Node(K key) {
            this.key = key;
            left = right = null;
            height = 1;
        }
    }

    private Node<K> root;
    private int Size;

    public AvlTree() {
        super();
        root = null;
        Size = 0;
    }

    @Override
    public Iterator<K> iterator() {
        return new AvlIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof AvlTree)) {
            return false;
        }
        AvlTree new_tree = (AvlTree) o;
        if (new_tree.size() != size()) {
            return false;
        }
        Iterator iter = new_tree.iterator();
        while (iter.hasNext()) {
            if (!contains(iter.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return Size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        }
        K key = (K)o;
        Node<K> node = root;
        while (node != null) {
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else if (key.compareTo(node.key) > 0) {
                node = node.right;
            } else return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[Size];
        Iterator<K> iter = this.iterator();
        int idx = 0;
        while (iter.hasNext()) {
            array[idx] = iter.next();
            idx++;
        }

        return array;
    }

    @Override
    public boolean add(K k) {
        if (k == null || contains(k)) {
            return false;
        }
        root = add(root, k);
        Size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null || !contains(o)) {
            return false;
        }
        root = erase(root, (K)o);
        Size--;
        return true;
    }

    @Override
    public void clear() {
        root = null;
        Size = 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        toString(root, str);
        return str.toString();
    }

    @Override
    public int size() {
        return Size;
    }

    private int height(Node<K> node) {
        return (node == null ? 0 : node.height);
    }

    private void fixHeight(Node<K> node) {
        int hr = height(node.right);
        int hl = height(node.left);
        node.height = (hr > hl ? hr : hl) + 1;
    }

    private int getBalance(Node<K> node) {
        return (height(node.right) - height(node.left));
    }

    private Node<K> rotateRight(Node<K> node) {
        Node<K> lchild = node.left;
        node.left = lchild.right;
        lchild.right = node;
        fixHeight(node);
        fixHeight(lchild);
        return lchild;
    }

    private Node<K> rotateLeft(Node<K> node) {
        Node<K> rchild = node.right;
        node.right = rchild.left;
        rchild.left = node;
        fixHeight(node);
        fixHeight(rchild);
        return rchild;
    }

    private Node<K> balance(Node<K> node) {
        fixHeight(node);
        if (getBalance(node) == 2) {
            if (getBalance(node.right) < 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        if (getBalance(node) == -2) {
            if (getBalance(node.left) > 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        return node;
    }

    private Node<K> add(Node<K> node, K k) {
        if (node == null) {
            return new Node<>(k);
        }
        if (k.compareTo(node.key) < 0) {
            node.left = add(node.left, k);
        } else if (k.compareTo(node.key) > 0) {
            node.right = add(node.right, k);
        }// else return node;
        return balance(node);
    }

    private Node<K> findMin(Node<K> node) {
        return (node.left != null ? findMin(node.left) : node);
    }

    private Node<K> findMax(Node<K> node) {
        return (node.right != null ? findMax(node.right) : node);
    }

    private Node<K> cutMin(Node<K> node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = cutMin(node.left);
        return balance(node);
    }

    private Node<K> erase(Node<K> node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = erase(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = erase(node.right, key);
        } else {
            Node<K> l = node.left, r = node.right;
            if (r == null) {
                return l;
            }
            Node<K> min = findMin(r);
            min.right = cutMin(r);
            min.left = l;
            return balance(min);
        }
        return balance(node);
    }

    private void toString(Node<K> node, StringBuilder str) {
        if (node != null) {
            toString(node.left, str);
            str.append(node.key).append(" ");
            toString(node.right, str);
        }
    }
}
