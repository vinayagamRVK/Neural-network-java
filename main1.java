import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class NNnetwork {
    private int layers;
    private int[] nodes;
    private Map<Edge, Double> weights;

    public NNnetwork(int layers, int[] nodes) {
        this.layers = layers;
        this.nodes = nodes;
        this.weights = new HashMap<>();

        weights();
    }

    @SuppressWarnings("resource")
    private void weights() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < layers - 1; i++) {
            for (int node1 = 0; node1 < nodes[i]; node1++) {
                for (int node2 = 0; node2 < nodes[i + 1]; node2++) {
                    System.out.print("Enter weight for edge (Layer " + i + ", Node " + node1 + ") to (Layer " + (i + 1) + ", Node " + node2 + "): ");
                    double weight = scanner.nextDouble();
                    weights.put(new Edge(i, node1, i + 1, node2), weight);
                }
            }
        }
    }

    public double weight(int layerfrom, int nodefrom, int layerto, int nodeto) {
        Edge edge = new Edge(layerfrom, nodefrom, layerto, nodeto);
        return weights.getOrDefault(edge, 0.0);
    }
}

class Edge {
    private int layerfrom, nodefrom, layerto, nodeto;

    public Edge(int layerfrom, int nodefrom, int layerto, int nodeto) {
        this.layerfrom = layerfrom;
        this.nodefrom = nodefrom;
        this.layerto = layerto;
        this.nodeto = nodeto;
    }

    @Override
    public int hashCode() {
        return (layerfrom * 31 + nodefrom) * 31 + (layerto * 31 + nodeto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return layerfrom == edge.layerfrom && nodefrom == edge.nodefrom && layerto == edge.layerto && nodeto == edge.nodeto;
    }
}

public class main1 {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter no. of layers in the Neural Network: ");
        int layers = scanner.nextInt();

        int[] nodes = new int[layers];
        for (int i = 0; i < layers; i++) {
            System.out.print("Enter no. of nodes in layer " + i + ": ");
            nodes[i] = scanner.nextInt();
        }

        NNnetwork neuralNetwork = new NNnetwork(layers, nodes);

        System.out.print("from layer: ");
        int layerfrom = scanner.nextInt();
        System.out.print("from node: ");
        int nodefrom = scanner.nextInt();
        System.out.print("to layer: ");
        int layerto = scanner.nextInt();
        System.out.print("to node: ");
        int nodeto = scanner.nextInt();

        double weight = neuralNetwork.weight(layerfrom, nodefrom, layerto, nodeto);
        System.out.println("The Weight is(" + layerfrom + ", " + nodefrom + ") to (" + layerto + ", " + nodeto + "): " + weight);
    }
}