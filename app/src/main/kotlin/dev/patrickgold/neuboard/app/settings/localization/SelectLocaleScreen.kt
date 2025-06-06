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

package dev.patrickgold.neuboard.app.settings.localization

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import dev.patrickgold.neuboard.R
import dev.patrickgold.neuboard.app.LocalNavController
import dev.patrickgold.neuboard.app.neuboardPreferenceModel
import dev.patrickgold.neuboard.ime.core.DisplayLanguageNamesIn
import dev.patrickgold.neuboard.lib.FlorisLocale
import dev.patrickgold.neuboard.lib.compose.NeuboardScreen
import dev.patrickgold.neuboard.lib.compose.florisScrollbar
import dev.patrickgold.neuboard.lib.compose.stringRes
import dev.patrickgold.jetpref.datastore.model.observeAsState
import dev.patrickgold.jetpref.material.ui.JetPrefListItem

const val SelectLocaleScreenResultLanguageTag = "SelectLocaleScreen.languageTag"

@Composable
fun SelectLocaleScreen() = NeuboardScreen {
    title = stringRes(R.string.settings__localization__subtype_select_locale)
    scrollable = false

    val prefs by neuboardPreferenceModel()
    val navController = LocalNavController.current

    val displayLanguageNamesIn by prefs.localization.displayLanguageNamesIn.observeAsState()
    var searchTermValue by remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current
    val systemLocales =
        FlorisLocale.extendedAvailableLocales(context).sortedBy { locale ->
            when (displayLanguageNamesIn) {
                DisplayLanguageNamesIn.SYSTEM_LOCALE -> locale.displayName()
                DisplayLanguageNamesIn.NATIVE_LOCALE -> locale.displayName(locale)
            }.lowercase()
        }

    val filteredSystemLocales = remember(searchTermValue) {
        if (searchTermValue.text.isBlank()) {
            systemLocales
        } else {
            val term = searchTermValue.text.trim().lowercase()
            systemLocales.filter { locale ->
                locale.displayName().lowercase().contains(term) ||
                    locale.displayName(locale).lowercase().contains(term) ||
                    locale.displayName(FlorisLocale.ENGLISH).lowercase().contains(term) ||
                    locale.languageTag().lowercase().startsWith(term) ||
                    locale.localeTag().lowercase().startsWith(term)
            }
        }
    }

    content {
        val state = rememberLazyListState()
        Column(modifier = Modifier.fillMaxSize()) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchTermValue,
                onValueChange = { searchTermValue = it },
                placeholder = { Text(stringRes(R.string.settings__localization__subtype_search_locale_placeholder)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                },
                singleLine = true,
                shape = RectangleShape,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
            )
            if (filteredSystemLocales.isEmpty()) {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    text = stringRes(
                        R.string.settings__localization__subtype_search_locale_not_found,
                        "search_term" to searchTermValue.text,
                    ),
                    color = LocalContentColor.current.copy(alpha = 0.54f),
                )
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .florisScrollbar(state, isVertical = true),
                state = state,
            ) {
                items(filteredSystemLocales) { systemLocale ->
                    JetPrefListItem(
                        modifier = Modifier.clickable {
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set(SelectLocaleScreenResultLanguageTag, systemLocale.languageTag())
                            navController.popBackStack()
                        },
                        text = when (displayLanguageNamesIn) {
                            DisplayLanguageNamesIn.SYSTEM_LOCALE -> systemLocale.displayName()
                            DisplayLanguageNamesIn.NATIVE_LOCALE -> systemLocale.displayName(systemLocale)
                        },
                    )
                }
            }
        }
    }
}
