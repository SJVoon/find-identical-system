package service.finder.pair;

import model.LegacyCustomer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LegacyCustomerPairFinderServiceTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void findAndDisplay() {
        LegacyCustomerPairFinderService service = new LegacyCustomerPairFinderService();
        List<LegacyCustomer> list = new ArrayList<>();
        list.add(new LegacyCustomer("001","James Bond","IC","007","1920/02/28"));
        list.add(new LegacyCustomer("002","Ian Flaming","IC","007","1920/02/28"));
        list.add(new LegacyCustomer("003","James Bond","IC","777","1990/12/30"));

        service.findAndDisplay(list);

        assertTrue("A new pair of customer found, ID for Customers that are matched : 001 and 002"
                .equals(outputStreamCaptor.toString().trim())
                || "A new pair of customer found, ID for Customers that are matched : 002 and 001"
                .equals(outputStreamCaptor.toString().trim()));
    }
}