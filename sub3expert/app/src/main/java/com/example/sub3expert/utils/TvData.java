package com.example.sub3expert.utils;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvData implements Parcelable {
    private int id;
    private String title;
    private String imgtv;
    private String overview;
    private String releaseDate;
    private Double voteAverage;
    private Double popularity;
    private String banner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getImgtv() {
        return imgtv;
    }

    public void setImgtv(String imgtv) {
        this.imgtv = imgtv;
    }
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    private String release_date;
    private String original_language;
    private String vote_count;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(imgtv);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        if (voteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(voteAverage);
        }
        if (popularity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(popularity);
        }
        dest.writeString(banner);
        dest.writeString(release_date);
        dest.writeString(original_language);
        dest.writeString(vote_count);
    }
    protected TvData(Parcel in) {
        id = in.readInt();
        title = in.readString();
        imgtv = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readDouble();
        }
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        banner = in.readString();
        release_date = in.readString();
        original_language = in.readString();
        vote_count = in.readString();
    }

    public static final Creator<TvData> CREATOR = new Creator<TvData>() {
        @Override
        public TvData createFromParcel(Parcel in) {
            return new TvData(in);
        }

        @Override
        public TvData[] newArray(int size) {
            return new TvData[size];
        }
    };
    public TvData (JSONObject object){
        try {
            String vote_count = object.getString("vote_count");
            Double vote_average = object.getDouble("vote_average");
            String title = object.getString("name");
            Double popularity = object.getDouble("popularity");
            String original_language = object.getString("original_language");
            String overview = object.getString("overview");
            String release_date = object.getString("first_air_date");
            String poster_path = object.getString("poster_path");
            String backdrop_path = object.getString("backdrop_path");

            this.vote_count = vote_count;
            this.voteAverage = vote_average;
            this.title = title;
            this.popularity = popularity;
            this.original_language = original_language;
            this.overview = overview;
            this.release_date = release_date;
            this.imgtv = poster_path;
            this.banner = backdrop_path;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
