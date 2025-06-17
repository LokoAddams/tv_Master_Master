package com.example.tvmaster

//import com.connectsdk.sampler.fragments.BaseFragment
import android.app.Activity
import android.app.AlertDialog
import android.text.InputType
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import com.connectsdk.core.AppInfo
import com.connectsdk.device.ConnectableDevice
import com.connectsdk.device.ConnectableDeviceListener
import com.connectsdk.device.DevicePicker
import com.connectsdk.discovery.DiscoveryManager
import com.connectsdk.service.DeviceService
import com.connectsdk.service.DeviceService.PairingType
import com.connectsdk.service.capability.KeyControl
import com.connectsdk.service.capability.Launcher
import com.connectsdk.service.capability.Launcher.AppLaunchListener
import com.connectsdk.service.capability.Launcher.AppListListener
import com.connectsdk.service.capability.MediaPlayer
import com.connectsdk.service.capability.MouseControl
import com.connectsdk.service.capability.PowerControl
import com.connectsdk.service.capability.TVControl
import com.connectsdk.service.capability.VolumeControl
import com.connectsdk.service.command.ServiceCommandError
import com.connectsdk.service.sessions.LaunchSession

class Manager(a: Activity)
{
    var mTV: ConnectableDevice? = null
    var dialog: AlertDialog? = null
    var pairingAlertDialog: AlertDialog? = null
    var pairingCodeDialog: AlertDialog? = null
    var dp: DevicePicker? = null
    var a : Activity = a
    ///algo
    ///algo
    ///alkg9o

    var connectItem: MenuItem? = null

//    var mSectionsPagerAdapter: SectionsPagerAdapter? = null

//    private var mDiscoveryManager: DiscoveryManager = DiscoveryManager.getInstance()

//    var mViewPager: ViewPager? = null
//    var actionBar: androidx.appcompat.app.ActionBar? = null



    private val deviceListener: ConnectableDeviceListener = object : ConnectableDeviceListener {
        override fun onPairingRequired(
            device: ConnectableDevice,
            service: DeviceService,
            pairingType: PairingType
        ) {
            Log.d("2ndScreenAPP", "Connected to " + mTV!!.ipAddress)

            when (pairingType) {
                PairingType.FIRST_SCREEN -> {
                    Log.d("2ndScreenAPP", "First Screen")
                    pairingAlertDialog!!.show()
                }

                PairingType.PIN_CODE, PairingType.MIXED -> {
                    Log.d("2ndScreenAPP", "Pin Code")
                    pairingCodeDialog!!.show()
                }

                PairingType.NONE -> {}
                else -> {}
            }
        }

        override fun onConnectionFailed(device: ConnectableDevice, error: ServiceCommandError) {
            Log.d("2ndScreenAPP", "onConnectFailed")
            connectFailed(mTV)
        }

        override fun onDeviceReady(device: ConnectableDevice) { // 1ra funcion que se ejecuta al conectar con el tv?
            Log.d("2ndScreenAPP", "onPairingSuccess")
            if (pairingAlertDialog!!.isShowing) {
                pairingAlertDialog!!.dismiss()
            }
            if (pairingCodeDialog!!.isShowing) {
                pairingCodeDialog!!.dismiss()
            }
            registerSuccess(mTV)
        }

        override fun onDeviceDisconnected(device: ConnectableDevice) {
            Log.d("2ndScreenAPP", "Device Disconnected")
            connectEnded(mTV)
//            connectItem!!.setTitle("Connect")

//            val frag: BaseFragment = mSectionsPagerAdapter.getFragment(mViewPager!!.currentItem)
//            if (frag != null) {
//                Toast.makeText(getApplicationContext(), "Device Disconnected", Toast.LENGTH_SHORT)
//                    .show()
//                frag.disableButtons()
//            }
        }

        override fun onCapabilityUpdated(
            device: ConnectableDevice,
            added: List<String>,
            removed: List<String>
        ) {
        }
    }

    init {
        setupPicker()
    }

    val imageDevices: List<ConnectableDevice>
        get() {
            val imageDevices: MutableList<ConnectableDevice> = ArrayList()

            for (device in DiscoveryManager.getInstance().compatibleDevices.values) {
                if (device.hasCapability(MediaPlayer.Display_Image)) imageDevices.add(device)
            }

            return imageDevices
        }



    fun hConnectToggle() // funcoin que se ejecuta al pulsar el boton de desconectar?
    {

//        if (!this.isFinishing()) {
            if (mTV != null) {
                if (mTV!!.isConnected)
                    mTV!!.disconnect()
                println("hola2")
//                connectItem!!.setTitle("Connect")
                mTV!!.removeListener(deviceListener)
                mTV = null
//                for (i in 0..<mSectionsPagerAdapter.getCount()) {
//                    if (mSectionsPagerAdapter.getFragment(i) != null) {
//                        mSectionsPagerAdapter.getFragment(i).setTv(null)
//                    }
//                }
            } else  //muestra el dialogo para conectarnos si no hay un tv conectado

                {
                    println("algo2")
                    println(DiscoveryManager.getInstance().getCompatibleDevices().values.toString())
                    dialog!!.show()

                }
//        }
    }

