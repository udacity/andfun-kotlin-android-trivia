package com.example.navigation

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import androidx.navigation.findNavController
import com.example.navigation.databinding.FragmentResultsWinnerBinding


class ResultsWinnerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentResultsWinnerBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_results_winner, container, false)

        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController().navigate(ResultsWinnerFragmentDirections.Action_results_winner_to_match())
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareSuccess() {
        val args = ResultsWinnerFragmentArgs.fromBundle(arguments)
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
        startActivity(shareIntent)
    }
}
