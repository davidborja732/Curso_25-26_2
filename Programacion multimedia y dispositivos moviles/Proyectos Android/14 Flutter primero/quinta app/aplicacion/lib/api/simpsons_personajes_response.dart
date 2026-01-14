// To parse this JSON data, do
//
//     final simpsonsPersonajesResponse = simpsonsPersonajesResponseFromJson(jsonString);

import 'dart:convert';

SimpsonsPersonajesResponse simpsonsPersonajesResponseFromJson(String str) => SimpsonsPersonajesResponse.fromJson(json.decode(str));

String simpsonsPersonajesResponseToJson(SimpsonsPersonajesResponse data) => json.encode(data.toJson());

class SimpsonsPersonajesResponse {
    int count;
    String next;
    dynamic prev;
    int pages;
    List<Result> results;

    SimpsonsPersonajesResponse({
        required this.count,
        required this.next,
        required this.prev,
        required this.pages,
        required this.results,
    });

    factory SimpsonsPersonajesResponse.fromJson(Map<String, dynamic> json) => SimpsonsPersonajesResponse(
        count: json["count"],
        next: json["next"],
        prev: json["prev"],
        pages: json["pages"],
        results: List<Result>.from(json["results"].map((x) => Result.fromJson(x))),
    );

    Map<String, dynamic> toJson() => {
        "count": count,
        "next": next,
        "prev": prev,
        "pages": pages,
        "results": List<dynamic>.from(results.map((x) => x.toJson())),
    };
}

class Result {
    int id;
    int? age;
    DateTime? birthdate;
    Gender gender;
    String name;
    String occupation;
    String portraitPath;
    List<String> phrases;
    Status status;

    Result({
        required this.id,
        required this.age,
        required this.birthdate,
        required this.gender,
        required this.name,
        required this.occupation,
        required this.portraitPath,
        required this.phrases,
        required this.status,
    });

    factory Result.fromJson(Map<String, dynamic> json) => Result(
        id: json["id"],
        age: json["age"],
        birthdate: json["birthdate"] == null ? null : DateTime.parse(json["birthdate"]),
        gender: genderValues.map[json["gender"]]!,
        name: json["name"],
        occupation: json["occupation"],
        portraitPath: json["portrait_path"],
        phrases: List<String>.from(json["phrases"].map((x) => x)),
        status: statusValues.map[json["status"]]!,
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "age": age,
        "birthdate": "${birthdate!.year.toString().padLeft(4, '0')}-${birthdate!.month.toString().padLeft(2, '0')}-${birthdate!.day.toString().padLeft(2, '0')}",
        "gender": genderValues.reverse[gender],
        "name": name,
        "occupation": occupation,
        "portrait_path": portraitPath,
        "phrases": List<dynamic>.from(phrases.map((x) => x)),
        "status": statusValues.reverse[status],
    };
}

enum Gender {
    FEMALE,
    MALE
}

final genderValues = EnumValues({
    "Female": Gender.FEMALE,
    "Male": Gender.MALE
});

enum Status {
    ALIVE,
    DECEASED
}

final statusValues = EnumValues({
    "Alive": Status.ALIVE,
    "Deceased": Status.DECEASED
});

class EnumValues<T> {
    Map<String, T> map;
    late Map<T, String> reverseMap;

    EnumValues(this.map);

    Map<T, String> get reverse {
            reverseMap = map.map((k, v) => MapEntry(v, k));
            return reverseMap;
    }
}
