package com.appsnica.consumodeapispblicas

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appsnica.consumodeapispblicas.databinding.ActivityMainBinding
import com.github.kittinunf.fuel.Fuel
import com.google.gson.Gson
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    //inicializamos el binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recyclerView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuracion del recicleview
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ApiAdapter(detaList)
        binding.recyclerView.adapter = adapter

        //Al hacer click en el boton, obtendremos datos de la api
        binding.btnGetData.setOnClickListener {
            showLoading(true)
            val url = "https://stephen-king-api.onrender.com/api/books"

            Fuel.get(url).response { request, response, result ->
                result.fold(
                    { data ->
                        val gson = Gson()
                        try {
                            // Recuperamos la respuesta en formato de cadena JSON
                            val jsonString = response.body().asString("application/json")
                            Log.d("ApiResponse", "Respuesta JSON: $jsonString")

                            // Convertimos el JSON en un objeto JSONObject
                            val jsonObject = JSONObject(jsonString)

                            // Asegurarnos de que existe la clave "data"
                            if (jsonObject.has("data")) {
                                val booksArray = jsonObject.getJSONArray("data")

                                // Limpiar la lista actual
                                detaList.clear()

                                // Iterar sobre los elementos del array y convertirlos en ApiResponse
                                for (i in 0 until booksArray.length()) {
                                    val bookJson = booksArray.getJSONObject(i)

                                    // Crear una instancia de ApiResponse solo con los campos que te interesan
                                    val apiResponse = ApiResponse(
                                        id = bookJson.getInt("id"),
                                        Year = bookJson.getInt("Year"),
                                        Title = bookJson.getString("Title"),
                                        handle = bookJson.getString("handle"),
                                        Publisher = bookJson.getString("Publisher"),
                                        ISBN = bookJson.getString("ISBN"),
                                        Pages = bookJson.getInt("Pages")
                                    )

                                    // Añadir el objeto a la lista global
                                    detaList.add(apiResponse)
                                }

                                // Notificar al adaptador que los datos han cambiado
                                binding.recyclerView.adapter?.notifyDataSetChanged()
                                showLoading(false)

                                // Log para confirmar que los datos han sido actualizados
                                Log.d("Adapter", "Datos actualizados en el RecyclerView")
                            } else {
                                Log.e("Api", "No se encontró el array 'data' en la respuesta JSON.")
                            }

                        } catch (e: Exception) {
                            Log.e("Api", "Error al convertir los datos JSON: ${e.message}")
                        }
                    },
                    { error ->
                        Log.e("Api", "Error en la solicitud: ${error.message}")
                        Toast.makeText(this, "Error al obtener datos", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }// aqui termina la funcion


    }//fin del oncreate



   private fun showLoading(show: Boolean) {
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }
}//fin de la clase


// NOTA
//Las funciones van fuera del onCreate

//Lo hice asi por que intente como lo habia hecho anteriormente y no funcionaba no mostraba los datos

//val apiResponse = ApiResponse(
//    id = bookJson.getInt("id"),
//    Year = bookJson.getInt("Year"),
//    Title = bookJson.getString("Title"),
//    handle = bookJson.getString("handle"),
//    Publisher = bookJson.getString("Publisher"),
//    ISBN = bookJson.getString("ISBN"),
//    Pages = bookJson.getInt("Pages")
//)
