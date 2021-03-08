package com.rbc.stock.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @ApiModelProperty(notes = "ID of the Stock Record", name = "id", required = true)
    @Id
    private String id;

    @ApiModelProperty(notes = "The yearly quarter (1 = Jan-Mar; 2 = Apr=Jun)", name = "quarter", required = true)
    private Integer quarter;

    @ApiModelProperty(notes = "The stock symbol", name = "stockSymbol", required = true)
    private String stockSymbol;

    @ApiModelProperty(notes = "The last business day of the work (this is typically a Friday)", name = "date", required = true)
    private LocalDate date;

    @ApiModelProperty(notes = "The price of the stock at the beginning of the week", name = "openPrice", required = true)
    private BigDecimal openPrice;

    @ApiModelProperty(notes = "The highest price of the stock during the week", name = "highPrice", required = true)
    private BigDecimal highPrice;

    @ApiModelProperty(notes = "The lowest price of the stock during the week", name = "lowPrice", required = true)
    private BigDecimal lowPrice;

    @ApiModelProperty(notes = "The price of the stock at the end of the week", name = "closePrice", required = true)
    private BigDecimal closePrice;

    @ApiModelProperty(notes = "The number of shares of stock that traded hands in the week", name = "volume", required = true)
    private Integer volume;

    @ApiModelProperty(notes = "The percentage change in price throughout the week", name = "percentChangePrice", required = true)
    private Double percentChangePrice;

    @ApiModelProperty(notes = "The percentage change in the number of shares of stock that traded hands for this week compared to the previous week", name = "percentChangeVolumeOverLastWeek", required = true)
    private Double percentChangeVolumeOverLastWeek;

    @ApiModelProperty(notes = "The number of shares of stock that traded hands in the previous week", name = "previousWeeksVolume", required = true)
    private Integer previousWeeksVolume;

    @ApiModelProperty(notes = "The opening price of the stock in the following week", name = "nextWeeksOpenPrice", required = true)
    private BigDecimal nextWeeksOpenPrice;

    @ApiModelProperty(notes = "The closing price of the stock in the following week", name = "nextWeeksClosePrice", required = true)
    private BigDecimal nextWeeksClosePrice;

    @ApiModelProperty(notes = "The percentage change in price of the stock in the following week", name = "percentChangeNextWeeksPrice", required = true)
    private Double percentChangeNextWeeksPrice;

    @ApiModelProperty(notes = "The number of days until the next dividend", name = "daysToNextDividend", required = true)
    private Integer daysToNextDividend;

    @ApiModelProperty(notes = "The percentage of return on the next dividend", name = "percentReturnNextDividend", required = true)
    private Double percentReturnNextDividend;

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Stock))
            return false;
        Stock stock = (Stock) o;
        return Objects.equals(this.quarter, stock.quarter)
                && Objects.equals(this.stockSymbol, stock.stockSymbol)
                && Objects.equals(this.date, stock.date)
                && Objects.equals(this.openPrice, stock.openPrice)
                && Objects.equals(this.highPrice, stock.highPrice)
                && Objects.equals(this.lowPrice, stock.lowPrice)
                && Objects.equals(this.closePrice, stock.closePrice)
                && Objects.equals(this.volume, stock.volume)
                && Objects.equals(this.percentChangePrice, stock.percentChangePrice)
                && Objects.equals(this.percentChangeVolumeOverLastWeek, stock.percentChangeVolumeOverLastWeek)
                && Objects.equals(this.previousWeeksVolume, stock.previousWeeksVolume)
                && Objects.equals(this.nextWeeksOpenPrice, stock.nextWeeksOpenPrice)
                && Objects.equals(this.nextWeeksClosePrice, stock.nextWeeksClosePrice)
                && Objects.equals(this.percentChangeNextWeeksPrice, stock.percentChangeNextWeeksPrice)
                && Objects.equals(this.daysToNextDividend, stock.daysToNextDividend)
                && Objects.equals(this.percentReturnNextDividend, stock.percentReturnNextDividend);
    }
    @Override
    public String toString() {
        return "Stock{" +
                "id=" + this.id +
                ", quarter='" + this.quarter +
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
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.quarter, this.stockSymbol, this.date, this.openPrice,
                this.highPrice, this.lowPrice, this.closePrice, this.volume, this.percentChangePrice,
                this.percentChangeVolumeOverLastWeek, this.previousWeeksVolume, this.nextWeeksOpenPrice,
                this.nextWeeksClosePrice, this.percentChangeNextWeeksPrice, this.daysToNextDividend,
                this.percentReturnNextDividend
        );
    }

    public String hashValue(){
        String originalString = this.quarter + this.stockSymbol + this.date + this.openPrice +
                this.highPrice + this.lowPrice + this.closePrice + this.volume + this.percentChangePrice +
                this.percentChangeVolumeOverLastWeek + this.previousWeeksVolume + this.nextWeeksOpenPrice +
                this.nextWeeksClosePrice + this.percentChangeNextWeeksPrice + this.daysToNextDividend +
                this.percentReturnNextDividend;
        return DigestUtils.sha256Hex(originalString);
    }
}

