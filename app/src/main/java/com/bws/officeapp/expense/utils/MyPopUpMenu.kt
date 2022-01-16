package com.bws.officeapp.expense.utils

import android.app.Activity
import android.content.Intent
import android.widget.ImageView
import androidx.appcompat.widget.PopupMenu
import com.bws.officeapp.R
import com.bws.officeapp.expense.AddNewExpenseActivity
import com.bws.officeapp.expense.ViewExpansegraphActivity
import com.bws.officeapp.expense.claim.AddNewClaimActivity
import com.bws.officeapp.expense.pendingapproval.PendingApprovalActivity
import com.bws.officeapp.expense.receipts.AddMyReceiptsActivity
import com.bws.officeapp.expense.trip.AddNewTripActivity

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
                        context.startActivity(Intent(context, ViewExpansegraphActivity::class.java))

                    R.id.pendingApproval ->
                        context.startActivity(Intent(context, PendingApprovalActivity::class.java))

                    R.id.addClaim ->
                        context.startActivity(Intent(context, AddNewClaimActivity::class.java))

                    R.id.addTrip ->
                        context.startActivity(Intent(context, AddNewTripActivity::class.java))

                    R.id.addReceipt ->
                        context.startActivity(Intent(context, AddMyReceiptsActivity::class.java))

                }
                true
            })
            popupMenu.show()
        }

    }

    public fun cl() {

    }
}