package pl.agh.soa.web;

import pl.agh.soa.jms.dto.ParkGuardNotificationData;

import javax.ejb.Singleton;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

@Singleton
public class PushNotifier {
    private final Queue<AsyncContext> peers = new ConcurrentLinkedQueue<>();

    public void addPeer(AsyncContext asyncContext) {
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent event) throws IOException {
                peers.remove(asyncContext);
            }

            @Override
            public void onTimeout(AsyncEvent event) throws IOException {
                peers.remove(asyncContext);
            }

            @Override
            public void onError(AsyncEvent event) throws IOException {
                peers.remove(asyncContext);
            }

            @Override
            public void onStartAsync(AsyncEvent event) throws IOException {
            }
        });
        peers.add(asyncContext);
    }

    public void notifyParkNotPayed(ParkGuardNotificationData data) {
        for (AsyncContext ac : peers) {
            try {
                final ServletOutputStream os = ac.getResponse().getOutputStream();
                os.print(new Date().toString());
                ac.complete();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                peers.remove(ac);
            }
        }
    }
}
