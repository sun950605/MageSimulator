package com.example.magicwandsimulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.andrognito.patternlockview.PatternLockView
import com.andrognito.patternlockview.listener.PatternLockViewListener
import com.andrognito.patternlockview.utils.PatternLockUtils
import com.plattysoft.leonids.ParticleSystem


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var wand: PatternLockView
    private lateinit var effect_view: ConstraintLayout
    private lateinit var spellFactory:SpellFactory

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

                val finishedPattern = PatternLockUtils.patternToString(wand, pattern);
                if (finishedPattern == "012"){
                    spellFactory.meteor()
                }

                if (finishedPattern == "345"){
                    spellFactory.lightning()
                }

                if (finishedPattern == "678"){
                    spellFactory.fire()
                }

                if (finishedPattern == "036"){
                    spellFactory.water()
                }
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
        activity?.let{
            spellFactory = SpellFactory(it)
        }
        effect_view = view.findViewById(R.id.effect_content_view)
        wand =  view.findViewById(R.id.pattern_lock_view);
        wand.addPatternLockListener(mPatternLockViewListener);

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}