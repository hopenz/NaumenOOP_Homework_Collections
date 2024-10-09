package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;

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
public class WarAndPeace
{

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    public static void main(String[] args) {

        Map<String, Integer> mapOfWOrds = new LinkedHashMap<>();

        new WordParser(WAR_AND_PEACE_FILE_PATH)
                .forEachWord(word -> {
                    if (mapOfWOrds.containsKey(word)) {
                        mapOfWOrds.put(word, mapOfWOrds.get(word) + 1);
                    } else {
                        mapOfWOrds.put(word, 1);
                    }
                });

        List<Map.Entry<String,Integer>> listOfWords = new ArrayList<>(mapOfWOrds.entrySet());
        listOfWords.sort(Map.Entry.comparingByValue());

        System.out.println("TOP 10 наиболее используемых слов: ");
        for (int i = listOfWords.size() - 1; i > listOfWords.size() - 11; i-- ){
            System.out.println(listOfWords.get(i).getKey() + " - " + listOfWords.get(i).getValue());
        }

        System.out.println("LAST 10 наименее используемых слов: ");
        for (int i = 0; i < 10; i++ ){
            System.out.println(listOfWords.get(i).getKey() + " - " + listOfWords.get(i).getValue());
        }
    }
    /**
     * Ответы на вопросы:
     * 1. Для данной задачи я выбрала LinkedHashMap, так как эта коллекция быстро итерируется и
     * в данном случает хранит слово и его счетчик (сколько раз встречалось слово).
     *
     * Также в качестве второй коллекции я выбрала Arraylist, так как обращение к элементу по индексу
     * требует O(1), а быстрая сортировка выполняется за О(nlog n).
     *
     * 2-3. Проход по каждому слову в файле требует О(n),
     * а добавление/обновление его в LinkedHashMap требует O(1). Итого: О(n).
     *
     * Далее создается Arraylist, который содержит все записи из LinkedHashMap. Сложность О(n),
     * где n - количество записей.
     *
     * Быстрая сортировка выполняется за О(nlog n).
     *
     * Дважды проход в цикле по листу, где по индексу достается map.entry, а из него сначала
     * берется сначала ключ, а потом значение: О(20) -> O(1).
     *(Тут подробнее будет: проход 1 раз по циклу, где 10 эл-тов - О(10) -> О(1);
     *Обращение к элементу по индексу в Arraylist требует О(1))
     *
     * ИТОГО: О(2n + nlog n) -> O(nlog n)
     *
     */
}
