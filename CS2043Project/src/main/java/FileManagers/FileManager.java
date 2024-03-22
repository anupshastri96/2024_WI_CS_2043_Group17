package FileManagers;

import java.io.*;

public class FileManager<T> {
    private T t;

    public FileManager(){}

    /**
     * A method for writing out any list of objects to a binary file.
     * @param list the data structure holding the data to be written.
     * @param filePath the file path starting from the program root dir.
     */
    public void writeList(T list, String filePath){
        try {
            FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir") + filePath);
            ObjectOutputStream writeOut = new ObjectOutputStream(outputStream);
            writeOut.writeObject(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method for reading in any list of objects from a binary file.
     * @param filePath the file path starting from the program root dir.
     * @return the dataStructure stored in the file.
     */
    public T readList(String filePath) throws FileNotFoundException {
        T list = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + filePath);
            ObjectInputStream readIn = new ObjectInputStream(inputStream);
            list = (T) readIn.readObject();
            inputStream.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
