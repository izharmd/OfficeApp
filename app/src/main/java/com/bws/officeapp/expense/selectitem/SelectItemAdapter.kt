package com.bws.officeapp.expense.selectitem

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R
import kotlinx.android.synthetic.main.activity_select_item.view.*

class SelectItemAdapter(val mList: ArrayList<SelectItemModel>) :
    RecyclerView.Adapter<SelectItemAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imv1: ImageView = itemView.findViewById(R.id.imv1);
        val imv2: ImageView = itemView.findViewById(R.id.imv2);
        val txtdescripton: TextView = itemView.findViewById(R.id.txtdescripton);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_select_item, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectItemModel = mList[position]
        holder.imv1.setImageResource(selectItemModel.image)
        holder.imv2.setImageResource(selectItemModel.imgCheck)
        holder.txtdescripton.text = selectItemModel.description
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}