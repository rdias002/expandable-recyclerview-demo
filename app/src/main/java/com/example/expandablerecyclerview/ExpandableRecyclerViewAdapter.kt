package com.example.expandablerecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.databinding.ListItemViewBinding

class ExpandableRecyclerViewAdapter(
    private val itemList: List<ItemData>,
    private val listener: OnItemCheckChangedListener
) : RecyclerView.Adapter<ExpandableRecyclerViewAdapter.ViewHolder>() {

    private var expandedItems: ArrayList<ItemData> = arrayListOf()

    inner class ViewHolder(
        private val binding: ListItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemData) {
            val isExpanded = expandedItems.contains(item)
            binding.textView.text = item.itemText
            binding.checkbox.isChecked = item.isChecked
            binding.imageView.setImageResource(
                if (isExpanded) R.drawable.ic_arrow_drop_up
                else R.drawable.ic_arrow_drop_down
            )
            binding.expandableLayout.isVisible = isExpanded

            binding.textView.setOnClickListener {
                if (expandedItems.contains(item)) {
                    expandedItems.remove(item)
                } else {
                    expandedItems.add(item)
                }
                notifyItemChanged(bindingAdapterPosition)
            }

            binding.checkbox.setOnClickListener {
                val isChecked = binding.checkbox.isChecked
                itemList[bindingAdapterPosition].isChecked = isChecked
                listener.onCheckChanged(bindingAdapterPosition, isChecked)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    interface OnItemCheckChangedListener {
        fun onCheckChanged(position: Int, isChecked: Boolean)
    }
}
