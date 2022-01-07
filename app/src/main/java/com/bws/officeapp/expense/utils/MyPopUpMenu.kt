package com.bws.officeapp.expense.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.widget.PopupMenu
import com.bws.officeapp.LoginActivity
import com.bws.officeapp.R
import com.bws.officeapp.expense.AddNewExpenseActivity
import com.bws.officeapp.expense.pendingapproval.PendingApprovalActivity
import kotlinx.android.synthetic.main.toolba_reminder.*

class MyPopUpMenu {

    fun popuLateMenu(context: Activity, imag: ImageView) {

        imag.setOnClickListener() {
            val popupMenu: PopupMenu = PopupMenu(context, imag)
            popupMenu.menuInflater.inflate(R.menu.menu_expense, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.ddExpense ->
                        context.startActivity(Intent(context, AddNewExpenseActivity::class.java))
                    R.id.expenseDetails ->
                        Log.d("qwe", "qewrt")

                    R.id.pendingApproval ->
                        context.startActivity(Intent(context, PendingApprovalActivity::class.java))

                    R.id.addClaim ->
                        Log.d("qwe", "qewrt")

                    R.id.addReceipt ->
                        Log.d("qwe", "qewrt")

                }
                true
            })
            popupMenu.show()
        }

    }

    public fun cl() {

    }
}