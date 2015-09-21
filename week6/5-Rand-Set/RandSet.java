import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Deyan on 16.7.2015 ã..
 */
public class RandSet {
    private ArrayList<Integer>[] elements = new ArrayList[16];
    int count = 0;

    public void insert(int number) {
        int index = Integer.hashCode(number) % elements.length;
        elements[index].add();
    }
}
