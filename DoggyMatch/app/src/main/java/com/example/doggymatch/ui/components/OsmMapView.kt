package com.example.doggymatch.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun OsmMapView(
    modifier: Modifier = Modifier,
    latitude: Double,
    longitude: Double,
    zoomLevel: Double = 15.0
) {
    AndroidView(
        factory = { context ->
            val mapView = MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
            }

            val mapController = mapView.controller
            mapController.setZoom(zoomLevel)
            val startPoint = GeoPoint(latitude, longitude)
            mapController.setCenter(startPoint)

            // Add marker
            val marker = Marker(mapView)
            marker.position = startPoint
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            marker.title = "Location"
            mapView.overlays.add(marker)

            mapView
        },
        modifier = modifier
    )
}
