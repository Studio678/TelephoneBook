/**
 * Написать умный эмулятор телефонной книги. Если в неё ввести новое имя, она должна запросить
 * номер телефона. Если в неё ввести новый номер телефона, должна запросить имя. Если введённое
 * имя или номер телефона найдены, должна вывести дополнительную информацию: номер или имя,
 * соответственно. Команда LIST должна выводить всех абонентов в алфавитном порядке с номерами телефонов.
 *
 * author studio678 24.05.2019
 */

package com.studio678;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static String readBlock() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input 'LIST' for print all items in List or command: ");
        String str = reader.readLine().trim();
        System.out.println();

        return str;
    }


    public static void main(String[] args) {
	// write your code here

    }
}
