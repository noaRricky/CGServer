package com.ricky.model;

import com.org.json.JSONObject;

public class GMessage {

    private String message;
    private GType gtype;

    public static enum GType {
        START, END, TURN, SURRENDER
    }

    public GMessage(JSONObject jsonObj) {
        this.message = jsonObj.getString("message");
        this.gtype = (GType) jsonObj.get("type");
    }

    public GMessage(String message, GType type) {
        this.message = message;
        this.gtype = type;
    }

    public void setGType(GType gtype) {
        this.gtype = gtype;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GType getGType() {
        return gtype;
    }

    public String getMessage() {
        return message;
    }

    public JSONObject toJSON() {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("message", this.message);
        jsonObj.put("type", this.gtype);

        return jsonObj;
    }

    @Override
    public String toString() {
        return "GMessage{" +
                "message='" + message + '\'' +
                ", gtype=" + gtype +
                '}';
    }
}
