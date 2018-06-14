package com.example.navigation

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.navigation.databinding.FragmentTitleScreenBinding

class TitleScreenFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentTitleScreenBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_title_screen, container, false)
        binding.title = this
        binding.playButton.setOnClickListener { v: View ->
            v.findNavController().navigate(TitleScreenFragmentDirections.Action_title_screen_to_in_game())
        }
        return binding.root
    }
}
