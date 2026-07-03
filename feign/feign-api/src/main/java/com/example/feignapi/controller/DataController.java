package com.example.feignapi.controller;

import com.example.feignapi.dto.DataRequest;
import com.example.feignapi.dto.DataResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class DataController {
    private Map<Long, DataResponse> dataStore = new HashMap<>();
    private Long idCounter = 1L;

    @PostConstruct
    public void initDataStore() {
        dataStore.put(idCounter++, new DataResponse(1L, "Item 1", 100));
        dataStore.put(idCounter++, new DataResponse(2L, "Item 2", 200));
        dataStore.put(idCounter++, new DataResponse(3L, "Item 3", 300));
        dataStore.put(idCounter++, new DataResponse(4L, "Item 4", 400));
        dataStore.put(idCounter++, new DataResponse(5L, "Item 5", 500));
    }

    @GetMapping("/{id}")
    public DataResponse getDataById(@PathVariable Long id) { //{id} id 변수이름 같아야함

        DataResponse dataResponse = dataStore.get(id);

        if(dataResponse == null) {
            throw new RuntimeException("Data not found" + id);
        }
        return dataResponse;
    }

    @PostMapping
    public DataResponse createData(@RequestBody DataRequest dataRequest) {
        DataResponse build = DataResponse.builder()
                .id(idCounter++).name(dataRequest.getName()).value(dataRequest.getValue()).build();

        dataStore.put(build.getId(),build);

        return build;
    }

    @PutMapping("/{id}")
    public DataResponse updateData(@PathVariable("id") Long id, @RequestBody DataRequest dataRequest) {
        DataResponse dataResponse = dataStore.get(id);

        if(dataResponse == null) {
            throw new RuntimeException("Data not found(update)" + id);
        }

        //set보단 new 객체 새로 던지는 걸 많이 씀 실무
        dataResponse.setName(dataRequest.getName());
        dataResponse.setValue(dataRequest.getValue());
        dataStore.put(id, dataResponse);
        return dataResponse;
    }

    @DeleteMapping("/{id}")
    public String deleteData(@PathVariable("id") Long id) {
        DataResponse removed = dataStore.remove(id);
        if(removed == null) {
            throw new RuntimeException("Data not found(delete)" + id);
        }
        return "Data deleted with id " + id;
    }
}
