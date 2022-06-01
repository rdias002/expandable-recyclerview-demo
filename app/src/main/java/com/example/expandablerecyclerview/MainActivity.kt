package com.example.expandablerecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expandablerecyclerview.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), ExpandableRecyclerViewAdapter.OnItemCheckChangedListener {
    private lateinit var binding: ActivityMainBinding
    private val itemsList = (1..100).map { ItemData("Item Number $it") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.recyclerView.adapter = ExpandableRecyclerViewAdapter(itemsList, this)
    }

    override fun onCheckChanged(position: Int, isChecked: Boolean) {
        val message =
            if (isChecked) "Item at position $position checked"
            else "Item at position $position unchecked"

        Snackbar.make(binding.recyclerView, message, Snackbar.LENGTH_SHORT).show()
    }
}