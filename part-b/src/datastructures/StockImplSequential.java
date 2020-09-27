package datastructures;

import domain.Agent;
import domain.producttypes.ExchangeableGood;
import java.util.Optional;

public class StockImplSequential<E extends ExchangeableGood> implements Stock<E> {

  private Node<E> root;
  private int size;

  @Override
  public void push(E item, Agent agent) {
    if (root == null) {
      root = new Node<>(agent.id, item);
    } else {
      push(root, item, agent);
    }
    size++;
  }

  private void push(Node<E> subtree, E item, Agent agent) {
    if (subtree.getAgentID() == agent.id) {
      // node already in, add item to node
      subtree.addItem(item);
    } else if (subtree.getAgentID() > agent.id) {
      // check left (smaller key)
      if (subtree.getLeft() == null) {
        // end of path, node not in, create new node
        subtree.setLeft(new Node<>(agent.id, item));
      } else {
        // check down left subtree
        push(subtree.getLeft(), item, agent);
      }
    } else {
      // check right (larger key)
      if (subtree.getRight() == null) {
        // end of path, node not in, create new node
        subtree.setRight(new Node<>(agent.id, item));
      } else {
        // check down right subtree
        push(subtree.getRight(), item, agent);
      }
    }
  }

  @Override
  public Optional<E> pop() {
    // Hint: always returns a product from the highest priority node. If a node gets to zero
    // products, it should be removed. Because this structure is a BST with nodes sorted by
    // agent.id,
    // the highest priority node should be the rightmost node, which can only be either a leaf or a
    // node with a single child (the left one).
    if (size == 0) {
      return Optional.empty();
    }
    Node<E> max = findMax();
    E item = max.getItem();
    if (max.empty()) {
      removeMax(max);
    }
    size--;
    return Optional.of(item);
  }

  private Node<E> findMax() {
    assert root != null;
    Node<E> curr = root;
    while (curr.getRight() != null) {
      curr = curr.getRight();
    }
    return curr;
  }

  private void removeMax(Node<E> max) {
    assert max != null;
    if (root == max) {
      root = root.getLeft();
    } else {
      removeMax(root, max);
    }
  }

  private void removeMax(Node<E> subtree, Node<E> max) {
    assert subtree != null && max != null;
    if (subtree.getRight() == max) {
      subtree.setRight(max.getLeft());
    } else {
      removeMax(subtree.getRight(), max);
    }
  }

  @Override
  public int size() {
    // Hint: it is just an integer that needs incrementing/decrementing...
    return size;
  }
}
