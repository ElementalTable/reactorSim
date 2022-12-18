import kotlin.math.exp

//Keff = e * fNLp * p * thNLp * f * n
class RxPower (moderator: Moderator, coreShape: Shape, coreDimensions: List<Double>, fuelVolume: Double, poisonVolume: Double, fuelType: Fuel, time: Double, power0: Double) {
    var keff = when(fuelType){
        Fuel.U235 -> Fuel.U235.fastFissionFactor*NonLeakageProbabilities(coreShape,moderator, coreDimensions).fastNonLeakage*Fuel.U235.resonanceEscape*NonLeakageProbabilities(coreShape,moderator,coreDimensions).thermalNonLeakage*ThermalUtilizationPoison(moderator, coreShape, coreDimensions, fuelVolume, poisonVolume).thermalUtilization*Fuel.U235.reproductionFactor
    }
    var power = when(fuelType){
        Fuel.U235 -> power0*exp(((keff-1)/Fuel.U235.betaTau)*time)
    }
}