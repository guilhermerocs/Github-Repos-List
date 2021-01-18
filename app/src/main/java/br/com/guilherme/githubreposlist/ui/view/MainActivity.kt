package br.com.guilherme.githubreposlist.ui.view

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
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
        handleIntent(intent)

    }

    override fun onNewIntent(intent: Intent?) {
        if (intent != null)
            handleIntent(intent)

        super.onNewIntent(intent)

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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            //use the query to search your data somehow
        }
    }


}