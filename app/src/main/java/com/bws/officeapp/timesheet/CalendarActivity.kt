package com.bws.officeapp.timesheet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bws.officeapp.R
import com.events.calendar.views.EventsCalendar
import kotlinx.android.synthetic.main.activity_calendar.*
import java.util.*

class CalendarActivity:AppCompatActivity(), EventsCalendar.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        supportActionBar?.hide()


       // selected.text = getDateString(eventsCalendar.getCurrentSelectedDate()?.timeInMillis)

        val today = Calendar.getInstance()
        val end = Calendar.getInstance()
        end.add(Calendar.YEAR, 2)
        eventsCalendar.setSelectionMode(eventsCalendar.MULTIPLE_SELECTION)
            .setToday(today)
            .setMonthRange(today, end)
            .setWeekStartDay(Calendar.SUNDAY, false)
            .setIsBoldTextOnSelectionEnabled(true)
            //.setDatesTypeface(FontsManager.getTypeface(FontsManager.OPENSANS_REGULAR, this))
            //.setMonthTitleTypeface(FontsManager.getTypeface(FontsManager.OPENSANS_SEMIBOLD, this))
            //.setWeekHeaderTypeface(FontsManager.getTypeface(FontsManager.OPENSANS_SEMIBOLD, this))
            .setCallback(this)
            .build()

        val c = Calendar.getInstance()
        c.add(Calendar.DAY_OF_MONTH, 2)
        eventsCalendar.addEvent(c)
        c.add(Calendar.DAY_OF_MONTH, 3)
        eventsCalendar.addEvent(c)
        c.add(Calendar.DAY_OF_MONTH, 4)
        eventsCalendar.addEvent(c)
        c.add(Calendar.DAY_OF_MONTH, 7)
        eventsCalendar.addEvent(c)
        c.add(Calendar.MONTH, 1)
        c[Calendar.DAY_OF_MONTH] = 1
        eventsCalendar.addEvent(c)

       /* selected.setOnClickListener {
            val dates = eventsCalendar.getDatesFromSelectedRange()
            Log.e("SELECTED SIZE", dates.size.toString())
        }*/

       // selected.typeface = FontsManager.getTypeface(FontsManager.OPENSANS_SEMIBOLD, this)

        val dc = Calendar.getInstance()
        dc.add(Calendar.DAY_OF_MONTH, 2)
    }

    override fun onDayLongPressed(selectedDate: Calendar?) {

    }

    override fun onDaySelected(selectedDate: Calendar?) {

    }

    override fun onMonthChanged(monthStartDate: Calendar?) {

    }
}