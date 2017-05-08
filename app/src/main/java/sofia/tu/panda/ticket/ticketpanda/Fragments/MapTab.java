package sofia.tu.panda.ticket.ticketpanda.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import sofia.tu.panda.ticket.ticketpanda.R;

public class MapTab extends Fragment {

    private MapView mapView;
    private LatLng location = new LatLng(42.657059, 23.355110);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_maps, container, false);

        mapView = (MapView) root.findViewById(R.id.mapView);

        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.addMarker(new MarkerOptions()
                        .position(location)
                        .anchor(0.0f, 1.0f)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                googleMap.getUiSettings().setMyLocationButtonEnabled(false);

                googleMap.getUiSettings().setZoomControlsEnabled(true);
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(location);
                LatLngBounds bounds = builder.build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 0);
                googleMap.moveCamera(cameraUpdate);

            }
        });

        MapsInitializer.initialize(getContext());

        mapView.onResume();

        return root;
    }
}
