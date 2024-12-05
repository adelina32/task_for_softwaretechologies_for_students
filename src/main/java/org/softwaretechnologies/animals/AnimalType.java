package org.softwaretechnologies.animals;

public enum AnimalType {
    CAT{
        @Override
        public Animal createAnimal(String name) {  // создает и возвращает объект животного, не зная его класс, а только тип
            return new Animal.Cat(name);
        }
    },
    DOG{
        @Override
        public Animal createAnimal(String name) {
            return new Animal.Dog(name);
        }
    },
    COW{
        @Override
        public Animal createAnimal(String name) {
            return new Animal.Cow(name);
        }
    };
    public abstract Animal createAnimal(String name);
}
