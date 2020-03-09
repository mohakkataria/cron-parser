package com.cronparser.segments;

public class Minute extends Base {
  public Minute(String expression) {
    super(expression);
    this.minimum = 0;
    this.maximum = 59;
  }
}
