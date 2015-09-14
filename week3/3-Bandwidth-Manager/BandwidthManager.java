import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by Deyan on 15.7.2015 ..
 */

class Packet {
    public String protocol;
    public String payload;
    public int order;

    public Packet(String protocol, String payload, int order) {
        this.protocol = protocol;
        this.payload = payload;
        this.order = order;
    }
}

class PacketComparator implements Comparator<Packet> {
    private HashMap<String, Integer> protocolPriorities;

    public PacketComparator() {
        protocolPriorities = new HashMap<>();
        protocolPriorities.put("ICMP", 6);
        protocolPriorities.put("UDP", 5);
        protocolPriorities.put("RTM", 4);
        protocolPriorities.put("IGMP", 3);
        protocolPriorities.put("DNS", 2);
        protocolPriorities.put("TCP", 1);
    }


    @Override
    public int compare(Packet o1, Packet o2) {
        int protocolCompare = protocolPriorities.get(o2.protocol) - protocolPriorities.get(o1.protocol);
        if (protocolCompare == 0) {
            return o1.order - o2.order;
        }

        return protocolCompare;
    }
}

public class BandwidthManager {
    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner();
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(System.out));
        PriorityQueue<Packet> packets = new PriorityQueue<>(10, new PacketComparator());
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String command = scanner.nextString();
            if (command.equals("rcv")) {
                packets.add(new Packet(scanner.nextString(), scanner.nextString(), i + 1));
            }
            else {
                if (packets.isEmpty()) {
                    writer.println("Nothing to send!");
                }
                else {
                    writer.println(packets.remove().payload);
                }
            }
        }
        writer.close();
    }
}

class MyScanner {
    StringTokenizer tokenizer = null;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    String nextToken() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }

        return tokenizer.nextToken();
    }

    String nextString() throws IOException {
        return nextToken();
    }
}