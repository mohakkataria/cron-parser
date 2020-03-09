package com.cronparser.parsers;

import static com.cronparser.parsers.Base.ANY;
import static org.junit.Assert.assertEquals;

import com.cronparser.segments.Day;
import com.cronparser.segments.Weekday;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.junit.Test;

public class AnyTest {

  @Test
  public void testDayAnyParserPossibilities()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Day(ANY);
    assertEquals(d.parse(), List
      .of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
        25, 26, 27, 28, 29, 30, 31));
  }

  @Test
  public void testWeekdayAnyParserPossibilities()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    com.cronparser.segments.Base d = new Weekday(ANY);
    assertEquals(d.parse(), List
      .of(0, 1, 2, 3, 4, 5, 6));
  }
}
