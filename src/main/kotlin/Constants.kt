enum class Shape {
    RECT, SPHERE, CYL
}

enum class Moderator(
    val density: Double,
    val molarMass: Double,
    val thermalNeutronCross: Double,
){
    WATER(1.0, 18.01528, 0.335),
    HEAVY_WATER(1.0635, 20.0276, 0.000521),
    BERYLLIUM(1.85, 9.012182, 0.00849),
    BEO(3.01, 25.01158, 0.001309),
    GRAPHITE(2.26,12.011, 0.0034);
}

enum class Fuel(
    val density: Double,
    val molarMass: Double,
    val crossSection: Double,
    val reproductionFactor: Double,
    val fastFissionFactor: Double,
    val resonanceEscape: Double,
    val betaTau: Double){
    U235(19.1, 235.04393, 686.3, 2.07, 1.0, 1.0, 0.0841529)
}