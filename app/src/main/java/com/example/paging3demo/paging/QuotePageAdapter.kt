package com.example.paging3demo.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.paging3demo.R
import com.example.paging3demo.models.Result
import java.util.Objects

class QuotePageAdapter:PagingDataAdapter<Result,QuotePageAdapter.QuoteViewHolder>(COMPARATOR) {

    class QuoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val quote=itemView.findViewById<TextView>(R.id.quote)
    }
    companion object{
        private val COMPARATOR=object:DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                 return oldItem._id==newItem._id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem==newItem
            }
        }
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item=getItem(position)
        if(item!=null){
            holder.quote.text=item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_quote_layout,parent,false)
        return QuoteViewHolder(view)
    }
}