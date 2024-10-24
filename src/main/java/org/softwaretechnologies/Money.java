package org.softwaretechnologies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.softwaretechnologies.MoneyType.*;

public class Money {
    private final MoneyType type;
    private final BigDecimal amount;

    public Money(MoneyType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Money равны, если одинаковый тип валют и одинаковое число денег до 4 знака после запятой.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @param o объект для сравнения
     * @return true - равно, false - иначе
     */

    @Override
    public boolean equals(Object o) {
        // Проверяем, ссылаются ли оба объекта на один и тот же экземпляр
        // и является ли объект 'o' экземпляром класса Money
        if ((this == o) && (o instanceof Money)){
            return true; // Если да, то объекты равны
        }
        // Приводим 'o' к типу Money для дальнейшей работы
        Money otherMoney = (Money) o;

        // Устанавливаем масштаб (количество знаков после запятой) для текущего объекта
        // Используем RoundingMode.HALF_UP для округления
        BigDecimal thisAmount;
        if (amount != null) {
            thisAmount = amount.setScale(4, RoundingMode.HALF_UP);
        } else {
            thisAmount = BigDecimal.ZERO;
        }
        // Устанавливаем масштаб для другого объекта Money
        BigDecimal otherAmount;
        if (otherMoney.amount != null) {
            otherAmount = otherMoney.amount.setScale(4, RoundingMode.HALF_UP);
        } else {
            otherAmount = BigDecimal.ZERO;
        }

        // Сравниваем типы объектов и значения чисел
        // Возвращаем true, если типы равны и значения также равны
        return this.type == otherMoney.type && thisAmount.compareTo(otherAmount) == 0;
    }
    /**
     * Формула:
     * (Если amount null 10000, иначе количество денег окрукленные до 4х знаков * 10000) + :
     * если USD , то 1
     * если EURO, то 2
     * если RUB, то 3
     * если KRONA, то 4
     * если null, то 5
     * Если amount округленный до 4х знаков * 10000 >= (Integer.MaxValue - 5), то хеш равен Integer.MaxValue
     * Округление по правилу: если >= 5, то в большую сторону, иначе - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @return хеш код по указанной формуле
     */
    @Override
    public int hashCode() {

        // Устанавливаем масштаб для значения amount (числовое значение) с 4 знаками после запятой.
        BigDecimal scaledAmount;
        if (amount == null) {
            scaledAmount = BigDecimal.ZERO;
        } else {
            scaledAmount = amount.setScale(4, RoundingMode.HALF_UP);
        }

        // Умножаем на 10000 и приводим к int.
        int amountValue = scaledAmount.multiply(BigDecimal.valueOf(10000)).intValue();


        // Инициализируем переменную для типа.
        int typeId;

        // Проверяем, является ли type null.
        if (type == null) {
            // Если type равен null, присваиваем typeId значение 5.
            typeId = 5;
        } else {
            // Если type не null, используем условные операторы if для определения typeId.
            if (type.equals(USD)) {
                typeId = 1;
            } else if (type.equals(EURO)) {
                typeId = 2;
            } else if (type.equals(RUB)) {
                typeId = 3;
            } else if (type.equals(KRONA)) {
                typeId = 4;
            } else {
                // Если тип не совпадает с известными валютами, устанавливаем typeId в 5.
                typeId = 5;
            }
        }
        // Проверяем, не превышает ли значение amountValue максимальное целое значение.
        if (amountValue >= (Integer.MAX_VALUE - 5)) {
            return Integer.MAX_VALUE; // Если да, возвращаем Integer.MAX_VALUE.
        }
        // Возвращаем сумму amountValue и typeId.
        return amountValue + typeId;
    }



    /**
     * Верните строку в формате
     * Тип_ВАЛЮТЫ: количество.XXXX
     * Тип_валюты: USD, EURO, RUB или KRONA
     * количество.XXXX - округленный amount до 4х знаков.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     * <p>
     * Если тип валюты null, то вернуть:
     * null: количество.XXXX
     * Если количество денег null, то вернуть:
     * Тип_ВАЛЮТЫ: null
     * Если и то и то null, то вернуть:
     * null: null
     *
     * @return приведение к строке по указанному формату.
     */
    @Override
    public String toString() {

        if (type == null && amount == null) {
            return "null: null";
        }

        if (type == null) {
            String result;
            if (amount == null) {
                result = "null";
            } else {
                result = amount.setScale(4, RoundingMode.HALF_UP).toString();
            }
            return "null: " + result;
        }


        if (amount == null) {
            return type + ": null";
        }
        return type + ": " + amount.setScale(4, RoundingMode.HALF_UP);


        //String str = type.toString()+": "+amount.setScale(4, RoundingMode.HALF_UP).toString();
        //return str;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public MoneyType getType() {
        return type;
    }

    public void main(String[] args) { //static

        Money money = new Money(MoneyType.EURO, BigDecimal.valueOf(10.00012));
        Money money1 = new Money(MoneyType.USD, BigDecimal.valueOf(10.5000));

        System.out.println(money1.toString());
        System.out.println(money1.hashCode());
        System.out.println(money.equals(money1));
    }
}