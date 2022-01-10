package com.bws.officeapp.expense.claim

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R

class MyClaimAdapter(val mList:ArrayList<MyClaimModel>):RecyclerView.Adapter<MyClaimAdapter.ViewHolder>() {

    private var context:Context?= null;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_my_claim,parent,false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val myClaimModel = mList[position]
        holder.txtAF.text = myClaimModel.strCF
        holder.txtDay.text = myClaimModel.days
        holder.txtDescription.text = myClaimModel.description
        holder.txtStaus.text = myClaimModel.status
        holder.txtPrice.text = myClaimModel.price

        val status = myClaimModel.status

        if(status.equals("Pending",true)){
            holder.txtStaus.setTextColor(Color.parseColor("#951711"))
        }else{
            holder.txtStaus.setTextColor(Color.parseColor("#326e17"))
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


    class ViewHolder(ItemView:View):RecyclerView.ViewHolder(ItemView) {

        val txtAF:TextView = itemView.findViewById(R.id.txtAF)
        val txtDay:TextView = itemView.findViewById(R.id.txtDay)
        val txtDescription:TextView = itemView.findViewById(R.id.txtDescription)
        val txtStaus:TextView = itemView.findViewById(R.id.txtStaus)
        val txtPrice:TextView = itemView.findViewById(R.id.txtPrice)

    }
}