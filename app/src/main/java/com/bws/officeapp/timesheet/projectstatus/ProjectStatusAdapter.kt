package com.bws.timesheet.projectstatus

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R
import com.bws.officeapp.timesheet.MekkoChartActivity

class ProjectStatusAdapter(val mList: ArrayList<ProjectStatusModel>) :
    RecyclerView.Adapter<ProjectStatusAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_project_status, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val projectStatusModel = mList[position]

        holder.txtProjectName.text = projectStatusModel.projectName
        holder.txtProStartDate.text = projectStatusModel.date
        holder.txtStartTime.text = projectStatusModel.time
        holder.txtProStatus.text = projectStatusModel.status

        val status = projectStatusModel.status

        if(status.equals("Competed",true)){
            holder.txtProStatus.setTextColor(Color.parseColor("#088C08"));
        }else{
            holder.txtProStatus.setTextColor(Color.parseColor("#ed1a2e"));
        }

        holder.itemView.setOnClickListener(){
            val intent = Intent(context, MekkoChartActivity::class.java)
            context?.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val txtProjectName: TextView = itemView.findViewById(R.id.txtProjectName)
        val txtProStartDate: TextView = itemView.findViewById(R.id.txtProStartDate)
        val txtStartTime: TextView = itemView.findViewById(R.id.txtStartTime)
        val txtProStatus: TextView = itemView.findViewById(R.id.txtProStatus)

    }

}