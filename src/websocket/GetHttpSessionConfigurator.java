package websocket;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.List;
import java.util.Map;

/**
 * Created by xj
 * on 2016/6/22.
 */
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {

    private static final String HOST = "host";
    private static final String REMOTE_ADDRESS_AND_PORT = "remote_address_and_port";

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        super.modifyHandshake(sec, request, response);

        Map<String, List<String>> header = request.getHeaders();
        for (Map.Entry<String, List<String>> entry : header.entrySet()) {
            if (HOST.equals(entry.getKey())) {
                List<String> headersValues = entry.getValue();
                if (headersValues != null && headersValues.size() > 0) {
                    sec.getUserProperties().put(REMOTE_ADDRESS_AND_PORT, headersValues.get(0));
                    System.out.println(headersValues.get(0));
                }
                break;
            }
        }
    }

    @Override
    public boolean checkOrigin(String originHeaderValue) {
        if (originHeaderValue == null || originHeaderValue.trim().length() == 0) {
            return true;
        } else {
            return super.checkOrigin(originHeaderValue);
        }
    }
}
