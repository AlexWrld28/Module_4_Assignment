package org.example;

import java.nio.file.Path;
import java.time.Month;
import java.util.Locale;

/// Typed command-line overrides parsed from `key=value` arguments.
public sealed interface SettingOverride
        permits SettingOverride.MonthOverride, SettingOverride.ThresholdOverride, SettingOverride.SourceOverride {

    /// Parses a single `key=value` command-line argument.
    ///
    /// Supported keys are `month`, `threshold`, `source`, `file`, and `path`.
    ///
    /// @param argument raw command-line argument
    /// @return typed override record
    static SettingOverride parse(String argument) {
        var parts = argument.split("=", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException("Arguments must use key=value format: " + argument);
        }

        var key = parts[0].strip().toLowerCase(Locale.ROOT);
        var value = parts[1].strip();

        return switch (key) {
            case "month" -> new MonthOverride(parseMonth(value));
            case "threshold" -> new ThresholdOverride(Double.parseDouble(value));
            case "source", "file", "path" -> new SourceOverride(Path.of(value));
            default -> throw new IllegalArgumentException("Unsupported argument: " + argument);
        };
    }

    /// Parses numeric or named month values.
    ///
    /// @param value month argument value
    /// @return normalized month
    private static Month parseMonth(String value) {
        return value.chars().allMatch(Character::isDigit)
                ? Month.of(Integer.parseInt(value))
                : Month.valueOf(value.toUpperCase(Locale.ROOT));
    }

    /// Month override record.
    ///
    /// @param month requested month
    record MonthOverride(Month month) implements SettingOverride {
    }

    /// Threshold override record.
    ///
    /// @param threshold requested threshold
    record ThresholdOverride(double threshold) implements SettingOverride {
    }

    /// Source override record.
    ///
    /// @param source requested source file
    record SourceOverride(Path source) implements SettingOverride {
    }
}
