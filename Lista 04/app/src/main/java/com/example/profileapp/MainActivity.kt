package com.example.profileapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando componentes
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val nameText = findViewById<TextView>(R.id.nameText) // Corrigido o ID
        val descriptionText = findViewById<TextView>(R.id.descriptionText) // Adicionado
        val currentJobText = findViewById<TextView>(R.id.currentJobText)
        val experienceLayout = findViewById<LinearLayout>(R.id.experienceLayout)

        // Definindo informações de perfil
        nameText.text = "Italo Kaua"
        descriptionText.text = "Desenvolvedor de software com 3 anos de experiência."
        currentJobText.text = "Emprego Atual: Engenharia de Software na TechX"

        // Lista de experiências
        val experiencias = listOf(
            "Analista de Sistemas - Empresa A",
            "Desenvolvedor Júnior - Empresa B",
            "Estagiário - Empresa C"
        )

        // Adicionando experiências dinamicamente
        for (experiencia in experiencias) {
            val textView = TextView(this)
            textView.text = experiencia
            textView.textSize = 16f
            experienceLayout.addView(textView)
        }

        // Adicionando o setOnClickListener ao profileImage
        profileImage.setOnClickListener {
            Toast.makeText(this, "Foto de perfil de Italo Kaua", Toast.LENGTH_SHORT).show()
        }
    }
}
