package com.bws.officeapp.leavestatus

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R
import com.bws.officeapp.leavestatus.leavestatusviewmodel.LeaveStatusResponse
import com.bws.officeapp.utils.Response

class LeaveStatusAdapter(val mList: Response<LeaveStatusResponse>) :
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

        val itemLeave = mList.data?.data?.Leaves?.get(position)
        holder.txtEmpId.text = itemLeave?.UserID.toString()
        holder.txtEmpName.text = itemLeave?.FirstName + " " + itemLeave?.LastName
        holder.txtDayOfLeave.text = itemLeave?.DaysOfLeave.toString()
        holder.txtLeaveFrom.text = itemLeave?.LeaveFrom
        holder.txtLeaveTo.text = itemLeave?.LeaveTo
        holder.txtLeaveStatus.text = itemLeave?.ApprovalStatus
        holder.txtReason.text = itemLeave?.Reason

        var pos:Int = position+1
        holder.txtCount.text = pos.toString()

        val str = itemLeave?.ApprovalStatus
        if(str?.equals("Pending") == true){
            holder.txtLeaveStatus.setTextColor(Color.parseColor("#ed1a2e"));
        }else{
            holder.txtLeaveStatus.setTextColor(Color.parseColor("#088C08"));
        }

    }

    override fun getItemCount(): Int {
        return mList.data?.data?.Leaves?.size!!
    }
}