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

package dev.patrickgold.neuboard.app.ext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.patrickgold.neuboard.R
import dev.patrickgold.neuboard.lib.compose.NeuboardScreen
import dev.patrickgold.neuboard.lib.compose.stringRes

@Composable
internal fun ExtensionNotFoundScreen(id: String) = NeuboardScreen {
    title = stringRes(R.string.ext__error__not_found_title)

    content {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(stringRes(R.string.ext__error__not_found_description, "id" to id))
        }
    }
}
