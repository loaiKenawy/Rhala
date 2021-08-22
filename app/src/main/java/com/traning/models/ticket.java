package com.traning.models;

import java.util.ArrayList;

public class ticket {


    private String fromAirportCode, toAirportCode, flightCompany , price , departureDate, returnDate ,OutboundDepartureTime, DepartureLandingTime,OutboundReturnTime,ReturnLandingTime;

    public ticket() {

    }

    public ticket(String fromAirportCode, String toAirportCode, String flightCompany, String price, String departureDate, String aReturnDate, String outboundDepartureTime, String departureLandingTime, String outboundReturnTime, String returnLandingTime) {
        this.fromAirportCode = fromAirportCode;
        this.toAirportCode = toAirportCode;
        this.flightCompany = flightCompany;
        this.price = price;
        this.departureDate = departureDate;
        this.returnDate = aReturnDate;
        this.OutboundDepartureTime = outboundDepartureTime;
        this.DepartureLandingTime = departureLandingTime;
        this.OutboundReturnTime = outboundReturnTime;
        this.ReturnLandingTime = returnLandingTime;
    }

    public ticket(String fromAirportCode, String toAirportCode, String flightCompany, String price, String outboundDepartureTime, String departureLandingTime, String outboundReturnTime, String returnLandingTime) {
        this.fromAirportCode = fromAirportCode;
        this.toAirportCode = toAirportCode;
        this.flightCompany = flightCompany;
        this.price = price;
        this.OutboundDepartureTime = outboundDepartureTime;
        this.DepartureLandingTime = departureLandingTime;
        this.OutboundReturnTime = outboundReturnTime;
        this.ReturnLandingTime = returnLandingTime;
    }

    public String getToAirportCode() {
        return toAirportCode;
    }

    public String getFlightCompany() {
        return flightCompany;
    }

    public String getPrice() {
        return price;
    }

    private ArrayList<ticket> list = new ArrayList<>();

    public void setList(ArrayList<ticket> list) {
        this.list = list;
    }

    public ArrayList<ticket> getList() {
        return list;
    }

    public String getFromAirportCode() {
        return fromAirportCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getOutboundDepartureTime() {
        return OutboundDepartureTime;
    }

    public String getDepartureLandingTime() {
        return DepartureLandingTime;
    }

    public String getOutboundReturnTime() {
        return OutboundReturnTime;
    }

    public String getReturnLandingTime() {
        return ReturnLandingTime;
    }
}
