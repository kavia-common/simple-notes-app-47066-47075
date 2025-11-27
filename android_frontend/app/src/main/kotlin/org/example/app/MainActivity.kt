package org.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.app.ui.NoteEditorScreen
import org.example.app.ui.NotesListScreen
import org.example.app.ui.theme.NotesTheme
import org.example.app.viewmodel.NotesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesTheme {
                Surface {
                    NotesApp()
                }
            }
        }
    }
}

@Composable
fun NotesApp() {
    val navController = rememberNavController()
    val vm: NotesViewModel = viewModel()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            NotesListScreen(
                notes = vm.uiState.notes,
                onAdd = {
                    vm.prepareNewNote()
                    navController.navigate("editor")
                },
                onEdit = { noteId ->
                    vm.loadNote(noteId)
                    navController.navigate("editor")
                },
                onDelete = { noteId ->
                    vm.deleteNote(noteId)
                }
            )
        }
        composable("editor") {
            NoteEditorScreen(
                note = vm.uiState.currentEditing,
                onTitleChange = vm::updateTitle,
                onContentChange = vm::updateContent,
                onSave = {
                    vm.saveCurrent()
                    navController.popBackStack()
                },
                onCancel = {
                    vm.cancelEditing()
                    navController.popBackStack()
                },
                onDelete = {
                    vm.deleteCurrent()
                    navController.popBackStack()
                }
            )
        }
    }
}
