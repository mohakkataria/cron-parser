package com.cronparser.segments;

public class Month extends Base {
  public Month(String expression) {
    super(expression);
    this.minimum = 1;
    this.maximum = 12;
  }
}
