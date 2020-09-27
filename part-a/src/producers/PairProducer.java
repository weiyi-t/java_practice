package producers;

public class PairProducer<S, T> implements DataProducer<Pair<S, T>> {

  private DataProducer<S> producerS;
  private DataProducer<T> producerT;

  public PairProducer(DataProducer<S> producerS, DataProducer<T> producerT) {
    this.producerS = producerS;
    this.producerT = producerT;
  }

  @Override
  public Pair<S, T> next() {
    if (hasNext()) {
      return new Pair(producerS.next(), producerT.next());
    }
    throw new UnsupportedOperationException("No next element");
  }

  @Override
  public boolean hasNext() {
    return producerT.hasNext() && producerS.hasNext();
  }
}
