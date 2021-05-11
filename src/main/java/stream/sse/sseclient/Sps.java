package stream.sse.sseclient;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Sps {

  public static void main(String[] args) throws Exception {
    
    EventHandler eventHandler = new SpsEventHandler(new SpsStreamProcessor(new ConsoleStreamSink()));
    String url = String.format("https://tweet-service.herokuapp.com/sps");
    EventSource.Builder evBuilder = new EventSource.Builder(eventHandler, URI.create(url))
        .reconnectTime(Duration.ofMillis(3000));

    try (EventSource eventSource = evBuilder.build()) {
      eventSource.start();

      // Artificial hack for always running
      TimeUnit.MINUTES.sleep(10);
    }
  }

}
