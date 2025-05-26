package com.luisdev.marknotes.features.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.luisdev.marknotes.core.ui.components.MyDashboardBack
import com.luisdev.marknotes.core.utils.getAppVersion
import com.luisdev.marknotes.core.utils.getBuildNumber
import com.luisdev.marknotes.core.utils.loadMarkdownResource
import com.mikepenz.markdown.m3.Markdown
import marknotes.composeapp.generated.resources.Res
import marknotes.composeapp.generated.resources.app_version
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppVersionScreen(
    language: String,
    onBackClick: () -> Unit
) {
    MyDashboardBack(
        modifier = Modifier.padding(horizontal = 32.dp),
        title = stringResource(Res.string.app_version),
        onBackClick = { onBackClick() },
        content = {
            LazyColumn (
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    val version = getAppVersion()
                    val build = getBuildNumber()
                    var md = ""
                    if (language == "es")
                        md = """
                            ### 📱 Application Version
    
                            Welcome to **MarkNotes** ✨  
                            Your secure space for taking notes in Markdown.
    
                            ---
    
                            ##### ℹ️ Version Info
    
                            - **App Version:** $version  
                            - **Build Number:** $build
                            - **Last Update:** `May 26, 2025`
    
                            ---
    
                            ##### 🚀 What’s New?
    
                            - Authentication with Google, GitHub, and Apple
                            - Cross-device note synchronization
                            - Performance improvements and bug fixes
                            - Cleaner, modern UI
    
                            ---
    
                            ##### 📬 Feedback or Issues?
    
                            Contact us at  
                            [support@marknotes.app](mailto:support@marknotes.app)
    
                            ---
    
                            > Thank you for using MarkNotes ❤️
    
                            ---
    
                            ##### 🔁 Updates
    
                            We'll notify you here whenever a new version is available.
                        """.trimIndent()
                    else {
                        md = """
                            ### 📱 Application Version
                        
                            Welcome to **MarkNotes** ✨  
                            Your secure space for taking notes in Markdown.
                        
                            ---
                        
                            ##### ℹ️ Version Info
                        
                            - **App Version:** $version  
                            - **Build Number:** $build
                            - **Last Update:** `May 26, 2025`
                        
                            ---
                        
                            ##### 🚀 What’s New?
                        
                            - Authentication with Google, GitHub, and Apple
                            - Cross-device note synchronization
                            - Performance improvements and bug fixes
                            - Cleaner, modern UI
                        
                            ---
                        
                            ##### 📬 Feedback or Issues?
                        
                            Contact us at  
                            [support@marknotes.app](mailto:support@marknotes.app)
                        
                            ---
                        
                            > Thank you for using MarkNotes ❤️
                        
                            ---
                        
                            ##### 🔁 Updates
                        
                            We'll notify you here whenever a new version is available.
                        """.trimIndent()
                    }
                    Markdown(md)
                }
            }
        }
    )
}