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
     * Округление по правилу: если >= 5, то в большую сторону, иначе - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @param o объект для сравнения
     * @return true - равно, false - иначе
     */

    @Override
    public boolean equals(Object o) {

        // Проверяем, ссылается ли объект 'o' на тот же экземпляр, что и текущий объект (this).
        if (o == this) {
            return true;
        }

        // Если 'o' равен null, или 'o' не является экземпляром того же класса, что и текущий объект,
        // возвращаем false, так как такие объекты не равны.

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        // Приводим объект 'o' к типу Money для дальнейшего сравнения.
        // Мы уверены, что 'o' не null и является экземпляром класса Money на предыдущих шагах.
        Money otherMoney = (Money) o;

        // Сравниваем поле 'type' текущего объекта с полем 'type' объекта otherMoney.
        // Если они не равны, возвращаем false, так как два объекта (money) не равны.
        if (type != otherMoney.type) {
            return false;
        }

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
        return thisAmount.equals(otherAmount);
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

        int amountValue = scaledAmount.multiply(BigDecimal.valueOf(10000)).intValue();

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
}