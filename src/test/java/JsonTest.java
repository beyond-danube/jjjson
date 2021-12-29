import io.siniavtsev.jjjson.JsonSorter;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonTest {

    String expected = "{\"b\":1,\"a\":2,\"c\":[\"a\", \"b\"],\"d\":[1,2]}";
    String actual = "{\"a\":2,\"b\":1,\"c\":[\"b\", \"a\"],\"d\":[2,1]}";

    @Test
    public void jsonContentComparisonTest() throws Exception {

        assertNotEquals(expected, actual);

        String sortedExpected = JsonSorter.getSortedJson(expected);
        String sortedActual = JsonSorter.getSortedJson(actual);

        assertEquals(sortedExpected, sortedActual);
    }

    @Test
    public void jsonContentHashCodeComparisonTest() throws Exception {

        int expectedHash = expected.hashCode();
        int actualHash = actual.hashCode();

        assertNotEquals(expectedHash, actualHash);

        int sortedExpectedHash = JsonSorter.getSortedJson(expected).hashCode();
        int sortedActualHash = JsonSorter.getSortedJson(actual).hashCode();

        assertEquals(sortedExpectedHash, sortedActualHash);
    }

}
