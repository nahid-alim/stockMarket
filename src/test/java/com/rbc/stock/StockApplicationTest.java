package com.rbc.stock;

import com.google.gson.Gson;
import com.rbc.stock.api.dto.StockDTO;
import com.rbc.stock.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.File;

import static io.restassured.RestAssured.given;

@SpringBootTest
class StockApplicationTest {

    @Autowired
    StockRepository stockRepository;

    @AfterEach
    public void tearDown() {
        stockRepository.deleteAll();
    }

    @Test
    public void whenGetStatus_thenOK() {

        given()
                .when()
                .get("http://localhost:8080/v1/stocks/status")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void whenCreateStock_thenStockCreated() {

        StockDTO stockDTO = new Gson().fromJson(Fixtures.CREATE_STOCK_JSON_STRING, StockDTO.class);

        given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(stockDTO)
                .post("http://localhost:8080/v1/stocks/")
                .then()
                .statusCode(HttpStatus.OK.value());

        StockDTO[] results = given()
                .when()
                .get("http://localhost:8080/v1/stocks/searches/symbol/" + stockDTO.getStockSymbol())
                .as(StockDTO[].class);
        Assertions.assertEquals(results.length, 1);
        Assertions.assertEquals(results[0].getStockSymbol(), stockDTO.getStockSymbol());

    }

    @Test
    public void whenUploadValidCSV_thenAllRecordsGetCreated() {

        given()
                .when()
                .multiPart("file", new File("src/test/resources/dow_jones_index_50_records.data"))
                .post("http://localhost:8080/v1/stocks/upload/csv")
                .then()
                .statusCode(HttpStatus.OK.value());

        StockDTO[] results = given()
                .when()
                .get("http://localhost:8080/v1/stocks/searches/symbol/AA")
                .as(StockDTO[].class);
        Assertions.assertEquals(results.length, 12);
    }

    @Test
    public void whenUploadValidCSVAndCreateRepeatedStock_thenNoReplicatedStockSavedInDB() {

		StockDTO stockDTO = new Gson().fromJson(Fixtures.CREATE_STOCK_JSON_STRING, StockDTO.class);

		given()
				.when()
				.multiPart("file", new File("src/test/resources/dow_jones_index_50_records.data"))
				.post("http://localhost:8080/v1/stocks/upload/csv")
				.then()
				.statusCode(HttpStatus.OK.value());

		given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.when()
				.body(stockDTO)
				.post("http://localhost:8080/v1/stocks/")
				.then()
				.statusCode(HttpStatus.OK.value());

		StockDTO[] results = given()
				.when()
				.get("http://localhost:8080/v1/stocks/searches/symbol/AA")
				.as(StockDTO[].class);

		Assertions.assertEquals(results.length, 12);
    }
}
