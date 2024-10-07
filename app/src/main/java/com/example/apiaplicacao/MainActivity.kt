package com.example.apiaplicacao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostAdapter
    private var postList = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PostAdapter(postList)
        recyclerView.adapter = adapter

        retornarPosts()
    }

    private fun retornarPosts(){
        val api = RetrofitClient.instance.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            api.getPosts().enqueue(object : Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>){
                    if (response.isSuccessful){
                        response.body()?.let{
                            postList.addAll(it)
                            adapter.notifyDataSetChanged()
                        }
                    }else{
                        Toast.makeText(this@MainActivity, "Falha ao recuperar dados", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<List<Post>>, t: Throwable){
                    Toast.makeText(this@MainActivity, "ERRO: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}