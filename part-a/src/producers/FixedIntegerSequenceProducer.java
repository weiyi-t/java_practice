package producers;

public class FixedIntegerSequenceProducer implements IntegerProducer {

  private static final int MAX = 30;
  private int curr;

  public FixedIntegerSequenceProducer() {
    curr = 0;
  }

  @Override
  public Integer next() {
    if (hasNext()) {
      return curr++;
    }
    throw new UnsupportedOperationException("Exceeded max");
  }

  @Override
  public boolean hasNext() {
    return curr < MAX;
  }
}
