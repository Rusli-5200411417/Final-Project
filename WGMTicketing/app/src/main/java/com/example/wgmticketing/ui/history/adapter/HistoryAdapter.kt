package com.example.wgmticketing.ui.history.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wgmticketing.core.data.source.model.Order
import com.example.wgmticketing.databinding.CardFloatDetailBinding
import com.example.wgmticketing.ui.history.DetailOrderActivity
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("NotifyDataSetChanged")
class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    var data = ArrayList<Order>()

    inner class ViewHolder(val itemBinding: CardFloatDetailBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Order, position: Int) {


            itemBinding.apply {
                val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                val targetFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
                val dateFormat = originalFormat.parse(item.created_at)
                val formattedDate = targetFormat.format(dateFormat)

                val formattedPrice = NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(item.total_amount)

//                item.id.toString()
                orderId.text = item.id_order
                totalTicket.text = item.total_ticket.toString()
                totalGross.text = formattedPrice
                date.text = formattedDate
                status.text = item.status

                val backgroundColor = when (item.status) {
                    "unpaid" -> ContextCompat.getColor(itemBinding.root.context, com.inyongtisto.myhelper.R.color.red)
                    "paid" -> ContextCompat.getColor(itemBinding.root.context, com.inyongtisto.myhelper.R.color.green4)
                    else -> ContextCompat.getColor(itemBinding.root.context, android.R.color.transparent)
                }
                status.setBackgroundColor(backgroundColor)

                root.setOnClickListener {
                    it.intentActivity(DetailOrderActivity::class.java, item.id.toInt())
                }
            }
        }
    }

    fun View.intentActivity(DetailOrderActivity: Class<*>, itemId: Int?) {
        val intent = Intent(this.context,DetailOrderActivity).apply{
            putExtra("item_id", itemId)
        }
        this.context.startActivity(intent)
    }

    fun addItems(items: List<Order>) {
        //        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }
    fun setData(data: List<Order>) {
        this.data = ArrayList(data)
        notifyDataSetChanged()
    }
    fun clearItems() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            CardFloatDetailBinding.inflate(  LayoutInflater.from(parent.context),
                parent,
                false)
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val makanan = data[position]
        holder.bind(makanan, position)

    }

    override fun getItemCount(): Int {
        return data.size
    }
}