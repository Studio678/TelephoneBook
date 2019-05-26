/*
  Написать умный эмулятор телефонной книги. Если в неё ввести новое имя, она должна запросить
  номер телефона. Если в неё ввести новый номер телефона, должна запросить имя. Если введённое
  имя или номер телефона найдены, должна вывести дополнительную информацию: номер или имя,
  соответственно. Команда LIST должна выводить всех абонентов в алфавитном порядке с номерами телефонов.

  author studio678 24.05.2019
 */

package com.studio678;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static String readBlock() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input 'LIST' for print all items in List or value of field: ");
        String str = reader.readLine().trim();
        System.out.println();

        return str;
    }

    //find name
    private final static Pattern FIO_PATTERN = Pattern.compile("\\s*+([А-ЯЁ][а-яё]++(?:-[А-ЯЁ][а-яё]++)?)\\s++");
    //find telephone
    private final static Pattern PHONE_PATTERN = Pattern.compile("([0-9]++)");//pattern works incorect



    private static TreeMap<String,String> getPhoneBook() throws IOException {
        TreeMap<String, String> telephoneBook = new TreeMap<>();
        String phone;
        String name;
        while(true) {
            //read command
            String command = readBlock();
            //check if LIST, name or telephone
            if (command.equals("LIST")) {
                return telephoneBook;
            } else if (command.contains("[0-9]++")) {
                //filter telephone replaceAll("[^0-9]+","")
                phone = command.replaceAll("[^0-9]+", "");
                //Matcher matcher = PHONE_PATERN.matcher(phone);
                if (telephoneBook.containsValue(phone)) {
                    //show name for this phone

                    System.out.println(telephoneBook.get(phone));
                }
                else{
                    System.out.println("Field name:/n");
                    name = readBlock();
                    Matcher matcherName = FIO_PATTERN.matcher(name);
                    if (matcherName.matches()) {
                        //if input correct put record in book
                        telephoneBook.put(phone, name);
                    } else {//incorrect input
                        //cycle input until it will be correct
                        while (!matcherName.matches()) {
                            name = readBlock();
                            if (name.equals("LIST")) {//exit if list
                                return telephoneBook;
                            }
                        }
                        //put record in book
                        telephoneBook.put(phone, name);
                    }
                }
            } else {
                name = command;
                //Matcher matcher = FIO_PATTERN.matcher(name);
                //check if book contains name
                if(telephoneBook.containsKey(name)){
                    //print name => phone
                    System.out.println(name + " => " + telephoneBook.get(name));
                }else{
                    //reed phone put new record
                    System.out.println("Field phone:/n");
                    String str = readBlock();
                    phone = str.replaceAll("[^0-9]+", "");
                    Matcher matcherPhone = PHONE_PATTERN.matcher(phone);
                    if(matcherPhone.matches()){
                        //if input correct put record in book
                        telephoneBook.put(name, phone);
                    }else{//incorrect input
                        //cycle input phone until it will be correct
                        String phoneIncorectInput;
                        while (!matcherPhone.matches()){
                            System.out.println("Incorrect phone, input phone in field:/n");
                            String str1 = readBlock();
                            phoneIncorectInput = str1.replaceAll("[^0-9]+", "");
                            if (str1.equals("LIST")){//exit if list
                                return telephoneBook;
                            }
                            //put record in book
                            telephoneBook.put(name,phoneIncorectInput);
                        }
                    }
                }
            }

        }
    }



    public static void main(String[] args) throws IOException {
	// write your code here
        TreeMap<String,String> phoneBook = getPhoneBook();
        //print all records in book
        for(String name: phoneBook.keySet()){
            System.out.println(name + " => " + phoneBook.get(name));
        }
    }
}
