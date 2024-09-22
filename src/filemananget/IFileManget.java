package filemananget;

import java.util.List;

public interface IFileManget<E> {

    boolean saveToFile(List<E> list);
    
    List<E> readFromFile();
}