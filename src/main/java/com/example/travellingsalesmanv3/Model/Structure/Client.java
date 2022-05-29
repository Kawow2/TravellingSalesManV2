package com.example.travellingsalesmanv3.Model.Structure;


public class Client {
    private String label;
    private Point point;
    private int value;


    public Client(Point point, int value, String label) {
        this.point = point;
        this.value = value;
        this.label = label;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point p) {
        this.point = p;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
