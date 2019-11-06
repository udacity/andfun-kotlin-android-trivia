package com.example.android.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_title, container, false
        )

/* Use anonymous function and either the findNavController of Navigation or of the view: */

        binding.playButton.setOnClickListener { view: View ->
//            Navigation.findNavController(view).navigate(R.id.action_titleFragment_to_gameFragment)
            view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }

/* ... Or let Navigation create the onClick listener directly: */

//        binding.playButton.setOnClickListener {
//            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment)
//        }
        return binding.root
    }
}
