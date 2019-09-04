package com.example.quiz.explanation

import android.view.ViewGroup
import com.example.quiz.model.Chat
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class ChatAdapter : FirestoreRecyclerAdapter<Chat, ChatHolder>(buildFirestoreOptions()) {
    companion object {

        private fun buildFirestoreOptions(): FirestoreRecyclerOptions<Chat> {
            val db = FirebaseFirestore.getInstance()
            val collectionRef = db.collection("Chat")
            val query = collectionRef.limit(10)

            return FirestoreRecyclerOptions.Builder<Chat>()
                .setQuery(query, Chat::class.java)
                .build()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChatHolder.from(parent)
    override fun onBindViewHolder(p0: ChatHolder, p1: Int, p2: Chat) = p0.bind(p2)
}