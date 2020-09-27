package producers;

import org.junit.Assert;
import org.junit.Test;

public class Question4Tests {

    class AnotherStringProducer implements DataProducer<String> {

      private int step = 0;

      @Override
      public String next() {
        step++;
        switch (step - 1) {
          case 0:
            return "A";
          case 1:
            return "B";
          case 2:
            return "C";
          default:
            throw new UnsupportedOperationException("Out of strings.");
        }
      }

      @Override
      public boolean hasNext() {
        return step <= 2;
      }
    }

    class AnotherIntegerProducer implements IntegerProducer {

      private int nextValue = 0;

      @Override
      public Integer next() {
        final int result = nextValue;
        nextValue = 1 - nextValue;
        return result;
      }

      @Override
      public boolean hasNext() {
        return true;
      }
    }

    @Test
    public void testAnotherStringProducer() {
      final DataProducer<String> producer = new AnotherStringProducer();
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("A", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("B", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("C", producer.next());
      Assert.assertFalse(producer.hasNext());
      try {
        producer.next();
        Assert.fail("An exception was expected.");
      } catch (UnsupportedOperationException exception) {
        // Good: an exception should have been thrown.
      }
    }

    @Test
    public void testPairProducer1() {
      final PairProducer<String, Integer> producer = new PairProducer<>(
              new AnotherStringProducer(), new AnotherIntegerProducer()
      );

      Assert.assertTrue(producer.hasNext());
      final Pair<String, Integer> p1 = producer.next();
      Assert.assertEquals("A", p1.getElem1());
      Assert.assertEquals(0, p1.getElem2().intValue());
    }

    @Test
    public void testPairProducer2() {
      final PairProducer<String, Integer> producer = new PairProducer<>(
              new AnotherStringProducer(), new AnotherIntegerProducer()
      );

      Assert.assertTrue(producer.hasNext());
      final Pair<String, Integer> p1 = producer.next();
      Assert.assertEquals("A", p1.getElem1());
      Assert.assertEquals(0, p1.getElem2().intValue());

      Assert.assertTrue(producer.hasNext());
      final Pair<String, Integer> p2 = producer.next();
      Assert.assertEquals("B", p2.getElem1());
      Assert.assertEquals(1, p2.getElem2().intValue());

      Assert.assertTrue(producer.hasNext());
      final Pair<String, Integer> p3 = producer.next();
      Assert.assertEquals("C", p3.getElem1());
      Assert.assertEquals(0, p3.getElem2().intValue());

      Assert.assertFalse(producer.hasNext());
    }

}