    fun volumenUp(): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }

        mTV!!.getCapability<VolumeControl>(VolumeControl::class.java).volumeUp(null)
        return "Volumen Subido"
    }

    fun volumenDown(): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability<VolumeControl>(VolumeControl::class.java).volumeDown(null)
        return "Accion Exitosa"
    }

    fun tvOff(): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability<PowerControl>(PowerControl::class.java).powerOff(null)
        return "Accion Exitosa"
    }

    fun channelUp(): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability(TVControl::class.java).channelUp(null)
        return "Accion Exitosa"
    }

    fun channelDown(): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability(TVControl::class.java).channelDown(null)
        return "Accion Exitosa"
    }

    private var mouseConnected = false

    fun moveMouse(dx: Double, dy: Double): String {
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability<MouseControl>(MouseControl::class.java)?.move(dx, dy)
        return "Accion Exitosa"
    }

    fun clickMouse(): String {
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability<MouseControl>(MouseControl::class.java)?.click()
        return "Accion Exitosa"
    }


    fun back(): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability<KeyControl>(KeyControl::class.java).back(null)
        return "Accion Exitosa"
    }

    fun home (): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability<KeyControl>(KeyControl::class.java).home(null)
        return "Accion Exitosa"
    }

    fun okay(): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability<KeyControl>(KeyControl::class.java).ok(null)
        return "Accion Exitosa"
    }

    fun up(): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability<KeyControl>(KeyControl::class.java).up(null)
        return "Movimiento Conseguido"
    }

    fun down(): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability<KeyControl>(KeyControl::class.java).down(null)
        return "Movimiento Conseguido"
    }

    fun left(): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability<KeyControl>(KeyControl::class.java).left(null)
        return "Movimiento Conseguido"
    }

    fun right(): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }
        mTV!!.getCapability<KeyControl>(KeyControl::class.java).right(null)
        return "Movimiento Conseguido"
    }

    fun lauch( appName: String): String{
        if (mTV == null) {
            return "Error: Tv no conectada"
        }

        if(appName == "YouTube")
        {

            mTV!!.getCapability<Launcher>(Launcher::class.java).launchYouTube(
                "eRsGyueVLvQ",
                object : AppLaunchListener {
                    override fun onSuccess(session: LaunchSession) {

                    }

                    override fun onError(error: ServiceCommandError) {
                    }
                })
        }else{

            if( appName == "Netflix")
            {
                mTV!!.getCapability<Launcher>(Launcher::class.java).launchNetflix(
                    "70217913", object : AppLaunchListener {
                        override fun onSuccess(session: LaunchSession) {


                        }

                        override fun onError(error: ServiceCommandError) {
                        }
                    }
                )
            }
            else
            {



                    if( appName == "Google")
                    {
                        mTV!!.getCapability<Launcher>(Launcher::class.java).launchBrowser(
                            "https://google.com/", object : AppLaunchListener {
                                override fun onSuccess(session: LaunchSession) {

                                }

                                override fun onError(error: ServiceCommandError) {
                                }
                            }
                        )
                    }else
                    {

                        if( appName == "Amazon Prime")
                        {
                            mTV!!.getCapability<Launcher>(Launcher::class.java).launchApp(
                                "amazon", object : AppLaunchListener {
                                    override fun onError(error: ServiceCommandError) {

                                    }

                                    override fun onSuccess(`object`: LaunchSession) {

                                    }
                                }
                            )

                        }
                        else
                        {
                            if( appName == "HBO Max")
                            {
                                mTV!!.getCapability<Launcher>(Launcher::class.java).launchApp(
                                    "com.hbo.hbomax-2017", object : AppLaunchListener {
                                        override fun onError(error: ServiceCommandError) {

                                        }

                                        override fun onSuccess(`object`: LaunchSession) {

                                        }
                                    }
                                )
                            }
                            else {
                                if (appName == "Disney+") {
                                    mTV!!.getCapability<Launcher>(Launcher::class.java).launchApp(
                                        "com.disney.disneyplus-prod", object : AppLaunchListener {
                                            override fun onError(error: ServiceCommandError) {

                                            }

                                            override fun onSuccess(`object`: LaunchSession) {

                                            }
                                        }
                                    )
                                } else
                                {
                                    if( appName == "Busqueda" )
                                    {
                                        mTV!!.getCapability<Launcher>(Launcher::class.java).launchApp(
                                            "com.webos.app.voice", object : AppLaunchListener {
                                                override fun onError(error: ServiceCommandError) {

                                                }

                                                override fun onSuccess(`object`: LaunchSession) {

                                                }
                                            }
                                        )
                                    }
                                    else
                                    {
                                        if( appName == "Spotify" )
                                        {
                                            mTV!!.getCapability<Launcher>(Launcher::class.java).launchApp(
                                                "spotify-beehive", object : AppLaunchListener {
                                                    override fun onError(error: ServiceCommandError) {

                                                    }

                                                    override fun onSuccess(`object`: LaunchSession) {

                                                    }
                                                }
                                            )
                                        }
                                        else
                                        {
                                            if( appName == "Twitch" )
                                            {
                                                mTV!!.getCapability<Launcher>(Launcher::class.java).launchApp(
                                                    "tv.twitch.tv.starshot.lg", object : AppLaunchListener {
                                                        override fun onError(error: ServiceCommandError) {

                                                        }

                                                        override fun onSuccess(`object`: LaunchSession) {

                                                        }
                                                    }
                                                )
                                            }
                                            else
                                            {
                                                if( appName == "Facebook" )
                                                {
                                                    mTV!!.getCapability<Launcher>(Launcher::class.java).launchApp(
                                                        "com.facebook.app.fbwatch", object : AppLaunchListener {
                                                            override fun onError(error: ServiceCommandError) {

                                                            }

                                                            override fun onSuccess(`object`: LaunchSession) {

                                                            }
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    }

                                }
                            }

                        }



                    }




            }
        }

        return "App Lanzada"
    }


    private fun setupPicker() {
        /**  Metodo usado en el onCreate para inicializar el dialogo de seleccion de tvs */
        dp = DevicePicker(a)
        dialog = dp!!.getPickerDialog(
            "Device List"
        ) { arg0, arg1, arg2, arg3 ->
            mTV = arg0.getItemAtPosition(arg2) as ConnectableDevice
            mTV!!.addListener(deviceListener)
            mTV!!.setPairingType(null)
            mTV!!.connect()
//            connectItem!!.setTitle(mTV!!.friendlyName)
            dp!!.pickDevice(mTV)

        }

        pairingAlertDialog = AlertDialog.Builder(a)
            .setTitle("Pairing with TV")
            .setMessage("Please confirm the connection on your TV")
            .setPositiveButton("Okay", null)
            .setNegativeButton("Cancel") { dialog, which ->
                dp!!.cancelPicker()
                hConnectToggle()
            }
            .create()

        val input = EditText(a)
        input.inputType = InputType.TYPE_CLASS_TEXT

//        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        pairingCodeDialog = AlertDialog.Builder(a)
            .setTitle("Enter Pairing Code on TV")
            .setView(input)
            .setPositiveButton(
                android.R.string.ok
            ) { arg0, arg1 ->
                if (mTV != null) {
                    val value = input.text.toString().trim { it <= ' ' }
                    mTV!!.sendPairingKey(value)
//                    imm.hideSoftInputFromWindow(input.windowToken, 0)
                }
            }
            .setNegativeButton(
                android.R.string.cancel
            ) { dialog, whichButton ->
                dp!!.cancelPicker()
                hConnectToggle()
//                imm.hideSoftInputFromWindow(input.windowToken, 0)
            }
            .create()
    }



    fun registerSuccess(device: ConnectableDevice?) { // 2da funcion que se ejecuta al conectar con el tv?
        Log.d("2ndScreenAPP", "successful register")

//        val frag: BaseFragment = mSectionsPagerAdapter.getFragment(mViewPager!!.currentItem)
//        if (frag != null) frag.setTv(mTV)
    }

    fun connectFailed(device: ConnectableDevice?) {
        if (device != null) Log.d("2ndScreenAPP", "Failed to connect to " + device.ipAddress)

        if (mTV != null) {
            mTV!!.removeListener(deviceListener)
            mTV!!.disconnect()
            mTV = null
        }
    }

    fun connectEnded(device: ConnectableDevice?) {
        if (pairingAlertDialog!!.isShowing) {
            pairingAlertDialog!!.dismiss()
        }
        if (pairingCodeDialog!!.isShowing) {
            pairingCodeDialog!!.dismiss()
        }

//        if (mTV.isConnecting === false) {
//            mTV!!.removeListener(deviceListener)
//            mTV = null
//        }
    }



//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.action_connect) {
//            hConnectToggle()
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    override fun onTabUnselected(
//        tab: androidx.appcompat.app.ActionBar.Tab,
//        fragmentTransaction: FragmentTransaction
//    ) {
//    }
//
//    override fun onTabReselected(
//        tab: androidx.appcompat.app.ActionBar.Tab,
//        fragmentTransaction: FragmentTransaction
//    ) {
//    }
//
//    override fun onTabSelected(
//        tab: androidx.appcompat.app.ActionBar.Tab,
//        fragmentTransaction: FragmentTransaction
//    ) {
//        mViewPager!!.currentItem = tab.getPosition()
//        setTitle(mSectionsPagerAdapter.getTitle(tab.getPosition()))
//        val frag: BaseFragment = mSectionsPagerAdapter.getFragment(tab.getPosition())
//        if (frag != null) frag.setTv(mTV)
//    }
}