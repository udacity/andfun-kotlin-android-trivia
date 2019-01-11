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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.android.navigation.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_title, container, false)
        // TODO (09) Call binding.playButton.setOnClickListener and navigate to the gameFragment
        // Use Navigation.createNavigateOnClickListener with
        // R.id.action_titleFragment_to_gameFragment
        return binding.root
    }
    // TODO (06) Add the Title Fragment to the Navigation Graph
    // Go to the navigation.xml file and select the design tab
    // Click the add icon with the + on it to add a new destination to the graph
    // Select fragment_title to add this fragment to the graph as the start destination

    // TODO (08) Connect the Title and Game Fragments with an Action
    // In the navigation editor, hover over the titleFragment.  Click on the circular connection
    // point and drag to gameFragment to create the Action
}
