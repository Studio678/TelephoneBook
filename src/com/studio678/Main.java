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
import java.util.Map;
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
    private final static Pattern FIO_PATTERN = Pattern.compile("([А-ЯЁA-Z][а-яёa-z]++(?:-[А-ЯЁA-Z][а-яёa-z]++)?)|([а-яёa-z]++(?:-[а-яёa-z]++)?)");
    //find telephone
    private final static Pattern PHONE_PATTERN = Pattern.compile("([0-9]+)");//pattern works incorect

    private static String getKeyByValue(TreeMap<String,String> map, String value){

        for(Map.Entry<String,String> thisKey: map.entrySet()){
            if(value.equals(thisKey.getValue())){
                return thisKey.getKey();
            }
        }
        return null;
    }


    private static TreeMap<String,String> getPhoneBook() throws IOException {
        TreeMap<String, String> telephoneBook = new TreeMap<>();
        String phone;
        String name;
        while(true) {
            //read command
            String command = readBlock();
            String filteredPhone = command.replaceAll("[^0-9]+", "");
            Matcher matcherPhone = PHONE_PATTERN.matcher(filteredPhone);
            //check if LIST, name or telephone
            if (command.equals("LIST")) {
                return telephoneBook;
            } else if (matcherPhone.matches()) {
                //Matcher matcher = PHONE_PATERN.matcher(phone);
                if (telephoneBook.containsValue(filteredPhone)) {
                    //show name for this phone
                    System.out.println(getKeyByValue(telephoneBook,filteredPhone) + " => " + filteredPhone + "\n");
                    //System.out.println(telephoneBook.get(phone));
                }
                else{
                    System.out.println("Field name:/n");
                    name = readBlock();
                    Matcher matcherName = FIO_PATTERN.matcher(name);
                    if (matcherName.matches()) {
                        //if input correct put record in book
                        telephoneBook.put(name, filteredPhone);
                    } else {//incorrect input
                        //cycle input until it will be correct
                        while (!matcherName.matches()) {
                            System.out.println("Incorrect name, input name field:");
                            name = readBlock();
                            if (name.equals("LIST")) {//exit if list
                                return telephoneBook;
                            }
                            matcherName = FIO_PATTERN.matcher(name);
                        }
                        //put record in book
                        telephoneBook.put(name, filteredPhone);
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
                    System.out.println("Field phone:\n");
                    String str = readBlock();
                    phone = str.replaceAll("[^0-9]+", "");
                    Matcher matcherPhone1 = PHONE_PATTERN.matcher(phone);
                    if(matcherPhone1.matches()){
                        //if input correct put record in book
                        telephoneBook.put(name, phone);
                    }else{//incorrect input
                        //cycle input phone until it will be correct
                        String phoneIncorrectInput;
                        while (!matcherPhone1.matches()){
                            System.out.println("Incorrect phone, input phone in field:\n");
                            String str1 = readBlock();
                            phoneIncorrectInput = str1.replaceAll("[^0-9]+", "");
                            if (str1.equals("LIST")){//exit if list
                                return telephoneBook;
                            }
                            //put record in book
                            telephoneBook.put(name,phoneIncorrectInput);
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
