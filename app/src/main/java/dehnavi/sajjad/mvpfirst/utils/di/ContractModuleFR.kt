package dehnavi.sajjad.mvpfirst.utils.di

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dehnavi.sajjad.mvpfirst.ui.add.AddContracts
import dehnavi.sajjad.mvpfirst.ui.main.MainContracts

@Module
@InstallIn(FragmentComponent::class)
class ContractModuleFR {

    @Provides
    fun provideAddView(fragment: Fragment): AddContracts.View {
        return fragment as AddContracts.View
    }
}