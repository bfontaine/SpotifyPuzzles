import org.junit.Test;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertFalse;

public class ReversedBinaryTest {

    @Test
    public void testSampleInput1() {
        assertEquals(11, ReversedBinary.reverseBinary(13));
    }

    @Test
    public void testSampleInput2() {
        assertEquals(47, ReversedBinary.reverseBinary(61));
    }


}
