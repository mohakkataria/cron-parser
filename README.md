# Cron Expression Parser

This command line application parses a cron string and expands each field
to show the times at which it will run.

We only consider the standard cron format with five time fields (minute, hour, day of
month, month, and day of week) plus a command. The input will be on a single line.
The cron string is passed as a single argument to the application.

```~$ your-program "*/15 0 1,15 * 1-5 /usr/bin/find"```



## Limitations
- This does not handle the special time strings such as "@yearly"
- This does not handle if the month has less than 31 days or not.

## Instructions to run the program
```
$ ./build_and_run.sh "*/15 0 1,15 * 1-5 /usr/bin/find"
```

The shell script checks if there is a java installation available. If not it throws and error.
Else it checks if the version is >= 11. If yes, then it compiles the java files to ./src/out directory and runs the main class from there. 

The output is formatted as a table with the field name taking the first 14 columns and
the times as a space-separated list following it.

For example, the following input argument:

```./build_and_run.sh "*/15 0 1,15 * 1-5 /usr/bin/find"```

Yields the following output:

```
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```

## Tests

The tests are not exhaustive but are present in the `test/` directory. They do cover basic cases of Parsers and over tests of an expression to the required output.
