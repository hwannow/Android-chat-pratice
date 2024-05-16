package com.example.assignment11th_

import androidx.recyclerview.widget.RecyclerView
import com.example.assignment11th_.databinding.LeftchattingSampleBinding

class LeftChattingViewHolder(binding: LeftchattingSampleBinding): RecyclerView.ViewHolder(binding.root) {
    val chat = binding.tvText
    val time = binding.tvTime
}