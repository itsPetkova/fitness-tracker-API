package org.example.service.serviceImpl;

import org.example.dto.DataEntryDto;
import org.example.dto.DataEntryResponse;
import org.example.entity.Application;
import org.example.entity.DataEntry;
import org.example.repository.EntryRepository;
import org.example.service.EntryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {
    private final EntryRepository entryRepository;
    private final ModelMapper modelMapper;

    public EntryServiceImpl(EntryRepository entryRepository, ModelMapper modelMapper) {
        this.entryRepository = entryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveEntry(DataEntryDto dataEntry) {
        Application app = (Application) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        DataEntry entry = modelMapper.map(dataEntry, DataEntry.class);
        entry.setApplication(app);
        entryRepository.save(entry);
    }

    @Override
    public List<DataEntryResponse> getAllEntries() {
        return entryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(entry -> modelMapper.map(entry, DataEntryResponse.class))
                .toList();
    }
}