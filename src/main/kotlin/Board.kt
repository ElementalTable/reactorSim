import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

import javax.swing.JPanel
import javax.swing.Timer

class Board : JPanel(), ActionListener {
    private val boardWidth = 700
    private val boardHeight = 300

    private val delay = 1

    private val moderator = Moderator.GRAPHITE
    private val coreShape = Shape.SPHERE
    private val coreDimensions = listOf(500.0)
    private val fuelVolume = 100.0
    private var poisonVolume = 10.0
    private val fuelType = Fuel.U235
    private val power0 = 1.0

    private var poisonOut = false
    private var poisonIn = false

    private var timer: Timer? = null
    private var time = 0.0

    init {
        addKeyListener(TAdapter())
        background = Color.white
        isFocusable = true

        preferredSize = Dimension(boardWidth, boardHeight)
        initGame()
    }

    private fun initGame() {
        timer = Timer(delay, this)
        timer!!.start()
    }

    private fun controlSystem() {
        if (poisonIn) poisonVolume += 0.001
        if (poisonOut) poisonVolume -= 0.001
    }

    public override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        doDrawing(g)
    }

    private fun doDrawing(g: Graphics?) {
        val power = RxPower(moderator, coreShape, coreDimensions, fuelVolume, poisonVolume, fuelType, time, power0)
        g?.drawString("Elapsed Time:", 10, 75)
        g?.drawString(time.toBigDecimal().toPlainString(), 100, 75)
        g?.drawString("RX Power:", 10, 100)
        g?.drawString(power.power.toBigDecimal().toPlainString(), 100, 100)
        g?.drawString("Keff:", 10, 125)
        g?.drawString(power.keff.toBigDecimal().toPlainString(), 100, 125)
        g?.drawString("Period:", 10, 150)
        g?.drawString(power.period.toBigDecimal().toPlainString(), 100, 150)
        g?.drawString("Reactivity:", 10, 175)
        g?.drawString(power.reactivity.toBigDecimal().toPlainString(), 100, 175)
        g?.drawString("Poison Volume:", 10, 200)
        g?.drawString(poisonVolume.toBigDecimal().toPlainString(), 100, 200)
        g?.color = Color.black
        Toolkit.getDefaultToolkit().sync()
    }

    private inner class TAdapter : KeyAdapter() {
        override fun keyPressed(e: KeyEvent?) {
            val key = e!!.keyCode
            if (key == KeyEvent.VK_DOWN && !poisonOut) {
                poisonIn = true
                poisonOut = false
            }

            if (key == KeyEvent.VK_UP && !poisonIn) {
                poisonIn = false
                poisonOut = true
            }

            if (key == KeyEvent.VK_SPACE) {
                poisonIn = false
                poisonOut = false
            }
        }
    }
    override fun actionPerformed(e: ActionEvent?) {
        time += 0.01
        controlSystem()
        this.repaint()
    }
}
