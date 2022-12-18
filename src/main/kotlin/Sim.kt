import java.awt.EventQueue
import javax.swing.JFrame
class Sim : JFrame() {
    init {
        initUI()
    }

    private fun initUI() {
        add(Board())
        title = "RX Sim"
        isResizable = false

        pack()

        setLocationRelativeTo(null)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>)
            EventQueue.invokeLater {
                val ex = Sim()
                ex.isVisible = true
            }
    }
}