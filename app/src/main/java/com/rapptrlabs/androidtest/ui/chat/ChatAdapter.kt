package com.rapptrlabs.androidtest.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.rapptrlabs.androidtest.R
import com.rapptrlabs.androidtest.data.model.ChatMessageModel

class ChatAdapter(private var messages: List<ChatMessageModel> = mutableListOf()) : RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat, parent, false)

        return ChatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount(): Int = messages.size

    inner class ChatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
        var messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
        var userNameTextView: TextView = itemView.findViewById(R.id.usernameTextView)

        fun bind(message: ChatMessageModel) {
            avatarImageView.load(message.avatarUrl.replace("http://", "https://"))
            messageTextView.text = message.message
            userNameTextView.text = message.userName
        }
    }
}