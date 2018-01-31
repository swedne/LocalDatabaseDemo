package com.swedne.locdb.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2018/1/31.
 */

@DatabaseTable(tableName = "gamelist")
public class Game {

    public Game(String name, String remak) {
        this.nav = name;
        this.remark = remak;
    }

    public Game() {
    }

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "nav")
    private String nav;
    @DatabaseField(columnName = "nav_parents")
    private String nav_parents;
    @DatabaseField(columnName = "remark")
    private String remark;
    @DatabaseField(columnName = "h5img")
    private String imgs;
    @DatabaseField(columnName = "status")
    private String status;
    @DatabaseField(columnName = "time")
    private int time;
    @DatabaseField(columnName = "click")
    private String click;
    @DatabaseField(columnName = "type")
    private String type;
    @DatabaseField(columnName = "delete")
    private String delete;
    @DatabaseField(columnName = "sort")
    private String sort;
    @DatabaseField(columnName = "pcimg")
    private String pcimg;
    @DatabaseField(columnName = "game_platform")
    private String game_platform;
    @DatabaseField(columnName = "equipment")
    private String equipment;
    @DatabaseField(columnName = "tag")
    private String tag;
    @DatabaseField(columnName = "gameid")
    private String gameid;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public String getImgs() {

        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNav() {
        return nav;
    }

    public void setNav(String nav) {
        this.nav = nav;
    }

    public String getNav_parents() {
        return nav_parents;
    }

    public void setNav_parents(String nav_parents) {
        this.nav_parents = nav_parents;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getH5img() {
        return imgs;
    }

    public void setH5img(String h5img) {
        this.imgs = imgs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getClick() {
        return click;
    }

    public void setClick(String click) {
        this.click = click;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPcimg() {
        return pcimg;
    }

    public void setPcimg(String pcimg) {
        this.pcimg = pcimg;
    }

    public String getGame_platform() {
        return game_platform;
    }

    public void setGame_platform(String game_platform) {
        this.game_platform = game_platform;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
