package com.example.baitapbuoi4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.baitapbuoi4.ui.StudentViewModel
import com.example.baitapbuoi4.ui.screens.AddEditStudentScreen
import com.example.baitapbuoi4.ui.screens.StudentListScreen
import com.example.baitapbuoi4.ui.theme.BaiTapBuoi4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BaiTapBuoi4Theme {
                val navController = rememberNavController()
                val viewModel: StudentViewModel = viewModel()

                NavHost(
                    navController = navController,
                    startDestination = "student_list"
                ) {
                    composable("student_list") {
                        StudentListScreen(
                            viewModel = viewModel,
                            onNavigateToAdd = {
                                navController.navigate("add_student")
                            },
                            onNavigateToEdit = { id ->
                                navController.navigate("edit_student/$id")
                            }
                        )
                    }

                    composable("add_student") {
                        AddEditStudentScreen(
                            studentId = null,
                            viewModel = viewModel,
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(
                        route = "edit_student/{studentId}",
                        arguments = listOf(
                            navArgument("studentId") {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        val studentId = backStackEntry.arguments?.getInt("studentId") ?: -1
                        AddEditStudentScreen(
                            studentId = studentId,
                            viewModel = viewModel,
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
