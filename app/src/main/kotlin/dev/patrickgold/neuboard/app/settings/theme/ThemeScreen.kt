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

package dev.patrickgold.neuboard.app.settings.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessAuto
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.patrickgold.neuboard.R
import dev.patrickgold.neuboard.app.LocalNavController
import dev.patrickgold.neuboard.app.Routes
import dev.patrickgold.neuboard.app.enumDisplayEntriesOf
import dev.patrickgold.neuboard.app.ext.AddonManagementReferenceBox
import dev.patrickgold.neuboard.app.ext.ExtensionListScreenType
import dev.patrickgold.neuboard.ime.theme.ThemeManager
import dev.patrickgold.neuboard.ime.theme.ThemeMode
import dev.patrickgold.neuboard.lib.compose.NeuboardInfoCard
import dev.patrickgold.neuboard.lib.compose.NeuboardScreen
import dev.patrickgold.neuboard.lib.compose.stringRes
import dev.patrickgold.neuboard.lib.ext.ExtensionComponentName
import dev.patrickgold.neuboard.themeManager
import dev.patrickgold.jetpref.datastore.model.observeAsState
import dev.patrickgold.jetpref.datastore.ui.ColorPickerPreference
import dev.patrickgold.jetpref.datastore.ui.ListPreference
import dev.patrickgold.jetpref.datastore.ui.Preference
import dev.patrickgold.jetpref.datastore.ui.isMaterialYou
import org.neuboard.lib.color.ColorMappings

@Composable
fun ThemeScreen() = NeuboardScreen {
    title = stringRes(R.string.settings__theme__title)
    previewFieldVisible = true

    val context = LocalContext.current
    val navController = LocalNavController.current
    val themeManager by context.themeManager()

    @Composable
    fun ThemeManager.getThemeLabel(id: ExtensionComponentName): String {
        val configs by indexedThemeConfigs.observeAsState()
        configs?.get(id)?.let { return it.label }
        return id.toString()
    }

    content {
        val themeMode by prefs.theme.mode.observeAsState()
        val dayThemeId by prefs.theme.dayThemeId.observeAsState()
        val nightThemeId by prefs.theme.nightThemeId.observeAsState()

        /*Card(modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text("If you want to give feedback on the new stylesheet editor and theme engine, please do so in below linked feedback thread:\n")
                Button(onClick = {
                    context.launchUrl("https://github.com/florisboard/florisboard/discussions/1531")
                }) {
                    Text("Open Feedback Thread")
                }
            }
        }*/

        ListPreference(
            prefs.theme.mode,
            icon = Icons.Default.BrightnessAuto,
            title = stringRes(R.string.pref__theme__mode__label),
            entries = enumDisplayEntriesOf(ThemeMode::class),
        )
        if (themeMode == ThemeMode.FOLLOW_TIME) {
            NeuboardInfoCard(
                modifier = Modifier.padding(8.dp),
                text = """
                The theme mode "Follow time" is not available in this beta release.
            """.trimIndent()
            )
        }
        Preference(
            icon = Icons.Default.LightMode,
            title = stringRes(R.string.pref__theme__day),
            summary = themeManager.getThemeLabel(dayThemeId),
            enabledIf = { prefs.theme.mode isNotEqualTo ThemeMode.ALWAYS_NIGHT },
            onClick = {
                navController.navigate(Routes.Settings.ThemeManager(ThemeManagerScreenAction.SELECT_DAY))
            },
        )
        Preference(
            icon = Icons.Default.DarkMode,
            title = stringRes(R.string.pref__theme__night),
            summary = themeManager.getThemeLabel(nightThemeId),
            enabledIf = { prefs.theme.mode isNotEqualTo ThemeMode.ALWAYS_DAY },
            onClick = {
                navController.navigate(Routes.Settings.ThemeManager(ThemeManagerScreenAction.SELECT_NIGHT))
            },
        )
        ColorPickerPreference(
            pref = prefs.theme.accentColor,
            title = stringRes(R.string.pref__theme__theme_accent_color__label),
            defaultValueLabel = stringRes(R.string.action__default),
            icon = Icons.Default.ColorLens,
            defaultColors = ColorMappings.colors,
            showAlphaSlider = false,
            enableAdvancedLayout = false,
            colorOverride = {
                if (it.isMaterialYou(context)) {
                    Color.Unspecified
                } else {
                    it
                }
            }
        )

        AddonManagementReferenceBox(type = ExtensionListScreenType.EXT_THEME)
    }
}
