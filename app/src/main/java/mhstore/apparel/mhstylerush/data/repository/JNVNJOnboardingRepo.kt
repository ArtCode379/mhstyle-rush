package mhstore.apparel.mhstylerush.data.repository

import mhstore.apparel.mhstylerush.data.datastore.JNVNJOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class JNVNJOnboardingRepo(
    private val jnvnjOnboardingStoreManager: JNVNJOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return jnvnjOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            jnvnjOnboardingStoreManager.setOnboardedState(state)
        }
    }
}