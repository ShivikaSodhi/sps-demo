package stream.sse.sseclient;

public interface StreamSink {

    // Close this sink and release any resources associated
    public void close();

    // Writes outData to this sink
    public void writeData(String outData);

}