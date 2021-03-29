package com.example.project_mobile_prog.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_mobile_prog.R
import com.example.project_mobile_prog.presentation.api.GW2Api
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

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

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.guildwars2.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val GW2Api: GW2Api = retrofit.create(GW2Api::class.java)

        //var i: Int = 0;
        //while(i!=31) {
            GW2Api.getItemList(28445).enqueue(object : Callback<GW2> {
                override fun onFailure(call: Call<GW2>, t: Throwable) {
                    //TODO("Not yet implemented")
                }

                override fun onResponse(call: Call<GW2>, response: Response<GW2>) {
                    if (response.isSuccessful && response.body() != null) {
                        val gw2Response = response.body()
                        if (gw2Response != null) {
                            adapter.addItemToList(gw2Response)
                        }
                    }
                }
            })
         //   i++
        //}
    }
}