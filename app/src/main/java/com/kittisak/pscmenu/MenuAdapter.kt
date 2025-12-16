package com.kittisak.pscmenu.activity

import android.view.LayoutInflater
import android.view.View
import android.R
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kittisak.pscmenu.activity.Menuitem

class MenuAdapter(
    private val menuItems: List<Menuitem>,
    private val onItemClick: (MenuItem) -> Unit
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.item_image)
        val itemName: TextView = itemView.findViewById(R.id.item_name)
        val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        val itemAmount: TextView = itemView.findViewById(R.id.item_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuItem = menuItems[position]

        holder.itemName.text = menuItem.name
        holder.itemPrice.text = menuItem.price
        holder.itemAmount.text = "คงเหลือ: ${menuItem.amount} ชิ้น"
        holder.itemImage.setImageResource(menuItem.image)

        holder.editButton.setOnClickListener {
            onItemClick(menuItem)
        }
    }

    override fun getItemCount(): Int = menuItems.size
}