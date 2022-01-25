package com.example.android.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.android.navigation.databinding.FragmentTitleBinding

class TitleFragment : Fragment(){
override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View?
    {
        // cant use set content view so we will use DataBindingUtil and inflate fragment_title
        val binding : FragmentTitleBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_title,container,false)

        // setOnClickListener on button
        binding.playButton.setOnClickListener(
            // use the fun createNavigateOnClickListener
            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment)
        )
        // return the root layout....ie.constraint layout
        return binding.root
    }
}
