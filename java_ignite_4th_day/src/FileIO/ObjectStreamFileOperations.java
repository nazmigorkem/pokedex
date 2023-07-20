package FileIO;

import java.io.*;

public class ObjectStreamFileOperations {
        public void serialize(Student student, String filePath) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(student);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        public void deserialize(String filePath) {
            try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                Student student = (Student) objectInputStream.readObject();
                System.out.println(student.getName());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
}
