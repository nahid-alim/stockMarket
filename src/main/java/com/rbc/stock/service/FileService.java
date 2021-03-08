package com.rbc.stock.service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.rbc.stock.api.constant.Constants;
import com.rbc.stock.api.dto.StockDTO;
import com.rbc.stock.exception.FileInWrongFormat;
import com.rbc.stock.exception.FileEmpty;
import com.rbc.stock.exception.FileTooBig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileService implements IFileService {

    @Override
    public List<StockDTO> getAllRecords(MultipartFile file) {

        if(fileSizeInMB(file) > Constants.MAX_FILE_SIZE){
            throw new FileTooBig();
        }
        if (file.isEmpty()) {
            throw new FileEmpty();
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                Map mapping = new HashMap();
                mapping.put("quarter", "quarter");
                mapping.put("stock", "stockSymbol");
                mapping.put("date", "date");
                mapping.put("open", "openPrice");
                mapping.put("high", "highPrice");
                mapping.put("low", "lowPrice");
                mapping.put("close", "closePrice");
                mapping.put("volume", "volume");
                mapping.put("percent_change_price", "percentChangePrice");
                mapping.put("percent_change_volume_over_last_wk", "percentChangeVolumeOverLastWeek");
                mapping.put("previous_weeks_volume", "previousWeeksVolume");
                mapping.put("next_weeks_open", "nextWeeksOpenPrice");
                mapping.put("next_weeks_close", "nextWeeksClosePrice");
                mapping.put("percent_change_next_weeks_price", "percentChangeNextWeeksPrice");
                mapping.put("days_to_next_dividend", "daysToNextDividend");
                mapping.put("percent_return_next_dividend", "percentReturnNextDividend");

                HeaderColumnNameTranslateMappingStrategy strategy = new HeaderColumnNameTranslateMappingStrategy();
                strategy.setType(StockDTO.class);
                strategy.setColumnMapping(mapping);
                CsvToBean csv = new CsvToBean();
                csv.setMappingStrategy(strategy);
                csv.setCsvReader(new CSVReader(reader));
                return csv.parse();

            } catch (Exception ex) {
                throw new FileInWrongFormat();
            }
        }
    }

    private double fileSizeInMB(MultipartFile file){
        return file.getSize()/Constants.MB_SIZE_IN_BYTE;
    }
}

