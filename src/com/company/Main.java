package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void openZip(String pathZipFiles, String pathOpenZipFiles) {
        try(ZipInputStream zis = new ZipInputStream(new FileInputStream(pathZipFiles))) {
            ZipEntry entry;
            String name;
            while ((entry = zis.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fos = new FileOutputStream(pathOpenZipFiles + name);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fos.write(c);
                }
                fos.flush();
                zis.closeEntry();
                fos.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static GameProgress openProgress(String pathSaveGamer) {
        GameProgress gamer = null;
        try(FileInputStream fis = new FileInputStream(pathSaveGamer);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            gamer = (GameProgress) ois.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
     return gamer;
    }

    public static void main(String[] args) {
        String path = "D://Games//savegames//";
	    openZip(path + "zip.zip", path);
        GameProgress gamer1 = openProgress(path + "save1.dat");
        System.out.println(gamer1);
    }
}
