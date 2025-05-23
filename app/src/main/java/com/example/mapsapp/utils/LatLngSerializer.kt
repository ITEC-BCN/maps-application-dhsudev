package com.example.mapsapp.utils

import com.google.android.gms.maps.model.LatLng
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


// Custom serializer for LatLng,
// since we need to send it as a string to Supabase
// and as parameter to CreateMarkerScreen
object LatLngSerializer : KSerializer<LatLng> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("LatLng", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LatLng) {
        val str = "${value.latitude},${value.longitude}"
        encoder.encodeString(str)
    }

    override fun deserialize(decoder: Decoder): LatLng {
        val parts = decoder.decodeString().split(",")
        val lat = parts[0].toDouble()
        val lng = parts[1].toDouble()
        return LatLng(lat, lng)
    }
}
