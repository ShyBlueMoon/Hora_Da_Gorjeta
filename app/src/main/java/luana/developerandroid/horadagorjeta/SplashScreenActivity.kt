package luana.developerandroid.horadagorjeta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import luana.developerandroid.horadagorjeta.databinding.ActivityMainBinding
import luana.developerandroid.horadagorjeta.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        handlerTrocarTela()
    }

    private fun handlerTrocarTela() {
        Handler(Looper.getMainLooper()).postDelayed( {
            fun trocarTela() {

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            trocarTela()

        }, 3000)
    }
}