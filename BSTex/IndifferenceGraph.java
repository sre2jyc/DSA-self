import java.util.ArrayList;

/**
 * IndifferenceGraph
 */
public class IndifferenceGraph   {
    public class Node{
        double firstVertex;
        double secondVertex;

        public Node(double v1, double v2){
            this.firstVertex = v1;
            this.secondVertex = v2;
        }
    }
    
    SeparateChainingHashST <Integer, Double> st ;
    ArrayList <Node> edges ;

    public IndifferenceGraph(){
        st = new SeparateChainingHashST<>(5);
        edges = new ArrayList<>();
    }
    
    public void createST (double[] in){

        for(int i = 0; i < in.length; i ++){
            Integer ck = (int)(Math.round(in[i]));
            Double cv = in[i];

            
            st.put(ck, cv);
        }

    }

    public void createGraph(){
        for(int i = 0; i < st.tables(); i++){
            ArrayList <Double> points = st.ithTableValues(i);
            for(int j = 0;j < points.size(); j ++){
                for(int k = j+1; k < points.size(); k ++){
                    if(Math.abs(points.get(j) - points.get(k)) <= 1){
                        edges.add(new Node(points.get(j), points.get(k)));
                    }
                }
            }
        }
    }


    public ArrayList returnGraph(){
        return edges;
    }


    public static void main(String[] args) {

        
    }
}