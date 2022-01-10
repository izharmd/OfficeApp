package com.bws.officeapp.expense.trip

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R

class RecentTripAdapter(val mList: ArrayList<RecentTripModel>) :
    RecyclerView.Adapter<RecentTripAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recent_trip, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recentTripModel = mList[position]

        holder.txtAF.text = recentTripModel.txtFC
        holder.txtDay.text = recentTripModel.days
        holder.txtDescription.text = recentTripModel.description
        holder.txtDetails.text = recentTripModel.details
        if (position % 2 == 0) {
            holder.txtAF.setBackgroundResource(R.drawable.round_cf1)
        } else {
            holder.txtAF.setBackgroundResource(R.drawable.round_cf2)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val txtAF: TextView = itemView.findViewById(R.id.txtAF)
        val txtDay: TextView = itemView.findViewById(R.id.txtDay)
        val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        val txtDetails: TextView = itemView.findViewById(R.id.txtDetails)

    }
}