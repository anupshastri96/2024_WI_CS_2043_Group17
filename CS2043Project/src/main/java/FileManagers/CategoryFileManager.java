package FileManagers;

//import Transactional.Category;

import java.io.*;
import java.util.LinkedList;

/*
public class CategoryFileManager implements Serializable {

    public static LinkedList<Category> readInTags(){
        LinkedList<Category> categoryList = null;
        try {
            FileInputStream categoryFile = new FileInputStream(System.getProperty("user.dir") + "\\CS2043Project\\data\\categories.bin");
            ObjectInputStream categoryIn = new ObjectInputStream(categoryFile);

            categoryList = (LinkedList<Category>) categoryIn.readObject();

            categoryIn.close();
        } catch (FileNotFoundException e) {
            return categoryList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    public static void writeOutTags(LinkedList<Category> categoryList){
        try {
            FileOutputStream categoryFile = new FileOutputStream(System.getProperty("user.dir") + "\\CS2043Project\\data\\categories.bin");
            ObjectOutputStream categoryOut = new ObjectOutputStream(categoryFile);

            categoryOut.writeObject(categoryList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


 */