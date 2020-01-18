package com.example.list;

public class LinkedList {
    public enum LoopType {NONE, LOOPED, CIRCULAR}

    private Node head;

    public boolean isEmpty() {
        return head == null;
    }

    public int length() {
        Node current = head;
        int count = 0;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public void print() {
        StringBuilder result = new StringBuilder("{ ");
        Node current = this.head;
        while (current != null) {
            result.append(current.toString()).append(", ");
            current = current.getNext();
        }
        result.append("}");
        System.out.println(result.toString());
    }

    public void addHead(int value) {
        head = new Node(value, head);
    }

    public void addTail(int value) {
        Node newNode = new Node(value);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node current = this.head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void removeHead() {
        if (!isEmpty()) {
            this.head = this.head.getNext();
        }
    }

    public boolean remove(int value) {
        if (isEmpty()) return false;
        if (head.getValue() == value) {
            head = head.getNext();
            return true;
        }
        Node current = head;
        while (current.getNext() != null) {
            if (current.getNext().getValue() == value) {
                current.setNext(current.getNext().getNext());
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void removeAll(int value) {
        Node current = head;
        while (current != null && current.getValue() == value) {
            head = current.getNext();
            current = head;
        }
        while (current != null) {
            Node next = current.getNext();
            if (next != null && next.getValue() == value) {
                current.setNext(next.getNext());
            } else {
                current = next;
            }
        }
    }

    public boolean isPresent(int value) {
        Node current = this.head;
        while (current != null) {
            if (current.getValue() == value) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void reverse() {
        Node current = head;
        Node next, prev = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }

    public void reverseRecursively() {
        head = reverseRecursively(head, null);
    }

    public void removeDuplicate() {
        Node current = head;
        while (current != null) {
            if (current.getNext() != null && current.getValue() == current.getNext().getValue()) {
                current.setNext(current.getNext().getNext());
            } else {
                current = current.getNext();
            }
        }
    }

    public LinkedList copy() {
        LinkedList linkedList = new LinkedList();
        Node current = head;
        while (current != null) {
            linkedList.addTail(current.getValue());
            current = current.getNext();
        }
        return linkedList;
    }

    public LinkedList reverseCopy() {
        Node current = head;
        Node temp, tempNext = null;
        while (current != null) {
            temp = new Node(current.getValue(), tempNext);
            current = current.getNext();
            tempNext = temp;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.head = tempNext;
        return linkedList;
    }

    public boolean compare(LinkedList linkedList) {
        return compare(head, linkedList.head);
    }

    public Node nthNodeFromStart(int n) {
        if (n < 1 || n > length()) return null;
        Node current = head;
        int index = 1;
        while (current != null && index < n) {
            index++;
            current = current.getNext();
        }
        return current;
    }

    public Node nthNodeFromEnd(int n) {
        int length = length();
        if (length == 0 || n > length) return null;
        return nthNodeFromStart(length - n + 1);
    }

    public boolean isLooped() {
        Node slow = head, fast = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) return true;
        }
        return false;
    }

    public boolean isLooped2() {
        Node temp = head;
        reverse();
        if (temp == head) {
            reverse();
            return true;
        } else {
            reverse();
            return false;
        }
    }

    public int detectLoopType() {
        Node slow = head, fast = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            if (head == fast.getNext() || head == fast.getNext().getNext()) {
                return LoopType.CIRCULAR.ordinal();
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                return LoopType.LOOPED.ordinal();
            }
        }
        return LoopType.NONE.ordinal();
    }

    public Node detectLoopPoint() {
        Node slow = head, fast = head;
        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) return slow;
        }
        return null;
    }

    public void removeLoop() {
        Node loopPoint = detectLoopPoint();
        Node first = head;
        if (loopPoint == head) {
            while (first.getNext() != head) {
                first = first.getNext();
            }
            first.setNext(null);
            return;
        }
        Node second = loopPoint;
        while (first.getNext() != second.getNext()) {
            first = first.getNext();
            second = second.getNext();
        }
        second.setNext(null);
    }

    public Node findIntersectionPoint(LinkedList linkedList) {
        Node head1 = head;
        Node head2 = linkedList.head;
        int length1 = length();
        int length2 = linkedList.length();
        int diff;
        if (length1 >= length2) {
            diff = length1 - length2;
        } else {
            diff = length2 - length1;
            Node temp = head1;
            head1 = head2;
            head2 = temp;
        }
        while (diff > 0) {
            head1 = head1.getNext();
            diff--;
        }
        while (head1 != head2) {
            head1 = head1.getNext();
            head2 = head2.getNext();
        }
        return head1;
    }

    private boolean compare(Node head1, Node head2) {
        if (head1 == null && head2 == null) return true;
        else if (head1 == null || head2 == null || head1.getValue() != head2.getValue()) return false;
        else return compare(head1.getNext(), head2.getNext());
    }

    private Node reverseRecursively(Node current, Node next) {
        if (current == null) return null;
        if (current.getNext() == null) {
            current.setNext(next);
            return current;
        }
        Node node = reverseRecursively(current.getNext(), current);
        current.setNext(next);
        return node;
    }
}
