package com.latnok.expense_tracker_kotlin.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.latnok.expense_tracker_kotlin.R
import com.latnok.expense_tracker_kotlin.data.model.Expense
import java.text.SimpleDateFormat
import java.util.*

class ExpenseAdapter (
    private val items: List<Expense>
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    inner class ExpenseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.expenseName)
        val dateText: TextView = view.findViewById(R.id.expenseDate)
        val amountText: TextView = view.findViewById(R.id.expenseAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = items[position]
        holder.nameText.text = expense.item_name
        holder.amountText.text = "₦${expense.item_amount}"
        holder.dateText.text = formatDate(expense.expense_date)
    }

    override fun getItemCount(): Int = items.size

    private fun formatDate(dateStr: String): String {
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val date = sdf.parse(dateStr)
            val now = Date()
            val diff = now.time - date.time
            val hours = diff / (1000 * 60 * 60)
            val days = diff / (1000 * 60 * 60 * 24)
            when {
                hours < 24 -> "$hours hours ago"
                days < 7 -> "$days days ago"
                else -> SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(date)
            }
        } catch (e: Exception) {
            ""
        }
    }
}