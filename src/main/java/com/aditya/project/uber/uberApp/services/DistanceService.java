package com.aditya.project.uber.uberApp.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {
    double calculateDistance(Point p1, Point p2);
}
