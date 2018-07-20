package com.example.onewdivideslaptop.minihrm_application.responseAndBody;

public class remainLeaveDayResponse {
    int annualLeaveRemain,personalLeaveRemain,sickLeaveRemain,ordinationLeaveRemain;

    public remainLeaveDayResponse(int annualLeaveRemain, int personalLeaveRemain, int sickLeaveRemain, int ordinationLeaveRemain) {
        this.annualLeaveRemain = annualLeaveRemain;
        this.personalLeaveRemain = personalLeaveRemain;
        this.sickLeaveRemain = sickLeaveRemain;
        this.ordinationLeaveRemain = ordinationLeaveRemain;
    }

    public int getAnnualLeaveRemain() {
        return annualLeaveRemain;
    }

    public void setAnnualLeaveRemain(int annualLeaveRemain) {
        this.annualLeaveRemain = annualLeaveRemain;
    }

    public int getPersonalLeaveRemain() {
        return personalLeaveRemain;
    }

    public void setPersonalLeaveRemain(int personalLeaveRemain) {
        this.personalLeaveRemain = personalLeaveRemain;
    }

    public int getSickLeaveRemain() {
        return sickLeaveRemain;
    }

    public void setSickLeaveRemain(int sickLeaveRemain) {
        this.sickLeaveRemain = sickLeaveRemain;
    }

    public int getOrdinationLeaveRemain() {
        return ordinationLeaveRemain;
    }

    public void setOrdinationLeaveRemain(int ordinationLeaveRemain) {
        this.ordinationLeaveRemain = ordinationLeaveRemain;
    }
}
