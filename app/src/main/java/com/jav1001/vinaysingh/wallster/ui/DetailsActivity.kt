package com.jav1001.vinaysingh.wallster.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.jav1001.vinaysingh.wallster.R
import com.jav1001.vinaysingh.wallster.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        binding.textViewRes.text = intent.getStringExtra("res")
        binding.textViewType.text = intent.getStringExtra("type")
        binding.textViewSource.text = intent.getStringExtra("source")
        setContentView(binding.root)
        Glide.with(this@DetailsActivity).load(intent.getStringExtra("url")).centerInside().into(binding.imageDetail)
        //binding.imageDetail.setImageResource(1)

    }
}