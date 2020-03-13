package com.cronparser.parsers;

import static org.junit.Assert.assertEquals;

import com.cronparser.segments.Minute;
import com.cronparser.segments.Year;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StepTest {
  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testStepParserPossibilities()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Minute("*/15");
    assertEquals(d.parse(), List.of(0, 15,30,45));
  }

  @Test
  public void testStepWithMissingValues()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Minute("*/");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Step does not have valid expression : */");
    d.parse();
  }

  @Test
  public void testStepWithInvalidStepStart()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Minute("a/15");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Step does not have valid expression : a/15");
    d.parse();
  }

  @Test
  public void testStepWithWrongStepStart()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Minute("60/15");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Step start is more than maximum value");
    d.parse();
  }

  @Test
  public void testStepWithWrongStepValue()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Minute("*/60");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Step size is more than maximum value");
    d.parse();
  }

  @Test
  public void testStepWithWrongStepValueForYear()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Year("2074/2");
    assertEquals(d.parse(), List.of(2074, 2076));
  }
}
