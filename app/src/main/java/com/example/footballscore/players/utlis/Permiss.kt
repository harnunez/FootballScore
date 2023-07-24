class Permiss {
    /*private val context: Context

    constructor(parentActivity: AppCompatActivity) {
        context = parentActivity
        initFromActivity(parentActivity)
    }

    constructor(parentFragment: Fragment) {
        context = parentFragment.requireContext()
        initFromFragment(parentFragment)
    }

    enum class RequestType {
        PhotoLibrary,
        Camera,
        Video,
        File,
        Scan,
        Contacts,
        PushNotifications
    }

    lateinit var requestType: RequestType
    var onPermissionGranted: (() -> Unit)? = null

    private lateinit var photoLibraryRequestLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var cameraRequestLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var videoRequestLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var fileRequestLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var scanRequestLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var contactRequestLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var pushNotificationRequestLauncher: ActivityResultLauncher<Array<String>>

    private lateinit var permissions: Array<String>

    private var permissionDialog: PermissionDialog? = null

    @SuppressLint("InlinedApi")
    private fun getPermissionsForAndroidTiramisu(requestType: RequestType): Array<String> {
        return when (requestType) {
            RequestType.PhotoLibrary -> {
                arrayOf(
                    Manifest.permission.READ_MEDIA_IMAGES
                )
            }
            RequestType.Camera -> {
                arrayOf(
                    Manifest.permission.READ_MEDIA_VIDEO, Manifest.permission.CAMERA
                )
            }
            RequestType.Video -> {
                arrayOf(
                    Manifest.permission.READ_MEDIA_VIDEO,
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO
                )
            }

            RequestType.Scan -> {
                arrayOf(
                    Manifest.permission.READ_MEDIA_VIDEO, Manifest.permission.CAMERA
                )
            }
            RequestType.Contacts -> {
                arrayOf(
                    Manifest.permission.READ_CONTACTS
                )
            }
            RequestType.PushNotifications -> {
                arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }

            else -> arrayOf()
        }
    }

    @SuppressLint("InlinedApi")
    private fun getPermissions(requestType: RequestType): Array<String> {
        return when (requestType) {
            RequestType.PhotoLibrary -> {
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
            RequestType.Camera -> {
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
                )
            }
            RequestType.Video -> {
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO
                )
            }
            RequestType.File -> {
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
            RequestType.Scan -> {
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA
                )
            }
            RequestType.Contacts -> {
                arrayOf(
                    Manifest.permission.READ_CONTACTS
                )
            }
            RequestType.PushNotifications -> {
                arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            }
        }
    }


    fun checkAndAskForPermissions() {
        permissions = if (isAndroidTiramisu()) getPermissionsForAndroidTiramisu(requestType)
        else getPermissions(requestType)

        val permissionsAllowed = checkPermissions(permissions)

        if (permissionsAllowed) {
            onPermissionGranted?.invoke()
        } else {
            when (requestType) {
                RequestType.PhotoLibrary -> {
                    photoLibraryRequestLauncher.launch(permissions)
                }
                RequestType.Camera -> {
                    cameraRequestLauncher.launch(permissions)
                }
                RequestType.Video -> {
                    videoRequestLauncher.launch(permissions)
                }
                RequestType.File -> {
                    fileRequestLauncher.launch(permissions)
                }
                RequestType.Scan -> {
                    scanRequestLauncher.launch(permissions)
                }
                RequestType.Contacts -> {
                    contactRequestLauncher.launch(permissions)
                }
                RequestType.PushNotifications -> {
                    if (isAndroidTiramisu()) {
                        val model = TwoButtonModel(
                            R.drawable.ic_logo_circle,
                            title = LocalizableStrings.getMessage("NotificationsAlert.Title"),
                            description = LocalizableStrings.getMessage("NotificationsAlert.Body"),
                            positiveButtonText = LocalizableStrings.getMessage("NotificationsAlert.MainButton"),
                            negativeButtonText = LocalizableStrings.getMessage("NotificationsAlert.Cancel"),
                            defaultDismiss = true
                        ) {
                            pushNotificationRequestLauncher.launch(permissions)
                        }

                        TwoButtonsDialog(
                            context,
                            model
                        ).show()
                    }
                }
            }
        }
    }

    private fun checkPermissions(permission: Array<String>): Boolean  {
        var isPermissionApproved = true
        for (value in permission) {
            val isThisPermissionApproved = isPermissionsAllowed(value)
            if (!isThisPermissionApproved) {
                isPermissionApproved = false
            }
        }
        return isPermissionApproved
    }

    private fun isPermissionsAllowed(permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    private fun showPermissionDeniedDialog() {
        if (permissionDialog == null) {
            permissionDialog = PermissionDialog(context) {
                onOpenSettings()
            }
        }
        permissionDialog?.show()
    }

    private fun onOpenSettings() {
        permissionDialog?.dismiss()
        val uri = Uri.fromParts("package", context.packageName, null)
        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = uri
        }
        context.startActivity(intent)
    }

    /* Permission Request Launcher */
    private fun initFromActivity(parentActivity: AppCompatActivity) {

        photoLibraryRequestLauncher =
            parentActivity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
        cameraRequestLauncher =
            parentActivity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
        videoRequestLauncher =
            parentActivity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
        fileRequestLauncher =
            parentActivity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
        scanRequestLauncher =
            parentActivity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
        contactRequestLauncher =
            parentActivity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
        pushNotificationRequestLauncher =
            parentActivity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
    }

    private fun initFromFragment(parentFragment: Fragment) {
        photoLibraryRequestLauncher =
            parentFragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
        cameraRequestLauncher =
            parentFragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
        videoRequestLauncher =
            parentFragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
        fileRequestLauncher =
            parentFragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
        scanRequestLauncher =
            parentFragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
        contactRequestLauncher =
            parentFragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
        pushNotificationRequestLauncher =
            parentFragment.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                checkGranted(permissions)
            }
    }

    private fun isAndroidTiramisu() =  Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

    private fun checkGranted(permissions: Map<String, Boolean>) {
        val isGranted = permissions.entries.all {
            it.value
        }
        if (requestType == RequestType.File  && isAndroidTiramisu()) {
            onPermissionGranted?.invoke()
        } else {
            if (isGranted) {
                onPermissionGranted?.invoke()
            } else {
                showPermissionDeniedDialog()
            }
        }
    }*/
}