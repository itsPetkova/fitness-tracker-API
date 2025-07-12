package org.example.service;

import org.example.dto.DataEntryDto;
import org.example.dto.DataEntryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EntryService {
    void saveEntry(DataEntryDto dataEntryDto);
    List<DataEntryResponse> getAllEntries();
}

