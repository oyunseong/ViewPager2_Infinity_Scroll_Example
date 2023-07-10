package com.example.viewpager2infinityscrollexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2infinityscrollexample.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var bannerPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list: List<Int> = listOf(1, 2, 3, 4, 6)

        binding.pager.adapter = PagerAdapter(list)
        binding.pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.currentPosition.text = "초기"

        // 중간 position부터 시작
        bannerPosition = Int.MAX_VALUE / 2 - ceil(list.size.toDouble() / 2).toInt()
        binding.pager.setCurrentItem(bannerPosition, false)

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bannerPosition = position
                binding.currentPosition.text = "${bannerPosition%list.size + 1}/ ${list.size}"
            }
        })

    }
}