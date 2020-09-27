package market;

import datastructures.StockImpl;
import domain.Agent;
import domain.producttypes.Product;
import domain.producttypes.RawMaterial.Origin;
import goods.Pencil;
import goods.RawGraphite;
import goods.RawPlastic;
import java.util.Optional;
import org.checkerframework.checker.nullness.qual.Raw;

public class MarketImpl implements Market {

  private StockImpl<Pencil> pencils;
  private StockImpl<Product> disposed;
  private StockImpl<RawGraphite> newRawGraphite;
  private StockImpl<RawGraphite> recycledRawGraphite;
  private StockImpl<RawPlastic> newRawPlastic;
  private StockImpl<RawPlastic> recycledRawPlastic;

  public MarketImpl() {
    pencils = new StockImpl<>();
    disposed = new StockImpl<>();
    newRawGraphite = new StockImpl<>();
    recycledRawGraphite = new StockImpl<>();
    newRawPlastic = new StockImpl<>();
    recycledRawPlastic = new StockImpl<>();
  }

  @Override
  public void sellRawPlastic(RawPlastic item, Agent agent) {
    if (item.origin == Origin.NEW) {
      newRawPlastic.push(item, agent);
    } else {
      recycledRawPlastic.push(item, agent);
    }
  }

  @Override
  public Optional<RawPlastic> buyRawPlastic() {
    if (recycledRawPlastic.size() == 0) {
      return newRawPlastic.pop();
    }
    return recycledRawPlastic.pop();
  }

  @Override
  public void sellRawGraphite(RawGraphite item, Agent agent) {
    if (item.origin == Origin.NEW) {
      newRawGraphite.push(item, agent);
    } else {
      recycledRawGraphite.push(item, agent);
    }
  }

  @Override
  public Optional<RawGraphite> buyRawGraphite() {
    if (recycledRawGraphite.size() == 0) {
      return newRawGraphite.pop();
    }
    return recycledRawGraphite.pop();
  }

  @Override
  public void sellPencil(Pencil item, Agent agent) {
    pencils.push(item, agent);
  }

  @Override
  public Optional<Pencil> buyPencil() {
    return pencils.pop();
  }

  @Override
  public void disposePencil(Pencil item, Agent agent) {
    disposed.push(item, agent);
  }

  @Override
  public Optional<Product> collectDisposedGood() {
    return disposed.pop();
  }
}
