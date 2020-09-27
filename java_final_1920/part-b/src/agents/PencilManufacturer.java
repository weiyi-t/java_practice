package agents;

import domain.Agent;
import goods.Pencil;
import goods.RawGraphite;
import goods.RawPlastic;
import java.util.Optional;
import market.Market;

public class PencilManufacturer extends Agent {

  public PencilManufacturer(int thinkingTimeInMillis, Market market) {
    super(thinkingTimeInMillis, market);
  }

  @Override
  public void doAction() {
    Optional<RawGraphite> graphite1;
    Optional<RawPlastic> plastic1, plastic2;
    do {
      graphite1 = market.buyRawGraphite();
    } while (graphite1.isEmpty());
    do {
      plastic1 = market.buyRawPlastic();
    } while (plastic1.isEmpty());
    do {
      plastic2 = market.buyRawPlastic();
    } while (plastic2.isEmpty());
    Pencil pencil = new Pencil(graphite1.get(), plastic1.get(), plastic2.get());
    market.sellPencil(pencil, this);
  }
}
