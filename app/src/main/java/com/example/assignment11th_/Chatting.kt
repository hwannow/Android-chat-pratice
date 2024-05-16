package com.example.assignment11th_

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment11th_.databinding.ActivityChattingBinding
import java.time.LocalDateTime

class Chatting : AppCompatActivity() {
    private lateinit var binding: ActivityChattingBinding
    var chattingList: MutableList<ChatData> = mutableListOf()
    val chattingAdapter by lazy { ChattingAdapter(chattingList) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rcvChatting.adapter = chattingAdapter
        binding.rcvChatting.layoutManager = LinearLayoutManager(this@Chatting, LinearLayoutManager.VERTICAL, false)

        chattingAdapter.onChatClickListener(object: ChattingAdapter.ChatClick {
            override fun onLongClick(view: View, position: Int) {
                chattingList.removeAt(position)
                // 모든 채팅의 position을 갱신 -> notifyDataSetChanged 사용
                binding.rcvChatting.adapter?.notifyDataSetChanged()
            }
        })

        binding.btnLeft.setOnClickListener {
            val input = binding.etTextinput.text.toString()
            if (input.isEmpty()) {
                Toast.makeText(this@Chatting, "메시지를 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else {
                chattingList.add(ChatData(input, 0, LocalDateTime.now()))
                binding.rcvChatting.adapter?.notifyItemInserted(chattingList.size)
                binding.etTextinput.setText("")
                binding.rcvChatting.scrollToPosition(chattingList.size - 1)
                // 채팅을 추가할 때는 이전 채팅의 time만 수정해 주면 됨 -> notifyItemChanged 사용
                binding.rcvChatting.adapter?.notifyItemChanged(chattingList.size - 2)
            }
        }

        binding.btnRight.setOnClickListener {
            val input = binding.etTextinput.text.toString()
            if (input.isEmpty()) {
                Toast.makeText(this@Chatting, "메시지를 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else {
                chattingList.add(ChatData(input, 1, LocalDateTime.now()))
                binding.rcvChatting.adapter?.notifyItemInserted(chattingList.size)
                binding.etTextinput.setText("")
                binding.rcvChatting.scrollToPosition(chattingList.size - 1)
                binding.rcvChatting.adapter?.notifyItemChanged(chattingList.size - 2)
            }
        }
    }
}