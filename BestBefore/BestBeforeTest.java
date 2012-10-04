import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class BestBeforeTest {


    @Test
    public void testSplitBasicString() {
        String s = "2012/3/3";

        int[] numbers = BestBefore.splitDate(s);

        assertEquals(3, numbers.length);
        assertEquals(2012, numbers[0]);
        assertEquals(3, numbers[1]);
        assertEquals(3, numbers[2]);
    }

    @Test
    public void testSplitPaddedNumbersString() {
        String s = "0012/03/04";

        int[] numbers = BestBefore.splitDate(s);

        assertEquals(3, numbers.length);
        assertEquals(12, numbers[0]);
        assertEquals(3, numbers[1]);
        assertEquals(4, numbers[2]);
    }

    @Test
    public void testIsALeapYear() {
        assertTrue(BestBefore.isALeapYear(2012));
        assertTrue(BestBefore.isALeapYear(2008));
        assertTrue(BestBefore.isALeapYear(2000));
        assertFalse(BestBefore.isALeapYear(2100));
    }

    @Test
    public void testGetDaysNumber() {
        assertEquals(31, BestBefore.getDaysNumber(1, 2000));
        assertEquals(29, BestBefore.getDaysNumber(2, 2000));
        assertEquals(28, BestBefore.getDaysNumber(2, 2001));
        assertEquals(31, BestBefore.getDaysNumber(3, 2000));
        assertEquals(30, BestBefore.getDaysNumber(4, 2000));
        assertEquals(31, BestBefore.getDaysNumber(5, 2000));
        assertEquals(31, BestBefore.getDaysNumber(7, 2000));
        assertEquals(31, BestBefore.getDaysNumber(8, 2000));
        assertEquals(30, BestBefore.getDaysNumber(9, 2000));
    }

    @Test
    public void testToFourDigitsYear() {
        assertEquals(2000, BasicDate.toFourDigitsYear(0));
        assertEquals(2001, BasicDate.toFourDigitsYear(1));
        assertEquals(2042, BasicDate.toFourDigitsYear(42));
        assertEquals(2043, BasicDate.toFourDigitsYear(2043));
    }

    @Test
    public void testCanBeAYearFourDigits() {
        assertTrue(BestBefore.canBeAYear(2012));

        assertFalse(BestBefore.canBeAYear(3012));
        assertFalse(BestBefore.canBeAYear(1900));
    }

    @Test
    public void testCanBeAYearTwoDigits() {
        assertTrue(BestBefore.canBeAYear(12));
        assertTrue(BestBefore.canBeAYear(99));
    }

    @Test
    public void testCanBeAYearOneDigit() {
        assertTrue(BestBefore.canBeAYear(0));
        assertTrue(BestBefore.canBeAYear(5));
    }

    @Test
    public void testCanBeAMonthTwoDigits() {
        assertTrue(BestBefore.canBeAMonth(10));
        assertTrue(BestBefore.canBeAMonth(12));
        assertFalse(BestBefore.canBeAMonth(13));
    }

    @Test
    public void testCanBeAMonthOneDigit() {
        assertTrue(BestBefore.canBeAMonth(1));
        assertTrue(BestBefore.canBeAMonth(9));
        assertFalse(BestBefore.canBeAMonth(0));
    }

    @Test
    public void testCanBeADayTwoDigitsWithMonthAndYear() {
        assertTrue(BestBefore.canBeADay(31, 1, 2001));
        assertFalse(BestBefore.canBeADay(31, 4, 2001));
        assertTrue(BestBefore.canBeADay(29, 2, 2000));
        assertFalse(BestBefore.canBeADay(29, 2, 2001));
    }

    @Test
    public void testBestBeforeNoPossibleMonth() {
        assertEquals("31/0/73 is illegal", BestBefore.bestBefore("31/0/73"));
        assertEquals("31/13/73 is illegal", BestBefore.bestBefore("31/13/73"));
        assertEquals("20/20/20 is illegal", BestBefore.bestBefore("20/20/20"));
    }

    @Test
    public void testBestBeforeNoPossibleDay() {
        assertEquals("31/9/73 is illegal", BestBefore.bestBefore("31/9/73"));
        assertEquals("32/7/73 is illegal", BestBefore.bestBefore("32/7/73"));
        assertEquals("30/2002/02 is illegal", BestBefore.bestBefore("30/2002/02"));
    }

    @Test
    public void testBestBeforeLeapYears() {
        assertEquals("2000-02-29", BestBefore.bestBefore("29/2/0"));
        assertEquals("2002-04-29", BestBefore.bestBefore("2/04/29"));

        assertEquals("29/2005/2 is illegal", BestBefore.bestBefore("29/2005/2"));
    }

    @Test
    public void testIsEarlier() {
        BasicDate bd = new BasicDate(20, 9, 2009);

        assertTrue(bd.isEarlierThan(21,  9, 2009));
        assertTrue(bd.isEarlierThan( 5, 10, 2009));
        assertTrue(bd.isEarlierThan( 1,  1, 2010));

        assertFalse(bd.isEarlierThan(19,  9, 2009));
        assertFalse(bd.isEarlierThan(21,  8, 2009));
        assertFalse(bd.isEarlierThan(21, 10, 2008));
    }

    @Test
    public void testBasicDateToString() {
        assertEquals("2067-02-04", new BasicDate(4, 2, 67).toString());
        assertEquals("2067-02-04", new BasicDate(4, 2, 2067).toString());

        assertEquals("2000-02-04", new BasicDate(4, 2, 2000).toString());
        assertEquals("2000-02-04", new BasicDate(4, 2, 0).toString());
    }

    @Test
    public void testBestBeforeGoodDate() {
        assertEquals("2067-02-04", BestBefore.bestBefore("02/4/67"));
        assertEquals("2001-02-03", BestBefore.bestBefore("3/2/1"));
        assertEquals("2010-10-10", BestBefore.bestBefore("10/10/10"));
        assertEquals("2030-02-02", BestBefore.bestBefore("30/2/02"));
    }
}
