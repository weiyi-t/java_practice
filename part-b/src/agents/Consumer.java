package agents;

import domain.Agent;
import goods.Pencil;
import java.util.Optional;
import market.Market;

public class Consumer extends Agent {

  public Consumer(int thinkingTimeInMillis, Market market) {
    super(thinkingTimeInMillis, market);
  }

  @Override
  public void doAction() {
    Optional<Pencil> pencil = market.buyPencil();
    if (pencil.isPresent()) {
      think();
      market.disposePencil(pencil.get(), this);
    }
  }
}
