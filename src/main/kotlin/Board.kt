import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

import javax.swing.JPanel
import javax.swing.Timer

class Board : JPanel(), ActionListener {
    private val boardWidth = 300
    private val boardHeight = 300

    private val delay = 1000

    private val moderator = Moderator.GRAPHITE
    private val coreShape = Shape.SPHERE
    private val coreDimensions = listOf(200.0)
    private val fuelVolume = 30.0
    private val poisonVolume = 0.0
    private val fuelType = Fuel.U235
    private var time = 0.0
    private val power0 = 1.0

    private var inGame = true
    private var timer: Timer? = null

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
        Toolkit.getDefaultToolkit().sync()
    }

    private inner class TAdapter : KeyAdapter() {
        override fun keyPressed(e: KeyEvent?) {
            //val key = e!!.keyCode

        }
    }
    override fun actionPerformed(e: ActionEvent?) {
        time += 1.0
        this.repaint()
    }
}
