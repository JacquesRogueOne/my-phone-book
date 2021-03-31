import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumberUtils {
    public Map<String, Integer> numbersApparition(List<String> phoneNumbers) {
        if (phoneNumbers == null) {
            return Collections.emptyMap();
        }

        return phoneNumbers.parallelStream()
                .reduce(new HashMap<>(), this::accumulate, this::combine);
    }

    private Map<String, Integer> combine(Map<String, Integer> chunk1, Map<String, Integer> chunk2) {
        HashMap<String, Integer> hashMap = new HashMap<>(chunk1);
        chunk2.forEach((s, integer) -> hashMap.merge(s, integer, Integer::sum));
        return hashMap;
    }

    private Map<String, Integer> accumulate(Map<String, Integer> accumulator, String phoneNumber) {
        Map<String, Integer> stringIntegerMap = new HashMap<>(accumulator);
        stringIntegerMap.compute(phoneNumber, (phone, occurrence) -> occurrence == null ? 1 : occurrence + 1);
        return stringIntegerMap;
    }
}
