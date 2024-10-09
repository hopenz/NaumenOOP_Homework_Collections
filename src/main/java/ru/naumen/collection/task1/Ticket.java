package ru.naumen.collection.task1;

import java.util.Objects;

/**
 * Билет
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Ticket {
    private long id;
    private String client;

    /**
     * Я запомнила! Программа не будет работать оптимально без методов
     * hashcode() и equals(), потому что в этом случае hash ключа будет высчитываться
     * по его ссылке, и возможна ситуация, когда ключи буду записываться нерационально,
     * что отразиться на скорости работы программы.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
