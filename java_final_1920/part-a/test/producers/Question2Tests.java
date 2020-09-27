package producers;

import org.junit.Assert;
import org.junit.Test;

public class Question2Tests {

    @Test
    public void testDigitCombinationsProducer1() {
      final StringProducer producer = new DigitCombinationsProducer();
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("4", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("5", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("6", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("7", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("44", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("45", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("46", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("47", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("54", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("55", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("56", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("57", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("64", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("65", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("66", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("67", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("74", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("75", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("76", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("77", producer.next());
      Assert.assertTrue(producer.hasNext());
      Assert.assertEquals("444", producer.next());
    }

    @Test
    public void testDigitCombinationsProducer2() {
      final StringProducer producer = new DigitCombinationsProducer();
      for (int i = 0; i < 5000; i++) {
        Assert.assertTrue(producer.hasNext());
        final String value = producer.next();
        switch (i) {
          case 4:
            Assert.assertEquals("7", value);
            break;
          case 5:
            Assert.assertEquals("44", value);
            break;
          case 6:
            Assert.assertEquals("45", value);
            break;
          case 16:
            Assert.assertEquals("67", value);
            break;
          case 17:
            Assert.assertEquals("74", value);
            break;
          case 18:
            Assert.assertEquals("75", value);
            break;
          case 64:
            Assert.assertEquals("667", value);
            break;
          case 65:
            Assert.assertEquals("674", value);
            break;
          case 66:
            Assert.assertEquals("675", value);
            break;
          case 256:
            Assert.assertEquals("6667", value);
            break;
          case 257:
            Assert.assertEquals("6674", value);
            break;
          case 258:
            Assert.assertEquals("6675", value);
            break;
          case 1024:
            Assert.assertEquals("66667", value);
            break;
          case 1025:
            Assert.assertEquals("66674", value);
            break;
          case 1026:
            Assert.assertEquals("66675", value);
            break;
          case 4096:
            Assert.assertEquals("666667", value);
            break;
          case 4097:
            Assert.assertEquals("666674", value);
            break;
          case 4098:
            Assert.assertEquals("666675", value);
            break;
          default:
            break;
        }

      }

    }

}
