package com.example.android.navigation


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
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
            view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }

/* ... Or let Navigation create the onClick listener directly: */

//        binding.playButton.setOnClickListener {
//            Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment)
//        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}
