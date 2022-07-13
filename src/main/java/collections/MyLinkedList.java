package collections;

import java.util.Objects;

public class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> last;
    private int size;

    public void add(T element) {
        add(size, element);
    }

    public void add(int index, T element) {
        Objects.checkIndex(index, size + 1);
        Node<T> newNode = new Node<>(element);
        if (size == 0) {
            head = newNode;
            last = newNode;
        } else if (index == size) {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            Node<T> nodeToAdd = findNode(index);
            nodeToAdd.prev.next = newNode;
            newNode.prev = nodeToAdd.prev;
            nodeToAdd.prev = newNode;
            newNode.next = nodeToAdd;
        }
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return findNode(index).element;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        if (size == 1) {
            head = null;
            last = null;
        } else if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            last = last.prev;
            last.next = null;
        } else {
            Node<T> nodeToRemove = findNode(index);
            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;
        }
        size--;
    }

    public void set(int index, T element) {
        Objects.checkIndex(index, size);
        findNode(index).element = element;
    }

    private Node<T> findNode(int index) {
        int currentIndex;
        Node<T> tmp;
        if (index < size / 2) {
            currentIndex = 0;
            tmp = head;
            while (currentIndex != index) {
                currentIndex++;
                tmp = tmp.next;
            }
        } else {
            currentIndex = size - 1;
            tmp = last;
            while (currentIndex != index) {
                currentIndex--;
                tmp = tmp.prev;
            }
        }
        return tmp;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node<T> tmp = head;
        while (tmp != null) {
            str.append(tmp.element).append(" ");
            tmp = tmp.next;
        }

        return str.toString();
    }

    private static class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> prev;

        private Node(T element) {
            this.element = element;
        }
    }
}
