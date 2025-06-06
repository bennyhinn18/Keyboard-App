/*
 * Copyright (C) 2021-2025 The NeuBoard Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.patrickgold.neuboard.lib.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes
import dev.patrickgold.neuboard.R
import dev.patrickgold.neuboard.lib.devtools.flogError
import dev.patrickgold.neuboard.lib.io.FlorisRef
import org.neuboard.lib.android.stringRes
import org.neuboard.lib.kotlin.CurlyArg
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.reflect.KClass

fun Context.launchUrl(url: String) {
    val intent = Intent().also {
        it.action = Intent.ACTION_VIEW
        it.data = FlorisRef.fromUrl(url).uri
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    try {
        this.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        flogError { e.toString() }
        Toast.makeText(
            this,
            this.stringRes(R.string.general__no_browser_app_found_for_url, "url" to url),
            Toast.LENGTH_LONG,
        ).show()
    }
}

fun Context.launchUrl(@StringRes url: Int) {
    launchUrl(this.stringRes(url))
}

fun Context.launchUrl(@StringRes url: Int, vararg args: CurlyArg) {
    launchUrl(this.stringRes(url, *args))
}

inline fun <T : Any> Context.launchActivity(kClass: KClass<T>, intentModifier: (Intent) -> Unit = { }) {
    contract {
        callsInPlace(intentModifier, InvocationKind.AT_MOST_ONCE)
    }
    try {
        val intent = Intent(this, kClass.java)
        intentModifier(intent)
        this.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        flogError { e.toString() }
        Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()
    }
}

inline fun Context.launchActivity(intentModifier: (Intent) -> Unit) {
    contract {
        callsInPlace(intentModifier, InvocationKind.AT_MOST_ONCE)
    }
    try {
        val intent = Intent()
        intentModifier(intent)
        this.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        flogError { e.toString() }
        Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()
    }
}
