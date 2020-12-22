package com.example.sprinklerassignement

import com.example.sprinklerassignement.data.FILTER_TYPE

interface FilterClickListener {

    fun updateFilterSelection(filterType: FILTER_TYPE)
}