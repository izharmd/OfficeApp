package com.bws.officeapp.expense.pendingapproval

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R

class PendingApprovalAdapter(val mList:ArrayList<PendingApprovalModel>):RecyclerView.Adapter<PendingApprovalAdapter.ViewHolder>() {

    private var context:Context? = null
    class ViewHolder(ItemView:View):RecyclerView.ViewHolder(ItemView) {
        val txtAF: TextView = itemView.findViewById(R.id.txtAF)
        val txtDay: TextView = itemView.findViewById(R.id.txtDay)
        val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
        val txtStatusApproval:TextView = itemView.findViewById(R.id.txtStatusApproval)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pending_approval,parent,false)
        context = parent.context
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pendingApprovalModel = mList[position]

        holder.txtAF.text = pendingApprovalModel.textCF
        holder.txtDay.text = pendingApprovalModel.days
        holder.txtDescription.text = pendingApprovalModel.description
        holder.txtPrice.text = pendingApprovalModel.price
        holder.txtStatusApproval.text = pendingApprovalModel.status

        val status = pendingApprovalModel.status
        if(status.equals("Pending..",true)){
            holder.txtStatusApproval.setTextColor(ContextCompat.getColor(context!!, R.color.red_graph))
        }else{
            holder.txtStatusApproval.setTextColor(ContextCompat.getColor(context!!, R.color.green))
        }


        if(position % 2 == 0){
            holder.txtAF.setBackgroundResource(R.drawable.round_cf1)
        }else{
            holder.txtAF.setBackgroundResource(R.drawable.round_cf2)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}