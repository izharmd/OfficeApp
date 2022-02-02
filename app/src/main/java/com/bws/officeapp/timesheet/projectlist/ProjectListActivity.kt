package com.bws.officeapp.timesheet.projectlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.officeapp.R
import com.bws.officeapp.timesheet.addproject.AddProjectActivity
import com.bws.officeapp.timesheet.ProfileTimeSheetActivity
import com.bws.officeapp.utils.ToastMessage
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_project_list.*
import kotlinx.android.synthetic.main.toolba_reminder.*
import org.json.JSONObject

import org.json.JSONArray


class ProjectListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_list)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_TIME_SHEET_APP)
        recyProjectList.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<ProjectListModel>()

        val profileName = intent.getStringExtra("RESULT")

        try {
            val jsonarray = JSONArray(profileName)
            for (i in 0..jsonarray.length() - 1) {
                val jsonobject: JSONObject = jsonarray.getJSONObject(i)
                val pTotalTime:String = jsonobject.getString("TotalTime")
                val agTime:String = jsonobject.getString("AgreedTime")
                val totaltime = timeConvert(pTotalTime.toUInt().toInt())
                val agreedTime = timeConvert(agTime.toUInt().toInt())
                data.add(
                    ProjectListModel(
                        jsonobject.getString("ProjectName"),
                        jsonobject.getString("AllocatedBy"),
                        jsonobject.getString("StartDate"),
                        jsonobject.getString("AgreedDeliveryDate"),
                        totaltime.toString(),
                        agreedTime.toString(),
                        ""
                    )
                )
            }

            val dividerDrawable =
                ContextCompat.getDrawable(this@ProjectListActivity, R.drawable.line_divider)
            recyProjectList.addItemDecoration(DividerItemDecoration(dividerDrawable))

            val adapter = ProjectListAdapter(data)
            recyProjectList.adapter = adapter
            adapter.notifyDataSetChanged()
        } catch (e: Exception) {
            ToastMessage.message(this, e.message.toString())
        }

        txtAdd.setOnClickListener() {
            startActivity(Intent(this@ProjectListActivity, AddProjectActivity::class.java))
        }


        imv_Shutdown.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this, imv_Shutdown)
            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.setting ->
                        startActivity(
                            Intent(
                                applicationContext,
                                ProfileTimeSheetActivity::class.java
                            )
                        )
                    R.id.logOut ->
                        // LogOut().closeAllActivity(applicationContext)

                        Log.d("qwe", "qewrt");

                }
                true
            })
            popupMenu.show()
        }

        imvBack.setOnClickListener() {
            finish()
        }
    }

    fun timeConvert(time: Int): String? {
        var t = ""
        val d = time / (24 * 60)
        val h = time % (24 * 60) / 60
        val m = time % (24 * 60) % 60
        t =  d.toString() +" Days "+ h.toString() +" Hours"
        return t
    }
}