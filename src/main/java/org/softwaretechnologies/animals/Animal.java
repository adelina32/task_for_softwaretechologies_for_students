package org.softwaretechnologies.animals;

public abstract class Animal {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Создайте три класса животных: кошка, корова, собака.
     * Кошка должна издавать звук: "meow"
     * Корова должна издавать звук: "moo"
     * Собака должна издавать звук: "woof"
     * @return Метод должен возвращать строку, соответсвующую издаваемого животным звука.
     */
    public abstract String sound();

    public static class Cat extends Animal {
        public Cat(String name){
            super(name);
        }
        @Override
        public String sound(){
            return "meow";
        }
    }

    public static class Cow extends Animal {
        public Cow(String name) {
            super(name);
        }

        @Override
        public String sound() {
            return "moo";
        }
    }

    public static class Dog extends Animal {
        public Dog(String name){
            super(name);
        }
        @Override
        public String sound(){
            return "woof";
        }
    }
}
