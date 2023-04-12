package com.work.test.controller;

import com.work.test.data.model.Region;
import com.work.test.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @PostMapping("/create")
    public void saveRegion(@RequestBody Region region) {
        regionService.saveRegion(region);
    }

    @GetMapping("/regions")
    public ResponseEntity<List<Region>> getRegions() {
        return ResponseEntity.ok(regionService.getRegions());
    }

    @PutMapping("/region/{id}")
    public void updateRegions(@PathVariable Long id) {
        regionService.getRegionById(id);
    }

    @PutMapping("/update")
    public void updateRegions(@RequestBody Region region) {
         regionService.updateRegion(region);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRegions(@PathVariable Long id) {
        regionService.deleteRegion(id);
    }

}
