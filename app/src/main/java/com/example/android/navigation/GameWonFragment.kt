/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.app.ShareCompat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding
import android.content.pm.ResolveInfo
import android.content.pm.PackageManager



class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                    GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }

        //  (01) Add setHasOptionsMenu(true)
        // This allows onCreateOptionsMenu to be called
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)

//        check to see if the activity resolves to an activity
        if(null == getShareIntent().resolveActivity(activity!!.packageManager)){
            //hide the share menu item
            menu.findItem(R.id.share)?.isVisible = false
        }
    }

    //  (02) Create getShareIntent method
    private fun getShareIntent(): Intent{
        val args = GameWonFragmentArgs.fromBundle(requireArguments())

/*        Old way of implicit intent creation not using method chaining
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT,
            getString(R.string.share_success_text, args.numCorrect, args.numQuestions))

        return shareIntent
*/

//        Better way of implicit intent creation using method chaining
        return ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.share_success_text, args.numQuestions, args.numCorrect))
            .setType("text/plain")
            .intent
    }

    /*
    * Note: When we do not have an app that could handle our implicit intent,
    * our app could crash and we can simulate this by removing the setType method
    * in the method chain and when we run the app, we get an ActivityNotFoundException
    *
    * We can handle this in various ways, either we show a toast that says there is no app available.
    * Or we can do something more elegant by checking in the onCreateOptionsMenu method to see if the
    * intent wil actually resolve to an activity and hide our send menu item if we cannot share*/

    // (03) Create shareSuccess method
    private fun shareSuccess(){
        startActivity(getShareIntent())
    }

    // (05) Override onOptionsItemSelected
    // Call the shareSuccess method when the item id matches R.id.share
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

}
