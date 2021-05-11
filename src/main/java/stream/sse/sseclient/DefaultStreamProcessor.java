package stream.sse.sseclient;
import jakarta.json.JsonObject;

public class DefaultStreamProcessor implements StreamProcessor {

  protected StreamSink cSink;

  public DefaultStreamProcessor(StreamSink cSink) {
    this.cSink = cSink;
  }  

  @Override
  public void insertEvent(JsonObject jsonObject) throws Exception  {
  }

}