package com.cronparser.parsers;

import static org.junit.Assert.assertEquals;

import com.cronparser.segments.Day;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExactTest {
  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testDayExactParserPossibilities()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Day("1");
    assertEquals(d.parse(), List
      .of(1));
  }

  @Test
  public void testDayExactParserWithException()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Day("59");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("The value for segment is more than the maximum allowed");
    d.parse();
  }
}
