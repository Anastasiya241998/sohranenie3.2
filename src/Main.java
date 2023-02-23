import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        saveGames("C:/Users/Анастасия/Desktop/Games/savegames/gp1.dat",
                new GameProgress(100, 2, 5, 100));
        saveGames("C:/Users/Анастасия/Desktop/Games/savegames/gp2.dat",
                new GameProgress(80, 3, 50, 20));
        saveGames("C:/Users/Анастасия/Desktop/Games/savegames/gp3.dat",
                new GameProgress(99, 99, 1, 1));

        List<String> arr = new ArrayList<>(Arrays.asList(
                "C:/Users/Анастасия/Desktop/Games/savegames/gp1.dat",
                "C:/Users/Анастасия/Desktop/Games/savegames/gp2.dat",
                "C:/Users/Анастасия/Desktop/Games/savegames/gp3.dat"));

        zipFiles("C:/Users/Анастасия/Desktop/Games/savegames/zip.zip", arr);
        delete(arr);



    }

    public static void saveGames(String input, GameProgress gp) {
        try (ObjectOutputStream oop = new ObjectOutputStream(new FileOutputStream(input))) {
            oop.writeObject(gp);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }


    private static void zipFiles(String path, List<String> listOfObjects) {
        FileInputStream fis = null;
        try (ZipOutputStream zipInputStream = new ZipOutputStream(new FileOutputStream(path))) {
            for (String a : listOfObjects) {
                fis = new FileInputStream(a);
                ZipEntry entry = new ZipEntry(a.substring(a.lastIndexOf("/") + 1));
                zipInputStream.putNextEntry(entry);
                zipInputStream.write(fis.readAllBytes());
                zipInputStream.closeEntry();
                try {
                    if (fis != null) {
                        fis.close();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void delete(List<String> listOfObjects) {
        for (String a : listOfObjects) {
            File gp = new File(a);
            gp.delete();
        }
    }
}

