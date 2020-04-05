package com.example.sharedpreferenciesk

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SharedMemory
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var botaoSalvar: Button
    private lateinit var textResultado: TextView
    private lateinit var editNome: EditText
    val ARQUIVO_PREFERENCIA: String = "ArquivoPreferencia"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botaoSalvar = findViewById(R.id.id_buttonSalvar)
        textResultado = findViewById(R.id.id_resultado)
        editNome = findViewById(R.id.id_editNome)

        //Passando valor ao botão salvar
        botaoSalvar.setOnClickListener {
            val preferences: SharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0)
            val editor: SharedPreferences.Editor = preferences.edit() //Basicamente edita nosso arquivo de preferencias

            //Validar o nome
            if (editNome.text.toString().equals("")) {
                Toast.makeText(applicationContext, "Preecha o campo mano!!!", Toast.LENGTH_LONG).show()
            } else {
                val nome: String = editNome.text.toString()
                editor.putString("nome",nome) //"Utilizando a palavra nome como um identificador"
                editor.commit() //Salvando o nome
                textResultado.setText("Ola $nome")
            }
        }

        val preferences: SharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0)

        if(preferences.contains("nome")) {
            val nome: String?
            nome = preferences.getString("nome","Não encontramos esse usuário!")
            textResultado.setText("Olá $nome")
        } else {
            textResultado.setText("Não encontramos esse usuário!")
        }
    }
}
