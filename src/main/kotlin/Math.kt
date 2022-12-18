//Keff = e * fNLp * p * thNLp * f * n
var fastFission = 1.0
var fNLp = 1.0
var resonanceEscape = 1.0
var thNLp = 1.0
var thermalUtilization = 1.0
var reproduction = 2.03

fun thermalUtilCalcMacro(fuelCross: Double, moderatorCross: Double, poisonCross: Double = 0.0, numberM: Int, numberF: Int, numberP: Int = 0): Double {
    val fuelMacro = fuelCross * numberF
    val moderatorMacro = moderatorCross * numberM
    val poisonMacro = poisonCross * numberP
    return fuelMacro / (fuelMacro + moderatorMacro + poisonMacro)
}

var Keff =