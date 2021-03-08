package com.rbc.stock.service;

import com.rbc.stock.api.dto.StockDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {
    List<StockDTO> getAllRecords(MultipartFile file);
}
