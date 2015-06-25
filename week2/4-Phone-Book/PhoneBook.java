import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PhoneBook {

    public static class Contact {

        public String name;
        public int number;
    }

    // Find the names of people based on their phone numbers.
    public List<String> lookupNames(List<Contact> phoneBook, List<Integer> numbers) {
        phoneBook.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                if (o1.number == o2.number) {
                    return 0;
                }
                else if (o1.number < o2.number) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        });

        ArrayList<String> names = new ArrayList<>();
        for (Integer number : numbers) {
            Contact unknown = new Contact();
            unknown.number = number;
            int index = Collections.binarySearch(phoneBook, unknown, new Comparator<Contact>() {
                @Override
                public int compare(Contact o1, Contact o2) {
                    if (o1.number == o2.number) {
                        return 0;
                    }
                    else if (o1.number < o2.number) {
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
            });

            if (index >= 0) {
                names.add(phoneBook.get(index).name);
            }
        }
        return names;
    }
}