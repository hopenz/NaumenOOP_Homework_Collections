package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    public static void main(String[] args) {

        Map<String, Integer> mapOfWOrds = new LinkedHashMap<>();

        PriorityQueue<Map.Entry<String, Integer>> topTenWords = new PriorityQueue<>(Comparator
                .comparingInt(Map.Entry::getValue));

        PriorityQueue<Map.Entry<String, Integer>> lastTenWords = new PriorityQueue<>(Comparator
                .comparingInt(value -> -value.getValue()));

        new WordParser(WAR_AND_PEACE_FILE_PATH)
                .forEachWord(word -> {
                    if (mapOfWOrds.containsKey(word)) {
                        mapOfWOrds.put(word, mapOfWOrds.get(word) + 1);
                    } else {
                        mapOfWOrds.put(word, 1);
                    }
                });

        for (Map.Entry<String, Integer> wordAndCount : mapOfWOrds.entrySet()) {
            if (topTenWords.size() <= 9) {
                topTenWords.add(wordAndCount);
            } else if (wordAndCount.getValue() > topTenWords.peek().getValue()) {
                topTenWords.remove();
                topTenWords.add(wordAndCount);
            }

            if (lastTenWords.size() <= 9) {
                lastTenWords.add(wordAndCount);
            } else if (wordAndCount.getValue() < lastTenWords.peek().getValue()) {
                lastTenWords.remove();
                lastTenWords.add(wordAndCount);
            }
        }
        List<Map.Entry<String,Integer>> listOfWords = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map.Entry<String, Integer> wordAndCount = topTenWords.poll();
            listOfWords.add(wordAndCount);
        }
        System.out.println("Топ наиболее встречающихся слов");
        for (int i = 9; i >= 0; i--) {
            System.out.println(listOfWords.get(i).getKey() + "-" + listOfWords.get(i).getValue());
        }
        System.out.println("Топ наименее встречающихся слов");
        for (int i = 0; i < 10; i++) {
            Map.Entry<String, Integer> wordAndCount = lastTenWords.poll();
            System.out.println(wordAndCount.getKey() + "-" + wordAndCount.getValue());
        }

    }


/**
 * Ответы на вопросы:
 * 1. Для данной задачи я выбрала LinkedHashMap, так как эта коллекция быстро итерируется и
 * в данном случает хранит слово и его счетчик (сколько раз встречалось слово).
 * <p>
 * Также в качестве второй коллекции я выбрала PriorityQueue, так как сложность добавления
 * элемента O(log n), где n - количество элементов в очереди.
 * А сложность удаления и получения элемента - O(1).
 * И элементы хранятся в отсортированном порядке
 * <p>
 * 2-3. Проход по каждому слову в файле требует О(n),
 * а добавление/обновление его в LinkedHashMap требует O(1). Итого: О(n).
 * <p>
 * Далее припрохождении по циклу и добавлении элементов в очередь с приоритетом
 * сложнолсть будет O(nlog 10 + nlog 9).
 *
 * Сложность вывода будет: O(2 *(log 10 + log 9 + ... + log 1) + 10) ~ O(1)
 *
 * ИТОГО: О(n + nlog 10 + nlog 9)
 */
}
