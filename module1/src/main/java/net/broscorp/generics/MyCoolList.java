package net.broscorp.generics;

import java.util.function.Function;

public class MyCoolList<T extends Number> {

  int curr;
  T[] arr; // null for non initialized array (has no elements)

  public MyCoolList()
  {
    // non initialized array (has no elements)
    this.curr = -1;
    this.arr = null;
  }

  /**
   * Add object o to the collection.
   * @param o - object to add to the collection.
   */
  public void add(T o) {
    if (arr != null && this.curr <= this.arr.length - 2) {
      this.arr[++this.curr] = o;
    } else {
      this.increaseArray();
      this.add(o);
    }
  }

  private void increaseArray() {
    if (this.arr == null) { // initialize array with size 1
      this.arr = (T[])new Number[1];
    } else { // double array size and copy elements

      // TBD: add check if newLength becomes greater than MAX_INTEGER

      int newLength = this.arr.length * 2;
      T[] arrTmp = this.arr;
      this.arr = (T[])new Number[newLength];
      System.arraycopy(arrTmp, 0, arr, 0, arrTmp.length - 1 + 1);
    }
  }

  /**
   * Fetches the element at position index.
   * @param index - posotion to fetch the element
   * @return the fetched element
   */
  public T get(int index) {

    if (isValidIndex(index)) {
      return this.arr[index];
    } else {
      throw new IllegalArgumentException();
    }
  }

  /**
   * Removes element at position index.
   * @param index - position of the element to be removed.
   * @return the removed element.
   */
  public T remove(int index) {

    if (isValidIndex(index)) {
      T o = this.get(index);

      if (this.arr.length - index >= 0) {
        System.arraycopy(this.arr, index + 1, this.arr, index, this.arr.length - index);
      }
      this.curr--;
      return o;
    } else {
      throw new IllegalArgumentException();
    }
  }

  private boolean isValidIndex(int index) {
    if (this.arr == null) {
      return false;
    } else {
      return index >= 0 && index <= this.curr;
    }
  }

  public MyCoolList map(Function f) {
    throw new UnsupportedOperationException();
  }

  public int size() {
    return this.curr + 1;
  }
}
