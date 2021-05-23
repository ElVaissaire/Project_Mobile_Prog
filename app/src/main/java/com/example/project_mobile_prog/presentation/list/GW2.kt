package com.example.project_mobile_prog.presentation.list

import java.io.Serializable

data class GW2(
        var name: String,
        var item_id: Int,
        var description: String,
        var type: String,
        var level: Int,
        var rarity: String,
        var vendor_value: Int,
        var icon_file_id: Int
) : Serializable