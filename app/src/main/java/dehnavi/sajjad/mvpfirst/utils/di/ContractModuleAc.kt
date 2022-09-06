package dehnavi.sajjad.mvpfirst.utils.di

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dehnavi.sajjad.mvpfirst.ui.main.MainContracts

@Module
@InstallIn(ActivityComponent::class)
class ContractModuleAc {

    @Provides
    fun provideMainView(activity: Activity): MainContracts.View {
        return activity as MainContracts.View
    }
}