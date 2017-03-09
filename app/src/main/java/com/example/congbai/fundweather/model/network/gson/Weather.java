package com.example.congbai.fundweather.model.network.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fundmarkhua on 2017/3/7
 * Email:57525101@qq.com
 * 天气信息类  gson格式适配器   利用GsonFormat快捷生成
 */

public class Weather {

    private List<HeWeatherBean> HeWeather;

    public List<HeWeatherBean> getHeWeather() {
        return HeWeather;
    }

    public void setHeWeather(List<HeWeatherBean> HeWeather) {
        this.HeWeather = HeWeather;
    }

    public static class HeWeatherBean {
        /**
         * aqi : {"city":{"aqi":"25","pm10":"25","pm25":"8","qlty":"优"}}
         * basic : {"city":"海淀","cnty":"中国","id":"CN101010200","lat":"39.590000","lon":"116.170000","update":{"loc":"2017-03-07 08:51","utc":"2017-03-07 00:51"}}
         * daily_forecast : [{"astro":{"mr":"12:48","ms":"02:35","sr":"06:38","ss":"18:13"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-03-07","hum":"22","pcpn":"0.0","pop":"0","pres":"1025","tmp":{"max":"10","min":"1"},"uv":"3","vis":"10","wind":{"deg":"202","dir":"北风","sc":"3-4","spd":"15"}},{"astro":{"mr":"13:48","ms":"03:31","sr":"06:37","ss":"18:14"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-03-08","hum":"24","pcpn":"0.0","pop":"0","pres":"1021","tmp":{"max":"14","min":"4"},"uv":"3","vis":"10","wind":{"deg":"318","dir":"北风","sc":"3-4","spd":"16"}},{"astro":{"mr":"14:50","ms":"04:20","sr":"06:35","ss":"18:15"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2017-03-09","hum":"19","pcpn":"0.0","pop":"0","pres":"1018","tmp":{"max":"16","min":"4"},"uv":"4","vis":"10","wind":{"deg":"301","dir":"南风","sc":"微风","spd":"8"}}]* hourly_forecast : [{"cond":{"code":"100","txt":"晴"},"date":"2017-03-07 10:00","hum":"19","pop":"0","pres":"1027","tmp":"3","wind":{"deg":"226","dir":"西南风","sc":"微风","spd":"16"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-03-07 13:00","hum":"14","pop":"0","pres":"1025","tmp":"7","wind":{"deg":"328","dir":"西北风","sc":"3-4","spd":"23"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-03-07 16:00","hum":"14","pop":"0","pres":"1023","tmp":"8","wind":{"deg":"325","dir":"西北风","sc":"3-4","spd":"27"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-03-07 19:00","hum":"21","pop":"0","pres":"1023","tmp":"5","wind":{"deg":"323","dir":"西北风","sc":"3-4","spd":"21"}},{"cond":{"code":"100","txt":"晴"},"date":"2017-03-07 22:00","hum":"25","pop":"0","pres":"1023","tmp":"2","wind":{"deg":"305","dir":"西北风","sc":"微风","spd":"14"}}]
         * now : {"cond":{"code":"100","txt":"晴"},"fl":"-6","hum":"25","pcpn":"0","pres":"1026","tmp":"0","vis":"10","wind":{"deg":"20","dir":"北风","sc":"4-5","spd":"23"}}
         * status : ok
         * suggestion : {"air":{"brf":"很差","txt":"气象条件不利于空气污染物稀释、扩散和清除，请尽量避免在室外长时间活动。"},"comf":{"brf":"较舒适","txt":"白天虽然天气晴好，但早晚会感觉偏凉，午后舒适、宜人。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"flu":{"brf":"较易发","txt":"天凉，昼夜温差较大，较易发生感冒，请适当增减衣服，体质较弱的朋友请注意适当防护。"},"sport":{"brf":"较不宜","txt":"天气较好，但考虑风力较大，天气寒冷，推荐您进行室内运动，若在户外运动须注意保暖。"},"trav":{"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。"},"uv":{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}}
         */

