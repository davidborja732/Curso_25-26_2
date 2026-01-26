class SuperheroResponse {
  final String status;
  final List<String> urls;

  SuperheroResponse({
    required this.status,
    required this.urls,
  });

  factory SuperheroResponse.fromJson(Map<String, dynamic> json) {
    return SuperheroResponse(
      status: json['status'],
      urls: List<String>.from(json['message']),
    );
  }
}
