package market;

import domain.Agent;
import domain.producttypes.Product;
import goods.Pencil;
import goods.RawGraphite;
import goods.RawPlastic;
import java.util.Optional;

public interface Market {

  void sellRawPlastic(RawPlastic item, Agent agent);

  Optional<RawPlastic> buyRawPlastic();

  void sellRawGraphite(RawGraphite item, Agent agent);

  Optional<RawGraphite> buyRawGraphite();

  void sellPencil(Pencil item, Agent agent);

  Optional<Pencil> buyPencil();

  void disposePencil(Pencil item, Agent agent);

  Optional<Product> collectDisposedGood();
}
