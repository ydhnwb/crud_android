package com.ydhnwb.myapplication

import android.app.Application
import androidx.room.Room
import com.ydhnwb.myapplication.data.database.AppDatabase
import com.ydhnwb.myapplication.ui.create.ProductViewModel
import com.ydhnwb.myapplication.ui.main.MainViewModel
import com.ydhnwb.myapplication.ui.update.UpdateViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApp)
            modules(listOf(appDatabase, viewModelModule))
        }
    }

}

val appDatabase = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "mydb")
            .allowMainThreadQueries().build()
    }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        ProductViewModel(get())
    }
    viewModel {
        UpdateViewModel(get())
    }

}