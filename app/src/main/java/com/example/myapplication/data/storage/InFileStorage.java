package com.example.myapplication.data.storage;

import android.content.Context;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InFileStorage<T extends Serializable> {
    private Context context;
    private String fileName;

    public InFileStorage(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
    }

    public void saveList(List<T> list) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> loadList() {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            List<T> list = (List<T>) ois.readObject();
            ois.close();
            return list != null ? list : new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}

