package com.httpurlcon.androidnetworking107kot.mdel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class contactsResponse(
    @SerializedName("contacts")
    @Expose
    var contacts:List<contacts>
)