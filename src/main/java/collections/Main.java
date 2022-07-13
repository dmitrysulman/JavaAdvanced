package collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>();
        List<Integer> arrayList = new ArrayList<>();
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

        measureTime(linkedList, myLinkedList);
//        measureTime(arrayList, myLinkedList);
    }

    public static void measureTime(List<Integer> list, MyLinkedList<Integer> myLinkedList) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            list.add(0, i);
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            myLinkedList.add(0, i);
        }
        myLinkedList.remove(0);
        myLinkedList.add(1, 87);
        myLinkedList.set(2, 77);
//        System.out.println(myLinkedList.get(70000));
        System.out.println(myLinkedList);
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}