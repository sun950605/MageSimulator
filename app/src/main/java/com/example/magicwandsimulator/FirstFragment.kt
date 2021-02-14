package com.example.magicwandsimulator

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
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
    private lateinit var effect_view: ConstraintLayout
    private lateinit var spellFactory:SpellFactory
    private lateinit var dragAnim: AnimationDrawable
    private lateinit var shieldView:ImageView
    private lateinit var spellBookView:ImageView
    private lateinit var game:Game
    private lateinit var dragon:Dragon
    private lateinit var mapView:ConstraintLayout
    private var cd:Long = 500


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
        shieldView = view.findViewById(R.id.shield_view)
        mapView = view.findViewById(R.id.enemy_view)
        spellBookView = view.findViewById(R.id.spell_book_view)

        val dragImgView =  view.findViewById<ImageView>(R.id.dragon_img_view)
        val dragonEffectView = view.findViewById<ImageView>(R.id.dragon_effect_view)
        dragon = Dragon(dragImgView , dragonEffectView)
        dragon.idle()

        activity?.let{
            val displayMetrics: DisplayMetrics = it.getResources().getDisplayMetrics()
            val margin = Math.round(10 / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
            game = Game(it, view.findViewById(R.id.hp_bar) , view.findViewById(R.id.mana_bar) ,shieldView, dragon, wand , mapView)
            game.init()
        }
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }


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
                if (finishedPattern == "048"){
                    game.changeShield(1)
                    //game.changeMana(30)
                    cooldown(cd)
                }

                else if (finishedPattern == "147"){
                    game.changeShield(2)
                    //game.changeMana(30)
                    cooldown(cd)
                }

                else if (finishedPattern == "246"){
                    game.changeShield(3)
                    //game.changeMana(30)
                    cooldown(cd)
                }
                else if (finishedPattern == "0124678"&& game.mana>=30){
                    spellFactory.lightning()
                    game.changeMana(-30)
                    dragon.hit()
                    cooldown(cd)
                }


                else{
                    if (game.mana >= 30){
                        if (finishedPattern.length >=3 && finishedPattern[0] == '0' || finishedPattern[0] == '3' || finishedPattern[0] == '6' ){
                            spellFactory.fire()
                            game.changeMana(-30)
                            dragon.hit()
                            cooldown(cd)
                        }

                        if (finishedPattern.length >=3 && finishedPattern[0] == '1' || finishedPattern[0] == '4' || finishedPattern[0] == '7'){
                            spellFactory.water()
                            game.changeMana(-30)
                            dragon.hit()
                            cooldown(cd)
                        }

                        if (finishedPattern.length >=3 && finishedPattern[0] == '2' || finishedPattern[0] == '5' || finishedPattern[0] == '8' ){
                            spellFactory.meteor()
                            game.changeMana(-30)
                            dragon.hit()
                            cooldown(cd)
                        }
                    }
                }

            }

            fun cooldown(cd:Long){
                object : CountDownTimer(cd, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        wand.alpha = (1 - millisUntilFinished.toFloat() /10000.00).toFloat()
                        spellBookView.alpha = (1 - millisUntilFinished.toFloat() /10000.00).toFloat()
                        wand.isEnabled = false
                    }

                    override fun onFinish() {
                        wand.alpha = 1f
                        spellBookView.alpha = 1f
                        wand.isEnabled = true
                    }
                }.start()
            }

            override fun onCleared() {
                android.util.Log.e(javaClass.name, "Pattern has been cleared")
            }
        }

}