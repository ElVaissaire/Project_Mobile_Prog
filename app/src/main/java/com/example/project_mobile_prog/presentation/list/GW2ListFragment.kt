package com.example.project_mobile_prog.presentation.list

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_mobile_prog.R
import com.example.project_mobile_prog.presentation.api.GW2Api
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class GW2ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = GW2Adapter(listOf(), ::onClickedGW2)
    private var sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

    private val layoutManager = LinearLayoutManager(context)


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gw2_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.gw2_recyclerview)
        recyclerView.apply {
            layoutManager = this@GW2ListFragment.layoutManager
            adapter = this@GW2ListFragment.adapter
        }


        val list_obj : MutableList<GW2> = mutableListOf()
        readFromCache(list_obj)
        if(list_obj.isEmpty()){
            callAPIitems()
        }
        else{
            showList(list_obj)
        }

    }


    private fun callAPIitems(){

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.guildwars2.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val GW2Api: GW2Api = retrofit.create(GW2Api::class.java)

        var i: Int = 1
        var counter = 0
        val itemList: MutableList<GW2> = mutableListOf()
        while(i!=100){ //weird API that needs to call objects by their id number
            GW2Api.getItemList(i).enqueue(object : Callback<GW2> {
                override fun onFailure(call: Call<GW2>, t: Throwable) {
                    //TODO("Not yet implemented")
                }
                override fun onResponse(call: Call<GW2>, response: Response<GW2>) {
                    if (response.isSuccessful && response.body() != null) {
                        val gw2Response = response.body()
                        if (gw2Response != null) {
                            counter += 1
                            itemList.add(gw2Response)
                            saveIntoCache(gw2Response, counter)
                        }
                    }
                    if(i==100){
                        showList(itemList)
                    }
                }
            })

            i++
        }
    }

    private fun saveIntoCache(gw2 : GW2, counter : Int){
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref!!.edit()) {

            var i = counter

            putString("name_key$i", gw2.name)
            putInt("item_id_key$i", gw2.item_id)
            putString("description_key$i", gw2.description)
            putString("type_key$i", gw2.type)
            putInt("level_key$i", gw2.level)
            putString("rarity_key$i", gw2.rarity)
            putInt("vendor_value_key$i", gw2.vendor_value)
            putInt("icon_file_id_key$i", gw2.icon_file_id)
            apply()
        }
    }

    private fun readFromCache(list_obj: MutableList<GW2>) {
        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return

        var i = 1
        for (i in 1..30) {
            val gw2: GW2 = GW2(
                    name = sharedPref!!.getString("name_key$i", " ").toString(),
                    item_id = sharedPref!!.getInt("item_id_key$i", 0),
                    description = sharedPref!!.getString("description_key$i", " ").toString(),
                    type = sharedPref!!.getString("type_key$i", " ").toString(),
                    level = sharedPref!!.getInt("level0_key$i", 0),
                    rarity = sharedPref!!.getString("rarity_key$i", " ").toString(),
                    vendor_value = sharedPref!!.getInt("vendor_value_key$i", 0),
                    icon_file_id = sharedPref!!.getInt("icon_file_id_key$i", 0)
            )

            if (gw2.item_id != 0) {
                list_obj.add(gw2)
            }
        }
    }

    private fun showList(itemList : MutableList<GW2>){
        adapter.updateList(itemList)
    }

    private fun onClickedGW2(gw2: GW2) {
        findNavController().navigate(R.id.navigateToGW2DetailFragment, bundleOf("gw2" to gw2))
    }
}