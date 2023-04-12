package com.work.test.service;

import com.work.test.data.model.Region;
import com.work.test.data.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    @Transactional
    public void saveRegion(Region region) {
        Integer counts = regionRepository.findAll().size();
        region.setId(counts.longValue() + 1);
        regionRepository.create(region);
    }

    @Transactional
    public List<Region> getRegions() {
        return regionRepository.findAll();
    }

    @Transactional
    public void updateRegion(Region region) {
        regionRepository.updateRegion(region);
    }

    @Transactional
    public Region getRegionById(Long id) {
        return regionRepository.findById(id);
    }

    @Transactional
    public void deleteRegion(Long id) {
        regionRepository.delete(id);
    }
}
