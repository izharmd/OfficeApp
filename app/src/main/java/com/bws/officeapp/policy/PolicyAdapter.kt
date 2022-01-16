package com.bws.officeapp.policy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R

class PolicyAdapter(val mList: ArrayList<PolicyModel>) :
    RecyclerView.Adapter<PolicyAdapter.ViewHolder>() {

    private var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_policy_document, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val policyModel = mList[position]
        holder.txtPolicyDocument.text = policyModel.policy
    }

    override fun getItemCount(): Int {

        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val txtPolicyDocument: TextView = itemView.findViewById(R.id.txtPolicyDocument)

    }
}