package com.kittisak.pscmenu.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kittisak.pscmenu.R
import com.kittisak.pscmenu.activity.MenuAdapter


private var MenuItem.price: Any
private var MenuItem.name: Any
private var Unit.layoutManager: LinearLayoutManager
private var Unit.Menuadapter: MenuAdapter
private var MenuItem.amount: Any
private var RecyclerView.adapter: Any

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var menuItems: List<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_item)

        setupMenuItems()
        setupRecyclerView()
        setupBottomNavigation()
    }

    private fun setupMenuItems() {
        menuItems = listOf(
            MenuItem("น้ำพริกกากหมู", "ราคา : 20 บาท", R.drawable.product , "20"),
            MenuItem("น้ำพริกคะน่า", "ราคา : 20 บาท", R.drawable.product , "20"),
            MenuItem("น้ำพริกปลาร้า", "ราคา : 20 บาท", R.drawable.product , "20"),
            MenuItem("น้ำพริกกันต์", "ราคา : 20 บาท", R.drawable.product , "20"),
            MenuItem("น้ำพริกกากหมู", "ราคา : 20 บาท", R.drawable.product , "20"),
            MenuItem("น้ำพริกคะน่า", "ราคา : 20 บาท", R.drawable.product , "20"),
            MenuItem("น้ำพริกปลาร้า", "ราคา : 20 บาท", R.drawable.product , "20"),
            MenuItem("น้ำพริกกันต์", "ราคา : 20 บาท", R.drawable.product , "20")
        )
    }

    private fun setupRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        val adapter = MenuAdapter(menuItems, ::addToCart)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.Menuadapter = adapter
    }

    private fun findViewById(recyclerView: Int) {
        TODO("Not yet implemented")
    }

    private fun addToCart(menuItem: MenuItem) {
        // Add item to cart logic
        menuItem.amount
        // You can show a toast or update UI here
    }

    private fun showEditDialog(menuItem: MenuItem) {
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.fragment_edit, null)

        // หา Views ใน Dialog
        val etName = dialogView.findViewById<EditText>(R.id.edit_name)
        val etPrice = dialogView.findViewById<EditText>(R.id.et_edit_price)
        val etQuantity = dialogView.findViewById<EditText>(R.id.et_edit_amount)
        val btnSave = dialogView.findViewById<Button>(R.id.btn_save)
        val btnCancel = dialogView.findViewById<Button>(R.id.btn_cancel)
        val recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        val adapter = MenuAdapter(menuItems) { menuItem ->
            // เมื่อกดปุ่ม + จะเรียกฟังก์ชันนี้
            showEditDialog(menuItem)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.Menuadapter = adapter
        // ใส่ข้อมูลปัจจุบัน
        etName.setText(menuItem.name)
        etPrice.setText(menuItem.price.replace("ราคา : ", ""))
        etQuantity.setText(menuItem.amount.toString())

        // สร้าง Dialog
        val alertDialog = dialog.setView(dialogView)
            .setTitle("แก้ไขข้อมูลสินค้า")
            .create()

        // เมื่อกดปุ่มบันทึก
        btnSave.setOnClickListener {
            val newName = etName.text.toString()
            val newPrice = etPrice.text.toString()
            val newAmount = etQuantity.text.toString().toIntOrNull() ?: 0

            if (newName.isNotEmpty() && newPrice.isNotEmpty()) {
                // อัปเดตข้อมูล
                menuItem.name = newName
                menuItem.price = "ราคา : $newPrice"
                menuItem.amount = "$newAmount"

                // Refresh หน้าจอ
                findViewById<RecyclerView>(R.id.RecyclerView).Menuadapter?.notifyDataSetChanged()

                Toast.makeText(this, "อัปเดตข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show()
                alertDialog.dismiss()
            } else {
                Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show()
            }
        }

        // เมื่อกดปุ่มยกเลิก
        btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        // แสดง Dialog
        alertDialog.show()
    }
}

private fun Any.replace(string: String, string2: String) {}
