package org.example;

import java.nio.file.Path;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/// Immutable analysis results with text-block reporting output.
public record WeatherSummary(
        Path source,
        Month month,
        double threshold,
        double averageTemperature,
        List<WeatherReading> thresholdDays,
        long rainyDayCount,
        Map<String, Long> categoryBreakdown) {

    /// Formats the computed metrics as a multi-line report.
    ///
    /// @return text-block summary ready for console output
    public String summary() {
        return """
                Weather Data Analyzer
                =====================
                Source File       : %s
                Selected Month    : %s
                Threshold         : %.1f\u00B0C
                Average Temp      : %.2f\u00B0C
                Rainy Days        : %d

                Days Above Threshold
                --------------------
                %s

                Weather Categories
                ------------------
                %s
                """.stripIndent().formatted(
                source,
                month.getDisplayName(TextStyle.FULL, Locale.US),
                threshold,
                averageTemperature,
                rainyDayCount,
                formatThresholdDays(),
                formatCategories());
    }

    /// Formats the matching threshold days for human-readable output.
    ///
    /// @return one line per day or `None`
    public String formatThresholdDays() {
        return thresholdDays.isEmpty()
                ? "None"
                : thresholdDays.stream()
                        .map(reading -> "%s -> %.1f\u00B0C, humidity %d%%, %s".formatted(
                                reading.date(),
                                reading.temperature(),
                                reading.humidity(),
                                reading.category()))
                        .collect(Collectors.joining(System.lineSeparator()));
    }

    /// Formats the category breakdown section.
    ///
    /// @return category totals separated by line breaks
    public String formatCategories() {
        return categoryBreakdown.entrySet().stream()
                .map(entry -> "%s: %d".formatted(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
