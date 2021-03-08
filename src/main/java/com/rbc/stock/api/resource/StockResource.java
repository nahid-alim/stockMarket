package com.rbc.stock.api.resource;

import com.rbc.stock.api.dto.StockDTO;
import com.rbc.stock.exception.FileEmpty;
import com.rbc.stock.exception.FileInWrongFormat;
import com.rbc.stock.exception.FileTooBig;
import com.rbc.stock.exception.StockNotFound;
import com.rbc.stock.service.FileService;
import com.rbc.stock.service.StockService;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("v1/stocks")
public class StockResource {

    @Autowired
    FileService fileUploadService;

    @Autowired
    StockService stockService;

    Logger logger = LogManager.getLogger(StockService.class);

    @GetMapping("/status")
    ResponseEntity<Object> status() {

        return ResponseEntity.ok("Stock application is up and running.");
    }

    @ApiOperation(value = "Add a New Stock Record", response = ResponseEntity.class)
    @PostMapping("/")
    ResponseEntity<String> createStock(@RequestBody StockDTO stockDTO) {

        logger.info("createStock method is called. Stock: " + stockDTO);
        return ResponseEntity.ok("New stock record is created with id: " + stockService.createStock(stockDTO));
    }

    @ApiOperation(value = "Upload a CSV file of stock records", response = ResponseEntity.class)
    @PostMapping("/upload/csv")
    public ResponseEntity<Object> uploadCSV(@RequestParam("file") MultipartFile file) {

        logger.info("uploadCSV method is called.");
        try {
            List<StockDTO> records = fileUploadService.getAllRecords(file);
            stockService.createStocksList(records);
            logger.info("The file is uploaded successfully. Number of uploaded records: " + records.size());
            return ResponseEntity.ok("The file is uploaded successfully. Number of uploaded records: " + records.size());
        } catch (FileTooBig ftb) {
            return ResponseEntity
                    .status(HttpStatus.PAYLOAD_TOO_LARGE)
                    .body(Problem.create()
                            .withDetail(ftb.getMessage()));
        } catch (FileEmpty fe) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Problem.create()
                            .withDetail(fe.getMessage()));
        } catch (FileInWrongFormat rf) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(Problem.create()
                            .withDetail(rf.getMessage()));
        }
    }

    @ApiOperation(value = "Fetch Records By Stock Symbol", response = CollectionModel.class)
    @GetMapping("/searches/symbol/{stockSymbol}")
    public ResponseEntity<?> getStocksBySymbol(@PathVariable String stockSymbol) {

        logger.info("getStocksBySymbol method is called. stockSymbol: " + stockSymbol);
        try {
            List<StockDTO> records = stockService.getStocksBySymbol(stockSymbol);
            logger.info("Number of records found with stock symbol '" + stockSymbol + "' is: " + records.size());
            return ResponseEntity.ok(records);
        } catch (StockNotFound snf) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Problem.create()
                            .withDetail(snf.getMessage()));
        }
    }
}
