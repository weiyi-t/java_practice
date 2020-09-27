package agents;

import domain.Agent;
import domain.producttypes.RawMaterial.Origin;
import goods.RawGraphite;
import goods.RawPlastic;
import market.Market;

public class RawPlasticSupplier extends Agent {

  public RawPlasticSupplier(int thinkingTimeInMillis, Market market) {
    super(thinkingTimeInMillis, market);
  }

  @Override
  public void doAction() {
    market.sellRawPlastic(new RawPlastic(Origin.NEW), this);
  }
}
