package com.bws.officeapp.timesheet

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView

import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.R
import com.bws.officeapp.timesheet.projectlist.ProjectListActivity
import kotlinx.android.synthetic.main.activity_search_project.*
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class SearchProjectActivity :AppCompatActivity(), AdapterView.OnItemSelectedListener{

    var list_of_items = arrayOf("-Select-","Project", "Project Status", "Date Range")

    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_project)
        supportActionBar?.hide()

        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, list_of_items)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_SearchProject.setAdapter(aa)
        sp_SearchProject.onItemSelectedListener = this


        txtDateFrom.setOnClickListener(){
            val dpd = DatePickerDialog(applicationContext, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                txtDateFrom.setText("" + dayOfMonth + " " + MONTHS + ", " + year)
            }, year, month, day)

            dpd.show()
        }

        txtSearch.setOnClickListener(){
           startActivity(Intent(this@SearchProjectActivity, ProjectListActivity::class.java))
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, arg1: View, position: Int, id: Long) {
        val str: String = parent?.getItemAtPosition(position).toString()
       // Toast.makeText(this@SearchProjectActivity, str, Toast.LENGTH_SHORT).show()
        if(str.equals("-Select-",true)){
         //   Toast.makeText(this@SearchProjectActivity, "Please select one.", Toast.LENGTH_SHORT).show()
        }else if(str.equals("Project",true)){
            edtProjectName.visibility = View.VISIBLE
            edtEmployee.visibility = View.GONE
            ll_DateRange.visibility = View.GONE
            txtProjectSearchWith.visibility = View.VISIBLE
            txtProjectSearchWith.text = "Search by Project"
        }else if(str.equals("Employee",true)){
            edtProjectName.visibility = View.GONE
            edtEmployee.visibility = View.VISIBLE
            ll_DateRange.visibility = View.GONE
            txtProjectSearchWith.visibility = View.VISIBLE
            txtProjectSearchWith.text = "Search by Employee"
        }else{
            edtProjectName.visibility = View.GONE
            edtEmployee.visibility = View.GONE
            ll_DateRange.visibility = View.VISIBLE
            txtProjectSearchWith.visibility = View.VISIBLE
            txtProjectSearchWith.text = "Search by Date Range"
        }

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }
}