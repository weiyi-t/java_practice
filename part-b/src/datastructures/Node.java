package datastructures;

import java.util.ArrayList;
import java.util.List;

public class Node<E> {

  private final int agentID;
  private List<E> items;

  private Node<E> left;
  private Node<E> right;

  public Node(int agentID, E item) {
    this.agentID = agentID;
    items = new ArrayList<>();
    addItem(item);
  }

  public void addItem(E item) {
    items.add(item);
  }

  public E getItem() {
    return items.remove(0);
  }

  public int getAgentID() {
    return agentID;
  }

  public Node<E> getLeft() {
    return left;
  }

  public Node<E> getRight() {
    return right;
  }

  public void setLeft(Node<E> left) {
    this.left = left;
  }

  public void setRight(Node<E> right) {
    this.right = right;
  }

  public boolean empty() {
    return items.size() == 0;
  }
}
