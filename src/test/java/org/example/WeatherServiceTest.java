package org.example;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

/// Regression tests for the weather analyzer workflow.
record WeatherServiceTest() {

    /// Confirms that the sample CSV file is parsed into immutable records.
    @Test
    void loadsWeatherReadings() throws Exception {
        var readings = WeatherService.load(Path.of("weatherdata.csv"));

        assertEquals(15, readings.size());
        assertEquals(32.5, readings.getFirst().temperature());
    }

    /// Confirms that the assignment metrics are derived correctly for August.
    @Test
    void calculatesRequiredMetrics() throws Exception {
        var summary = WeatherService.analyze(
                WeatherService.load(Path.of("weatherdata.csv")),
                new AppSettings(Path.of("weatherdata.csv"), Month.AUGUST, 30.0));

        assertEquals(30.95, summary.averageTemperature(), 0.001);
        assertEquals(6, summary.thresholdDays().size());
        assertEquals(7, summary.rainyDayCount());
    }

    /// Confirms that the enhanced switch-based categorization matches the thresholds.
    @Test
    void categorizesDaysByTemperature() {
        assertEquals("Hot", new WeatherReading(LocalDate.parse("2023-08-02"), 35.0, 60, 0.2).category());
        assertEquals("Warm", new WeatherReading(LocalDate.parse("2023-08-01"), 32.5, 65, 0.0).category());
        assertEquals("Cold", new WeatherReading(LocalDate.parse("2023-09-03"), 19.5, 80, 6.2).category());
    }
}
