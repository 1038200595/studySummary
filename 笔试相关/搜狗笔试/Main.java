import java.util.*;

/**
 * 	1. ʹ��hashmap�������νṹ
 * 	2. �ݹ���η���������֪ �����ӽڵ�ķַ�ʱ�䣺
 *     	�� 2.1 ���ӽڵ�ķַ�ʱ����н�������
 *     	    2.2 ��ǰ�ڵ����ȶ� �ӽڵ�ַ�ʱ�� �ϳ��Ľڵ���зַ�, 
 * 				��:�ӽڵ�ַ�ʱ�� 4 2 1 1 1 0, k=2, ��: �Ⱥ�ַ�˳��(42)(11)(10),Ҳ����
 *                     (42)+1 (11)+2 (10)+3 => 5 3 3 3 4 3 => �����ַַ�, ��������ʱ��Ϊ 5 h��
 *             2.3 ���ڵ�ΪҶ�ӽ��, �ַ�ʱ��Ϊ0
 *             2.4 ���ʹ�÷��η����еݹ�ַ�,�൱�ڴ�Ҷ�ӽڵ������ơ�
 */
class Node{

    int id = 0;
    List<Node> sons = new ArrayList<>();
    Node parent = null;
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashMap<Integer , Node> hashMap = new HashMap<>();

        int K = sc.nextInt();
        int N = sc.nextInt();

        if(K == 0 || N == 0){
            System.out.println(0);
        }

        sc.nextLine();

        //���ι���
        for(int i = 0;i < N;i++){
            int node_n = sc.nextInt();
            //ָ�����ڵ�
            int parent_id = sc.nextInt();
            Node my = null;
            if(hashMap.containsKey(parent_id)){
                //���ڵ����
                my = hashMap.get(parent_id);
            }else{
                //���ڵ㲻����
                my = new Node();
                my.id = parent_id;
                hashMap.put(parent_id,my);
            }
            //���ӽڵ�
            for(int j = 0;j < node_n - 1;j++){
                int son_id = sc.nextInt();
                if(hashMap.containsKey(son_id)){
                    //���ӽڵ����
                    hashMap.get(parent_id).sons.add(hashMap.get(son_id));
                }else{
                    //���ӽڵ㲻����
                    Node son = new Node();
                    son.id = son_id;
                    son.parent = my;
                    hashMap.put(son.id,son);
                    hashMap.get(parent_id).sons.add(son);
                }
            }
            sc.nextLine();
        }

        //���ڵ����
        Node root = hashMap.get(0);
        //���η�
        System.out.println(FZ(root , K));

    }

    public static int FZ(Node node , int k){
        int max = 0;
        if(node.sons.size() == 0){
            return 0;
        }else{
            Integer[] sons_time = new Integer[node.sons.size()];
            int i = 0;
            //ͳ�Ʒ�֧ʱ��
            for(Node son:node.sons){
                sons_time[i++] = FZ(son , k);
            }
            //�ϲ���֧�뵱ǰʱ��
            //��������
            Arrays.sort(sons_time, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            //���㵱ǰ�ڵ�Ϊroot����Ҫ��ʱ��
            int add = 1;
            for(int j = 0; j < sons_time.length ; j++){
                if(j!=0 && (j%k) == 0){
                    add++;
                }
                sons_time[j] += add;
                if(sons_time[j] > max){
                    max = sons_time[j];
                }
            }
        }
        return max;
    }
}