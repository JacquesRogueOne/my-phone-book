package phone;

import java.util.List;

public class PhoneCallsList implements CallsList {
    private List<PhoneNumber> calls;

    private PhoneCallsList(List<PhoneNumber> calls) {
        this.calls = calls;
    }

    public static PhoneCallsList of(List<PhoneNumber> calls) {
        return new PhoneCallsList(calls);
    }

    @Override
    public long numberOfCalls(PhoneNumber phoneNumber) {
        if (calls == null) {
            return 0;
        }

        return calls.stream().filter(phoneNumber::equals).count();
    }
}
