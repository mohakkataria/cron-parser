package com.cronparser.parsers;


import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class Base {
  protected com.cronparser.segments.Base segment;

  protected static final String ANY = "*";
  protected static final String LIST = ",";
  protected static final String STEP = "/";
  protected static final String RANGE = "-";
  protected static final String EXACT = "e";

  private static final Map<String, Class> expressionParserMap = Map.of(
    ANY, Any.class,
    LIST, List.class,
    STEP, Step.class,
    RANGE, Range.class,
    EXACT, Exact.class
  );

  public Base(com.cronparser.segments.Base segment) {
    this.segment = segment;
  }

  public static Base get(com.cronparser.segments.Base segment)
    throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    String parser = null;
    if (segment.getExpression().equals(ANY)) parser = ANY;
    if (segment.getExpression().matches(".*,.*")) parser = LIST;
    if (segment.getExpression().matches("[0-9]+-[0-9]+")) parser = RANGE;
    if (segment.getExpression().matches(".*\\/.*")) parser = STEP;
    if (segment.getExpression().matches("^[0-9]+$")) parser = EXACT;

    if (parser == null) throw new RuntimeException("Invalid segment expression : " + segment.getExpression());

    return (Base) expressionParserMap
      .get(parser)
      .getDeclaredConstructor(com.cronparser.segments.Base.class).newInstance(segment);
  }

  public abstract java.util.List<Integer> generatePossibilities();
}
