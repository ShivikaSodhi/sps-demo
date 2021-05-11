package stream.sse.sseclient;

import com.launchdarkly.eventsource.MessageEvent;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import com.launchdarkly.eventsource.EventHandler;

import java.io.StringReader;

public class SpsEventHandler extends DefaultEventHandler {

  public SpsEventHandler(StreamProcessor streamProcessor) {
    super(streamProcessor);
  }

  @Override
  public void onMessage(String event, MessageEvent messageEvent) throws Exception {
    try (JsonReader jsonReader = Json
    .createReader(new StringReader(messageEvent.getData()))) {
      JsonObject jsonObject = jsonReader.readObject();
      streamProcessor.insertEvent(jsonObject);
    }
  }

}
