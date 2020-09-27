package producers;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class Question5Tests {

    class AnIntegerProducer implements DataProducer<Integer> {
      private final int bound;
      private int current;

      AnIntegerProducer(int bound) {
        this.bound = bound;
        this.current = 0;
      }

      @Override
      public Integer next() {
        if (current < bound) {
          current++;
          return current - 1;
        }
        throw new UnsupportedOperationException();
      }

      @Override
      public boolean hasNext() {
        return current < bound;
      }
    }

    class NothingProducer implements DataProducer<Integer> {

      @Override
      public Integer next() {
        throw new UnsupportedOperationException();
      }

      @Override
      public boolean hasNext() {
        return false;
      }
    }

    @Test
    public void testCompoundProducer1() {
      final DataProducer<Integer> producer = new CompoundDataProducer<>(
              Arrays.asList(new AnIntegerProducer(1),
                            new AnIntegerProducer(2),
                            new AnIntegerProducer(3)));
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(0, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(0, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(1, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(0, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(1, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(2, producer.next().intValue());
    }

    @Test
    public void testCompoundProducer2() {
      final DataProducer<Integer> producer = new CompoundDataProducer<>(
              Arrays.asList(
                      new NothingProducer(),
                      new NothingProducer(),
                      new AnIntegerProducer(1),
                      new NothingProducer(),
                      new AnIntegerProducer(2),
                      new NothingProducer(),
                      new AnIntegerProducer(3),
                      new NothingProducer(),
                      new NothingProducer()));
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(0, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(0, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(1, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(0, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(1, producer.next().intValue());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals(2, producer.next().intValue());
    }

}
