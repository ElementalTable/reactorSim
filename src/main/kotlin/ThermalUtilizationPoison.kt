import kotlin.math.PI
import kotlin.math.pow
class ThermalUtilizationPoison (moderator: Moderator, coreShape: Shape, coreDimensions: List<Double>, fuelVolume: Double, poisonVolume: Double){
    private var moderatorVolume = when(coreShape){
        Shape.RECT -> coreDimensions[0]*coreDimensions[1]*coreDimensions[2] - fuelVolume
        Shape.SPHERE -> (4/3)*PI*coreDimensions[0].pow(2) - fuelVolume
        Shape.CYL -> PI*coreDimensions[0].pow(2)*coreDimensions[1] - fuelVolume
    }
    private var macroMod = when(moderator){
        Moderator.WATER -> (Moderator.WATER.density*moderatorVolume*6.022e22*(Moderator.WATER.thermalNeutronCross/1e+24))/Moderator.WATER.molarMass
        Moderator.HEAVY_WATER -> (Moderator.HEAVY_WATER.density*moderatorVolume*6.022e22*(Moderator.HEAVY_WATER.thermalNeutronCross/1e+24))/Moderator.HEAVY_WATER.molarMass
        Moderator.BERYLLIUM -> (Moderator.BERYLLIUM.density*moderatorVolume*6.022e22*(Moderator.BERYLLIUM.thermalNeutronCross/1e+24))/Moderator.BERYLLIUM.molarMass
        Moderator.BEO -> (Moderator.BEO.density*moderatorVolume*6.022e22*(Moderator.BEO.thermalNeutronCross/1e+24))/Moderator.BEO.molarMass
        Moderator.GRAPHITE -> (Moderator.GRAPHITE.density*moderatorVolume*6.022e22*(Moderator.GRAPHITE.thermalNeutronCross/1e+24))/Moderator.GRAPHITE.molarMass
    }
    private var macroPoison = (poisonVolume*2.34*6.022e22*(3847/1e+24))/10.0129369
    private var macroFuel = (fuelVolume*19.1*6.022e22*((587.0+99.3)/1e+24))/235.04393
    var thermalUtilization = macroFuel/(macroFuel+macroPoison+macroMod)
}