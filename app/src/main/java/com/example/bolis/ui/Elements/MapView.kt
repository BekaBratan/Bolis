package com.example.bolis.presentation.profile

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

@SuppressLint("MissingPermission")
@Composable
fun MapWithUserLocation() {
    val context = LocalContext.current
    var location by remember { mutableStateOf<Location?>(null) }
    var hasLocationPermission by remember { mutableStateOf(false) }
    val fusedLocationClient: FusedLocationProviderClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val mapViewState = rememberMapViewWithLifecycle()
    val mapKitInitialized = remember { mutableStateOf(false) }

    // 1. Check and request permissions:
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasLocationPermission = isGranted
        }
    )
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            hasLocationPermission = true
        } else {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    // Initialize MapKit after permission check
    LaunchedEffect(hasLocationPermission) {
        if(hasLocationPermission) {
            MapKitFactory.initialize(context)
            mapKitInitialized.value = true

            // 2. Get Location:
            fusedLocationClient.lastLocation.addOnSuccessListener { lastKnownLocation ->
                if (lastKnownLocation != null) {
                    location = lastKnownLocation
                    Log.d("Location", "Last known location: ${lastKnownLocation.latitude}, ${lastKnownLocation.longitude}")
                } else {
                    Log.d("Location", "Last known location was null, requesting a single update.")
                    fusedLocationClient.getCurrentLocation(100, null).addOnSuccessListener { currentlocation ->
                        if(currentlocation != null) {
                            location = currentlocation
                            Log.d("Location", "Current location update: ${currentlocation.latitude}, ${currentlocation.longitude}")
                        } else {
                            Log.e("Location", "Unable to retrieve current location.")
                        }
                    }.addOnFailureListener { e ->
                        Log.e("Location", "Error getting current location: ${e.message}", e)
                    }
                }
            }.addOnFailureListener { e ->
                Log.e("Location", "Error getting last known location: ${e.message}", e)
            }
        } else {
            Log.e("Location", "Location permission not granted.")
        }
    }

    // 3. Update Map (inside AndroidView):
    if(mapKitInitialized.value) {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                factory = { mapViewState },
                modifier = Modifier.fillMaxSize(),
                update = { mapView ->
                    val targetPoint = if (location != null) {
                        Point(location!!.latitude, location!!.longitude)
                    } else {
                        // Default to Moscow Red Square
                        Point(55.751225, 37.62954)
                    }

                    mapView.map.move(
                        CameraPosition(
                            targetPoint,
                            17.0f,
                            if (location != null) 0.0f else 150.0f, // Default azimuth if no user location
                            if (location != null) 0.0f else 30.0f    // Default tilt if no user location
                        )
                    )

                    mapView.map.mapObjects.clear()
                    mapView.map.mapObjects.addPlacemark(targetPoint)
                }
            )

        }
    }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    // Add lifecycle observers to handle the MapView's lifecycle
    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    DisposableEffect(lifecycleObserver) {
        onDispose {
            mapView.onStop()
        }
    }

    return mapView
}
@Composable
fun rememberMapLifecycleObserver(mapView: MapView): MapLifecycleObserver {
    val context = LocalContext.current
    return remember {
        MapLifecycleObserver(context, mapView)
    }
}
class MapLifecycleObserver(private val context: Context, private val mapView: MapView):
    DefaultLifecycleObserver {
    init {
        MapKitFactory.initialize(context)
    }
    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }
}