        private AqiBean aqi;
        private BasicBean basic;
        private NowBean now;
        private String status;
        private SuggestionBean suggestion;
        private List<DailyForecastBean> daily_forecast;

        public AqiBean getAqi() {
            return aqi;
        }

        public void setAqi(AqiBean aqi) {
            this.aqi = aqi;
        }

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public SuggestionBean getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionBean suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public static class AqiBean {
            /**
             * city : {"aqi":"25","pm10":"25","pm25":"8","qlty":"优"}
             */

            private CityBean city;

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }

            public static class CityBean {
                /**
                 * aqi : 25
                 * pm10 : 25
                 * pm25 : 8
                 * qlty : 优
                 */

                private String aqi;
                private String pm10;
                private String pm25;
                private String qlty;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }
            }
        }

        public static class BasicBean {
            /**
             * city : 海淀
             * cnty : 中国
             * id : CN101010200
             * lat : 39.590000
             * lon : 116.170000
             * update : {"loc":"2017-03-07 08:51","utc":"2017-03-07 00:51"}
             */

            @SerializedName("city")
            private String cityName;
            @SerializedName("id")
            private String weatherId;
            private UpdateBean update;

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getWeatherId() {
                return weatherId;
            }

            public void setWeatherId(String weatherId) {
                this.weatherId = weatherId;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                /**
                 * loc : 2017-03-07 08:51
                 * utc : 2017-03-07 00:51
                 */

                @SerializedName("loc")
                private String updateTime;
                private String utc;

                public String getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(String updateTime) {
                    this.updateTime = updateTime;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class NowBean {
            /**
             * cond : {"code":"100","txt":"晴"}
             * fl : -6
             * hum : 25
             * pcpn : 0
             * pres : 1026
             * tmp : 0
             * vis : 10
             * wind : {"deg":"20","dir":"北风","sc":"4-5","spd":"23"}
             */

            private CondBean cond;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            private WindBean wind;

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class CondBean {
                /**
                 * code : 100
                 * txt : 晴
                 */

                private String code;
                @SerializedName("txt")
                private String weatherInfo;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getWeatherInfo() {
                    return weatherInfo;
                }

                public void setWeatherInfo(String weatherInfo) {
                    this.weatherInfo = weatherInfo;
                }
            }

            public static class WindBean {
                /**
                 * deg : 20
                 * dir : 北风
                 * sc : 4-5
                 * spd : 23
                 */

                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class SuggestionBean {
            /**
             * air : {"brf":"很差","txt":"气象条件不利于空气污染物稀释、扩散和清除，请尽量避免在室外长时间活动。"}
             * comf : {"brf":"较舒适","txt":"白天虽然天气晴好，但早晚会感觉偏凉，午后舒适、宜人。"}
             * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
             * drsg : {"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
             * flu : {"brf":"较易发","txt":"天凉，昼夜温差较大，较易发生感冒，请适当增减衣服，体质较弱的朋友请注意适当防护。"}
             * sport : {"brf":"较不宜","txt":"天气较好，但考虑风力较大，天气寒冷，推荐您进行室内运动，若在户外运动须注意保暖。"}
             * trav : {"brf":"适宜","txt":"天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。"}
             * uv : {"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}
             */

            private AirBean air;
            @SerializedName("comf")
            private ComfBean comfort;
            @SerializedName("cw")
            private CwBean carWash;
            private DrsgBean drsg;
            private FluBean flu;
            private SportBean sport;
            private TravBean trav;
            private UvBean uv;

            public AirBean getAir() {
                return air;
            }

            public void setAir(AirBean air) {
                this.air = air;
            }

            public ComfBean getComfort() {
                return comfort;
            }

            public void setComfort(ComfBean comfort) {
                this.comfort = comfort;
            }

            public CwBean getCarWash() {
                return carWash;
            }

            public void setCarWash(CwBean carWash) {
                this.carWash = carWash;
            }

            public DrsgBean getDrsg() {
                return drsg;
            }

            public void setDrsg(DrsgBean drsg) {
                this.drsg = drsg;
            }

            public FluBean getFlu() {
                return flu;
            }

            public void setFlu(FluBean flu) {
                this.flu = flu;
            }

            public SportBean getSport() {
                return sport;
            }

            public void setSport(SportBean sport) {
                this.sport = sport;
            }

            public TravBean getTrav() {
                return trav;
            }

            public void setTrav(TravBean trav) {
                this.trav = trav;
            }

            public UvBean getUv() {
                return uv;
            }

            public void setUv(UvBean uv) {
                this.uv = uv;
            }

            public static class AirBean {
                /**
                 * brf : 很差
                 * txt : 气象条件不利于空气污染物稀释、扩散和清除，请尽量避免在室外长时间活动。
                 */

                private String brf;
                @SerializedName("txt")
                private String info;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }
            }

            public static class ComfBean {
                /**
                 * brf : 较舒适
                 * txt : 白天虽然天气晴好，但早晚会感觉偏凉，午后舒适、宜人。
                 */

                private String brf;
                @SerializedName("txt")
                private String info;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }
            }

            public static class CwBean {
                /**
                 * brf : 较适宜
                 * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
                 */

                private String brf;
                @SerializedName("txt")
                private String info;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }
            }

            public static class DrsgBean {
                /**
                 * brf : 较冷
                 * txt : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
                 */

                private String brf;
                @SerializedName("txt")
                private String info;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }
            }

            public static class FluBean {
                /**
                 * brf : 较易发
                 * txt : 天凉，昼夜温差较大，较易发生感冒，请适当增减衣服，体质较弱的朋友请注意适当防护。
                 */

                private String brf;
                @SerializedName("txt")
                private String info;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }
            }

            public static class SportBean {
                /**
                 * brf : 较不宜
                 * txt : 天气较好，但考虑风力较大，天气寒冷，推荐您进行室内运动，若在户外运动须注意保暖。
                 */

                private String brf;
                @SerializedName("txt")
                private String info;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }
            }

            public static class TravBean {
                /**
                 * brf : 适宜
                 * txt : 天气较好，风稍大，但温度适宜，是个好天气哦。适宜旅游，您可以尽情地享受大自然的无限风光。
                 */

                private String brf;
                @SerializedName("txt")
                private String info;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }
            }

            public static class UvBean {
                /**
                 * brf : 中等
                 * txt : 属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。
                 */

                private String brf;
                @SerializedName("txt")
                private String info;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getInfo() {
                    return info;
                }

                public void setInfo(String info) {
                    this.info = info;
                }
            }
        }

        public static class DailyForecastBean {
            /**
             * astro : {"mr":"12:48","ms":"02:35","sr":"06:38","ss":"18:13"}
             * cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
             * date : 2017-03-07
             * hum : 22
             * pcpn : 0.0
             * pop : 0
             * pres : 1025
             * tmp : {"max":"10","min":"1"}
             * uv : 3
             * vis : 10
             * wind : {"deg":"202","dir":"北风","sc":"3-4","spd":"15"}
             */

            private AstroBean astro;
            private CondBeanX cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            private TmpBean tmp;
            private String uv;
            private String vis;
            private WindBeanX wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public CondBeanX getCond() {
                return cond;
            }

            public void setCond(CondBeanX cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getUv() {
                return uv;
            }

            public void setUv(String uv) {
                this.uv = uv;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBeanX getWind() {
                return wind;
            }

            public void setWind(WindBeanX wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                /**
                 * mr : 12:48
                 * ms : 02:35
                 * sr : 06:38
                 * ss : 18:13
                 */

                private String mr;
                private String ms;
                private String sr;
                private String ss;

                public String getMr() {
                    return mr;
                }

                public void setMr(String mr) {
                    this.mr = mr;
                }

                public String getMs() {
                    return ms;
                }

                public void setMs(String ms) {
                    this.ms = ms;
                }

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondBeanX {
                /**
                 * code_d : 100
                 * code_n : 100
                 * txt_d : 晴
                 * txt_n : 晴
                 */

                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpBean {
                /**
                 * max : 10
                 * min : 1
                 */

                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBeanX {
                /**
                 * deg : 202
                 * dir : 北风
                 * sc : 3-4
                 * spd : 15
                 */

                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
