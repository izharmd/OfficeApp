package com.bws.officeapp.policy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bws.officeapp.R
import kotlinx.android.synthetic.main.activity_policy.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class PolicyDocumentActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_policy)

        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_POLICY_DOC)

        recyPolicyDocument.layoutManager = LinearLayoutManager(this)

        val data  = ArrayList<PolicyModel>()

        data.add(PolicyModel(R.drawable.policy,"Leave Policy"))
        data.add(PolicyModel(R.drawable.policy,"Employee Provident Fund"))
        data.add(PolicyModel(R.drawable.policy,"Employee Wages"))
        data.add(PolicyModel(R.drawable.policy,"Gratuity Policy"))
        data.add(PolicyModel(R.drawable.policy,"Work from Home Policy"))
        data.add(PolicyModel(R.drawable.policy,"Performance Management and Appraisal"))

        val adapter = PolicyAdapter(data)
        recyPolicyDocument.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}