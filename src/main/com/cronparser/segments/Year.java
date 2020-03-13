package com.cronparser.segments;

public class Year extends Base {
  public Year(String expression) {
    super(expression);
    // TODO:: get epoch min/maxmium
    this.minimum = 1970;
    this.maximum = 2076;
  }
}
