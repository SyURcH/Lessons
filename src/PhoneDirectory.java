import java.util.*;

public class PhoneDirectory {
    private final Map<String, List<String>> directory = new HashMap<>();

    public void add(String surname, String phoneNumber) {
        if (!directory.containsKey(surname)) {
            directory.put(surname, new ArrayList<>());
        }
        directory.get(surname).add(phoneNumber);
    }

    public List<String> get(String surname) {
        return directory.getOrDefault(surname, new ArrayList<>());
    }
}