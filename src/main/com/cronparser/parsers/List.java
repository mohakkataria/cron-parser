package com.cronparser.parsers;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class List extends com.cronparser.parsers.Base {

  public List(com.cronparser.segments.Base segment) {
    super(segment);
  }

  @Override
  public java.util.List<Integer> generatePossibilities() {
    String[] lists = this.segment.getExpression().split(",");

    if (lists.length != 2) {
      throw new RuntimeException(
        "List does not have valid expression : " + this.segment.getExpression());
    }

    return java.util.List.of(lists).stream()
      .flatMap(l -> {
        try {
          return com.cronparser.parsers.Base.get(new com.cronparser.segments.Base(l) {
            @Override
            public Integer getMinimum() {
              return segment.getMinimum();
            }

            @Override
            public Integer getMaximum() {
              return segment.getMaximum();
            }

            @Override
            public String getExpression() {
              return l;
            }
          }).generatePossibilities().stream();
        } catch (Exception e) {
          throw new RuntimeException(e.getMessage());
        }
      }).distinct().sorted().collect(Collectors.toList());
  }
}
