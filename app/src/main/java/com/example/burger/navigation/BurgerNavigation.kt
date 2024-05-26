package com.example.burger.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.burger.R
import com.example.burger.commons.NavigationControl
import com.example.burger.domain.vo.BurgerItem

class BurgerNavigation(override var navController: NavController?) : NavigationControl {

    fun gotoDetails(data: BurgerItem) {
        val bundle = bundleOf("BURGER_DETAIL_DATA" to data)
        navController?.navigate(R.id.action_burgerFragment_to_detailsFragment, bundle)
    }

    fun gotoBurgerList() {
        navController?.navigate(R.id.action_detailsFragment_to_burgerFragment)
    }
}