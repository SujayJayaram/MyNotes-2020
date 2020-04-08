package com.top30javainterviewquestions.reversealinkedlist;

public class Node<T> {
   private Node<T> next;
   private T val;

   public Node(T val) {
       this.val = val;
       this.next = null;
   }

   public Node<T> getNext() {
       return next;
   }

   public void setNext(Node<T> node) {
       next = node;
   }

   public T getValue() {
       return val;
   }
}
