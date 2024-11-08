package com.example.lifecycleactivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflando o layout XML
        setContentView(R.layout.activity_main)

        Log.d("LifecycleDemo", "onCreate chamado(estamos no log)")
        Toast.makeText(this, "onCreate chamado", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.d("LifecycleDemo", "onStart chamado")
        Toast.makeText(this, "onStart chamado", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifecycleDemo", "onResume chamado")
        Toast.makeText(this, "onResume chamado", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifecycleDemo", "onPause chamado")
        Toast.makeText(this, "onPause chamado", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifecycleDemo", "onStop chamado(no logs)")
        Toast.makeText(this, "onStop chamado", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        Log.d("LifecycleDemo", "onDestroy chamado(no logs)")
        super.onDestroy()
    }
}
