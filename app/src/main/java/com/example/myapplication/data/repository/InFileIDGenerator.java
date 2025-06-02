package com.example.myapplication.data.repository;

import android.content.Context;
import java.io.*;

public class InFileIDGenerator {
    private final File file;
    private long id;

    public InFileIDGenerator(Context context, String fileName) {
        file = new File(context.getFilesDir(), fileName);
        try {
            if (!file.exists()) {
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(file, false));
                dos.writeLong(1L); // Начинаем с 1
                dos.close();
            }
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            id = dis.readLong();
            dis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long generate() {
        try {
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file, false));
            dos.writeLong(id + 1);
            dos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return id++;
    }
}
