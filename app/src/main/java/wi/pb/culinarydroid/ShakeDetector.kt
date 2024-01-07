package wi.pb.culinarydroid

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class ShakeDetector(private val context: Context, private val onShake: () -> Unit) : SensorEventListener {

    private var lastX: Float = 0.0f
    private var lastY: Float = 0.0f
    private var lastZ: Float = 0.0f

    private var lastUpdate: Long = 0

    private val shakeThreshold = 800 // Wartość prógu potrząsania, można dostosować

    init {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Niepotrzebne w tym przypadku
    }

    override fun onSensorChanged(event: SensorEvent) {
        val curTime = System.currentTimeMillis()

        if ((curTime - lastUpdate) > 100) {
            val diffTime = curTime - lastUpdate
            lastUpdate = curTime

            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val speed = sqrt((x - lastX) * (x - lastX) + (y - lastY) * (y - lastY) + (z - lastZ) * (z - lastZ)) / diffTime * 10000

            if (speed > shakeThreshold) {
                onShake.invoke()
            }

            lastX = x
            lastY = y
            lastZ = z
        }
    }
}
