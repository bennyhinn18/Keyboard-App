/*
 * Copyright (C) 2022-2025 The NeuBoard Contributors
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

package dev.patrickgold.neuboard.app.ext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.patrickgold.neuboard.R
import dev.patrickgold.neuboard.ime.nlp.LanguagePackComponent
import dev.patrickgold.neuboard.ime.theme.ThemeExtensionComponent
import dev.patrickgold.neuboard.lib.compose.FlorisIconButton
import dev.patrickgold.neuboard.lib.compose.NeuboardOutlinedBox
import dev.patrickgold.neuboard.lib.compose.FlorisTextButton
import dev.patrickgold.neuboard.lib.compose.stringRes
import dev.patrickgold.neuboard.lib.ext.ExtensionComponent
import dev.patrickgold.neuboard.lib.ext.ExtensionComponentName
import dev.patrickgold.neuboard.lib.ext.ExtensionMeta

@Composable
fun ExtensionComponentNoneFoundView() {
    Text(
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        text = stringRes(R.string.ext__meta__components_none_found),
        fontStyle = FontStyle.Italic,
    )
}

@Composable
fun ExtensionComponentView(
    meta: ExtensionMeta,
    component: ExtensionComponent,
    modifier: Modifier = Modifier,
    onDeleteBtnClick: (() -> Unit)? = null,
    onEditBtnClick: (() -> Unit)? = null,
) {
    val componentName = remember(meta.id, component.id) { ExtensionComponentName(meta.id, component.id).toString() }
    NeuboardOutlinedBox(
        modifier = modifier,
        title = component.label,
        subtitle = componentName,
    ) {
        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                bottom = if (onDeleteBtnClick == null && onEditBtnClick == null) 8.dp else 0.dp,
            ),
        ) {
            when (component) {
                is ThemeExtensionComponent -> {
                    val text = remember(
                        component.authors, component.isNightTheme, component.stylesheetPath(),
                    ) {
                        buildString {
                            appendLine("authors = ${component.authors}")
                            appendLine("isNightTheme = ${component.isNightTheme}")
                            append("stylesheetPath = ${component.stylesheetPath()}")
                        }
                    }
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = LocalContentColor.current,
                    )
                }
                is LanguagePackComponent -> {
                    val text = remember(
                        component.authors, component.locale, component.hanShapeBasedKeyCode,
                    ) {
                        buildString {
                            appendLine("authors = ${component.authors}")
                            appendLine("locale = ${component.locale.localeTag()}")
                            appendLine("hanShapeBasedKeyCode = ${component.hanShapeBasedKeyCode}")
                        }
                    }
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyMedium,
                        color = LocalContentColor.current,
                    )
                }
                else -> { }
            }
        }
        if (onDeleteBtnClick != null || onEditBtnClick != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
            ) {
                if (onDeleteBtnClick != null) {
                    FlorisTextButton(
                        onClick = onDeleteBtnClick,
                        icon = Icons.Default.Delete,
                        text = stringRes(R.string.action__delete),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.error,
                        ),
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                if (onEditBtnClick != null) {
                    FlorisTextButton(
                        onClick = onEditBtnClick,
                        icon = Icons.Default.Edit,
                        text = stringRes(R.string.action__edit),
                    )
                }
            }
        }
    }
}

@Composable
fun <T : ExtensionComponent> ExtensionComponentListView(
    modifier: Modifier = Modifier,
    title: String,
    components: List<T>,
    onCreateBtnClick: (() -> Unit)? = null,
    componentGenerator: @Composable (T) -> Unit,
) {
    Column(modifier = modifier) {
        ListItem(
            headlineContent = { Text(
                text = title,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            ) },
            trailingContent = if (onCreateBtnClick != null) {
                @Composable {
                    FlorisIconButton(
                        onClick = onCreateBtnClick,
                        icon = Icons.Default.Add,
                        iconColor = MaterialTheme.colorScheme.secondary,
                    )
                }
            } else { null },
        )
        if (components.isEmpty()) {
            ExtensionComponentNoneFoundView()
        } else {
            for (component in components) {
                componentGenerator(component)
            }
        }
    }
}
