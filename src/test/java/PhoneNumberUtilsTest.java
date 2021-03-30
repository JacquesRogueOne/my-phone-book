import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PhoneNumberUtilsTest {

    @Nested
    class phoneNumbersApparition {

        @Test
        void should_return_an_empty_collection_when_there_is_no_input() {
            // Arrange
            PhoneNumberUtils phoneNumberUtils = new PhoneNumberUtils();

            // Act
            Map<String, Integer> numbersApparition = phoneNumberUtils.numbersApparition(null);

            // Assert
            assertThat(numbersApparition).isEmpty();
        }

        @Test
        void should_return_an_empty_collection_when_the_input_does_not_contain_any_phone_number() {
            // Arrange
            PhoneNumberUtils phoneNumberUtils = new PhoneNumberUtils();
            List<String> phoneNumbers = Collections.emptyList();

            // Act
            Map<String, Integer> numbersApparition = phoneNumberUtils.numbersApparition(phoneNumbers);

            // Assert
            assertThat(numbersApparition).isEmpty();
        }

        @Test
        void should_return_a_collection_who_indicate_that_the_unique_phone_number_appear_one_time() {
            // Arrange
            PhoneNumberUtils phoneNumberUtils = new PhoneNumberUtils();
            List<String> phoneNumbers = List.of("0664994504");

            // Act
            Map<String, Integer> numbersApparition = phoneNumberUtils.numbersApparition(phoneNumbers);

            // Assert
            assertThat(numbersApparition).hasSize(1).extractingByKey("0664994504").isEqualTo(1);
        }

        @Test
        void should_return_a_collection_with_the_same_size_as_the_input_when_the_input_has_only_distinct_numbers() {
            // Arrange
            PhoneNumberUtils phoneNumberUtils = new PhoneNumberUtils();
            List<String> phoneNumbers = List.of("0664994504", "0600000000");

            // Act
            Map<String, Integer> numbersApparition = phoneNumberUtils.numbersApparition(phoneNumbers);

            // Assert
            assertThat(numbersApparition).hasSize(phoneNumbers.size());
        }

        @Test
        void should_return_a_collection_who_indicate_that_the_unique_phone_number_appear_two_times() {
            // Arrange
            PhoneNumberUtils phoneNumberUtils = new PhoneNumberUtils();
            List<String> phoneNumbers = List.of("0664994504", "0664994504");

            // Act
            Map<String, Integer> numbersApparition = phoneNumberUtils.numbersApparition(phoneNumbers);

            // Assert
            assertThat(numbersApparition).hasSize(1).extractingByKey("0664994504").isEqualTo(2);
        }

        @Test
        void should_return_a_collection_who_indicate_phone_numbers_appearance() {
            // Arrange
            PhoneNumberUtils phoneNumberUtils = new PhoneNumberUtils();
            List<String> phoneNumbers = List.of("0664994504", "0664994504", "0600000000", "0664994504", "0600000000", "0600000001");

            // Act
            Map<String, Integer> numbersApparition = phoneNumberUtils.numbersApparition(phoneNumbers);

            // Assert
            assertThat(numbersApparition).hasSize(3);
            assertThat(numbersApparition).extractingByKey("0664994504").isEqualTo(3);
            assertThat(numbersApparition).extractingByKey("0600000000").isEqualTo(2);
            assertThat(numbersApparition).extractingByKey("0600000001").isEqualTo(1);
        }
    }
}