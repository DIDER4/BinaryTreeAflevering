import Dictionary.Dictionary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Dictionary.DictionaryBST;

import static org.junit.jupiter.api.Assertions.*;
class DictionaryBSTTest {

    Dictionary<Integer, String> dictionary;

    @BeforeEach
    public void setUp() {
        dictionary = new DictionaryBST<Integer, String>();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(dictionary.isEmpty());

        dictionary.put(1, "Søren");
        assertFalse(dictionary.isEmpty());

        dictionary.remove(1);
        assertTrue(dictionary.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, dictionary.size());

        dictionary.put(1, "Én");
        assertEquals(1, dictionary.size());

        dictionary.put(2, "To");
        assertEquals(2, dictionary.size());

        dictionary.put(1, "Opdateret");
        assertEquals(2, dictionary.size());

        dictionary.remove(1);
        assertEquals(1, dictionary.size());

        dictionary.remove(2);
        assertEquals(0, dictionary.size());
    }

    @Test
    public void testGet() {
        assertNull(dictionary.get(1));

        dictionary.put(1, "Ét");
        dictionary.put(2, "To");
        dictionary.put(3, "Tre");

        assertEquals("Ét", dictionary.get(1));
        assertEquals("To", dictionary.get(2));
        assertEquals("Tre", dictionary.get(3));

        assertNull(dictionary.get(4));
    }

    @Test
    public void testPut() {
        // Test indsættelse af nye nøgleværdi
        assertNull(dictionary.put(1, "Ét"));
        assertNull(dictionary.put(2, "To"));

        // Test opdatering af eksisterende nøgle
        assertEquals("Ét", dictionary.put(1, "Opdateret"));
        assertEquals("Opdateret", dictionary.get(1));

        // Test med null nøgle eller værdi
        assertNull(dictionary.put(null, "værdi"));
        assertNull(dictionary.put(3, null));
        assertEquals(2, dictionary.size());
    }

    @Test
    public void testRemove() {
        assertNull(dictionary.remove(1));

        dictionary.put(1, "Ét");
        dictionary.put(2, "To");
        dictionary.put(3, "Tre");
        dictionary.put(4, "Fire");
        dictionary.put(5, "Fem");

        assertEquals("Fem", dictionary.remove(5));
        assertEquals(4, dictionary.size());

        assertEquals("Tre", dictionary.remove(3));
        assertEquals(3, dictionary.size());

        assertEquals("To", dictionary.remove(2));
        assertEquals(2, dictionary.size());

        assertEquals("Ét", dictionary.remove(1));
        assertEquals(1, dictionary.size());

        assertNull(dictionary.remove(99));
    }
}