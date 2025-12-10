import 'package:flutter/material.dart';

class GridScreen extends StatelessWidget {
  const GridScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('GridView con bordes')),
      body: GridView.count(
        crossAxisCount: 2, 
        children: [
          Container(
            margin: const EdgeInsets.all(8),
            decoration: BoxDecoration(
              border: Border.all(color: Colors.black, width: 2),
            ),
            child: const Center(child: Text('Item 1')),
          ),
          Container(
            margin: const EdgeInsets.all(8),
            decoration: BoxDecoration(
              border: Border.all(color: Colors.black, width: 2),
            ),
            child: const Center(child: Text('Item 2')),
          ),
          Container(
            margin: const EdgeInsets.all(8),
            decoration: BoxDecoration(
              border: Border.all(color: Colors.black, width: 2),
            ),
            child: const Center(child: Text('Item 3')),
          ),
          Container(
            margin: const EdgeInsets.all(8),
            decoration: BoxDecoration(
              border: Border.all(color: Colors.black, width: 2),
            ),
            child: const Center(child: Text('Item 4')),
          ),
          Container(
            margin: const EdgeInsets.all(8),
            decoration: BoxDecoration(
              border: Border.all(color: Colors.black, width: 2),
            ),
            child: const Center(child: Text('Item 5')),
          ),
          Container(
            margin: const EdgeInsets.all(8),
            decoration: BoxDecoration(
              border: Border.all(color: Colors.black, width: 2),
            ),
            child: const Center(child: Text('Item 6')),
          ),
        ],
      ),
    );
  }
}
