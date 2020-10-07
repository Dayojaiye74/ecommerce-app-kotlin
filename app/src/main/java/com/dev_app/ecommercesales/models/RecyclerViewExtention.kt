package com.dev_app.ecommercesales.models
//
//import android.view.View
//import androidx.recyclerview.widget.RecyclerView
//
//
//class RecyclerViewExtention {
//
//
//    interface OnItemClickListener {
//        fun onItemClicked(position: Int, view: View)
//    }
//
//    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
//        this.addOnChildAttachStateChangeListener(object :
//            RecyclerView.OnChildAttachStateChangeListener {
//            override fun onChildViewDetachedFromWindow(view: View?) {
//                view?.setOnClickListener(null)
//            }
//
//            override fun onChildViewAttachedToWindow(view: View?) {
//                view?.setOnClickListener({
//                    val holder = getChildViewHolder(view)
//                    onClickListener.onItemClicked(holder.adapterPosition, view)
//                })
//            }
//        })
//    }
//
//// Usage:
//    recyclerView.addOnItemClickListener(
//    object : OnItemClickListener {
//        override fun onItemClicked(position: Int, view: View) {
//            // Your logic
//        }
//    })
//}