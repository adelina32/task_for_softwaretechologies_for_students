package org.softwaretechnologies;

import org.softwaretechnologies.animals.Animal;
import org.softwaretechnologies.animals.AnimalType;

import java.util.*;

public class Zoo {
    private final List<Animal> animalList = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animalList.add(animal);
    }

    /**
     * Метод должен возвращать список звуков животных.
     * Звуки животных должны быть отсортированы по имени житного. Пример
     * Животные:
     *
     * Корова: Яша
     * Кошка: Дуся
     * Собака: Жучка
     * Корова: Абракадабра
     * Собака: Шарик
     * Кошка: Мурзик
     * Собака: Бобик
     *
     * Возвращаемый список звуков: moo, woof, meow, woof, meow, woof, moo
     *
     * @return Звуки животных, в алфавитном порядке имени животного.
     */

    public List<String> soundAllAnimalsSortByName() {
        List<String> sounds = new ArrayList<>();
        // TODO заполните корректно список звуков

        Collections.sort(animalList, (a1, a2) -> a1.getName().compareTo(a2.getName())); //a1,a2 принимает 2 объекта Animal из списка
        // с помощью getName получаем их имен,
        // compareTo() — это метод, который сравнивает две строки.
        // Он возвращает:
        //отрицательное значение, если первая строка меньше второй (т.е. a1 идет перед a2 в алфавитном порядке),
        //ноль, если строки равны,
        //положительное значение, если первая строка больше второй (т.е. a1 идет после a2).

        for (int i = 0; i < animalList.size(); i++) {
            Animal a = animalList.get(i); // Получаем животное по индексу
            sounds.add(a.sound()); // Добавляем звук животного в список
        }
        return sounds;
    }
}
