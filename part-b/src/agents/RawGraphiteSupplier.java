package agents;

import domain.Agent;
import domain.producttypes.RawMaterial.Origin;
import goods.RawGraphite;
import market.Market;

public class RawGraphiteSupplier extends Agent {

  public RawGraphiteSupplier(int thinkingTimeInMillis, Market market) {
    super(thinkingTimeInMillis, market);
  }

  @Override
  public void doAction() {
    market.sellRawGraphite(new RawGraphite(Origin.NEW), this);
  }
}
