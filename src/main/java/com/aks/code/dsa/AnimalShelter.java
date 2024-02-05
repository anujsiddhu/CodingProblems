package com.aks.code.dsa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// https://aaronice.gitbook.io/lintcode/data_structure/animal_shelter
public class AnimalShelter {
    private static final int CAT = 0;
    private static final int DOG = 1;

    PriorityQueue<Animal> pq;
    int index;

    public AnimalShelter() {
        pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.index));
        index = 0;
    }

    void enqueue(String name, int type) {
        Animal animal = new Animal(type, index++, name);
        pq.add(animal);
    }

    public String dequeueAny() {
        if (pq.isEmpty()) {
            return null;
        }
        return pq.poll().name;
    }

    public String dequeueDog() {
        return dequeueType(DOG);
    }

    public String dequeueCat() {
        return dequeueType(CAT);
    }

    private String dequeueType(int type) {
        int size = pq.size();
        List<Animal> list = new ArrayList<>();
        Animal res = null;
        while (size-- > 0) {
            Animal top = pq.poll();
            if (top.type == type) {
                res = top;
                break;
            }
            list.add(top);
        }
        for (Animal animal : list) {
            pq.add(animal);
        }
        return res != null ? res.name : null;
    }

    private class Animal {
        int type;
        String name;
        int index;

        public Animal(int t, int i, String n) {
            type = t;
            index = i;
            name = n;
        }
    }


    public static void main(String[] args) {
        AnimalShelter animalShelter = new AnimalShelter();
        animalShelter.enqueue("james", DOG);
        animalShelter.enqueue("tom", DOG);
        animalShelter.enqueue("mimi", CAT);

        System.out.println(animalShelter.dequeueAny());
        System.out.println(animalShelter.dequeueCat());
        System.out.println(animalShelter.dequeueDog());
    }
}
