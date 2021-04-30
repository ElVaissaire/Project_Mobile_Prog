package com.example.project_mobile_prog.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_mobile_prog.R

class GW2Adapter(private var dataSet: List<GW2>, val listener: ((GW2)->Unit)? = null) :
    RecyclerView.Adapter<GW2Adapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val idView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.gw2_name)
            idView = view.findViewById(R.id.gw2_id)
        }
    }

    fun updateList(list: List<GW2>){
        dataSet = list
        notifyDataSetChanged()
    }

    fun addItemToList(item: GW2){
        dataSet = dataSet + item
        notifyDataSetChanged()
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.gw2_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val gw2 = dataSet[position]
        viewHolder.textView.text = gw2.name
        viewHolder.idView.text = gw2.item_id.toString()
        viewHolder.itemView.setOnClickListener {
            listener?.invoke(gw2)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}