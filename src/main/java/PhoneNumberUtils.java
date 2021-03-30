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
        chunk2.forEach(chunk1::putIfAbsent);
        return chunk1;
    }

    private Map<String, Integer> accumulate(Map<String, Integer> accumulator, String phoneNumber) {
        accumulator.compute(phoneNumber, (phone, occurrence) -> occurrence == null ? 1 : occurrence + 1);
        return accumulator;
    }
}
