package com.example.myapplication
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytets.Highschool
import com.example.mytets.R
import com.example.mytets.SchoolAdapter
import com.example.mytets.school
//import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : Activity() {
    private lateinit var SchoolAdapter: SchoolAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val countries = apiService.getCountries()
                setupRecyclerView(countries)
            } catch (e: Exception) {
                // Handle network request errors
                Log.e("MainActivity", "Error fetching countries: ${e.message}")
            }
        }
    }

    private fun setupRecyclerView(schooles: List<school>) {
        this.SchoolAdapter = SchoolAdapter(schooles)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SchoolAdapter
    }

    interface ApiService {
        @GET("https://data.cityofnewyork.us/resource/s3k6-pzi2.json")
        suspend fun getCountries(): List<school>
    }

}

