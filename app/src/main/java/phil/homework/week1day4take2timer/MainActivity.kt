package phil.homework.week1day4take2timer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var stopwatch: Stopwatch
    lateinit var btnPlayPause: Button
    lateinit var btnStopReset: Button
    lateinit var timerText: TextView

    val handler: Handler = Handler()
    val runnable: Runnable = object : Runnable {
        override  fun run() {
            timerText.text = stopwatch.printElapsedTime()
            handler.postDelayed(this, 0)
        }
    }
    private fun startRunnable() {
        handler.postDelayed(runnable, 0)
    }
    private fun stopRunnable() {
        handler.removeCallbacks(runnable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stopwatch = Stopwatch()

        btnPlayPause = findViewById(R.id.btnPlayPause)
        btnStopReset = findViewById(R.id.btnStopReset)
        timerText = findViewById(R.id.timerText)

        startRunnable()
    }

    override fun onPause() {
        super.onPause()
        stopRunnable()
    }

    override fun onResume() {
        super.onResume()
        startRunnable()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("stopwatch", stopwatch)
        outState?.putCharSequenceArray("btns", arrayOf(btnPlayPause.text, btnStopReset.text))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        stopwatch = savedInstanceState?.getParcelable("stopwatch") as Stopwatch
        with(savedInstanceState.getCharSequenceArray("btns")){
            btnPlayPause.text = this[0] ?: getString(R.string.start)
            btnStopReset.text = this[1] ?: getString(R.string.reset)
        }
    }

    fun registerPlayPauseClick(view: View){
        if (stopwatch.isRunning) {
            stopwatch.pause()
            btnPlayPause.text = getString(R.string.resume)
            btnStopReset.text = getString(R.string.reset)
        } else {
            stopwatch.start()
            btnPlayPause.text = getString(R.string.pause)
            btnStopReset.text = getString(R.string.stop)
        }
    }

    fun registerStopResetClick(view: View){
        if(stopwatch.isRunning) {
            stopwatch.stop()
            btnPlayPause.text = getString(R.string.start)
            btnStopReset.text = getString(R.string.reset)
        } else {
            stopwatch.reset()
            btnPlayPause.text = getString(R.string.start)
        }
    }
}
