package org.iesch.intermedio.provider

import org.iesch.intermedio.model.AndroidVersion
// Me creo el repositorio de datos con el que voy a trabajar
class AndroidVersionProvider {
    companion object {
        val androidVersionsList = listOf(
            AndroidVersion("Android 14", "Upside Down Cake", 34, 2023),
            AndroidVersion("Android 13", "Tiramisu", 33, 2022),
            AndroidVersion("Android 12", "Snow Cone", 31, 2021),
            AndroidVersion("Android 11", "Red Velvet Cake", 30, 2020),
            AndroidVersion("Android 10", "Quince Tart", 29, 2019),
            AndroidVersion("Android 9", "Pie", 28, 2018),
            AndroidVersion("Android 8.1", "Oreo", 27, 2017),
            AndroidVersion("Android 8.0", "Oreo", 26, 2017),
            AndroidVersion("Android 7.1", "Nougat", 25, 2016),
            AndroidVersion("Android 7.0", "Nougat", 24, 2016),
            AndroidVersion("Android 6.0", "Marshmallow", 23, 2015),
            AndroidVersion("Android 5.1", "Lollipop", 22, 2015),
            AndroidVersion("Android 5.0", "Lollipop", 21, 2014),
            AndroidVersion("Android 4.4", "KitKat", 19, 2013),
            AndroidVersion("Android 4.3", "Jelly Bean", 18, 2013),
            AndroidVersion("Android 4.2", "Jelly Bean", 17, 2012),
            AndroidVersion("Android 4.1", "Jelly Bean", 16, 2012)
        )
    }
}