package com.example.investidor2.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.AndroidViewModel
import com.example.investidor2.MainActivity
import com.example.investidor2.R
import com.example.investidor2.model.Investimento
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class InvestimentoViewModel(application: Application) : AndroidViewModel(application) {

    private val database = FirebaseDatabase.getInstance()
        .reference.child("investimentos")

    private val _investimentos = MutableStateFlow<List<Investimento>>(emptyList())
    val investimentos: StateFlow<List<Investimento>> = _investimentos

    init {
        monitorarAlteracoes()
    }

    private fun monitorarAlteracoes(){
        database.addChildEventListener(object: ChildEventListener{
            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val nome = snapshot.child("nome").getValue(String::class.java) ?: "Desconhecido"
                val valor = snapshot.child("valor").getValue(Int::class.java) ?: 0

                enviarNotificacao("Investimento Atualizado","$nome - R$$valor")
                carregarInvestimentos()
            }

            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                carregarInvestimentos()
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                carregarInvestimentos()
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Erro ao monitorar alterações: ${error.message}")
            }
        }

        )
    }

    fun carregarInvestimentos(){
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lista = mutableListOf<Investimento>()

                for (item in snapshot.children){
                    val nome = item.child("nome").getValue(String::class.java) ?: "Desconhecido"
                    val valor = item.child("valor").getValue(Int::class.java) ?: 0

                    lista.add(Investimento(nome, valor))
                }
                _investimentos.value = lista
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Erro ao carregar investimentos: ${error.message}")
            }
        }

        )
    }

    @SuppressLint("MissingPermission")
    private fun enviarNotificacao(titulo: String, mensagem: String) {
        val channelId = "investimentos_notifications"
        val notificationId = (System.currentTimeMillis() % 10000).toInt()

        // Criar um canal de notificação (necessário para Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Notificações de Investimentos",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager =
                getApplication<Application>().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Intent para abrir a MainActivity ao clicar na notificação
        val intent = Intent(getApplication(), MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            getApplication(),
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        // Criar e exibir a notificação
        val notification = NotificationCompat.Builder(getApplication(), channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(titulo)
            .setContentText(mensagem)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(getApplication()).notify(notificationId, notification)
    }

}