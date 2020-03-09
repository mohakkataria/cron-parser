package com.cronparser.segments;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class Base<T> {
  Integer minimum;
  Integer maximum;
  String expression;
  List<T> possibilities;

  public Base(String expression) {
    this.expression = expression;
  }

  public Integer getMinimum() {
    return minimum;
  }

  public Integer getMaximum() {
    return maximum;
  }

  public String getExpression() {
    return expression;
  }

  public List<T> parse()
    throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    this.possibilities = (List<T>) com.cronparser.parsers.Base.get(this).generatePossibilities();
    return this.possibilities;
  }
}
