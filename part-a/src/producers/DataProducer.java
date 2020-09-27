package producers;

public interface DataProducer<T> {

  T next();
  boolean hasNext();

}
