package producers;

public interface StringProducer extends DataProducer<String> {

  @Override
  String next();

  @Override
  boolean hasNext();

}
