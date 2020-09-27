package producers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class Question1Tests {

    @Test
    public void testFixedIntegerSequenceProducer1() {
      final IntegerProducer producer = new FixedIntegerSequenceProducer();
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(0, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(1, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(2, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(3, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(4, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(5, producer.next().intValue());
    }

    @Test
    public void testFixedIntegerSequenceProducer2() {
      final IntegerProducer producer = new FixedIntegerSequenceProducer();
      final int limit = 30;
      for (int i = 0; i < limit; i++) {
        Assert.assertTrue(producer.hasNext());
        Assert.assertEquals(i, producer.next().intValue());
      }
      Assert.assertFalse(producer.hasNext());
    }

    @Test
    public void testFixedIntegerSequenceProducer3() {
      final IntegerProducer producer = new FixedIntegerSequenceProducer();
      final int limit = 30;
      for (int i = 0; i < limit; i++) {
        producer.next();
      }
      try {
        producer.next();
        Assert.fail("An exception was expected.");
      } catch (UnsupportedOperationException exception) {
        // Good: an exception should have been thrown.
      }
    }

    @Test
    public void testMissingPrimesProducer() {
      final Set<Integer> expected = new HashSet<>(Arrays.asList(
          1, 3, 7, 9, 10, 11, 13, 17, 19,
          20, 21, 23, 27, 29, 30, 31, 33, 37, 39,
          40, 41, 43, 47, 49, 50, 51, 53, 57, 59,
          60, 61, 63, 67, 69, 70, 71, 73, 77, 79,
          80, 81, 83, 87, 89, 90, 91, 93, 97, 99,
          100, 101, 103, 107, 109, 110, 111, 113, 117, 119,
          120, 121, 123, 127, 129, 130, 131, 133, 137, 139,
          140, 141, 143, 147, 149, 150, 151, 153, 157, 159,
          160, 161, 163, 167, 169, 170, 171, 173, 177, 179,
          180, 181, 183, 187, 189, 190, 191, 193, 197, 199,
          200
      ));

      final IntegerProducer producer = new MissingPrimesProducer();
      for (int i = 0; i < 100; i++) {
        Assert.assertTrue(producer.hasNext());
        int element = producer.next();
        Assert.assertTrue("Did not expect " + element, expected.contains(element));
        expected.remove(element);
      }
      Assert.assertTrue("Expected but did not see " + expected, expected.isEmpty());
    }

}
