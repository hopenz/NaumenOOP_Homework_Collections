package ru.naumen.collection.task4;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Класс управления расчётами
 */
public class ConcurrentCalculationManager<T> {

    BlockingQueue<Future<T>> blQueue = new LinkedBlockingQueue<>();

    /**
     * Добавить задачу на параллельное вычисление
     */
    public void addTask(Supplier<T> task) throws InterruptedException {
        blQueue.put(CompletableFuture.supplyAsync(task));
    }

    /**
     * Получить результат вычисления.
     * Возвращает результаты в том порядке, в котором добавлялись задачи.
     */
    public T getResult() {
        try {
            return blQueue.take().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
/**
 * Ответы на вопросы:
 * 1. Я выбрала BlockingQueue, потому что она поддерживает ожидание эл-тов
 *
 * 2-3. При извлечении и добавлении в очередь сложность составляет О(1).
 * Следовательно сложность всего алгоритма будет O(1).
 */