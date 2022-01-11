package com.bws.officeapp.expense.selectitem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.officeapp.R
import com.bws.officeapp.expense.utils.MyPopUpMenu
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_expense_detsail_list.*
import kotlinx.android.synthetic.main.activity_select_item.*
import kotlinx.android.synthetic.main.toolba_reminder.*

class SelectItemActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_item)
        supportActionBar?.hide()
        textUserName.text = resources.getText(R.string.WELCOME_TO_ESPENSA)

        recySelectItem.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<SelectItemModel>()

        data.add(SelectItemModel(R.drawable.img1,"From",R.drawable.img2))
        data.add(SelectItemModel(R.drawable.img_to,"To",R.drawable.img2))
        data.add(SelectItemModel(R.drawable.img4,"Description",R.drawable.img2))
        data.add(SelectItemModel(R.drawable.img5,"Journey",R.drawable.img2))
        data.add(SelectItemModel(R.drawable.img6,"Category",R.drawable.img2))
        data.add(SelectItemModel(R.drawable.img7,"Approver",R.drawable.img2))
        data.add(SelectItemModel(R.drawable.img8,"Project Code",R.drawable.img3))
        data.add(SelectItemModel(R.drawable.img9,"VAT Code",R.drawable.img3))


        val dividerDrawable =
            ContextCompat.getDrawable(this@SelectItemActivity, R.drawable.line_divider)
        recySelectItem.addItemDecoration(DividerItemDecoration(dividerDrawable))

        val adapter = SelectItemAdapter(data)
        recySelectItem.adapter = adapter
        adapter.notifyDataSetChanged()

        //Use for side popup menu
        MyPopUpMenu().popuLateMenu(this,imv_Shutdown)
    }
}