package com.example.magicwandsimulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.andrognito.patternlockview.utils.PatternLockUtils


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var wand: PatternLockView

    private val mPatternLockViewListener: PatternLockViewListener =
        object : PatternLockViewListener {
            override fun onStarted() {
                android.util.Log.e(javaClass.name, "Pattern drawing started")
            }

            override fun onProgress(progressPattern: List<PatternLockView.Dot>) {
                android.util.Log.e(
                    javaClass.name, "Pattern progress: " +
                            PatternLockUtils.patternToString(wand, progressPattern)
                )
            }

            override fun onComplete(pattern: List<PatternLockView.Dot>) {
                android.util.Log.e(
                    javaClass.name, "Pattern complete: " +
                            PatternLockUtils.patternToString(wand, pattern)
                )
            }

            override fun onCleared() {
                android.util.Log.e(javaClass.name, "Pattern has been cleared")
            }
        }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wand =  view.findViewById(R.id.pattern_lock_view);
        wand.addPatternLockListener(mPatternLockViewListener);

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}