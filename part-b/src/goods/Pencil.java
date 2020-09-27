package goods;

import domain.producttypes.Product;
import java.util.Set;

public class Pencil extends Product {

  public Pencil(RawGraphite graphite1, RawPlastic plastic1, RawPlastic plastic2) {
    super(Set.of(graphite1, plastic1, plastic2));
  }
}
