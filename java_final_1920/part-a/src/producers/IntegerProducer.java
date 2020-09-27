package producers;

public interface IntegerProducer extends DataProducer<Integer> {

  @Override
  Integer next();

  @Override
  boolean hasNext();

}
