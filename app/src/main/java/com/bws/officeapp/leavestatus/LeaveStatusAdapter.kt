package com.bws.officeapp.leavestatus

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R

class LeaveStatusAdapter(val mList: List<LeaveStatusModel>) :
    RecyclerView.Adapter<LeaveStatusAdapter.ViewHolder>() {

    private var context: Context? = null

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtEmpId: TextView = itemView.findViewById(R.id.txtEmpId);
        val txtEmpName: TextView = itemView.findViewById(R.id.txtEmpName);
        val txtDayOfLeave: TextView = itemView.findViewById(R.id.txtDayOfLeave);
        val txtLeaveFrom: TextView = itemView.findViewById(R.id.txtLeaveFrom);
        val txtLeaveTo: TextView = itemView.findViewById(R.id.txtLeaveTo);
        val txtLeaveStatus: TextView = itemView.findViewById(R.id.txtLeaveStatus);
        val txtReason: TextView = itemView.findViewById(R.id.txtReason);
        val txtCount: TextView = itemView.findViewById(R.id.txtCount);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_leave_status, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemLeave = mList[position]
        holder.txtEmpId.text = itemLeave.empid
        holder.txtEmpName.text = itemLeave.empName
        holder.txtDayOfLeave.text = itemLeave.dayOfleave
        holder.txtLeaveFrom.text = itemLeave.leaveFrom
        holder.txtLeaveTo.text = itemLeave.leaveTo
        holder.txtLeaveStatus.text = itemLeave.leaveStatus
        holder.txtReason.text = itemLeave.Reason

        var pos:Int = position+1
        holder.txtCount.text = pos.toString()

        val str = itemLeave.leaveStatus
        if(str.equals("Pending",true)){
            holder.txtLeaveStatus.setTextColor(Color.parseColor("#ed1a2e"));
        }else{
            holder.txtLeaveStatus.setTextColor(Color.parseColor("#088C08"));
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }
}