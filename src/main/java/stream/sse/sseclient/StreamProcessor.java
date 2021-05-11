package stream.sse.sseclient;

import jakarta.json.JsonObject;

public interface StreamProcessor {

    // Call-back method for receiving events of type insert
    void insertEvent(JsonObject jsonObject) throws Exception ;

}