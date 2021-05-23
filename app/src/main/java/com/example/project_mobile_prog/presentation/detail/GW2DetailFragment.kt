package com.example.project_mobile_prog.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.project_mobile_prog.R
import com.example.project_mobile_prog.presentation.list.GW2
import com.example.project_mobile_prog.presentation.list.GW2Adapter

class GW2DetailFragment : Fragment() {


    private lateinit var textViewName: TextView
    private lateinit var textViewId: TextView
    private lateinit var textViewType: TextView
    private lateinit var textViewLevel: TextView
    private lateinit var textViewVendorValue: TextView
    private lateinit var textViewRarity: TextView
    private lateinit var textViewDescription: TextView
    private lateinit var textViewIconID: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gw2_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gw2:GW2 = arguments?.get("gw2") as GW2
        textViewName = view.findViewById(R.id.gw2_detail_name)
        textViewId = view.findViewById(R.id.gw2_detail_id)
        textViewType = view.findViewById(R.id.gw2_detail_type)
        textViewLevel = view.findViewById(R.id.gw2_detail_level)
        textViewVendorValue = view.findViewById(R.id.gw2_detail_vendor_value)
        textViewRarity = view.findViewById(R.id.gw2_detail_rarity)
        textViewDescription = view.findViewById(R.id.gw2_detail_description)
        textViewIconID = view.findViewById(R.id.gw2_detail_icon_file_id)

        textViewName.text = gw2.name
        textViewId.text = gw2.item_id.toString()
        textViewType.text = gw2.type
        textViewVendorValue.text = gw2.vendor_value.toString()
        textViewRarity.text = gw2.rarity
        textViewDescription.text = gw2.description
        textViewIconID.text = gw2.icon_file_id.toString()
        textViewLevel.text = gw2.level.toString()

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.navigateToGW2ListFragment)
        }
    }
}