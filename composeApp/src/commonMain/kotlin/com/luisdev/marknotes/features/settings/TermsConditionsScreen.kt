package com.luisdev.marknotes.features.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisdev.marknotes.core.ui.components.MyDashboardBack
import com.luisdev.marknotes.core.utils.loadMarkdownResource
import com.mikepenz.markdown.m3.Markdown
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.terms_conditions
import org.jetbrains.compose.resources.stringResource

@Composable
fun TermsConditionsScreen(
    language: String,
    onBackClick: () -> Unit
) {
    MyDashboardBack(
        modifier = Modifier.padding(horizontal = 32.dp),
        title = stringResource(Res.string.terms_conditions),
        onBackClick = { onBackClick() },
        content = {
            LazyColumn (
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    val markdown = """
                    ### What's included 🚀
                    
                    - Super simple setup
                    - Cross-platform ready
                    - Lightweight
                    """.trimIndent()

//                    Markdown(markdown)
                }

                item {
                    val content by produceState(initialValue = "") {
                        value = loadMarkdownResource("terms", language)
                    }
                    Markdown(content = """$content""".trimIndent())
                }
            }
        }
    )
}