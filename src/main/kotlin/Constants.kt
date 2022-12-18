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