package br.com.guilherme.githubreposlist.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import br.com.guilherme.githubreposlist.R

class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null
    private var navDestination: NavDestination? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavController()
    }

    private fun setUpNavController() {
        val host: NavHostFragment? = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment?

        navController = host?.navController ?: Navigation.findNavController(this, R.id.nav_host)
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            onDestinationChangedListener(destination)
        }

    }

    private fun onDestinationChangedListener(
        destination: NavDestination,
    ) {
        navDestination = destination

    }

}