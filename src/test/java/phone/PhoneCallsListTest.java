package phone;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PhoneCallsListTest {

    private static final PhoneNumber DUMMY_PHONE_NUMBER = new PhoneNumber("phone number");
    private static final PhoneNumber A_PHONE_NUMBER = new PhoneNumber("a phone number");
    private static final PhoneNumber ANOTHER_PHONE_NUMBER = new PhoneNumber("another phone number");

    @Nested
    class numberOfCalls {
        @Test
        void should_return_zero_when_there_is_no_list_of_calls() {
            // Arrange
            var phoneCallsList = PhoneCallsList.of(null);

            // Act
            var numberOfCalls = phoneCallsList.numberOfCalls(DUMMY_PHONE_NUMBER);

            // Assert
            assertThat(numberOfCalls).isZero();
        }

        @Test
        void should_return_one_when_there_is_only_one_number_in_the_list_of_calls() {
            // Arrange
            List<PhoneNumber> calls = List.of(A_PHONE_NUMBER);
            var phoneCallsList = PhoneCallsList.of(calls);

            // Act
            var numberOfCalls = phoneCallsList.numberOfCalls(A_PHONE_NUMBER);

            // Assert
            assertThat(numberOfCalls).isOne();
        }

        @Test
        void should_return_two_when_there_is_one_number_appearing_two_times_in_the_list_of_calls() {
            // Arrange
            List<PhoneNumber> calls = List.of(A_PHONE_NUMBER, A_PHONE_NUMBER);
            var phoneCallsList = PhoneCallsList.of(calls);

            // Act
            var numberOfCalls = phoneCallsList.numberOfCalls(A_PHONE_NUMBER);

            // Assert
            assertThat(numberOfCalls).isEqualTo(2);
        }

        @Test
        void should_return_one_when_there_is_a_list_of_calls_with_two_different_number() {
            // Arrange
            List<PhoneNumber> calls = List.of(A_PHONE_NUMBER, ANOTHER_PHONE_NUMBER);
            var phoneCallsList = PhoneCallsList.of(calls);

            // Act
            var numberOfCalls = phoneCallsList.numberOfCalls(A_PHONE_NUMBER);

            // Assert
            assertThat(numberOfCalls).isOne();
        }
    }
}