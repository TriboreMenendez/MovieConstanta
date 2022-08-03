package com.tribore.movieconstanta.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tribore.movieconstanta.MovieApp
import com.tribore.movieconstanta.R
import com.tribore.movieconstanta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MovieApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, HomeFragment())
                .commit()
        }
    }
}