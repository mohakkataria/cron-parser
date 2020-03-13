package com.cronparser.parsers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Step extends com.cronparser.parsers.Base {

  public Step(com.cronparser.segments.Base segment) {
    super(segment);
  }

  @Override
  public List<Integer> generatePossibilities() {
    String[] steps = this.segment.getExpression().split("/");

    if (steps.length != 2) {
      throw new RuntimeException(
        "Step does not have valid expression : " + this.segment.getExpression());
    }

    if (steps[0].equals("*")) {
      steps[0] = this.segment.getMinimum().toString();
    }

    Integer[] stepSegments;
    try {
      stepSegments = new Integer[]{Integer.valueOf(steps[0]), Integer.valueOf(steps[1])};
    } catch (Exception e) {
      throw new RuntimeException("Step does not have valid expression : " + this.segment.getExpression());
    }

    if (stepSegments[1] > this.segment.getMaximum()) {
      throw new RuntimeException("Step size is more than maximum value");
    }

    if (stepSegments[0] > this.segment.getMaximum()) {
      throw new RuntimeException("Step start is more than maximum value");
    }

    return IntStream.iterate(stepSegments[0], n -> n + stepSegments[1])
      .limit((this.segment.getMaximum() - stepSegments[0])/ stepSegments[1] + 1).boxed().collect(Collectors.toList());
  }
}
