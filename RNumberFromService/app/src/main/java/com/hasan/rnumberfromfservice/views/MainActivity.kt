package com.hasan.rnumberfromfservice.views

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.google.android.material.navigation.NavigationView
import com.hasan.rnumberfromfservice.NumberService
import com.hasan.rnumberfromfservice.R
import com.hasan.rnumberfromfservice.viewmodels.MainViewModel


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    //region Declaration
    private lateinit var mDrawer: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var acToolbar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var vModel :MainViewModel
   // private lateinit var br : MyBroadcastReceiver
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initValue()
        startService()
        if(savedInstanceState ==null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_Id,NumberFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_randomNumberWithTimer)}
    }
    //region View Initialization
    private fun initView(){
        mDrawer = findViewById(R.id.drawer_Layout_Id)
        acToolbar = findViewById(R.id.actionbarId)
        navigationView = findViewById(R.id.nav_view)
        toggle = ActionBarDrawerToggle(this,mDrawer,R.string.navigation_drawer_open,R.string.navigation_drawer_close)


    }
    //endregion

    //region Value Initialization
    private fun initValue(){
        vModel =  ViewModelProvider(this).get(MainViewModel::class.java)
       // br = MyBroadcastReceiver()
        navigationView.setNavigationItemSelectedListener(this)
        toggle.isDrawerIndicatorEnabled = true
        toggle.syncState()
        mDrawer.addDrawerListener(toggle)
        acToolbar.title = "Main Activity"
        setSupportActionBar(acToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //vModel.setData(br.getBrData())

    }

    //endregion


    //region Broadcast
    private val br : BroadcastReceiver = object : BroadcastReceiver()  {
        override fun onReceive(context: Context?, intent: Intent?) {
            val num = intent!!.getIntExtra("Number",0)
            val vModel: MainViewModel by viewModels()
            vModel.setData(num)
        }
    }
    //endregion




    //region Override Function
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_randomNumber->{
                Toast.makeText(applicationContext,"${item.title} is clicked", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container_Id,RandomNumberFragment()).commit()
                mDrawer.closeDrawer(GravityCompat.START)
            return true
            }
            R.id.nav_randomNumberWithTimer->{
                Toast.makeText(applicationContext,"${item.title} is clicked", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container_Id,NumberFragment()).commit()
                mDrawer.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_timer->{
                Toast.makeText(applicationContext,"${item.title} is clicked", Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container_Id,NumFragment()).commit()
                mDrawer.closeDrawer(GravityCompat.START)
                return true
            }
            R.id.nav_help->{
                Toast.makeText(applicationContext,"${item.title} is clicked", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.nav_settings->{
                Toast.makeText(applicationContext,"${item.title} is clicked", Toast.LENGTH_SHORT).show()
                return true
            }
            else->{
                return true
            }
        }
    }
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_container_Id)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }
    override fun onBackPressed() {
        if(mDrawer.isDrawerOpen(GravityCompat.START)){
            mDrawer.closeDrawer(GravityCompat.START)
        }else
            super.onBackPressed()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroy() {
        super.onDestroy()
        stopService()
    }
    //endregion

    /*override fun onStop(){
        super.onStop()
        stopService()
    }*/

    private fun startService() {
        //Toast.makeText(this,"service started",Toast.LENGTH_SHORT).show()
        val serviceIntent = Intent(applicationContext, NumberService::class.java)
        receiveBroadcastMessage()
        applicationContext.startService(serviceIntent)
    }
    private fun stopService(){
        val serviceIntent = Intent(this, NumberService::class.java)
        unregisterReceiver(br)
        applicationContext.stopService(serviceIntent)

    }
    private fun receiveBroadcastMessage() {
        val intentFilter = IntentFilter()
        intentFilter.addAction("Random_Number_Generator")
        registerReceiver(br,intentFilter)

    }


}
