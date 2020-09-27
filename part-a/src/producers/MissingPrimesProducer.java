package producers;

public class MissingPrimesProducer implements IntegerProducer {

  private static final int PRIME_2 = 2;
  private static final int PRIME_5 = 5;
  private int curr;

  public MissingPrimesProducer() {
    curr = 1;
  }

  @Override
  public Integer next() {
    while (invalid()) {
      curr++;
    }
    return curr++;
  }

  @Override
  public boolean hasNext() {
    return true;
  }

  private boolean invalid() {
    return (curr % (PRIME_2 * PRIME_5) != 0) && (curr % PRIME_2 == 0 || curr % PRIME_5 == 0);
  }

}
