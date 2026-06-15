{
    "name": "Hospital Management",
    "summary": "Gestión de pacientes, médicos, ingresos y diagnósticos de un hospital",
    "version": "16.0.1",
    "author": "David",
    "website": "https://example.com",
    "category": "Custom",
    "license": "LGPL-3",
    "depends": ["base", "web"],
    "data": [
        "security/security_groups.xml",
        "security/ir.model.access.csv",

        "data/hospital_room_data.xml",

        "views/hospital_patient_views.xml",
        "views/hospital_doctor_views.xml",
        "views/hospital_room_views.xml",
        "views/hospital_admission_views.xml",
        "views/hospital_diagnosis_views.xml",

        "views/hospital_menus.xml",

        "data/hospital_demo_data.xml",
    ],
    "application": True,
    "installable": True,
    "images": ["static/description/icon.png"],
}
