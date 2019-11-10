package com.anangkur.kotlinexpertsubmission.feature.custom

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class LeagueSliderTabAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val listFragment = ArrayList<LeagueSliderFragment>()

    override fun getCount(): Int = listFragment.size
    override fun getItem(position: Int): Fragment = listFragment[position]

    fun addFragment(fragment: LeagueSliderFragment){
        listFragment.add(fragment)
    }
}