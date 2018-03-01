package eu.sergehelfrich.ersaapi;

import eu.sergehelfrich.ersaapi.dataaccess.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.sergehelfrich.ersaapi.entities.Reading;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import request.ReadingRequest;

@Controller
@RequestMapping(path = "/ersa")
public class MainController {

    @Autowired
    private ReadingRepository readingRepository;

    @PostMapping(path = "/reading")
    public @ResponseBody
    long addReading(@RequestBody ReadingRequest request) {
        Reading reading = new Reading();
        reading.setTimestamp(System.currentTimeMillis() / 1000L);
        reading.setOrigin(request.origin);
        reading.setTemperature(request.temperature);
        reading.setHumidity(request.humidity);
        readingRepository.save(reading);
        readingRepository.flush();
        return reading.getId();
    }

    @GetMapping(path = "/latest")
    public @ResponseBody
    List<Reading> getLatest() {
        return readingRepository.findLatest();
    }

    @GetMapping(path = "/latest100")
    public @ResponseBody
    List<Reading> getLatest(@RequestParam String origin) {
        return readingRepository.findTop100ByOriginOrderByTimestampDesc(origin);
    }

    @GetMapping(path = "/range")
    public @ResponseBody
    List<Reading> getRange(@RequestParam String origin, @RequestParam long minTime, @RequestParam long maxTime) {
        return readingRepository.findRangeByOrigin(origin, minTime, maxTime);
    }
}
