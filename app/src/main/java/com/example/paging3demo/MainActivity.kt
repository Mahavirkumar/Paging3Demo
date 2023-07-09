package com.example.paging3demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3demo.paging.QuotePageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter:QuotePageAdapter
    lateinit var quoteViewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.quoteList)
        adapter= QuotePageAdapter()
        quoteViewModel=ViewModelProvider(this).get(QuoteViewModel::class.java)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter=adapter
        quoteViewModel.list.observe(this, Observer {
            adapter.submitData(lifecycle,it)
            Log.d("mahavir",it.toString())
            print(it.toString())
        })
    }
}