package com.luisdev.marknotes.core.utils

fun loadTerms(language: String): String {
    if (language == "en") {
        return "Welcome to MarkNotes.\n" +
                "\n" +
                "By using this application, you agree to the following terms:\n" +
                "\n" +
                "1. This app is provided \"as is\", with no warranties of any kind.\n" +
                "2. You are responsible for the content you create and store.\n" +
                "3. We do not collect personal information without your consent.\n" +
                "4. We may update these terms at any time without notice.\n" +
                "5. Continued use of the app implies acceptance of any changes.\n" +
                "\n" +
                "Thank you for using MarkNotes."
    } else {
        return "Bienvenido a MarkNotes.\n" +
                "\n" +
                "Al utilizar esta aplicación, aceptas los siguientes términos:\n" +
                "\n" +
                "1. Esta aplicación se proporciona \"tal cual\", sin garantías de ningún tipo.\n" +
                "2. Eres responsable del contenido que creas y almacenas.\n" +
                "3. No recopilamos información personal sin tu consentimiento.\n" +
                "4. Podemos actualizar estos términos en cualquier momento sin previo aviso.\n" +
                "5. El uso continuo de la aplicación implica la aceptación de cualquier cambio.\n" +
                "\n" +
                "Gracias por usar MarkNotes.\n"
    }
}