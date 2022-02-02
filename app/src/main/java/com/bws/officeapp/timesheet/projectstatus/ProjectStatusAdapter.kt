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
import com.bws.officeapp.timesheet.projectstatus.projectstatusviewmodel.ProjectStatusResponse
import com.bws.officeapp.utils.Response

class ProjectStatusAdapter(val mList: Response<ProjectStatusResponse>) :
    RecyclerView.Adapter<ProjectStatusAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_project_status, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val projectStatusModel = mList.data?.data?.ProjectList?.get(position)
        holder.txtProjectName.text = projectStatusModel?.ProjectName
        holder.txtProStartDate.text = projectStatusModel?.StartDate
        holder.txtDeliveryDate.text = projectStatusModel?.AgreedDeliveryDate
        holder.txtProStatus.text = projectStatusModel?.ProjectStatus

        val status = projectStatusModel?.ProjectStatus

        if(status?.equals("Competed") == true){
            holder.txtProStatus.setTextColor(Color.parseColor("#088C08"));
        }else{
            holder.txtProStatus.setTextColor(Color.parseColor("#ed1a2e"));
        }

       /* holder.itemView.setOnClickListener(){
            val intent = Intent(context, MekkoChartActivity::class.java)
            context?.startActivity(intent)
        }*/

    }

    override fun getItemCount(): Int {
        return mList.data?.data?.ProjectList?.size!!
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val txtProjectName: TextView = itemView.findViewById(R.id.txtProjectName)
        val txtProStartDate: TextView = itemView.findViewById(R.id.txtProStartDate)
        val txtDeliveryDate: TextView = itemView.findViewById(R.id.txtDeliveryDate)
        val txtProStatus: TextView = itemView.findViewById(R.id.txtProStatus)

    }

}