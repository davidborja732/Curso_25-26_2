import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class AppTheme{
  ThemeData getTheme()=>ThemeData(
    textTheme: TextTheme(
      titleLarge: GoogleFonts.montserratAlternates(),
      titleMedium: GoogleFonts.montserratAlternates(fontSize: 35),
      titleSmall: GoogleFonts.montserratAlternates(fontSize: 25),
      bodyLarge: GoogleFonts.montserratAlternates(),
      bodyMedium: GoogleFonts.montserratAlternates(fontSize: 35),
      bodySmall: GoogleFonts.montserratAlternates(fontSize: 25),
      
    )
  );
}