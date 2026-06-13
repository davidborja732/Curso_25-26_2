import 'dart:ui';

import 'package:flutter/material.dart';

class BlurContainer extends StatelessWidget {
  const BlurContainer({Key? key, required this.child}) : super(key: key);
  final Widget child;
  
  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: .circular(8),
      child: BackdropFilter(
        filter: ImageFilter.blur(sigmaX: 5,sigmaY: 5),
        child: child,
      ),
    );
  }
}