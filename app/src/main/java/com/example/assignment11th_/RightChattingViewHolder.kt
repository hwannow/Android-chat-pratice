package com.example.assignment11th_

import androidx.recyclerview.widget.RecyclerView
import com.example.assignment11th_.databinding.RightchattingSampleBinding

class RightChattingViewHolder(binding: RightchattingSampleBinding): RecyclerView.ViewHolder(binding.root) {
    val chat = binding.tvText
    val time = binding.tvTime
}