package com.kalemba128.auth.ui.main

import androidx.lifecycle.ViewModel
import com.kalemba128.auth.ServerApi
import com.kalemba128.auth.RetrofitHelper
import com.kalemba128.auth.model.User

class MainViewModel : ViewModel() {
    private var serverApi = RetrofitHelper.getInstance(null).create(ServerApi::class.java)
    lateinit var user: User
}