package com.lucaslz.weather.domain;

import android.arch.persistence.room.Update;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/24.
 */
public class Weather {

    public String status;

    public AQI aqi;

    public Basic basic;

    public Now now;

    public Suggestion suggestion;

    public List<Forecast> forecasts;
}

class Basic {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    class Update {
        @SerializedName("loc")
        public String updateTime;
    }
}

class AQI {
    public AQICity city;

    class AQICity {
        public String aqi;
        public String pm25;
    }
}

class Now {
    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {
        @SerializedName("txt")
        public String info;

    }
}

class Suggestion {

    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public CarWash carWash;

    public Sport sport;

    public class Comfort {

        @SerializedName("txt")
        public String info;

    }

    public class CarWash {

        @SerializedName("txt")
        public String info;

    }

    public class Sport {

        @SerializedName("txt")
        public String info;

    }

}

class Forecast {

    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature {

        public String max;

        public String min;

    }

    public class More {

        @SerializedName("txt_d")
        public String info;

    }

}