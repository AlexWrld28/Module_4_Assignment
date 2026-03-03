# Weather Data Analyzer

This project implements a CSV-based weather analysis application using modern Java features from Java 15 through Java 23. The solution avoids explicit user-defined classes in the application design and instead uses only records, interfaces, sealed interfaces, streams, and lambdas.

## Features

- Parses `weatherdata.csv` containing `Date`, `Temperature`, `Humidity`, and `Precipitation`.
- Calculates the average temperature for a selected month.
- Lists all days with temperatures above a configurable threshold.
- Counts rainy days based on precipitation values above `0.0`.
- Uses an enhanced `switch` expression to categorize each day as `Hot`, `Warm`, or `Cold`.
- Produces a formatted console report using a Java text block.

## Modern Java Features Used

- **Records**: `Main`, `WeatherReading`, `AppSettings`, and `WeatherSummary`.
- **Interfaces with static/private methods**: functional service organization in `WeatherService`.
- **Sealed interfaces**: typed command-line overrides in `SettingOverride`.
- **Enhanced switch expressions**: category selection and command parsing.
- **Pattern matching for switch**: applying typed overrides to settings.
- **Text blocks**: multi-line report formatting in `WeatherSummary`.
- **Lambdas and streams**: CSV parsing and metric calculations.
- **Markdown Javadoc**: `///` doc comments plus `@snippet` examples.
- **`Stream.toList()` and `List.getFirst()`**: concise modern collection handling.

## Project Structure

- `weatherdata.csv`: sample dataset required by the assignment.
- `src/main/java/org/example/Main.java`: record-based application entry point.
- `src/main/java/org/example/WeatherService.java`: CSV loading and analytics logic.
- `src/main/java/org/example/WeatherReading.java`: record for one weather row.
- `src/main/java/org/example/WeatherSummary.java`: record for computed results and reporting.
- `src/main/java/org/example/AppSettings.java`: immutable application settings.
- `src/main/java/org/example/SettingOverride.java`: sealed interface for CLI overrides.
- `src/test/java/org/example/WeatherServiceTest.java`: JUnit tests.

## Requirements

- JDK 23 or newer
- Maven 3.9 or newer

## Running the Application

Run with the default dataset, August analysis, and a `30.0` threshold:

```bash
mvn exec:java -Dexec.mainClass=org.example.Main
```

Run with custom options:

```bash
mvn exec:java -Dexec.mainClass=org.example.Main -Dexec.args="month=9 threshold=25 source=weatherdata.csv"
```

You can also run directly from IntelliJ using `org.example.Main`.

## Example Output

```text
Weather Data Analyzer
=====================
Source File       : weatherdata.csv
Selected Month    : August
Threshold         : 30.0 C
Average Temp      : 30.95 C
Rainy Days        : 7
```

## Testing

```bash
mvn test
```

The tests cover:

- CSV parsing
- monthly average temperature
- threshold filtering
- rainy day counting
- enhanced switch categorization

## Notes

- The default source file is the project-root `weatherdata.csv`.
- The application throws a clear error if the selected month has no data.
- The implementation is intentionally organized around records and interfaces to satisfy the assignment constraint of avoiding explicit classes.
