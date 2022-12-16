import java.util.Scanner
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.pow

//Keff = e * fNLp * p * thNLp * f * n
var fastFission = 1.0
var fNLp = 1.0
var resonanceEscape = 1.0
var thNLp = 1.0
var thermalUtilization = 1.0
var reproduction = 2.03

enum class Shape {
    SLAB, RECT, SPHERE, INFCYL, CYL
}

enum class Moderator {
    WATER, HEAVY_WATER, BERYLLIUM, BEO, GRAPHITE
}

fun nLpCalc(coreShape: Shape, moderator: Moderator, coreDimensions: List <Double>, fastQ: Boolean): Double {
    val buckling = when (coreShape) {
        Shape.SLAB -> (PI/coreDimensions[0]).pow(2)
        Shape.RECT -> (PI/coreDimensions[0]).pow(2)+(PI/coreDimensions[1]).pow(2)+(PI/coreDimensions[2]).pow(2)
        Shape.SPHERE -> (PI/coreDimensions[0]).pow(2)
        Shape.INFCYL -> (2.405/coreDimensions[0]).pow(2)
        Shape.CYL -> (2.405/coreDimensions[0]).pow(2)+(PI/coreDimensions[1]).pow(2)
    }
    val fermiAge = when (moderator) {
        Moderator.WATER -> 27.0
        Moderator.HEAVY_WATER -> 131.0
        Moderator.BERYLLIUM -> 102.0
        Moderator.BEO -> 100.0
        Moderator.GRAPHITE -> 368.0
    }
    val thermalDiffusion = when (moderator) {
        Moderator.WATER -> 8.12
        Moderator.HEAVY_WATER -> 24900.0
        Moderator.BERYLLIUM -> 537.0
        Moderator.BEO -> 856.0
        Moderator.GRAPHITE -> 3070.0
    }
    return when (fastQ) {
        true -> exp(-buckling*fermiAge)
        false -> 1/(1+thermalDiffusion*buckling)
    }

}

fun thermalUtilCalc(fuelCross: Double, moderatorCross: Double, poisonCross: Double = 0.0, numberM: Int, numberF: Int, numberP: Int = 0): Double {
    val fuelMacro = fuelCross * numberF
    val moderatorMacro = moderatorCross * numberM
    val poisonMacro = poisonCross * numberP
    return fuelMacro /(fuelMacro+moderatorMacro+poisonMacro)
}

fun userInput(askValue: String): Double {
    val reader = Scanner(System.`in`)
    print("Enter $askValue: ")
    return reader.nextDouble()
}

fun main() {
    //Enter rx shape, dimensions,
    println(nLpCalc(Shape.SPHERE, Moderator.GRAPHITE, listOf(500.0), true))
    val crossSection = userInput("Cross Section")
    thermalUtilization = thermalUtilCalc(585.0, crossSection,0.0,450, 1)
    println(thermalUtilization)
    fastFission
    fNLp
    resonanceEscape
    thNLp
    reproduction
}
