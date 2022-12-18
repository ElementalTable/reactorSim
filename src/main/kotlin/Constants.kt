enum class Shape {
    RECT, SPHERE, CYL
}

enum class Moderator {
    WATER{
        override fun density() = 1.0
        override fun molarMass() = 18.01528
        override fun thermalNeutronCrossBarn() = 0.335
    },
    HEAVY_WATER{
        override fun density() = 1.0635
        override fun molarMass() = 20.0276
        override fun thermalNeutronCrossBarn() = 0.000521
    },
    BERYLLIUM{
        override fun density() = 1.85
        override fun molarMass() = 9.012182
        override fun thermalNeutronCrossBarn() = 0.00849
    },
    BEO{
        override fun density() = 3.01
        override fun molarMass() = 25.01158
        override fun thermalNeutronCrossBarn() = 0.001309
    },
    GRAPHITE{
        override fun density() = 2.26
        override fun molarMass() = 12.011
        override fun thermalNeutronCrossBarn() = 0.0034
    };
    abstract fun density(): Double
    abstract fun molarMass(): Double
    abstract fun thermalNeutronCrossBarn(): Double
}