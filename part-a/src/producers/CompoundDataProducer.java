package producers;

import java.util.ArrayList;
import java.util.List;

public class CompoundDataProducer<T> implements DataProducer<T> {

  private List<DataProducer<T>> producers;
  private int currProducer;
  private boolean hasNext;

  public CompoundDataProducer(List<DataProducer<T>> producers) {
    this.producers = new ArrayList<>(producers);
    currProducer = 0;
    hasNext = true;
    updateState();
  }

  private void updateState() {
    if (currProducer < producers.size()) {
      if (!producers.get(currProducer).hasNext()) {
        currProducer++;
        updateState();
      }
    } else {
      hasNext = false;
    }
  }

  @Override
  public T next() {
    if (hasNext()) {
      T result = producers.get(currProducer).next();
      updateState();
      return result;
    }
    throw new UnsupportedOperationException("No more elements");
  }

  @Override
  public boolean hasNext() {
    return hasNext;
  }
}
