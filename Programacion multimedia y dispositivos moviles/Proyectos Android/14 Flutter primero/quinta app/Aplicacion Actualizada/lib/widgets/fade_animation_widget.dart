
import 'package:flutter/material.dart';

class FadeAnimation extends StatelessWidget {
  const FadeAnimation({
    super.key, 
    this.begin=0.0, 
    this.end=1.0, 
    this.duration=const Duration(microseconds: 3000), 
    this.intervalStart=0.0, 
    this.intervalEnd=1.0, 
    this.curve=Curves.fastEaseInToSlowEaseOut, 
    required this.child});
  final double begin;
  final double end;
  final Duration duration;
  final double intervalStart;
  final double intervalEnd;
  final Curve curve;
  final Widget child;

  @override
  Widget build(BuildContext context) {
    return TweenAnimationBuilder(
      tween: Tween<double>(
        begin: begin,
        end: end
      ), 
      duration: duration, 
      builder: (context,value,child){
        return Opacity(opacity: value,child: child,);
      },
      child: child,
      );
  }
}