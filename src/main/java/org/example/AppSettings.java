package org.example;

import java.nio.file.Path;
import java.time.Month;

/// Immutable application settings for the analyzer.
public record AppSettings(Path source, Month month, double threshold) {

    /// Builds the default settings used by the assignment.
    ///
    /// @return default source, month, and threshold values
    public static AppSettings defaults() {
        return new AppSettings(Path.of("weatherdata.csv"), Month.AUGUST, 30.0);
    }

    /// Returns a copy with a new source path.
    ///
    /// @param source CSV path to read
    /// @return updated settings
    public AppSettings withSource(Path source) {
        return new AppSettings(source, month, threshold);
    }

    /// Returns a copy with a new month.
    ///
    /// @param month month to analyze
    /// @return updated settings
    public AppSettings withMonth(Month month) {
        return new AppSettings(source, month, threshold);
    }

    /// Returns a copy with a new threshold.
    ///
    /// @param threshold temperature threshold in Celsius
    /// @return updated settings
    public AppSettings withThreshold(double threshold) {
        return new AppSettings(source, month, threshold);
    }
}
