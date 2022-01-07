package com.bws.officeapp.expense.expensedetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R

class ExpenseDetailsAdapter(val mList: ArrayList<ExpenseDetailsModel>) :
    RecyclerView.Adapter<ExpenseDetailsAdapter.ViewHolder>() {

    private var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_expense_details, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expanseDetalModel = mList[position]
        holder.txtAF.text = expanseDetalModel.txt
        holder.txtDay.text = expanseDetalModel.days
        holder.txtDescription.text = expanseDetalModel.details
        holder.txtPrice.text = expanseDetalModel.price

        if(position % 2 == 0){
            holder.txtAF.setBackgroundResource(R.drawable.round_cf1)
        }else{
            holder.txtAF.setBackgroundResource(R.drawable.round_cf2)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtAF:TextView = itemView.findViewById(R.id.txtAF)
        val txtDay:TextView = itemView.findViewById(R.id.txtDay)
        val txtDescription:TextView = itemView.findViewById(R.id.txtDescription)
        val txtPrice:TextView = itemView.findViewById(R.id.txtPrice)

    }
}