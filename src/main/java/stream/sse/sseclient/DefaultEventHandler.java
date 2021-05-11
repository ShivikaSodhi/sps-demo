package stream.sse.sseclient;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.EventHandler;

public class DefaultEventHandler implements EventHandler {

  protected StreamProcessor streamProcessor;

  public DefaultEventHandler(StreamProcessor streamProcessor){
    this.streamProcessor = streamProcessor;
  }

  @Override
  public void onOpen() throws Exception {
    System.out.println("onOpen");
  }

  @Override
  public void onClosed() throws Exception {
    System.out.println("onClosed");
  }

  @Override
  public void onComment(String comment) throws Exception {
  }

  @Override
  public void onError(Throwable t) {
    System.out.println("onError: " + t);
  }

  @Override
  public void onMessage(String event, MessageEvent messageEvent) throws Exception {
  }

}
