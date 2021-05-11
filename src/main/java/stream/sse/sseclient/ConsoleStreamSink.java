package stream.sse.sseclient;

public class ConsoleStreamSink implements StreamSink {

    @Override
    public void close(){
        // Do nothing.
    }

    @Override
    public void writeData(String outData){
        System.out.println(outData);
    }

}