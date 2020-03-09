package com.cronparser.segments;

public class Day extends Base {
  public Day(String expression) {
    super(expression);
    this.minimum = 0;
    this.maximum = 31;
  }
}
