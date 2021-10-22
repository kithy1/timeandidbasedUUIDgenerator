package generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

class TimeAndIdBasedUUIDGeneratorTest {

    @Test
    void givenLocalDateTimeAndLongNumber_whenTimeBased_thenCreateTimeBasedUUID() {
        Long number = 2L;
        LocalDateTime localDateTime = LocalDateTime.of(1983, 5, 24, 23, 21, 3);

        UUID uuid = TimeAndIdBasedUUIDGenerator.timeBased(localDateTime, number);

        Assertions.assertEquals(1, uuid.version());
    }

    @Test
    void givenLocalDateTimeAndLongNumber_whenTimeBased_thenAbleToReadDateAndTimeFromUUID() {
        Long number = 2L;
        LocalDateTime localDateTime = LocalDateTime.of(1983, 5, 24, 23, 21, 3);

        UUID uuid = TimeAndIdBasedUUIDGenerator.timeBased(localDateTime, number);

        long timestampFromUUID = uuid.timestamp() / 10000L - 12219292800000L;

        LocalDate date = Instant.ofEpochMilli(timestampFromUUID).atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestampFromUUID), ZoneId.systemDefault());

        Assertions.assertEquals(1983, date.getYear());
        Assertions.assertEquals(3, dateTime.getSecond());
    }

    @Test
    void givenLocalDateTimeAndLongNumber_whenTimeBased_thenCreateTimeBasedUUIDWithNodeEqualGivenLongNumber() {
        Long number = 2L;
        LocalDateTime localDateTime = LocalDateTime.of(1983, 5, 24, 23, 21, 3);

        UUID uuid = TimeAndIdBasedUUIDGenerator.timeBased(localDateTime, number);

        Assertions.assertEquals(2, uuid.node());
    }

}