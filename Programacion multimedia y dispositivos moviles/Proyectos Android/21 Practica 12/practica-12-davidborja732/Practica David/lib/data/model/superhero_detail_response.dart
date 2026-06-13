class SuperheroDetailResponse {
  final String status;
  final List<String> urls;

  SuperheroDetailResponse({required this.status, required this.urls});

  factory SuperheroDetailResponse.fromJson(Map<String, dynamic> json) {
    return SuperheroDetailResponse(
      status: json['status'],
      urls: List<String>.from(json['message']),
    );
  }
}
