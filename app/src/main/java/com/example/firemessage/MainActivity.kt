package com.example.firemessage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.firemessage.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation_bottom.setOnItemSelectedListener{
            when(it.itemId){
                R.id.navigation_people ->{

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_my_account ->{


                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }
    }
}