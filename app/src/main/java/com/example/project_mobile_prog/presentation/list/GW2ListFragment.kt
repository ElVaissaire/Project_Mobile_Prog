package com.example.project_mobile_prog.presentation.list

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

        //val list: List<GW2> = getListFromCache()
        //if(list.isEmpty()){
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.guildwars2.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val GW2Api: GW2Api = retrofit.create(GW2Api::class.java)

            var i: Int = 1
            val itemList: MutableList<GW2> = mutableListOf()
            while(i!=100){ //API bizarre
                GW2Api.getItemList(i).enqueue(object : Callback<GW2> {
                    override fun onFailure(call: Call<GW2>, t: Throwable) {
                        //TODO("Not yet implemented")
                    }
                    override fun onResponse(call: Call<GW2>, response: Response<GW2>) {
                        if (response.isSuccessful && response.body() != null) {
                            val gw2Response = response.body()
                            //showList(gw2Response)
                            if (gw2Response != null) {
                                itemList.add(gw2Response)
                            }
                        }
                        if(i==100){
                            adapter.updateList(itemList)
                        }
                    }
                })

                i++
            }
//        }
//        else {}


    }

    //private fun getListFromCache(): List<GW2>{}

    //private fun showList(gw2Response: ){}

    private fun onClickedGW2(gw2: GW2) {
        findNavController().navigate(R.id.navigateToGW2DetailFragment, bundleOf("gw2" to gw2))
    }
}