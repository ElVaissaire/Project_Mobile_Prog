package com.example.project_mobile_prog.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_mobile_prog.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GW2ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = GW2Adapter(listOf())
    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.gw2_recyclerview)
        recyclerView.apply {
            layoutManager = this@GW2ListFragment.layoutManager
            adapter = this@GW2ListFragment.adapter
        }

        val gw2List = arrayListOf<GW2>().apply{
            add(GW2("Sylvari"))
            add(GW2("Humain"))
            add(GW2("Charr"))
            add(GW2("Azura"))
        }

        adapter.updateList(gw2List)
    }
}