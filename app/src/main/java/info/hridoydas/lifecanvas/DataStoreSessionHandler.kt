/*
* MIT License
*
* Copyright (c) 2024 Hridoy Chandra Das
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
*
*/
package info.hridoydas.lifecanvas

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import info.hridoydas.lifecanvas.storage.CurrentUser
import info.hridoydas.lifecanvas.storage.SessionHandler
import info.hridoydas.lifecanvas.storage.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.userDataStore: DataStore<User> by dataStore(
    fileName = "user.pb",
    serializer = UserSerializer,
)

class DataStoreSessionHandler
    @Inject
    constructor(
        @ApplicationContext private val context: Context,
    ) : SessionHandler {
        override suspend fun setCurrentUser(
            id: Int,
            authKey: String?,
        ) {
            context.userDataStore.updateData {
                it.toBuilder()
                    .setAuthKey(authKey)
                    .setId(id)
                    .build()
            }
        }

        override fun getCurrentUser(): Flow<CurrentUser> {
            return context.userDataStore.data.map {
                CurrentUser(it.id, it.authKey)
            }
        }

        override suspend fun clear() {
            context.userDataStore.updateData {
                it.toBuilder()
                    .setAuthKey("")
                    .setId(-1)
                    .build()
            }
        }
    }
