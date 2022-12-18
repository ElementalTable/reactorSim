import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

import javax.swing.JPanel

class Board : JPanel(), ActionListener {
    private val boardWidth = 300
    private val boardHeight = 300

    init {
        addKeyListener(TAdapter())
        background = Color.black
        isFocusable = true

        preferredSize = Dimension(boardWidth, boardHeight)
        initGame()
    }

    private fun initGame() {

    }

    public override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)

        doDrawing(g)
    }

    private fun doDrawing(g: Graphics?) {
        g?.drawString("Hello World", 150 , 150)
    }
    private inner class TAdapter : KeyAdapter() {
        override fun keyPressed(e: KeyEvent?) {
            //val key = e!!.keyCode

        }
    }

    override fun actionPerformed(e: ActionEvent?) {

    }
}