package com.bws.officeapp.leavestatus

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R
import com.bws.officeapp.leave.leaveapprove.LeaveApproveActivity
import com.bws.officeapp.leavestatus.leavestatusviewmodel.LeaveStatusResponse
import com.bws.officeapp.utils.Response
import com.bws.officeapp.utils.SharedPreference

class LeaveStatusAdapter(val activity: Activity, val mList: Response<LeaveStatusResponse>) :
    RecyclerView.Adapter<LeaveStatusAdapter.ViewHolder>() {

    private var context: Context? = null

    lateinit var sharePref: SharedPreference

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtEmpId: TextView = itemView.findViewById(R.id.txtEmpId);
        val txtEmpName: TextView = itemView.findViewById(R.id.txtEmpName);
        val txtDayOfLeave: TextView = itemView.findViewById(R.id.txtDayOfLeave);
        val txtLeaveFrom: TextView = itemView.findViewById(R.id.txtLeaveFrom);
        val txtLeaveTo: TextView = itemView.findViewById(R.id.txtLeaveTo);
        val txtLeaveStatus: TextView = itemView.findViewById(R.id.txtLeaveStatus);
        val txtReason: TextView = itemView.findViewById(R.id.txtReason);
        val txtCount: TextView = itemView.findViewById(R.id.txtCount);
        val txtDetails: TextView = itemView.findViewById(R.id.txtDetails);
        val ll_reason: LinearLayout = itemView.findViewById(R.id.ll_reason);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_leave_status, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        sharePref = SharedPreference(activity)
        val itemLeave = mList.data?.data?.Leaves?.get(position)
        holder.txtEmpId.text = itemLeave?.UserID.toString()
        holder.txtEmpName.text = itemLeave?.Title + " " +itemLeave?.FirstName + " " + itemLeave?.LastName
        holder.txtDayOfLeave.text = itemLeave?.DaysOfLeave.toString()
        holder.txtLeaveFrom.text = itemLeave?.LeaveFrom
        holder.txtLeaveTo.text = itemLeave?.LeaveTo
        holder.txtLeaveStatus.text = itemLeave?.ApprovalStatus
        holder.txtReason.text = itemLeave?.Reason

        var pos: Int = position + 1
        holder.txtCount.text = pos.toString()

        val str = itemLeave?.ApprovalStatus
        if (str?.equals("Pending") == true) {
            holder.txtLeaveStatus.setTextColor(Color.parseColor("#ed1a2e"));
        } else {
            holder.txtLeaveStatus.setTextColor(Color.parseColor("#088C08"));
        }

        val rollId = sharePref.getValueString("KEY_ROLL_ID")

        if(rollId != "2" ){
            holder.txtDetails.setOnClickListener {
                context?.startActivity(Intent(context,LeaveApproveActivity::class.java))
            }
        }

        if (rollId == "1") { //DIRECTOR
            holder.txtDetails.visibility = View.VISIBLE
            holder.ll_reason.visibility = View.VISIBLE

        } else if (rollId == "2") { //EMPLOYEE
            holder.txtDetails.visibility = View.GONE
            holder.ll_reason.visibility = View.GONE
        } else if (rollId == "3") { //HR
            holder.txtDetails.visibility = View.GONE
            holder.ll_reason.visibility = View.GONE
        } else if (rollId == "4") {//MANAGER
            holder.txtDetails.visibility = View.GONE
            holder.ll_reason.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return mList.data?.data?.Leaves?.size!!
    }
}