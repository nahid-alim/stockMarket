package com.rbc.stock.service;

import com.rbc.stock.api.dto.StockDTO;
import com.rbc.stock.model.Stock;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ConvertService implements IConvertService {

    @Override
    public Stock stockDTOtoStock(StockDTO stockDTO) {

        LocalDate date = stringToLocalDate(stockDTO.getDate());
        BigDecimal openPrice = priceStringToBigDecimal(stockDTO.getOpenPrice());
        BigDecimal highPrice = priceStringToBigDecimal(stockDTO.getHighPrice());
        BigDecimal lowPrice = priceStringToBigDecimal(stockDTO.getLowPrice());
        BigDecimal closePrice = priceStringToBigDecimal(stockDTO.getClosePrice());
        BigDecimal nextWeeksOpenPrice = priceStringToBigDecimal(stockDTO.getNextWeeksOpenPrice());
        BigDecimal nextWeeksClosePrice = priceStringToBigDecimal(stockDTO.getNextWeeksClosePrice());

        Stock stock = new Stock();
        stock.setQuarter(stockDTO.getQuarter());
        stock.setStockSymbol(stockDTO.getStockSymbol());
        stock.setDate(date);
        stock.setOpenPrice(openPrice);
        stock.setHighPrice(highPrice);
        stock.setLowPrice(lowPrice);
        stock.setClosePrice(closePrice);
        stock.setVolume(stockDTO.getVolume());
        stock.setPercentChangePrice(stockDTO.getPercentChangePrice());
        stock.setPercentChangeVolumeOverLastWeek(stockDTO.getPercentChangeVolumeOverLastWeek());
        stock.setPreviousWeeksVolume(stockDTO.getPreviousWeeksVolume());
        stock.setNextWeeksOpenPrice(nextWeeksOpenPrice);
        stock.setNextWeeksClosePrice(nextWeeksClosePrice);
        stock.setPercentChangeNextWeeksPrice(stockDTO.getPercentChangeNextWeeksPrice());
        stock.setDaysToNextDividend(stockDTO.getDaysToNextDividend());
        stock.setPercentReturnNextDividend(stockDTO.getPercentReturnNextDividend());
        stock.setId(stock.hashValue());
        return stock;
    }

    @Override
    public StockDTO stockToStockDTO(Stock stock) {

        return new StockDTO(
                stock.getId(),
                stock.getQuarter(),
                stock.getStockSymbol(),
                localDateToString(stock.getDate()),
                priceBigDecimalToString(stock.getOpenPrice()),
                priceBigDecimalToString(stock.getHighPrice()),
                priceBigDecimalToString(stock.getLowPrice()),
                priceBigDecimalToString(stock.getClosePrice()),
                stock.getVolume(),
                stock.getPercentChangePrice(),
                stock.getPercentChangeVolumeOverLastWeek(),
                stock.getPreviousWeeksVolume(),
                priceBigDecimalToString(stock.getNextWeeksOpenPrice()),
                priceBigDecimalToString(stock.getNextWeeksClosePrice()),
                stock.getPercentChangeNextWeeksPrice(),
                stock.getDaysToNextDividend(),
                stock.getPercentReturnNextDividend());
    }

    private LocalDate stringToLocalDate(String str) {

        if(str != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("" + "[M/dd/yyyy]" + "[M/d/yyyy]");
            return LocalDate.parse(str, formatter);
        } else{
            return null;
        }
    }

    private String localDateToString(LocalDate localDate) {

        if(localDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[M/dd/yyyy]");
            return localDate.format(formatter);
        } else {
            return null;
        }
    }

    private BigDecimal priceStringToBigDecimal(String str) {

        if (str != null) {
            String bigDecimal = str.replaceAll("[$]", "");
            return BigDecimal.valueOf(Double.valueOf(bigDecimal));
        } else {
            return null;
        }
    }

    private String priceBigDecimalToString(BigDecimal bigDecimal) {

        if(bigDecimal != null) {
            return "$" + bigDecimal.toString();
        } else{
            return null;
        }
    }
}
