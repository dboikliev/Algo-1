import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BirthdayRanges {
    public static class Pair {
        public int start;
        public int end;
    }

    // Returns a vector with the number of people born in the specific ranges.
    public List<Integer> birthdaysCount(List<Integer> birthdays, List<Pair> ranges) {
        Collections.shuffle(birthdays);
        BinarySearch search = new BinarySearch();
        List<Integer> numbersOfBirthdays = new ArrayList<Integer>();
        for (Pair range : ranges) {
            int peopleInRange = 0;
            for (int i = range.start; i <= range.end; i++) {
                peopleInRange += search.find(birthdays, i) > - 1 ? 1 : 0;
            }
            numbersOfBirthdays.add(peopleInRange);
        }
        return numbersOfBirthdays;
    }
}
