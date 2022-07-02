package com.company;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


//8 Letter password should consist from at least from 1 number, 1 upper case and 1 lower case letter. All password should be unique
public class Main {
    final static byte [] numbers = new byte[10];
    final static byte [] lowLetters = new byte[26];
    final static byte [] upperLetters = new byte[26];
    final static ArrayList<byte[]> listOfArrays = new ArrayList<>();
    static ArrayList<String> passwords = new ArrayList<>();


    static {
        int index = 0;
        for (byte i = 48; i<58; i ++)  numbers[index++] = i;
        index = 0;
        for (byte i = 65; i<91; i++) upperLetters[index++] = i;
        index = 0;
        for (byte i = 97; i<123; i++) lowLetters[index++] = i;
        Collections.addAll(listOfArrays,numbers,lowLetters,upperLetters);
    }


    public static ArrayList<byte[]> generatedList(){
        ArrayList<byte[]> tempArray = new ArrayList<>(listOfArrays);
        Collections.shuffle(tempArray);
        return tempArray;
    }

    public static byte[] generatePassword() {
        Random random = new Random();
        ArrayList<byte[]> tempList = generatedList();
        byte [] result = new byte[8];
        for (int i = 0; i<8; i++) {
            byte[] randomArray = tempList.get(0);
            tempList.remove(0);
            int randomIndex = random.nextInt(randomArray.length);
            result[i] = randomArray[randomIndex];
            if(tempList.isEmpty()) tempList = generatedList();
        }
        if(passwords.contains(Arrays.toString(result))) generatePassword();
        passwords.add(Arrays.toString(result));
        return result;
    }



    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());


    }

    public static ByteArrayOutputStream getPassword()  {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            byteArrayOutputStream.write(generatePassword());}
        catch (IOException e){e.printStackTrace();}
        return byteArrayOutputStream;
    }
}
