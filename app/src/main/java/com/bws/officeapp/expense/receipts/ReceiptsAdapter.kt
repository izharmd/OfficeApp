package com.bws.officeapp.expense.receipts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R

class ReceiptsAdapter(val mList:ArrayList<ReceiptsModel>):RecyclerView.Adapter<ReceiptsAdapter.Viewholder>() {

    private var context:Context? = null
    class Viewholder(ItemView: View):RecyclerView.ViewHolder(ItemView) {

        val imgReceipt:ImageView = itemView.findViewById(R.id.imgReceipt)
        val txtReceiptDate:TextView = itemView.findViewById(R.id.txtReceiptDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receipt,parent,false)
        context = parent.context
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val receiptsModel = mList[position]
        holder.imgReceipt.setImageResource(receiptsModel.receiptsimage)
        holder.txtReceiptDate.text = receiptsModel.date
    }

    override fun getItemCount(): Int {
       return mList.size
    }
}