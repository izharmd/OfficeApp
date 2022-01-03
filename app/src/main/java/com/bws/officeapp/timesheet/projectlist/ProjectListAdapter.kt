package com.bws.officeapp.timesheet.projectlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R
import java.util.zip.Inflater

class ProjectListAdapter(val mList:ArrayList<ProjectListModel>):RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    private var context:Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project_list,parent,false)
       context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val modelProjectList = mList[position]
        holder.txtProjectName.text = modelProjectList.projectEndDate
        holder.txtAllocatedBy.text = modelProjectList.alocatedBy
        holder.txtProStartDate.text = modelProjectList.projectStartDate
        holder.txtProEndDate.text = modelProjectList.projectEndDate
        holder.txtSpendTime.text = modelProjectList.totalSpendTime
        holder.txtAgreedTime.text = modelProjectList.totalAggredTime
        holder.txtProStatus.text = modelProjectList.projectStatus

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView:View):RecyclerView.ViewHolder(ItemView) {

        val txtProjectName:TextView = itemView.findViewById(R.id.txtProjectName)
        val txtAllocatedBy:TextView = itemView.findViewById(R.id.txtAllocatedBy)
        val txtProStartDate:TextView = itemView.findViewById(R.id.txtProStartDate)
        val txtProEndDate:TextView = itemView.findViewById(R.id.txtProEndDate)
        val txtSpendTime:TextView = itemView.findViewById(R.id.txtSpendTime)
        val txtAgreedTime:TextView = itemView.findViewById(R.id.txtAgreedTime)
        val txtProStatus:TextView = itemView.findViewById(R.id.txtProStatus)

    }
}