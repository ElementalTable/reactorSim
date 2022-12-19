import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.exp
class NonLeakageProbabilities(coreShape: Shape, moderator:Moderator, coreDimensions: List<Double>) {
    private val buckling = when (coreShape) {
        Shape.RECT -> (PI /coreDimensions[0]).pow(2)+(PI /coreDimensions[1]).pow(2)+(PI /coreDimensions[2]).pow(2)
        Shape.SPHERE -> (PI /coreDimensions[0]).pow(2)
        Shape.CYL -> (2.405/coreDimensions[0]).pow(2)+(PI /coreDimensions[1]).pow(2)
    }
    private val fermiAge = when (moderator) {
        Moderator.WATER -> 27.0
        Moderator.HEAVY_WATER -> 131.0
        Moderator.BERYLLIUM -> 102.0
        Moderator.BEO -> 100.0
        Moderator.GRAPHITE -> 368.0
    }
    private val thermalDiffusion = when (moderator) {
        Moderator.WATER -> 8.12
        Moderator.HEAVY_WATER -> 24900.0
        Moderator.BERYLLIUM -> 537.0
        Moderator.BEO -> 856.0
        Moderator.GRAPHITE -> 3070.0
    }
    val fastNonLeakage = exp(-buckling*fermiAge)
    val thermalNonLeakage = 1/(1+thermalDiffusion*buckling)
}