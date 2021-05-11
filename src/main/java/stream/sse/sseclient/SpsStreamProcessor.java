package stream.sse.sseclient;

import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SpsStreamProcessor extends DefaultStreamProcessor {

  private long last_sec;
  private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
  ExecutorService executorService = Executors.newScheduledThreadPool(5); 

  public SpsStreamProcessor(StreamSink cSink) {
    super(cSink);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void insertEvent(JsonObject jsonObject) throws Exception  {
      JsonValue time = jsonObject.getValue("/time");
      long currTime = Long.parseLong(time.toString())/1000;
      
      if(currTime != last_sec) {
        last_sec = currTime;
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        printDataAndFlushMap();
      }

      executorService.execute(new Runnable() {
        public void run() {
          processDataTask(jsonObject);
        }
      });
  }

  public void processDataTask(JsonObject jsonObject) {
    JsonValue sev = jsonObject.getValue("/sev");
    JsonValue title = jsonObject.getValue("/title");
    JsonValue device = jsonObject.getValue("/device");
    JsonValue country = jsonObject.getValue("/country");

    if (!sev.toString().toLowerCase().contains("success")) {
        return;
    }

    if (title.toString().toLowerCase().contains("busted") || 
        device.toString().toLowerCase().contains("busted") ||
        country.toString().toLowerCase().contains("busted")) {
      return;
    }

    if(sev.toString().toLowerCase().contains("success")){
      String keyOfMap = title.toString() + "@" + device.toString() + "@" + country.toString();
      increaseCounter(keyOfMap);
    }
  }

  private void increaseCounter(String key) {
    map.compute(key, (k, v) -> v == null ? 1 : v + 1);
  }

  public void printDataAndFlushMap(){
    for (Map.Entry<String, Integer> e : map.entrySet()) {
      String[] keys = e.getKey().split("@");
      String outputData = "{“device”: " + keys[0] + ", “sps”: " + e.getValue() + ", “title” : " + keys[1] + ", “country” : "  + keys[2] + "}";
      cSink.writeData(outputData);
    }
    map = new ConcurrentHashMap<>();
    executorService = Executors.newScheduledThreadPool(5);
  }

}