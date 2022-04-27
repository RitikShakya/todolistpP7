package com.example.todolist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FIleHelper {

    public static final String filename ="file.dat";

    public static void writeData(ArrayList<String> item , Context context){

        try {
            FileOutputStream fos = context.openFileOutput(filename, context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(item);
            os.close();
        } catch (FileNotFoundException e) {
            new ArrayList<String>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static ArrayList<String> readData(Context context){

        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream os = new ObjectInputStream(fis);
            arrayList = (ArrayList<String>) os.readObject();

        } catch (FileNotFoundException e) {
            //item = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
