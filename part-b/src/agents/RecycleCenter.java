package agents;

import domain.Agent;
import domain.producttypes.Product;
import domain.producttypes.RawMaterial;
import domain.producttypes.RawMaterial.Origin;
import goods.RawGraphite;
import goods.RawPlastic;
import java.util.Optional;
import market.Market;

public class RecycleCenter extends Agent {

  public RecycleCenter(int thinkingTimeInMillis, Market market) {
    super(thinkingTimeInMillis, market);
  }

  @Override
  public void doAction() {
    Optional<Product> disposed = market.collectDisposedGood();
    if (disposed.isPresent()) {
      for (RawMaterial material: disposed.get().getConstituentMaterials()) {
        if (material.origin == Origin.NEW) {
          if (material instanceof RawPlastic) {
            market.sellRawPlastic(new RawPlastic(Origin.RECYCLED), this);
          } else if (material instanceof RawMaterial) {
            market.sellRawGraphite(new RawGraphite(Origin.RECYCLED), this);
          }
        }
      }
    }
  }
}
