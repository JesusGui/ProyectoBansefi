package mx.babel.arq.comun.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.atmosphere.jboss.as.websockets.WebSocket;
import org.atmosphere.jboss.as.websockets.servlet.WebSocketServlet;
import org.atmosphere.jboss.websockets.Frame;
import org.atmosphere.jboss.websockets.frame.CloseFrame;
import org.atmosphere.jboss.websockets.frame.TextFrame;

import com.fasterxml.jackson.core.io.JsonStringEncoder;

@SuppressWarnings("serial")
@WebServlet(NotificacionesWebSocketServlet.WEBSOCKET_PATH)
public class NotificacionesWebSocketServlet extends WebSocketServlet {
	private static final Logger LOGGER = LogManager.getLogger(NotificacionesWebSocketServlet.class.getName());
	public static final String WEBSOCKET_PATH = "/notificaciones";
    private static final JsonStringEncoder encoder = JsonStringEncoder.getInstance();

    private static Map<String,WebSocket> sockets = new HashMap<String,WebSocket>();

    @Override
    protected void onSocketOpened(WebSocket socket) throws IOException {
    	LOGGER.debug("Websocket opened: "+ socket.getSocketID());
        this.sockets.put(socket.getSocketID(), socket);
        
    }

    private static void writeMessageToSockets(Map<String, WebSocket> sockets, Pair<Long,String> message) {
        for (Map.Entry<String,WebSocket> entry : sockets.entrySet()) {
            writeMessageToSocket(entry.getValue(), message);
        }
    }

    private static void writeMessageToSocket(WebSocket socket,
            Pair<Long, String> message) {
        try {
            socket.writeFrame(TextFrame.from(asJSON(message) ));                
        } catch (IOException e) {
        	LOGGER.error("IOException writing socket response: " + message, e);
        } catch (Exception e) {
        	LOGGER.error("Error writing socket response: " + message, e);
        }
    }

    private static String asJSON(Pair<Long, String> message) {
        
        return String.format("{\"timestamp\": \"%s\", \"content\": \"%s\"}",
                encode(message.getLeft().toString()),
                encode(message.getRight())
                );
    }

    private static String encode(String str) {
        return new String(encoder.quoteAsUTF8(str));
    }

    @Override
    protected void onSocketClosed(WebSocket socket)  {
    	//do nothing here
    }

    @Override
    protected void onReceivedFrame(WebSocket socket) {
        try {
            final Frame frame = socket.readFrame();
            if(frame!= null){
	            LOGGER.info("Got a frame "+frame+" from socket:"+socket.getSocketID());
	            if (frame instanceof TextFrame) {
	                final String text = ((TextFrame) frame).getText();
	                LOGGER.info("Got message: "+ text);
	                Pair<Long,String> infoPair = new ImmutablePair<Long, String>(System.currentTimeMillis(), text);
	                writeMessageToSockets(NotificacionesWebSocketServlet.this.sockets, infoPair);
	            } else if (frame instanceof CloseFrame) {
	            	this.sockets.remove(socket.getSocketID());
	
	            	LOGGER.info("Websocket closed: "+ socket.getSocketID());
	            }
            }
        } catch (IOException ex) {
        	LOGGER.error("Issue reading frame: " + ex.getCause());
        }
    }

}
