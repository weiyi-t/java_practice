package producers;

public final class Pair<S, T> {

  private final S elem1;
  private T elem2;

  public Pair(S elem1, T elem2) {
    this.elem1 = elem1;
    this.elem2 = elem2;
  }

  public S getElem1() {
    return elem1;
  }

  public T getElem2() {
    return elem2;
  }

  @Override
  public String toString() {
    return "[" + elem1.toString() + ", " + elem2.toString() + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Pair) {
      Pair<?, ?> other = (Pair<?, ?>) obj;
      return elem1.equals(other.getElem1()) && elem2.equals(other.getElem2());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return elem1.hashCode() + elem2.hashCode();
  }
}
