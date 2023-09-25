package cash.spont.v6.takeaway.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Auth0ViewModel::class.java)) {
            return Auth0ViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}