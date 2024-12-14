package com.example.registrationforms

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class SampleActivity : AppCompatActivity() {

    private var showingFragmentA = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sample)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, FragmentA()) //initializing fragment a
                setReorderingAllowed(true)
            }
        }

        findViewById<Button>(R.id.button_change_fragment).setOnClickListener {
            swapFragment()
        }
    }

    private fun swapFragment() {
        val newFragment: Fragment = if (showingFragmentA) {
            FragmentB()
        } else {
            FragmentA()
        }

        supportFragmentManager.commit {
            replace(R.id.fragment_container, newFragment) //swaps fragments
            setReorderingAllowed(true)
            addToBackStack(null)
        }
        showingFragmentA = !showingFragmentA
    }

}