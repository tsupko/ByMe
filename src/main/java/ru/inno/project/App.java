package ru.inno.project;

/**
 * Клиентский код.
 */
public class App {
    private static final int N = 10; // количество повторений

    /**
     * Печатает заданное количество раз заданную строку.
     *
     * @param s строка, которую нужно напечатать
     * @param n количество раз
     */
    private static void printSeveralTimes(String s, int n) {
        while (n-- > 0) {
            System.out.println(s);
        }
    }

    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        printSeveralTimes("Hello, World!", N);
    }
}
