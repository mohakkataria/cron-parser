package com.cronparser;

import com.cronparser.segments.Day;
import com.cronparser.segments.Hour;
import com.cronparser.segments.Minute;
import com.cronparser.segments.Month;
import com.cronparser.segments.Weekday;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class CronParser {

  String expression;
  List minute;
  List hour;
  List day;
  List month;
  List weekday;
  String command;

  CronParser(String expression)
    throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    this.expression = expression;
    this.parse();
  }

  private CronParser parse()
    throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    System.out.println("Expression entered : " + this.expression);
    String[] segments = this.expression.split("\\s+");

    if (segments.length != 6) {
      throw new RuntimeException("There must be 6 segments in the expression");
    }

    this.minute = new Minute(segments[0]).parse();
    this.hour = new Hour(segments[1]).parse();
    this.day = new Day(segments[2]).parse();
    this.month = new Month(segments[3]).parse();
    this.weekday = new Weekday(segments[4]).parse();
    this.command = segments[5];
    return this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("minute        %s", this.minute.stream().map(i -> i.toString()).collect(
      Collectors.joining(" "))));
    sb.append(System.getProperty("line.separator"));
    sb.append(String.format("hour          %s", this.hour.stream().map(i -> i.toString()).collect(
      Collectors.joining(" "))));
    sb.append(System.getProperty("line.separator"));
    sb.append(String.format("day of month  %s", this.day.stream().map(i -> i.toString()).collect(
      Collectors.joining(" "))));
    sb.append(System.getProperty("line.separator"));
    sb.append(String.format("month         %s", this.month.stream().map(i -> i.toString()).collect(
      Collectors.joining(" "))));
    sb.append(System.getProperty("line.separator"));
    sb.append(
      String.format("day of week   %s", this.weekday.stream().map(i -> i.toString()).collect(
        Collectors.joining(" "))));
    sb.append(System.getProperty("line.separator"));
    sb.append(String.format("command       %s", this.command));
    return sb.toString();
  }

  public static void main(String[] args)
    throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    if (args.length != 1) {
      throw new RuntimeException("There should be 1 argument : " + String.join(";", args));
    }

    System.out.println(new CronParser(args[0]).toString());
  }
}
