package com.cronparser.parsers;

import static org.junit.Assert.assertEquals;

import com.cronparser.segments.Minute;
import com.cronparser.segments.Weekday;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ListTest {

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();

  @Test
  public void testListPossibilities()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Minute("1,5");
    assertEquals(d.parse(), List.of(1, 5));
  }

  @Test
  public void testListWithInvalidElement()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Minute("a,15");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("Invalid segment expression : a");
    d.parse();
  }

  @Test
  public void testListWithMoreThanMaximumValue()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Weekday("1,8");
    exceptionRule.expect(RuntimeException.class);
    exceptionRule.expectMessage("The value for segment is more than the maximum allowed");
    d.parse();
  }

  @Test
  public void testListWithValidComplexValues()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Weekday("1-2,5-6");
    assertEquals(d.parse(), List.of(1, 2, 5, 6));
  }

  @Test
  public void testListWithValidComplexValues2()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Weekday("1-4,3-6");
    assertEquals(d.parse(), List.of(1, 2, 3, 4, 5, 6));
  }
}
