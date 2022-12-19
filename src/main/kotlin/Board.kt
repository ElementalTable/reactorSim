import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

import javax.swing.JPanel
import javax.swing.Timer

class Board : JPanel(), ActionListener {
    private val boardWidth = 1500
    private val boardHeight = 700

    private val delay = 1

    private val moderator = Moderator.GRAPHITE
    private val coreShape = Shape.SPHERE
    private val coreDimensions = listOf(200.0)
    private val fuelVolume = 30.0
    private val poisonVolume = 0.0
    private val fuelType = Fuel.U235
    private val power0 = 1.0
    private var powerInt = 0.0

    private var inGame = true
    private var timer: Timer? = null
    private var time = 0.0
    private var timeInt = 0.0

    init {
        addKeyListener(TAdapter())
        background = Color.black
        isFocusable = true

        preferredSize = Dimension(boardWidth, boardHeight)
        initGame()
    }

    private fun initGame() {
        timer = Timer(delay, this)
        timer!!.start()
    }

    public override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        doDrawing(g)
    }

    private fun doDrawing(g: Graphics?) {
        var power = RxPower(moderator, coreShape, coreDimensions, fuelVolume, poisonVolume, fuelType, time, power0)
        g?.drawString(power.power.toBigDecimal().toPlainString(), 100, 100)
        g?.drawString(power.keff.toBigDecimal().toPlainString(), 100, 125)
        powerInt = power.power * 1000
        g?.drawLine(timeInt.toInt(), powerInt.toInt(), timeInt.toInt(), powerInt.toInt())
        Toolkit.getDefaultToolkit().sync()
    }

    private inner class TAdapter : KeyAdapter() {
        override fun keyPressed(e: KeyEvent?) {
            //val key = e!!.keyCode

        }
    }
    override fun actionPerformed(e: ActionEvent?) {
        time += 0.001
        timeInt = time*1000
        this.repaint()
    }

    fun plot(y:Double, height:Int, width: Int){

    }
}
