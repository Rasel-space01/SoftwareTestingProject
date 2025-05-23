import com.mycompany.darazjunittesting.DarazJunitTesting;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {

    private DarazJunitTesting daraz;

    @BeforeEach
    public void setUp() {
        daraz = new DarazJunitTesting();
    }

    // 🔍 Test product by valid ID
    @Test
    public void testGetProductById_ValidIds() {
        assertEquals("Smartphone", daraz.getProductById(1));
        assertEquals("Laptop", daraz.getProductById(2));
        assertEquals("Shoes", daraz.getProductById(3));
    }

    // ❌ Test product by invalid ID
    @Test
    public void testGetProductById_InvalidIds() {
        assertEquals("Unknown Product", daraz.getProductById(999));
        assertEquals("Unknown Product", daraz.getProductById(-1));
        assertEquals("Unknown Product", daraz.getProductById(0));
    }

    // 🔠 Case-insensitive check
    @Test
    public void testGetProductById_CaseInsensitive() {
        String product = daraz.getProductById(1);
        assertTrue(product.equalsIgnoreCase("smartphone"));
    }

    // ✅ Test category list is not null
    @Test
    public void testGetAvailableCategories_NotNull() {
        assertNotNull(daraz.getAvailableCategories());
    }

    // 🔢 Category count check
    @Test
    public void testGetAvailableCategories_Size() {
        List<String> categories = daraz.getAvailableCategories();
        assertEquals(4, categories.size());
    }

    // ✅ Test expected categories exist
    @Test
    public void testGetAvailableCategories_ContainsExpected() {
        List<String> categories = daraz.getAvailableCategories();
        assertTrue(categories.contains("Electronics"));
        assertTrue(categories.contains("Fashion"));
        assertTrue(categories.contains("Groceries"));
        assertTrue(categories.contains("Home Appliances"));
    }

    // 🔁 No duplicates in categories
    @Test
    public void testGetAvailableCategories_NoDuplicates() {
        List<String> categories = daraz.getAvailableCategories();
        Set<String> set = new HashSet<>(categories);
        assertEquals(categories.size(), set.size(), "Duplicate categories found");
    }

    // 🔍 No empty or blank categories
    @Test
    public void testGetAvailableCategories_NoEmptyValues() {
        List<String> categories = daraz.getAvailableCategories();
        for (String category : categories) {
            assertFalse(category.trim().isEmpty(), "Empty category name found");
        }
    }
}
