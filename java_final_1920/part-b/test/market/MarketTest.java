package market;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import agents.Consumer;
import agents.PencilManufacturer;
import agents.RawGraphiteSupplier;
import agents.RawPlasticSupplier;
import agents.RecycleCenter;
import domain.Agent;
import domain.producttypes.Product;
import domain.producttypes.RawMaterial.Origin;
import goods.Pencil;
import goods.RawGraphite;
import goods.RawPlastic;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import testutils.MockAgent;

public class MarketTest {

  private Market market;
  private Agent agent0, agent1;
  private RawGraphite newGraphite1, newGraphite2, recycledGraphite1, recycledGraphite2;
  private RawPlastic newPlastic1, newPlastic2, recycledPlastic1, recycledPlastic2;

  @Before
  public void setup() {
    market = new MarketImpl();

    newGraphite1 = new RawGraphite(Origin.NEW);
    newGraphite2 = new RawGraphite(Origin.NEW);
    recycledGraphite1 = new RawGraphite(Origin.RECYCLED);
    recycledGraphite2 = new RawGraphite(Origin.RECYCLED);

    newPlastic1 = new RawPlastic(Origin.NEW);
    newPlastic2 = new RawPlastic(Origin.NEW);
    recycledPlastic1 = new RawPlastic(Origin.RECYCLED);
    recycledPlastic2 = new RawPlastic(Origin.RECYCLED);

    agent0 = new MockAgent(market);
    agent1 = new MockAgent(market);
  }

  @Test
  public void marketHasInitiallyNoGoods() {
    assertTrue(market.buyRawGraphite().isEmpty());
    assertTrue(market.buyRawPlastic().isEmpty());
    assertTrue(market.buyPencil().isEmpty());
    assertTrue(market.collectDisposedGood().isEmpty());
  }

  @Test
  public void productsDoNotGetLost() {
    marketHasInitiallyNoGoods();

    market.sellRawGraphite(newGraphite1, agent0);
    market.sellRawGraphite(recycledGraphite2, agent1);
    market.sellRawGraphite(newGraphite2, agent1);
    market.sellRawGraphite(recycledGraphite1, agent0);

    Set<RawGraphite> boughtSet = new HashSet<>();

    Optional<RawGraphite> bought = market.buyRawGraphite();
    assertTrue(bought.isPresent());
    boughtSet.add(bought.get());

    bought = market.buyRawGraphite();
    assertTrue(bought.isPresent());
    boughtSet.add(bought.get());

    bought = market.buyRawGraphite();
    assertTrue(bought.isPresent());
    boughtSet.add(bought.get());

    bought = market.buyRawGraphite();
    assertTrue(bought.isPresent());
    boughtSet.add(bought.get());

    bought = market.buyRawGraphite();
    assertFalse(bought.isPresent());

    assertEquals(
        Set.of(newGraphite1, newGraphite2, recycledGraphite1, recycledGraphite2), boughtSet);
  }

  @Test
  public void trashDoesNotGetLost() {
    marketHasInitiallyNoGoods();

    var pencil1 = new Pencil(newGraphite1, recycledPlastic1, newPlastic1);
    var pencil2 = new Pencil(newGraphite2, recycledPlastic2, newPlastic2);
    market.disposePencil(pencil1, agent0);
    market.disposePencil(pencil2, agent1);

    Set<Product> boughtSet = new HashSet<>();

    Optional<Product> bought = market.collectDisposedGood();
    assertTrue(bought.isPresent());
    boughtSet.add(bought.get());

    bought = market.collectDisposedGood();
    assertTrue(bought.isPresent());
    boughtSet.add(bought.get());

    bought = market.collectDisposedGood();
    assertFalse(bought.isPresent());

    assertEquals(Set.of(pencil1, pencil2), boughtSet);
  }

  @Test
  public void recycledRawMaterialsFirstAndThenAgentsPriority() {
    marketHasInitiallyNoGoods();

    market.sellRawGraphite(newGraphite1, agent0);
    market.sellRawGraphite(recycledGraphite2, agent1);
    market.sellRawGraphite(newGraphite2, agent1);
    market.sellRawGraphite(recycledGraphite1, agent0);

    Optional<RawGraphite> bought = market.buyRawGraphite();
    assertTrue(bought.isPresent());
    assertEquals(recycledGraphite2, bought.get());

    bought = market.buyRawGraphite();
    assertTrue(bought.isPresent());
    assertEquals(recycledGraphite1, bought.get());

    bought = market.buyRawGraphite();
    assertTrue(bought.isPresent());
    assertEquals(newGraphite2, bought.get());

    bought = market.buyRawGraphite();
    assertTrue(bought.isPresent());
    assertEquals(newGraphite1, bought.get());

    bought = market.buyRawGraphite();
    assertFalse(bought.isPresent());
  }

  @Test
  public void lifeCycle() {
    final var graphiteSupplier = new RawGraphiteSupplier(1, market);
    final var plasticSupplier = new RawPlasticSupplier(1, market);
    final var pencilManufacturer = new PencilManufacturer(1, market);
    final var consumer = new Consumer(1, market);
    final var recycleCenter = new RecycleCenter(1, market);

    marketHasInitiallyNoGoods();

    // New raw materials
    graphiteSupplier.doAction();
    plasticSupplier.doAction();
    plasticSupplier.doAction();

    // Manufacturing, consume, recycle
    pencilManufacturer.doAction();
    consumer.doAction();
    recycleCenter.doAction();

    // Second manufacturing with all recycled materials (no new materials added)
    pencilManufacturer.doAction();
    consumer.doAction();
    recycleCenter.doAction();

    // The market is empty
    marketHasInitiallyNoGoods();
  }
}
