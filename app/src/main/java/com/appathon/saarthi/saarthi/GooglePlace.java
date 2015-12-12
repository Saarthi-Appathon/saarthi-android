/**
 * Created by shoyeb on 12-12-2015.
 */

package com.appathon.saarthi.saarthi;

public class GooglePlace
{
    private String UUID;
    private String name;
    private String latitude;
    private String longitude;
    private String placeId;
    private String icon;

    public GooglePlace()
    {
        this.UUID = "";
        this.name = "";
        this.latitude = "";
        this.longitude = "";
        this.placeId = "";
        this.icon = "";
    }

    public void setUUID(String uuid)
    {
        this.UUID = uuid;
    }

    public String getUUID()
    {
        return this.UUID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setLatitude(String lat)
    {
        this.latitude = lat;
    }

    public String getLatitude()
    {
        return this.latitude;
    }

    public void setLongitude(String longt)
    {
        this.longitude = longt;
    }

    public String getLongitude()
    {
        return this.longitude;
    }

    public void setPlaceId(String id)
    {
        this.placeId = id;
    }

    public String getPlaceId()
    {
        return this.placeId;
    }

    public void setIcon(String icn)
    {
        this.icon = icn;
    }

    public String getIcon()
    {
        return this.icon;
    }
}
