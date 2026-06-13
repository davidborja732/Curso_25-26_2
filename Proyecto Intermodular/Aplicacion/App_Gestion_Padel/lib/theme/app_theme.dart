import 'package:flutter/material.dart';

// Palette
const kYellow = Color(0xFFD9E600);
const kCream = Color(0xFFEAEFBD);
const kLightGreen = Color(0xFFC9E3AC);
const kGreen = Color(0xFF90BE6D);
const kOrange = Color(0xFFEA9010);
const kOlive = Color(0xFF37371F);
const kReadOnlyFill = Color(0xFFEAEFBD);

final appTheme = ThemeData(
  brightness: Brightness.light,
  scaffoldBackgroundColor: kCream,
  colorScheme: const ColorScheme.light(
    primary: kGreen,
    onPrimary: kOlive,
    secondary: kOrange,
    onSecondary: Colors.white,
    tertiary: kYellow,
    onTertiary: kOlive,
    surface: Colors.white,
    onSurface: kOlive,
    surfaceContainerHighest: kLightGreen,
  ),
  appBarTheme: const AppBarTheme(
    backgroundColor: kOlive,
    foregroundColor: kCream,
    elevation: 0,
    titleTextStyle: TextStyle(
      color: kCream,
      fontSize: 20,
      fontWeight: FontWeight.w900,
      letterSpacing: 1.5,
    ),
  ),
  textTheme: const TextTheme(
    displayLarge: TextStyle(
      color: kOlive,
      fontWeight: FontWeight.w900,
      letterSpacing: -1,
    ),
    displayMedium: TextStyle(color: kOlive, fontWeight: FontWeight.w900),
    titleLarge: TextStyle(
      color: kOlive,
      fontWeight: FontWeight.w700,
      letterSpacing: 0.5,
    ),
    titleMedium: TextStyle(color: kOlive, fontWeight: FontWeight.w600),
    bodyLarge: TextStyle(color: kOlive),
    bodyMedium: TextStyle(color: Color(0xFF555530)),
    labelLarge: TextStyle(
      color: kOlive,
      fontWeight: FontWeight.w800,
      letterSpacing: 1.2,
    ),
  ),
  elevatedButtonTheme: ElevatedButtonThemeData(
    style: ElevatedButton.styleFrom(
      backgroundColor: kGreen,
      foregroundColor: kOlive,
      textStyle: const TextStyle(
        fontWeight: FontWeight.w800,
        letterSpacing: 1.2,
      ),
      shape: const RoundedRectangleBorder(borderRadius: BorderRadius.zero),
      padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 14),
    ),
  ),
  filledButtonTheme: FilledButtonThemeData(
    style: FilledButton.styleFrom(
      shape: const RoundedRectangleBorder(borderRadius: BorderRadius.zero),
      padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 14),
      textStyle: const TextStyle(
        fontWeight: FontWeight.w800,
        letterSpacing: 1.2,
      ),
    ),
  ),
  outlinedButtonTheme: OutlinedButtonThemeData(
    style: OutlinedButton.styleFrom(
      foregroundColor: kOlive,
      side: const BorderSide(color: kGreen, width: 1.5),
      textStyle: const TextStyle(
        fontWeight: FontWeight.w700,
        letterSpacing: 0.8,
      ),
      shape: const RoundedRectangleBorder(borderRadius: BorderRadius.zero),
    ),
  ),
  inputDecorationTheme: const InputDecorationTheme(
    filled: true,
    fillColor: Colors.white,
    labelStyle: TextStyle(color: Color(0xFF777750)),
    focusedBorder: OutlineInputBorder(
      borderSide: BorderSide(color: kGreen, width: 2),
      borderRadius: BorderRadius.zero,
    ),
    enabledBorder: OutlineInputBorder(
      borderSide: BorderSide(color: kLightGreen),
      borderRadius: BorderRadius.zero,
    ),
    errorBorder: OutlineInputBorder(
      borderSide: BorderSide(color: kOrange),
      borderRadius: BorderRadius.zero,
    ),
    focusedErrorBorder: OutlineInputBorder(
      borderSide: BorderSide(color: kOrange, width: 2),
      borderRadius: BorderRadius.zero,
    ),
  ),
  dataTableTheme: const DataTableThemeData(
    dataTextStyle: TextStyle(color: kOlive),
  ),
  searchBarTheme: const SearchBarThemeData(
    shape: WidgetStatePropertyAll(RoundedRectangleBorder()),
  ),
  dividerColor: kLightGreen,
  cardColor: Colors.white,
);
