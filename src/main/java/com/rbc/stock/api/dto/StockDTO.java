package com.rbc.stock.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    private String id;
    private Integer quarter;
    private String stockSymbol;
    private String date;
    private String openPrice;
    private String highPrice;
    private String lowPrice;
    private String closePrice;
    private Integer volume;
    private Double percentChangePrice;
    private Double percentChangeVolumeOverLastWeek;
    private Integer previousWeeksVolume;
    private String nextWeeksOpenPrice;
    private String nextWeeksClosePrice;
    private Double percentChangeNextWeeksPrice;
    private Integer daysToNextDividend;
    private Double percentReturnNextDividend;

    @Override
    public String toString() {

        return "Stock{" +
                "quarter='" + this.quarter +
                ", stockSymbol='" + this.stockSymbol +
                ", date='" + this.date +
                ", openPrice='" + this.openPrice +
                ", highPrice='" + this.highPrice +
                ", lowPrice='" + this.lowPrice +
                ", closePrice='" + this.closePrice +
                ", volume='" + this.volume +
                ", percentChangePrice='" + this.percentChangePrice +
                ", percentChangeVolumeOverLastWeek='" + this.percentChangeVolumeOverLastWeek +
                ", previousWeeksVolume='" + this.previousWeeksVolume +
                ", nextWeeksOpenPrice='" + this.nextWeeksOpenPrice +
                ", nextWeeksClosePrice='" + this.nextWeeksClosePrice +
                ", percentChangeNextWeeksPrice='" + this.percentChangeNextWeeksPrice +
                ", daysToNextDividend='" + this.daysToNextDividend +
                ", percentReturnNextDividend='" + this.percentReturnNextDividend +
                '}';
    }
}
