package ru.naumen.collection.task2;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно реализовать метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 *
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2
{

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        Set<User> setA = new HashSet<>(collA);
        List<User> result = new ArrayList<>();
        for (User user : collB){
            if (setA.contains(user)){
                result.add(user);
            }
        }
        return result;
    }
    /**
     * Ответы на вопросы:
     * 1. Для решения этой задачи я выбрала две коллекции - Hashset и Arraylist, так как
     * hashset не допустает дубликатов и операция contains выполняется за О(1), а Arraylist
     * позволяет добавлять элемент в конец за О(1).
     *
     * 2-3. Так как я перезаписываю коллекцию collA в hashset - в начале сложность О(n),
     * где n - количество эл-тов в коллекции collA;
     * Далее прохожусь по массиву в цикле - сложность O(m), где m - количество элементов в collB;
     * Проверка принаблежности элемента и добавление элемента в конец - О(1);
     * Таким образом, итоговая сложность алгоритма получается О(n+m).
     *
     */
}
