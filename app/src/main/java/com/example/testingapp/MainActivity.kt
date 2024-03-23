package com.example.testingapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.example.testingapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setNavBar()

    }


    private fun setNavBar(){
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        val navView = binding.bottomNavigationView
        navView.selectedItemId = R.id.locationFragment
        findNavController(R.id.fragmentContainerView).addOnDestinationChangedListener{_, destination, _ ->
            when (destination.id) {
                R.id.fullImageFragment->{
                    navView.visibility = View.INVISIBLE
                    binding.clock.visibility = View.INVISIBLE
                }
                else->{
                    navView.visibility = View.VISIBLE
                    binding.clock.visibility = View.VISIBLE
                }
            }

        }
    }

    @SuppressLint("WrongConstant")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val selectedUri: Uri = data?.data ?: return
            val takeFlags = data.flags and (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            contentResolver.takePersistableUriPermission(selectedUri, takeFlags)
        }
    }

}