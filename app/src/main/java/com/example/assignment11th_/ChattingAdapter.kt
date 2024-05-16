package com.example.assignment11th_

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment11th_.databinding.LeftchattingSampleBinding
import com.example.assignment11th_.databinding.RightchattingSampleBinding

class ChattingAdapter(val chattingList: MutableList<ChatData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val binding = LeftchattingSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            LeftChattingViewHolder(binding)
        }
        else {
            val binding = RightchattingSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            RightChattingViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return chattingList.size
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position < 0) return
        // 길게 클릭 시 채팅 삭제 이벤트
        holder.itemView.setOnLongClickListener {
            chatClick?.onLongClick(it, position)
            return@setOnLongClickListener true
        }

        if (holder is LeftChattingViewHolder) {
            holder.chat.text = chattingList[position].chat

            if (position == chattingList.size - 1) { // 첫 채팅 or 마지막 채팅일 경우
                holder.time.text = calTime(position) + chattingList[position].time.minute
            }
            else if (chattingList[position + 1].time.minute != chattingList[position].time.minute || chattingList[position + 1].viewType != chattingList[position].viewType){
                holder.time.text = calTime(position) + chattingList[position].time.minute
            }
            else holder.time.text = ""

        }
        else if (holder is RightChattingViewHolder) {
            holder.chat.text = chattingList[position].chat

            if (position == chattingList.size - 1) { // 첫 채팅 or 마지막 채팅일 경우
                holder.time.text = calTime(position) + chattingList[position].time.minute
            }
            else if (chattingList[position + 1].time.minute != chattingList[position].time.minute || chattingList[position + 1].viewType != chattingList[position].viewType){
                holder.time.text = calTime(position) + chattingList[position].time.minute
            }
            else holder.time.text = ""
        }
    }

    override fun getItemViewType(position: Int): Int {
        return chattingList[position].viewType
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun calTime(position: Int): String {
        var str : String
        var h = chattingList[position].time.hour
        if (h > 12) {
            str = "오후 "
            h -= 12
        }
        else str = "오전 "
        str += h.toString() + ":"
        return str
    }

    interface ChatClick {
        fun onLongClick(view: View, position: Int)
    }
    var chatClick: ChatClick?= null

    fun onChatClickListener(listener: ChatClick) {
        this.chatClick = listener
    }
}